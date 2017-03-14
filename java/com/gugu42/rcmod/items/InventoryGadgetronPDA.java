package com.gugu42.rcmod.items;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;

public class InventoryGadgetronPDA implements IItemHandlerModifiable {

	public NonNullList<ItemStack> inv = NonNullList.withSize(15, ItemStack.EMPTY);

	public InventoryGadgetronPDA() {
	}


	@Override
	public ItemStack getStackInSlot(int i) {
		return inv.get(i);
	}

	@Override
	public int getSlots() {
		return inv.size();
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		inv.set(slot, stack);
		if (stack != ItemStack.EMPTY && stack.getCount() > 64) {
			stack.setCount(64);
		}
		
		return stack;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.getCount() <= amount) {
				insertItem(slot, ItemStack.EMPTY, false);
			} else {
				stack = stack.splitStack(amount);
				if (stack.getCount() == 0) {
					insertItem(slot, ItemStack.EMPTY, false);
				}
			}
		}
		return stack;
	}

	@Override
	public int getSlotLimit(int slot) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		inv.set(slot, stack);
	}

}
