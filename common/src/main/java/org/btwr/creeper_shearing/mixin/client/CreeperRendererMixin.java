package org.btwr.creeper_shearing.mixin.client;

import net.minecraft.client.model.CreeperModel;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Creeper;
import org.btwr.creeper_shearing.Constants;
import org.btwr.creeper_shearing.api.NeuteredCreeperTextures;
import org.btwr.creeper_shearing.data.CreeperDataAttachment;
import org.btwr.creeper_shearing.data.CreeperDataKeys;
import org.btwr.creeper_shearing.data.CreeperNeuterData;
import org.btwr.creeper_shearing.tag.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CreeperRenderer.class)
public abstract class CreeperRendererMixin extends MobRenderer<Creeper, CreeperModel<Creeper>> {

    @Unique
    private static final ResourceLocation NEUTERED_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            Constants.MOD_ID, "textures/entity/neutered_creeper.png"
    );

    public CreeperRendererMixin(EntityRendererProvider.Context context, CreeperModel<Creeper> entityModel, float f) {
        super(context, entityModel, f);
    }

    //@Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/monster/Creeper;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void injectedGetTexture(Creeper entity, CallbackInfoReturnable<ResourceLocation> cir) {
        // Only do this if the type belongs to the tag
        var type = entity.getType();

        if (!type.is(ModTags.EntityTypes.NEUTERABLE_CREEPERS)) return;

        CreeperNeuterData neuterData = CreeperDataAttachment.get(entity, CreeperDataKeys.NEUTER_DATA);

        if (neuterData == null || !neuterData.isNeutered()) return;

        // External override?
        var override = NeuteredCreeperTextures.get(type);
        if (override != null) {
            cir.setReturnValue(override);
            return;
        }

        // Fallback to the default neutered texture
        cir.setReturnValue(NEUTERED_TEXTURE);
    }

}