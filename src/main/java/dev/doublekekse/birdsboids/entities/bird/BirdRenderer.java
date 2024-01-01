package dev.doublekekse.birdsboids.entities.bird;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BirdRenderer extends MobRenderer<Bird, BirdModel<Bird>> {
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation("birdsboids", "textures/entity/bird/bird.png");
    public BirdRenderer(EntityRendererProvider.Context context) {
        super(context, new BirdModel<>(context.bakeLayer(BirdModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Bird entity) {
        return TEXTURE_LOCATION;
    }
}
