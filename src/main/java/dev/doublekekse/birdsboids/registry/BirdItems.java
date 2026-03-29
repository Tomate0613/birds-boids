package dev.doublekekse.birdsboids.registry;

import dev.doublekekse.birdsboids.BirdsBoids;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;

import static dev.doublekekse.birdsboids.BirdsBoids.BIRD;

public class BirdItems {
    public static final Item BIRD_ITEM = register((key) -> new SpawnEggItem(new Item.Properties().setId(key).spawnEgg(BIRD)), "bird_spawn_egg");

    public static final CreativeModeTab BIRDS_CREATIVE_MODE_TAB = register(
        FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(BIRD_ITEM))
            .displayItems((context, entries) -> {
                entries.accept(BIRD_ITEM);
            }),
        "birds"
    );

    private static CreativeModeTab register(CreativeModeTab.Builder builder, String path) {
        var tab = builder.title(Component.translatable("itemGroup.birdsboids." + path)).build();
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, BirdsBoids.id(path), tab);
    }

    private static Item register(Function<ResourceKey<Item>, Item> item, String path) {
        ResourceKey<Item> registryKey = ResourceKey.create(Registries.ITEM, BirdsBoids.id(path));
        return Registry.register(BuiltInRegistries.ITEM, registryKey.identifier(), item.apply(registryKey));
    }

    public static void register() {

    }
}
