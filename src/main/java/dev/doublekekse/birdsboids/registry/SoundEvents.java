package dev.doublekekse.birdsboids.registry;

import dev.doublekekse.birdsboids.BirdsBoids;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class SoundEvents {
    public static final SoundEvent BIRD_HURT = register("entity.bird.hurt");
    public static final SoundEvent BIRD_DEATH = register("entity.bird.death");
    public static final SoundEvent BIRD_FLAP = register("entity.bird.flap");

    private static SoundEvent register(String id) {
        var identifier = BirdsBoids.id(id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void register() {
    }
}
