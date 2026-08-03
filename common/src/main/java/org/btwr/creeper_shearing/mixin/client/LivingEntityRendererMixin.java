package org.btwr.creeper_shearing.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import org.btwr.creeper_shearing.Constants;
import org.btwr.creeper_shearing.api.NeuteredCreeperTextures;
import org.btwr.creeper_shearing.data.CreeperDataAttachment;
import org.btwr.creeper_shearing.data.CreeperDataKeys;
import org.btwr.creeper_shearing.data.CreeperNeuterData;
import org.btwr.creeper_shearing.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>>
        extends EntityRenderer<T> implements RenderLayerParent<T, M>
{

    @Unique
    private static final ResourceLocation DEFAULT_NEUTERED_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID, "textures/entity/neutered_creeper.png"
    );

    protected LivingEntityRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @ModifyExpressionValue(
            method = "getRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;getTextureLocation(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/resources/ResourceLocation;")
    )
    private ResourceLocation getDefaultNeuteredTexture(ResourceLocation original, @Local(argsOnly = true) T livingEntity) {
        if (!livingEntity.getType().is(ModTags.EntityTypes.NEUTERABLE_CREEPERS)) return original;
        if (!(livingEntity instanceof Monster monster)) return original;

        CreeperNeuterData neuterData = CreeperDataAttachment.get(monster, CreeperDataKeys.NEUTER_DATA);
        if (neuterData == null || !neuterData.isNeutered()) return original;

        ResourceLocation override = NeuteredCreeperTextures.get(livingEntity.getType());
        return override != null ? override : DEFAULT_NEUTERED_TEXTURE;
    }

}