package com.gugu42.rcmod.capabilities.bolt;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BoltProvider implements ICapabilitySerializable<NBTBase> {

	@CapabilityInject(IBolt.class)
	public static final Capability<IBolt> BOLT_CAP = null;
	
	private IBolt instance = BOLT_CAP.getDefaultInstance();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == BOLT_CAP;
	}

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == BOLT_CAP ? BOLT_CAP.<T> cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return BOLT_CAP.getStorage().writeNBT(BOLT_CAP, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
    	BOLT_CAP.getStorage().readNBT(BOLT_CAP, this.instance, null, nbt);
    }
	
}
