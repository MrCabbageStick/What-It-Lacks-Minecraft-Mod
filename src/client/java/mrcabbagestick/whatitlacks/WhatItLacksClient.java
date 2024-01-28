package mrcabbagestick.whatitlacks;

import net.fabricmc.api.ClientModInitializer;

public class WhatItLacksClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModBlockEntityRenderers.registerBlockEntityRenderers();
	}
}