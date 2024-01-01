package dev.doublekekse.birdsboids.client;

import dev.doublekekse.birdsboids.BirdsBoids;
import dev.doublekekse.birdsboids.entities.bird.BirdModel;
import dev.doublekekse.birdsboids.entities.bird.BirdRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class BirdsBoidsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(BirdsBoids.BIRD, BirdRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(BirdModel.LAYER_LOCATION, BirdModel::createBodyLayer);
    }
}
