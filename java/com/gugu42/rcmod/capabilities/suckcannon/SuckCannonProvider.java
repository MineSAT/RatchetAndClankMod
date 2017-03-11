package com.gugu42.rcmod.capabilities.suckcannon;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class SuckCannonProvider implements ICapabilitySerializable<NBTBase> {
	
    @CapabilityInject(ISuckCannon.class)
    public static final Capability<ISuckCannon> SUCK_CANNON_CAP = null;

    private ISuckCannon instance = SUCK_CANNON_CAP.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == SUCK_CANNON_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == SUCK_CANNON_CAP ? SUCK_CANNON_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return SUCK_CANNON_CAP.getStorage().writeNBT(SUCK_CANNON_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
    	SUCK_CANNON_CAP.getStorage().readNBT(SUCK_CANNON_CAP, this.instance, null, nbt);
    }
}
