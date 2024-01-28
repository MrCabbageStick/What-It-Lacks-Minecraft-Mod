package mrcabbagestick.whatitlacks;

import mrcabbagestick.whatitlacks.content.blocks.placard.PlacardBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModBlockEntities {

    public static final BlockEntityType<PlacardBlockEntity> PLACARD_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            WhatItLacks.createIdentifier("placard_block_entity"),
            FabricBlockEntityTypeBuilder.create(PlacardBlockEntity::new, ModBlocks.PLACARD).build()
    );

    public static void registerBlockEntities(){}
}
