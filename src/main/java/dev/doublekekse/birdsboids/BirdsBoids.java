package dev.doublekekse.birdsboids;

import dev.doublekekse.birdsboids.entities.bird.Bird;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;

public class BirdsBoids implements ModInitializer {
    public static final String MOD_ID = "birdsboids";

    public static final TagKey<Biome> SPAWNS_BIRDS = TagKey.create(Registries.BIOME, id("spawns_birds"));

    public static final EntityType<Bird> BIRD = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        id("bird"),
        FabricEntityTypeBuilder.create(MobCategory.AMBIENT, Bird::new).dimensions(EntityDimensions.fixed(1.5f, 0.6f)).build()
    );
    public static final Item BIRD_ITEM = new SpawnEggItem(BIRD, 0xFF4D3927, 0xFF7D706C, new Item.Properties());
    public static final CreativeModeTab BIRDS_CREATIVE_MODE_TAB = FabricItemGroup.builder()
        .icon(() -> new ItemStack(BIRD_ITEM))
        .title(Component.translatable("itemGroup.birdsboids.birds"))
        .displayItems((context, entries) -> {
            entries.accept(BIRD_ITEM);
        })
        .build();

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(BIRD, Bird.createMobAttributes());
        Registry.register(BuiltInRegistries.ITEM, id("bird_spawn_egg"), BIRD_ITEM);

        System.out.println(SPAWNS_BIRDS);
        BiomeModifications.addSpawn(BiomeSelectors.tag(SPAWNS_BIRDS), MobCategory.AMBIENT, BIRD, 50, 7, 10);
        SpawnPlacements.register(BIRD, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Bird::checkAnimalSpawnRules);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(content -> {
            content.accept(BIRD_ITEM);
        });

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, id("birds"), BIRDS_CREATIVE_MODE_TAB);
        FabricDefaultAttributeRegistry.register(BIRD, Bird.createMobAttributes());
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
