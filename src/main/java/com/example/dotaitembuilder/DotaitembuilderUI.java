package com.example.dotaitembuilder;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import model.Item;

import java.util.stream.Collectors;

@Theme("Dotaitembuilder")
public class DotaitembuilderUI extends UI {


    private final Grid grid = new Grid();
    private final TextField searchField = new TextField();
    private final NormalizedLevenshtein levenshtein = new NormalizedLevenshtein();
    private final Button clearTextButton = new Button(FontAwesome.TIMES);

    private DotaitembuilderServlet servlet = new DotaitembuilderServlet();


    @Override
    protected void init(final VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        final CssLayout search = new CssLayout();
        search.addComponents(searchField, clearTextButton);
        search.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        updateGrid();
        initSearch();
        initClearButton();
        layout.addComponents(search, grid);
        layout.setMargin(true);
        setContent(layout);
    }

    private void initClearButton() {
        clearTextButton.setDescription("Clear the current Search!");
        clearTextButton.addClickListener(e -> {
                    searchField.clear();
                    updateGrid();
                }
        );
    }

    private void initSearch() {
        searchField.setInputPrompt("Search for an item...");
        searchField.addTextChangeListener(e -> grid.setContainerDataSource(new BeanItemContainer<>(Item.class,
                (servlet.getItems().stream().filter(i -> i.getName().contains(e.getText()) || levenshtein.distance(i.getName(), e.getText()) <= 0.4)
                        .collect(Collectors.toList())))));
    }

    private void updateGrid() {
        grid.setContainerDataSource(new BeanItemContainer<>(Item.class, (servlet.getItems().stream().filter
                (i -> i.getName().contains(searchField.getValue()) || levenshtein.distance(i.getName(), searchField.getValue()) <= 0.4)
                .collect(Collectors.toList()))));
        grid.setColumns("name");
    }
}
