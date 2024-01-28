package mrcabbagestick.whatitlacks;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhatItLacks implements ModInitializer {
	public static final String MOD_ID = "what-it-lacks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerBlocks();
		ModItems.registerItems();
		ModItemGroups.registerItemGroups();
		ModBlockEntities.registerBlockEntities();
		LOGGER.info("What It Lacks successfully initialized.");
	}

	public static Identifier createIdentifier(String path){
		return new Identifier(MOD_ID, path);
	}
}