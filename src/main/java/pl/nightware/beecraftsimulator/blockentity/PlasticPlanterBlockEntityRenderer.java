package pl.nightware.beecraftsimulator.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class PlasticPlanterBlockEntityRenderer implements BlockEntityRenderer<PlasticPlanterBlockEntity>
{
    private final BlockEntityRendererProvider.Context context;
    public PlasticPlanterBlockEntityRenderer(BlockEntityRendererProvider.Context _context)
    {
        this.context = _context;
    }
    @Override
    public void render(PlasticPlanterBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay)
    {
        Level level = blockEntity.getLevel();
        if (level == null) { return; }


        this.context.getBlockRenderDispatcher().renderSingleBlock();
    }
}
