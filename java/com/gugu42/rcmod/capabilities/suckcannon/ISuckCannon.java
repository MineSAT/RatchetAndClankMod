package com.gugu42.rcmod.capabilities.suckcannon;

import java.util.ArrayList;
import java.util.Stack;

import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public interface ISuckCannon {

		NBTTagList getStackAsJsonArrayString();
	
	  void createStackFromJsonArrayString(NBTTagList list);

	  void pushToStack(EntityLiving entity);
	  
	  void setStack(Stack<NBTTagCompound> stack);
	  
	  Stack<NBTTagCompound> getStack();
	  
	  ArrayList<NBTTagCompound> getStackAsList();
	  
	  NBTTagCompound popStack();
}
