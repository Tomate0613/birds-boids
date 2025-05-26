package dev.doublekekse.birdsboids.goals;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;

public class DynamicHeightBoundsGoal extends Goal {
    private static final int CHECK_INTERVAL = 40;

    private final Mob mob;
    private final float offset;
    private final float range;
    private float minHeight;
    private int checkTicks = 0;

    public DynamicHeightBoundsGoal(Mob mob, float offset, float range) {
        this.mob = mob;
        this.offset = offset;
        this.range = range;
        this.minHeight = 0;

        checkTerrainHeight();
    }

    public boolean canUse() {
        checkTerrainHeight();

        return this.mob.getY() > this.minHeight + this.range || this.mob.getY() < this.minHeight;
    }

    public void checkTerrainHeight() {
        if (checkTicks-- < 0) {
            checkTicks = CHECK_INTERVAL;
        }

        minHeight = mob.level().getHeight(Heightmap.Types.MOTION_BLOCKING, mob.getBlockX(), mob.getBlockZ()) + offset;
    }

    public void tick() {
        this.mob.addDeltaMovement(this.bounds());
    }

    public Vec3 bounds() {
        double amount = 0.1;
        float dY = Mth.abs((float) this.mob.getDeltaMovement().y);
        if (dY > amount) {
            amount = dY;
        }

        if (this.mob.getY() > this.range + this.minHeight) {
            return new Vec3(0.0, -amount, 0.0);
        } else {
            return this.mob.getY() < this.minHeight ? new Vec3(0.0, amount, 0.0) : Vec3.ZERO;
        }
    }
}