package pl.nightware.beecraftsimulator.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.registries.ForgeRegistries;

public class PaperPlanterBlockEntityRenderer implements BlockEntityRenderer<PaperPlanterBlockEntity>
{
    private final BlockEntityRendererProvider.Context context;
    private final Item cabbage = ForgeRegistries.ITEMS.getValue(new ResourceLocation("beecraftsimulator:cabbage"));
    private final ItemStack stack = new ItemStack(cabbage);

    public PaperPlanterBlockEntityRenderer(BlockEntityRendererProvider.Context _context)
    {
        this.context = _context;
    }
    @Override
    public void render(PaperPlanterBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay)
    {
        Level level = blockEntity.getLevel();
        if (level == null) { return; }

        BlockPos pos = blockEntity.getBlockPos().above();
        float cabbageScaleMultiplier = blockEntity.getCabbageScale();
        //cabbageScaleMultiplier = 1f;
        int elapsedTime = blockEntity.getPassedTime();
        int requiredTime = blockEntity.getFullGrowthTime();
        double ratio = ((double)elapsedTime / (double)requiredTime);
        double moveUpRatio = ratio / 2f * cabbageScaleMultiplier;
        double additionalYFix = ratio * 0.05f;
        float finalCabbageScale = ((float)elapsedTime / (float)requiredTime) * cabbageScaleMultiplier;

        if (finalCabbageScale < 0.2f) { finalCabbageScale = 0.2f; moveUpRatio = finalCabbageScale / 2; }

        //animation is made here
        poseStack.pushPose();
        poseStack.translate(0.5, .9 + moveUpRatio + additionalYFix, 0.5);
        poseStack.scale(finalCabbageScale, finalCabbageScale, finalCabbageScale);

        this.context.getItemRenderer().renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                LightTexture.pack(
                        level.getBrightness(LightLayer.BLOCK, pos),
                        level.getBrightness(LightLayer.SKY, pos)
                ),
                OverlayTexture.pack(10 - (int)(ratio * 10), 10),
                poseStack,
                multiBufferSource,
                level,
                0
                );

        poseStack.popPose();
    }
}
