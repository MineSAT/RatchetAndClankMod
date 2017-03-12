package com.gugu42.rcmod.capabilities.bolt;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class BoltStorage implements IStorage<IBolt>
{
    @Override
    public NBTBase writeNBT(Capability<IBolt> capability, IBolt instance, EnumFacing side)
    {
        return new NBTTagInt(instance.getCurrentBolt());
    }

    @Override
    public void readNBT(Capability<IBolt> capability, IBolt instance, EnumFacing side, NBTBase nbt)
    {
        instance.setCurrentBolt(((NBTTagInt) nbt).getInt());
    }
}