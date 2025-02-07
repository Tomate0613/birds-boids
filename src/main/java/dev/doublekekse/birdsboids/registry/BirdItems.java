package dev.doublekekse.birdsboids.registry;

import dev.doublekekse.birdsboids.BirdsBoids;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;

import static dev.doublekekse.birdsboids.BirdsBoids.BIRD;

public class BirdItems {
    public static final Item BIRD_ITEM = register((key) -> new SpawnEggItem(BIRD, new Item.Properties().setId(key)), "bird");

    public static Item register(Function<ResourceKey<Item>, Item> item, String path) {
        ResourceKey<Item> registryKey = ResourceKey.create(Registries.ITEM, BirdsBoids.id(path));
        return Registry.register(BuiltInRegistries.ITEM, registryKey.location(), item.apply(registryKey));
    }

    public static void register() {

    }
}
