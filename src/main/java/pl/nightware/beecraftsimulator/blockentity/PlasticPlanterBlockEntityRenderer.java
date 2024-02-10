package pl.nightware.beecraftsimulator.blockentity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import pl.nightware.beecraftsimulator.BeeCraftSimulator;
import pl.nightware.beecraftsimulator.block.PlanterBlock;
import pl.nightware.beecraftsimulator.init.ModItemInit;

public class PlasticPlanterBlockEntityRenderer implements BlockEntityRenderer<PlasticPlanterBlockEntity>
{
    private final BlockEntityRendererProvider.Context context;
    private final Item cabbage = ForgeRegistries.ITEMS.getValue(new ResourceLocation("beecraftsimulator:cabbage"));
    private final ItemStack stack = new ItemStack(cabbage);

    public PlasticPlanterBlockEntityRenderer(BlockEntityRendererProvider.Context _context)
    {
        this.context = _context;
    }
    @Override
    public void render(PlasticPlanterBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay)
    {
        Level level = blockEntity.getLevel();
        if (level == null) { return; }

        BlockPos pos = blockEntity.getBlockPos().above();
        float cabbageScaleMultiplier = blockEntity.getCabbageScale();
        int elapsedTime = blockEntity.getPassedTime();
        int requiredTime = blockEntity.getFullGrowthTime();
        double moveUpRatio = ((double)elapsedTime / (double)requiredTime) * 0.4f;
        float finalCabbageScale = ((float)elapsedTime / (float)requiredTime) * cabbageScaleMultiplier;

        if (finalCabbageScale < 0.2f) { finalCabbageScale = 0.2f; moveUpRatio = 0.0f; }

        poseStack.pushPose();
        poseStack.translate(0.5, 1 + moveUpRatio, 0.5);
        poseStack.scale(finalCabbageScale, finalCabbageScale, finalCabbageScale);

        this.context.getItemRenderer().renderStatic(
                stack,
                ItemDisplayContext.FIXED,
                LightTexture.pack(
                        level.getBrightness(LightLayer.BLOCK, pos),
                        level.getBrightness(LightLayer.SKY, pos)
                ),
                OverlayTexture.u(0f),
                poseStack,
                multiBufferSource,
                level,
                0
                );

        poseStack.popPose();
    }
}
