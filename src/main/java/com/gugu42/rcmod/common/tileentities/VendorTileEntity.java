package com.gugu42.rcmod.common.tileentities;

import com.gugu42.rcmod.common.RcBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;

public class VendorTileEntity extends TileEntity implements ITickableTileEntity, IInventory {

    private int timeAlive;
    public NonNullList<ItemStack> inv = NonNullList.withSize(15, ItemStack.EMPTY);

    public VendorTileEntity() {
        super(RcBlocks.VENDOR_TILE_ENTITY.get());
        this.timeAlive = 0;
    }

    public int getTimeAlive() {
        return this.timeAlive;
    }

    @Override
    public void tick() {
        this.timeAlive++;
    }

    @Override
    public int getSizeInventory() {
        return inv.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return inv.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        inv.set(i, itemstack);
        if (itemstack != null && itemstack.getCount() > getInventoryStackLimit()) {
            itemstack.setCount(getInventoryStackLimit());
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {

    }
}
