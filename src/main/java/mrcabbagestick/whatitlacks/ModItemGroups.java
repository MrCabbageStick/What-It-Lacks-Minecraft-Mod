package mrcabbagestick.whatitlacks;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ModItemGroups {

    public static final ItemGroup MAIN_ITEM_GROUP = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.what-it-lacks.main"))
            .icon(() -> new ItemStack(ModBlocks.PLACARD.asItem()))
            .entries((displayContext, entries) -> {
                entries.addAll(ModItems.mod_items.stream().map(ItemStack::new).toList());
            })
            .build();

    public static void registerItemGroups(){
        Registry.register(Registries.ITEM_GROUP, WhatItLacks.createIdentifier("main"), MAIN_ITEM_GROUP);
    }
}
