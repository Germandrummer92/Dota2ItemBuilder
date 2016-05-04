/**
 * 
 */
package model;

/**
 * @author Daniel Draper
 * @version 1.0
 * This class is a help class to make it easier handling weird hero names.
 */
public class Hero {

	private static final String[] HeroStrings = { "Abaddon", "Alchemist", "Ancient Apparition", "Anti-Mage", "Axe", "Bane", "Batrider", "Beastmaster", "Bloodseeker", "Bounty Hunter", "Brewmaster", "Bristleback", "Broodmother", "Centaur Warrunner", "Chaos Knight", "Chen", "Clinkz", "Clockwerk", "Crystal Maiden", "Dark Seer", "Dazzle", "Death Prophet", "Disruptor", "Doom Bringer", "Dragon Knight", "Drow Ranger", "Earth Spirit", "Earthshaker", "Elder Titan", "Ember Spirit", "Enchantress", "Enigma", "Faceless Void", "Gyrocopter", "Huskar", "Invoker", "Io", "Jakiro", "Juggernaut", "Keeper of the Light", "Kunkka", "Legion Commander", "Leshrac", "Lich", "Lifestealer", "Lina", "Lion", "Lone Druid", "Luna", "Lycanthrope", "Magnus", "Medusa", "Meepo", "Mirana", "Morphling", "Naga Siren", "Nature's Prophet", "Necrophos", "Night Stalker", "Nyx Assassin", "Ogre Magi", "Omniknight", "Outworld Devourer", "Phantom Assassin", "Phantom Lancer", "Phoenix", "Puck", "Pudge", "Pugna", "Queen of Pain", "Razor", "Riki", "Rubick", "Sand King", "Shadow Demon", "Shadow Fiend", "Shadow Shaman", "Silencer", "Skywrath Mage", "Slardar", "Slark", "Sniper", "Spectre", "Spirit Breaker", "Storm Spirit", "Sven", "Templar Assassin", "Tidehunter", "Timbersaw", "Tinker", "Tiny", "Treant Protector", "Troll Warlord", "Tusk", "Undying", "Ursa", "Vengeful Spirit", "Venomancer", "Viper", "Visage", "Warlock", "Weaver", "Windranger", "Witch Doctor", "Wraith King", "Zeus"};

	private String heroNameBuild;
	private String heroNameFormatted;
	private String unformattedHero;
	public Hero(String hero) {
		unformattedHero = hero;
		if (hero.equals("Zeus") || hero.equals("zeus")) {
			heroNameBuild = "zuus";
			heroNameFormatted = "\"npc_dota_hero_zuus\"";
			}
		else {
			heroNameBuild = hero.replace(' ', '_');
			heroNameBuild = heroNameBuild.toLowerCase();
			heroNameFormatted = "\"npc_dota_hero_" + hero.replace(' ', '_').toLowerCase() + "\"";
		}
	}

	/**
	 * @return the hero Name for the build title
	 */
	public String getHeroNameBuild() {
		return heroNameBuild;
	}

	/**
	 * @return the formatted hero Name
	 */
	public String getHeroNameFormatted() {
		return heroNameFormatted;
	}
	
	@Override
	public String toString() {
		return unformattedHero;
	}

	/**
	 * @return all possible hero names
	 */
	public static String[] getHeroStrings() {
		
		return HeroStrings;
	}
}
