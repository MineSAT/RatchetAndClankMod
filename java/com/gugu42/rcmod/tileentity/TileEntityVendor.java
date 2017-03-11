package com.gugu42.rcmod.tileentity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class TileEntityVendor extends TileEntity implements IInventory {

	public boolean     isPlayerNear = false;
	public NonNullList<ItemStack> inv = NonNullList.withSize(12, ItemStack.EMPTY);
	public int         renderCountdown;

	public TileEntityVendor() {
		renderCountdown = 0;
	}

	@Override
	public int getSizeInventory() {

		return inv.size();
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv.get(i);
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack stack = getStackInSlot(i);
		if (stack != null) {
			if (stack.getCount() <= j) {
				setInventorySlotContents(i, null);
			} else {
				stack = stack.splitStack(j);
				if (stack.getCount() == 0) {
					setInventorySlotContents(i, null);
				}
			}
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inv.set(i, itemstack);
		if (itemstack != null && itemstack.getCount() > getInventoryStackLimit()) {
			itemstack.setCount(getInventoryStackLimit());
		}
	}

	public void updateEntity() {
		this.isPlayerNear = this.isPlayerStandingNear();

		if (renderCountdown >= 1 && isPlayerNear) {
			renderCountdown--;
		}
	}

	public void setRenderCountdown(int value) {
		this.renderCountdown = value;
	}

	public int getRenderCountdown() {
		return renderCountdown;
	}

	public boolean isPlayerStandingNear() {

		AxisAlignedBB axisalignedbb = new AxisAlignedBB((double) this.getPos().getX(), (double) this.getPos().getY(), (double) this.getPos().getZ(), (double) (this.getPos().getX() + 1), (double) (this.getPos().getY() + 1), (double) (this.getPos().getZ() + 1)).expand(2, 2, 2);
		axisalignedbb.setMaxY((double) this.world.getHeight());
		List list = this.world.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
		Iterator iterator = list.iterator();
		EntityPlayer entityplayer;

		while (iterator.hasNext()) {
			return true;
		}

		return false;

	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);

		NBTTagList tagList = tagCompound.getTagList("Inventory", 1);
		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
			byte slot = tag.getByte("Slot");
			if (slot >= 0 && slot < inv.size()) {
				inv.set(slot, new ItemStack(tag));
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);

		NBTTagList itemList = new NBTTagList();
		for (int i = 0; i < inv.size(); i++) {
			ItemStack stack = inv.get(i);
			if (stack != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("Slot", (byte) i);
				stack.writeToNBT(tag);
				itemList.appendTag(tag);
			}
		}
		tagCompound.setTag("Inventory", itemList);
		
		return tagCompound;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "rcmod.tileentityvendor";
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack stack = getStackInSlot(index);
		if (stack != ItemStack.EMPTY) {
			setInventorySlotContents(index, ItemStack.EMPTY);
		}
		return stack;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return world.getTileEntity(new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ())) == this && player.getDistanceSq(this.getPos().getX() + 0.5, this.getPos().getY() + 0.5, this.getPos().getZ() + 0.5) < 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
