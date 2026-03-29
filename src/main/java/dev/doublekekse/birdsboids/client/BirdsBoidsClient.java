package dev.doublekekse.birdsboids.client;

import dev.doublekekse.birdsboids.BirdsBoids;
import dev.doublekekse.birdsboids.entities.bird.BirdModel;
import dev.doublekekse.birdsboids.entities.bird.BirdRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;

public class BirdsBoidsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRenderers.register(BirdsBoids.BIRD, BirdRenderer::new);
        ModelLayerRegistry.registerModelLayer(BirdModel.LAYER_LOCATION, BirdModel::createBodyLayer);
    }
}
