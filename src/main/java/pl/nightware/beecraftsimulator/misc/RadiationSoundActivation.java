package pl.nightware.beecraftsimulator.misc;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraftforge.registries.ForgeRegistries;

public class RadiationSoundActivation {
    public static void execute(LevelAccessor world, double x, double y, double z) {
        if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
                _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("beecraftsimulator:radiation")), SoundSource.MASTER, 1, (float) Mth.nextDouble(RandomSource.create(), 0.88, 1.18));
            } else {
                _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("beecraftsimulator:radiation")), SoundSource.MASTER, 1, (float) Mth.nextDouble(RandomSource.create(), 0.88, 1.18), false);
            }
        }
    }

}
