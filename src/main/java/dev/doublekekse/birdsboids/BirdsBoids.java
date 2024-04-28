package dev.doublekekse.birdsboids;

import dev.doublekekse.birdsboids.entities.bird.Bird;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public class BirdsBoids implements ModInitializer {
    public static final EntityType<Bird> BIRD = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation("birdsboids", "bird"),
            EntityType.Builder.of(Bird::new, MobCategory.AMBIENT).sized(1.5f, 0.6f).build()
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
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("birdsboids", "bird_spawn_egg"), BIRD_ITEM);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.FOREST), MobCategory.AMBIENT, BIRD, 50, 7, 10);
        SpawnPlacements.register(BIRD, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING, Bird::checkAnimalSpawnRules);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(content -> {
            content.accept(BIRD_ITEM);
        });

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation("birdsboids", "birds"), BIRDS_CREATIVE_MODE_TAB);
        FabricDefaultAttributeRegistry.register(BIRD, Bird.createMobAttributes());
    }
}
