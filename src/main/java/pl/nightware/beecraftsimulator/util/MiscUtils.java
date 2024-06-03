package pl.nightware.beecraftsimulator.util;

import java.util.function.BiConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;

public class MiscUtils
{
    public static void createParticleLine(BlockPos fromPos, BlockPos toPos, int density, int increments, BiConsumer<Vec3i, Vec3i> func)
    {
        BlockPos goalPos = toPos.subtract(fromPos);
        int posX = goalPos.getX();
        int posY = goalPos.getY();
        int posZ = goalPos.getZ();

        Vec3i vec = new Vec3i(posX, posY, posZ);
        double d = Mth.sqrt((float)toPos.distSqr(fromPos));

        for (int i = 0; i < d * density; i += increments)
        {
            double i1 = i / (double)density;
            double xp = fromPos.getX() + vec.getX() * i1 + .5;
            double yp = fromPos.getY() + vec.getY() * i1 + .5;
            double zp = fromPos.getZ() + vec.getZ() * i1 + .5;
            func.accept(vec, new Vec3i((int)xp, (int)yp, (int)zp));
        }
    }
}