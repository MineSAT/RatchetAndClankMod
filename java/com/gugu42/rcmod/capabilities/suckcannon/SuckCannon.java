package com.gugu42.rcmod.capabilities.suckcannon;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Stack;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SuckCannon implements ISuckCannon {

    private Stack<NBTTagCompound> nbtStack;
    private EntityPlayer owner;
	
	@Override
	public void createStackFromJsonArrayString(NBTTagList list) {
        nbtStack.clear();
        for(int i = 0; i < list.tagCount(); i++)
        {
            NBTTagCompound s = list.getCompoundTagAt(i);
            nbtStack.push(s);
        }
	}

	@Override
	public void pushToStack(EntityLiving entity) {
        NBTTagCompound nbtData = new NBTTagCompound();
        entity.writeEntityToNBT(nbtData);
        nbtData.setBoolean("hadCustomTagName", entity.hasCustomName());
        nbtData.setString("id", EntityList.getEntityString(entity));
        nbtStack.push(nbtData);
	}

	@Override
	public void setStack(Stack<NBTTagCompound> stack) {
		this.nbtStack = stack;
	}

	@Override
	public Stack<NBTTagCompound> getStack() {
		 return nbtStack;
	}

	@Override
	public ArrayList<NBTTagCompound> getStackAsList() {
		/*Enumeration<NBTTagCompound> elements = nbtStack.elements();
        ArrayList<NBTTagCompound> list = new ArrayList<NBTTagCompound>();
        while(elements.hasMoreElements())
            list.add(elements.nextElement());*/
		//TODO : fix this
        return null;
	}

	@Override
	public NBTTagList getStackAsJsonArrayString() {
		/*
        ArrayList<NBTTagCompound> list = getStackAsList();
        NBTTagList result = new NBTTagList();
        for(NBTTagCompound compound : list)
            result.appendTag(compound);
        */
        return null;
	}

	@Override
	public NBTTagCompound popStack() {
		if(nbtStack.isEmpty())
            return null;
        return nbtStack.pop();
	}

}
