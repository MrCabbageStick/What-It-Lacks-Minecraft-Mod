package mrcabbagestick.whatitlacks.content.blocks.placard;

import mrcabbagestick.whatitlacks.ModBlockEntities;
import mrcabbagestick.whatitlacks.WhatItLacks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class PlacardBlockEntity extends BlockEntity {

//    public ItemStack displayItem = new ItemStack(Items.CAULDRON);
    public ItemStack displayItem = new ItemStack(Items.AIR);

    public PlacardBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLACARD_BLOCK_ENTITY, pos, state);
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {

        nbt.put("item", displayItem.writeNbt(new NbtCompound()));

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        NbtCompound item = nbt.getCompound("item");
        displayItem = item.isEmpty() ? new ItemStack(Items.AIR) : ItemStack.fromNbt(item);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public void replaceItem(ItemStack item, BlockState state){
        this.displayItem = item;
        markDirty();

        if(world != null)
            world.updateListeners(getPos(), state, state, Block.NOTIFY_LISTENERS);
    }
}
