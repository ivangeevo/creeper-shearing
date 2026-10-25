package org.btwr.creeper_shearing.mixin;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.monster.MobCreeper;
import net.minecraft.core.entity.monster.MobMonster;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.item.tool.ItemToolShears;
import net.minecraft.core.world.World;
import org.btwr.creeper_shearing.api.NeuterableCreeper;
import org.btwr.creeper_shearing.api.NeuteredCreeperTextures;
import org.btwr.creeper_shearing.api.NeuteredDropsRegistry;
import org.btwr.creeper_shearing.sound.ModSoundTypes;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobCreeper.class)
public abstract class MobCreeperMixin extends MobMonster implements NeuterableCreeper {
	@Unique
	private static final int DATA_NEUTERED = 18;

	@Shadow
	int timeSinceIgnited;

	public MobCreeperMixin(@NotNull World world) {
		super(world);
	}

	@Inject(method = "defineSynchedData", at = @At("TAIL"))
	private void defineNeuteredData(CallbackInfo ci) {
		this.entityData.define(DATA_NEUTERED, (byte) 0, Byte.class);
	}

	@Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
	private void saveNeutered(CompoundTag tag, CallbackInfo ci) {
		tag.putBoolean("Neutered", this.creeper_shearing$isNeutered());
	}

	@Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
	private void readNeutered(CompoundTag tag, CallbackInfo ci) {
		this.creeper_shearing$setNeutered(tag.getBoolean("Neutered"));
	}

	@Inject(method = "attackEntity", at = @At("HEAD"), cancellable = true)
	private void preventFuse(Entity entity, float distance, CallbackInfo ci) {
		if (!this.creeper_shearing$isNeutered()) {
			return;
		}
		this.entityData.set(MobCreeper.DATA_CREEPER_STATE, (byte) -1);
		if (this.timeSinceIgnited > 0) {
			this.timeSinceIgnited--;
		}
		ci.cancel();
	}

	@Override
	public boolean interact(@NotNull Player player) {
		if (super.interact(player)) {
			return true;
		}

		ItemStack stack = player.inventory.getCurrentItem();
		if (stack == null || !(stack.getItem() instanceof ItemToolShears) || this.creeper_shearing$isNeutered()) {
			return false;
		}

		if (!this.world.isClientSide) {
			this.creeper_shearing$setNeutered(true);
			float layerPitch = (this.random.nextFloat() - this.random.nextFloat()) * 0.1F + 0.7F;
			this.world.playSoundAtEntity(null, this, ModSoundTypes.CREEPER_SHEARED, 1.0F, 1.0F);
			this.world.playSoundAtEntity(null, this, ModSoundTypes.CREEPER_SHEARED_LAYER, 1.0F, layerPitch);

			ItemStack drop = NeuteredDropsRegistry.get((MobCreeper) (Object) this);
			if (drop != null) {
				this.dropItem(drop, 1.0F);
			}
		}

		for (int i = 0; i < 50; i++) {
			double particleX = this.x + this.random.nextDouble() - 0.5D;
			double particleY = this.y + 0.8D + this.random.nextDouble() * 0.5D;
			double particleZ = this.z + this.random.nextDouble() - 0.5D;
			this.world.spawnParticle("item", particleX, particleY, particleZ, 0.0D, 0.0D, 0.0D, Items.AMMO_SNOWBALL.id, false);
		}

		stack.damageItem(10, player);
		if (stack.stackSize <= 0) {
			player.destroyCurrentEquippedItem();
		}
		return true;
	}

	@Override
	public void playLivingSound() {
		if (this.creeper_shearing$isNeutered() && !this.world.isClientSide) {
			float pitch = (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.25F;
			this.world.playSoundAtEntity(null, this, this.getHurtSound(), 0.25F, pitch);
		}
	}

	@Override
	protected void dropDeathItems() {
		super.dropDeathItems();
		if (!this.creeper_shearing$isNeutered() && this.random.nextInt(3) == 0) {
			ItemStack drop = NeuteredDropsRegistry.get((MobCreeper) (Object) this);
			if (drop != null) {
				this.dropItem(drop, 0.0F);
			}
		}
	}

	@NotNull
	@Override
	public String getEntityTexture() {
		return this.creeper_shearing$isNeutered() ? NeuteredCreeperTextures.get((MobCreeper) (Object) this) : super.getEntityTexture();
	}

	@NotNull
	@Override
	public String getDefaultEntityTexture() {
		return this.creeper_shearing$isNeutered() ? NeuteredCreeperTextures.get((MobCreeper) (Object) this) : super.getDefaultEntityTexture();
	}

	@Override
	public boolean creeper_shearing$isNeutered() {
		return this.entityData.getByte(DATA_NEUTERED) == 1;
	}

	@Override
	public void creeper_shearing$setNeutered(boolean neutered) {
		this.entityData.set(DATA_NEUTERED, (byte) (neutered ? 1 : 0));
	}
}