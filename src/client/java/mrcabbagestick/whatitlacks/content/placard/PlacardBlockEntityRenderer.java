package mrcabbagestick.whatitlacks.content.placard;

import mrcabbagestick.whatitlacks.ModBlocks;
import mrcabbagestick.whatitlacks.ModItems;
import mrcabbagestick.whatitlacks.content.blocks.placard.Placard;
import mrcabbagestick.whatitlacks.content.blocks.placard.PlacardBlockEntity;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.renderer.v1.Renderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationAxis;
import org.joml.Vector3f;

import java.util.Map;

public class PlacardBlockEntityRenderer implements BlockEntityRenderer<PlacardBlockEntity> {

    public static final Map<Direction, Pair<Vector3f, Float>> itemPositionRotationMap;

    public PlacardBlockEntityRenderer(BlockEntityRendererFactory.Context ctx){}

    @Override
    public void render(PlacardBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        matrices.push();

        Pair<Vector3f, Float> rotationPosition = itemPositionRotationMap.get(entity.getCachedState().get(Placard.FACING));
        Vector3f translateBy = rotationPosition.getLeft();
        float rotateBy = rotationPosition.getRight();

        matrices.translate(translateBy.x, translateBy.y, translateBy.z);
        matrices.scale(.25f, .25f, .25f);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotateBy));

        MinecraftClient.getInstance().getItemRenderer().renderItem(
                entity.displayItem,
                ModelTransformationMode.FIXED,
                light,
                overlay,
                matrices,
                vertexConsumers,
                MinecraftClient.getInstance().world,
                0
        );

        matrices.pop();

    }


    static{
        itemPositionRotationMap = Map.of(
                Direction.NORTH, new Pair<>(new Vector3f(.5f, .8125f, .04f), 180.0f),
                Direction.EAST, new Pair<>(new Vector3f(.96f, .8125f, .5f), 90.0f),
                Direction.SOUTH, new Pair<>(new Vector3f(.5f, .8125f, .96f), 0.0f),
                Direction.WEST, new Pair<>(new Vector3f(.04f, .8125f, .5f), 270.0f)
        );
    }
}
