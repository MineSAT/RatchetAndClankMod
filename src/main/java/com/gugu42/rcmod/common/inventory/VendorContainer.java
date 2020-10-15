package com.gugu42.rcmod.common.inventory;

import com.gugu42.rcmod.common.RcContainers;
import com.gugu42.rcmod.common.tileentities.VendorTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VendorContainer extends Container {

    public VendorContainer(int id, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(RcContainers.VENDOR_CONTAINER.get(), id);

        VendorTileEntity tileEntity = (VendorTileEntity) world.getTileEntity(pos);
        addSlot(new VendorSlot(tileEntity, 1, 12, 173));
        addSlot(new VendorSlot(tileEntity, 2, 30, 173));
        addSlot(new VendorSlot(tileEntity, 3, 48, 173));
        addSlot(new VendorSlot(tileEntity, 4, 66, 173));
        addSlot(new VendorSlot(tileEntity, 5, 84, 173));
        addSlot(new VendorSlot(tileEntity, 6, 102, 173));
        addSlot(new VendorSlot(tileEntity, 7, 120, 173));
        addSlot(new VendorSlot(tileEntity, 8, 138, 173));
        addSlot(new VendorSlot(tileEntity, 9, 156, 173));
        addSlot(new VendorSlot(tileEntity, 10, 174, 173));
        addSlot(new VendorSlot(tileEntity, 11, 192, 173));
        addSlot(new VendorSlot(tileEntity, 12, 210, 173));
        addSlot(new VendorSlot(tileEntity, 13, 228, 173));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public void putStackInSlot(int slotID, ItemStack stack) {
        super.putStackInSlot(slotID, stack);
        this.detectAndSendChanges();
    }
}
