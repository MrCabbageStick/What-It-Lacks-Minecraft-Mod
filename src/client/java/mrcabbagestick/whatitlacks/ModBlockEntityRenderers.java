package mrcabbagestick.whatitlacks;

import mrcabbagestick.whatitlacks.content.placard.PlacardBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;

public class ModBlockEntityRenderers {

    public static void registerBlockEntityRenderers(){
        BlockEntityRendererFactories.register(ModBlockEntities.PLACARD_BLOCK_ENTITY, PlacardBlockEntityRenderer::new);
    }
}
