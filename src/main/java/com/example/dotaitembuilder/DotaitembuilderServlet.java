package com.example.dotaitembuilder;

import com.vaadin.server.VaadinServlet;
import gui.ImagePanel;
import lombok.Getter;
import model.Item;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(
        asyncSupported = false,
        urlPatterns = {"/*", "/VAADIN/*"},
        initParams = {
                @WebInitParam(name = "ui", value = "com.example.dotaitembuilder.DotaitembuilderUI")
        })
public class DotaitembuilderServlet extends VaadinServlet {

    @Getter
    private List<Item> items = new ArrayList<>();

    public DotaitembuilderServlet() {
        getItemList();
    }

    /**
     */
    private void getItemList() {
        final File[] itemFolder;
        itemFolder = new File(ImagePanel.class.getResource("/images").getFile()).listFiles();
        if (itemFolder != null) {
            Arrays.asList(itemFolder).forEach((File f) -> {
                String item = f.getName();
                item = item.replace(".png", "");
                item = item.replace("_lg", "");
                item = item.replace("_", " ");
                items.add(new Item(item, f));
            });
        }
    }

}
