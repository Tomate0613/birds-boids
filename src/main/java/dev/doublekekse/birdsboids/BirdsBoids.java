package dev.doublekekse.birdsboids;

import dev.doublekekse.birdsboids.entities.bird.Bird;
import dev.doublekekse.birdsboids.registry.BirdItems;
import dev.doublekekse.birdsboids.registry.SoundEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;

import static dev.doublekekse.birdsboids.registry.BirdItems.BIRD_ITEM;

public class BirdsBoids implements ModInitializer {
    public static final String MOD_ID = "birdsboids";

    public static final TagKey<Biome> SPAWNS_BIRDS = TagKey.create(Registries.BIOME, id("spawns_birds"));

    public static final EntityType<Bird> BIRD = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        id("bird"),
        EntityType.Builder.of(Bird::new, MobCategory.AMBIENT).sized(1.5f, 0.6f).build(ResourceKey.create(Registries.ENTITY_TYPE, id("bird")))
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(BIRD, Bird.createMobAttributes());

        BirdItems.register();

        BiomeModifications.addSpawn(BiomeSelectors.tag(SPAWNS_BIRDS), MobCategory.AMBIENT, BIRD, 50, 7, 10);
        SpawnPlacements.register(BIRD, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Bird::checkBirdSpawnRule);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(content -> {
            content.accept(BIRD_ITEM);
        });

        FabricDefaultAttributeRegistry.register(BIRD, Bird.createMobAttributes());

        SoundEvents.register();
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
