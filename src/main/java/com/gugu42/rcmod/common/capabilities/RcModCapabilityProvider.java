package com.gugu42.rcmod.common.capabilities;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RcModCapabilityProvider implements ICapabilitySerializable<INBT> {

    private IRcModCapability instance = RcModCapability.RCMOD_CAP.getDefaultInstance();

    @Override
    public INBT serializeNBT() {
        return RcModCapability.RCMOD_CAP.getStorage().writeNBT(RcModCapability.RCMOD_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        RcModCapability.RCMOD_CAP.getStorage().readNBT(RcModCapability.RCMOD_CAP, this.instance, null, nbt);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return RcModCapability.RCMOD_CAP.orEmpty(cap, LazyOptional.of(() -> this.instance));
    }
}
