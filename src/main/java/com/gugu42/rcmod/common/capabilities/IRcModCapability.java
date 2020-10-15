package com.gugu42.rcmod.common.capabilities;

import net.minecraft.entity.player.PlayerEntity;

public interface IRcModCapability {
    int getBolts();
    void addBolts(int amount);
    void setBolts(int amount);
    void sync(PlayerEntity player);

    boolean isUsingHelipack();
    void setUsingHelipack(boolean using);
}
