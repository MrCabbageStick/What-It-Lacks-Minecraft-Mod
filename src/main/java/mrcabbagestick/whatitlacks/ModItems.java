package mrcabbagestick.whatitlacks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.ArrayList;

public class ModItems {

    public static ArrayList<Item> mod_items = new ArrayList<>();
    public static Item registerItem(Item item, String name){
        mod_items.add(item);
        return Registry.register(Registries.ITEM, WhatItLacks.createIdentifier(name), item);
    }

    public static Item registerBlockItem(Block block, String name){
        return registerItem(new BlockItem(block, new FabricItemSettings()), name);
    }
    public static void registerItems(){}
}
