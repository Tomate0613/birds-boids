package dev.doublekekse.birdsboids.entities.bird;

import dev.doublekekse.birdsboids.goals.DynamicHeightBoundsGoal;
import dev.doublekekse.boids.goals.LimitSpeedAndLookInVelocityDirectionGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import dev.doublekekse.boids.goals.BoidGoal;
import org.jetbrains.annotations.NotNull;

public class Bird extends FlyingMob {
    public final AnimationState flyAnimationState = new AnimationState();
    float flapCooldownTick = 0;
    final int flapOffset;

    public Bird(EntityType<? extends FlyingMob> entityType, Level level) {
        super(entityType, level);
        flapOffset = getRandom().nextInt(0, 10);
    }

    public static AttributeSupplier.@NotNull Builder createMobAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 2.0);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new DynamicHeightBoundsGoal(this, 15, 120));
        this.goalSelector.addGoal(2, new BoidGoal(this, 0.1f, 2, 8 / 20f, 1 / 25f));
        this.goalSelector.addGoal(3, new LimitSpeedAndLookInVelocityDirectionGoal(this, 0.3f, 0.7f));
    }

    public static boolean checkAnimalSpawnRules(EntityType<Bird> entityType, LevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        boolean isBrightEnough = MobSpawnType.ignoresLightRequirements(mobSpawnType) || isBrightEnoughToSpawn(levelAccessor, blockPos);
        return levelAccessor.getBlockState(blockPos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) && isBrightEnough;
    }

    protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter blockAndTintGetter, BlockPos blockPos) {
        return blockAndTintGetter.getRawBrightness(blockPos, 0) > 8;
    }

    @Override
    public void tick() {
        super.tick();
        var animationTime = (tickCount + flapOffset) % (20 * 0.5F);
        if (animationTime < 4 && animationTime > 2 && --flapCooldownTick < 0) {
            this.level().playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.PHANTOM_FLAP, this.getSoundSource(), 0.1f + this.random.nextFloat() * 0.05F, 1.95F + this.random.nextFloat() * 0.05F, false);
            flapCooldownTick = 0;
        }
        flyAnimationState.startIfStopped(tickCount + flapOffset);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.FOX_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.FOX_DEATH;
    }
}
