package com.gugu42.rcmod.common;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.common.inventory.VendorContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RcContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, RcMod.MOD_ID);

    public static final RegistryObject<ContainerType<VendorContainer>> VENDOR_CONTAINER = CONTAINERS.register("vendor_container", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new VendorContainer(windowId, world, pos, inv, inv.player);
    }));
}
