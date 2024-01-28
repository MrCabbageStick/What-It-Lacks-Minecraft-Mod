package mrcabbagestick.whatitlacks.content.blocks.placard;

import mrcabbagestick.whatitlacks.WhatItLacks;
import mrcabbagestick.whatitlacks.helpers.VoxelShapeTools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class Placard extends Block implements BlockEntityProvider {

    public static Map<Direction, VoxelShape> SHAPE_MAP;
    public static final VoxelShape BASE_SHAPE;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public Placard(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction side = ctx.getSide().getOpposite();

        if(side == Direction.DOWN || side == Direction.UP)
            side = ctx.getHorizontalPlayerFacing();

        return getDefaultState().with(FACING, side);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_MAP.get(state.get(FACING));
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    static {
        BASE_SHAPE = VoxelShapes.cuboid(6 / 16.0f, 11 / 16.0f, 0.0f, 10 / 16.0f, 15 / 16.0f, 0.5f / 16.0f);
        SHAPE_MAP = Map.of(
            Direction.NORTH, BASE_SHAPE,
            Direction.EAST, VoxelShapeTools.rotate(BASE_SHAPE, VoxelShapeTools.RotationDegrees.DEG90, Direction.Axis.Y),
            Direction.SOUTH, VoxelShapeTools.rotate(BASE_SHAPE, VoxelShapeTools.RotationDegrees.DEG180, Direction.Axis.Y),
            Direction.WEST, VoxelShapeTools.rotate(BASE_SHAPE, VoxelShapeTools.RotationDegrees.DEG270, Direction.Axis.Y)
        );
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        ItemStack usedItem = player.getStackInHand(hand);
        PlacardBlockEntity target = (PlacardBlockEntity)world.getBlockEntity(pos);

        if(target == null)
            return ActionResult.PASS;

        if(!world.isClient()){
            target.displayItem = usedItem;
            world.updateListeners(pos, state, state, 0);
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PlacardBlockEntity(pos, state);
    }
}
