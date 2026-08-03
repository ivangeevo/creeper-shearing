package org.btwr.creeper_shearing.data.impl;

import net.minecraft.world.entity.monster.Monster;

public interface CreeperDataProvider {
    <T> T get(Monster creeper, DataKey<T> key);
    <T> T getOrCreate(Monster creeper, DataKey<T> key);
    <T> void set(Monster creeper, DataKey<T> key, T data);
}
