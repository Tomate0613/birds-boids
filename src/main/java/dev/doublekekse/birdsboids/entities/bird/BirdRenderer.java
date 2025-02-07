package dev.doublekekse.birdsboids.entities.bird;

import dev.doublekekse.birdsboids.BirdsBoids;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BirdRenderer extends MobRenderer<Bird, LivingEntityRenderState, BirdModel<Bird>> {
    private static final ResourceLocation TEXTURE_LOCATION = BirdsBoids.id("textures/entity/bird/bird.png");

    public BirdRenderer(EntityRendererProvider.Context context) {
        super(context, new BirdModel<>(context.bakeLayer(BirdModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return TEXTURE_LOCATION;
    }
}
