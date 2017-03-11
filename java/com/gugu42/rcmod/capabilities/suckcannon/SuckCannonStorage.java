package com.gugu42.rcmod.capabilities.suckcannon;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.Constants.NBT;

public class SuckCannonStorage implements IStorage<ISuckCannon>
{
    @Override
    public NBTBase writeNBT(Capability<ISuckCannon> capability, ISuckCannon instance, EnumFacing side)
    {
    	NBTTagCompound nbtData = new NBTTagCompound();

        NBTTagList list = instance.getStackAsJsonArrayString();
        nbtData.setTag("stack", list);

        return nbtData;
    }

    @Override
    public void readNBT(Capability<ISuckCannon> capability, ISuckCannon instance, EnumFacing side, NBTBase nbt)
    {
        //instance.set(((NBTTagFloat) nbt).getFloat());
    	instance.createStackFromJsonArrayString(((NBTTagCompound) nbt).getTagList("stack", NBT.TAG_COMPOUND));
    	//(nbt.getTagList);
    }
}