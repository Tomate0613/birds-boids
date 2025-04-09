package dev.doublekekse.birdsboids.entities.bird;

import dev.doublekekse.birdsboids.BirdsBoids;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BirdRenderer extends MobRenderer<Bird, BirdRenderState, BirdModel<Bird>> {
    private static final ResourceLocation TEXTURE_LOCATION = BirdsBoids.id("textures/entity/bird/bird.png");

    public BirdRenderer(EntityRendererProvider.Context context) {
        super(context, new BirdModel<>(context.bakeLayer(BirdModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public BirdRenderState createRenderState() {
        return new BirdRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(BirdRenderState renderState) {
        return TEXTURE_LOCATION;
    }

    @Override
    public void extractRenderState(Bird bird, BirdRenderState renderState, float f) {
        super.extractRenderState(bird, renderState, f);

        renderState.flyAnimationState.copyFrom(bird.flyAnimationState);
    }
}
