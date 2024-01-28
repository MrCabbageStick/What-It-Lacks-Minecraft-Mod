package mrcabbagestick.whatitlacks;

import mrcabbagestick.whatitlacks.content.blocks.placard.Placard;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlocks {

    public static final Block PLACARD = registerBlock(new Placard(FabricBlockSettings.copyOf(Blocks.LEVER)), "placard");

    public static Block registerBlock(Block block, String name){
        ModItems.registerBlockItem(block, name);
        return Registry.register(Registries.BLOCK, WhatItLacks.createIdentifier(name), block);
    }
    public static void registerBlocks(){}
}
