package org.btwr.creeper_shearing.config;

import org.btwr.creeper_shearing.CreeperShearingMod;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

import java.io.IOException;

public class ModConfig {
	private static TomlConfigHandler CONFIG;

	public static final String IDS = "ID's Settings";

	private static int BLOCK_ID_STARTING_FROM = 13500;
	private static int ITEM_ID_STARTING_FROM = 29500;

	private static int currentBlockID;
	private static int currentItemID;

	public static void init() {
		Toml properties = new Toml("Creeper Shearing Configurations.toml");

		properties.addCategory(IDS)
			.addEntry("cfgVersion", 1)
			.addEntry("BLOCK_ID_STARTING_FROM", BLOCK_ID_STARTING_FROM)
			.addEntry("ITEM_ID_STARTING_FROM", ITEM_ID_STARTING_FROM);

		CONFIG = new TomlConfigHandler(CreeperShearingMod.MOD_ID, properties);

		if (CONFIG.getConfigFile().exists()) {
			CONFIG.loadConfig();
		} else {
			try {
				CONFIG.getConfigFile().createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			CONFIG.writeConfig();
		}

		currentBlockID = BLOCK_ID_STARTING_FROM = CONFIG.getInt(IDS + ".BLOCK_ID_STARTING_FROM");
		currentItemID = ITEM_ID_STARTING_FROM = CONFIG.getInt(IDS + ".ITEM_ID_STARTING_FROM");
	}

	public static int blockID() {
		return currentBlockID++;
	}

	public static int itemID() {
		return currentItemID++;
	}
}