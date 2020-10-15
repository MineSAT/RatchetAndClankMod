package com.gugu42.rcmod.common;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.common.blocks.AmmoCrateBlock;
import com.gugu42.rcmod.common.blocks.CrateBlock;
import com.gugu42.rcmod.common.blocks.TntCrateBlock;
import com.gugu42.rcmod.common.blocks.VendorBlock;
import com.gugu42.rcmod.common.tileentities.VendorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RcBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RcMod.MOD_ID);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, RcMod.MOD_ID);

    public static final RegistryObject<Block> CRATE_BLOCK = BLOCKS.register("crate", () -> new CrateBlock(Block.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> AMMO_CRATE_BLOCK = BLOCKS.register("ammo_crate", () -> new AmmoCrateBlock(Block.Properties.create(Material.WOOD)));
    public static final RegistryObject<Block> TNT_CRATE_BLOCK = BLOCKS.register("tnt_crate", () -> new TntCrateBlock(Block.Properties.create(Material.TNT)));
    public static final RegistryObject<Block> VENDOR_BLOCK = BLOCKS.register("vendor", () -> new VendorBlock(Block.Properties.create(Material.IRON)));

    public static final RegistryObject<TileEntityType<VendorTileEntity>> VENDOR_TILE_ENTITY =
            TILE_ENTITIES.register("vendor_tile_entity", () -> TileEntityType.Builder.create(VendorTileEntity::new, RcBlocks.VENDOR_BLOCK.get()).build(null));
}
