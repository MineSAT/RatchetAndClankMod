package com.gugu42.rcmod.common;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.client.render.items.BlasterITSER;
import com.gugu42.rcmod.client.render.items.VendorItemStackTileEntityRenderer;
import com.gugu42.rcmod.common.items.BlasterItem;
import com.gugu42.rcmod.common.items.HarbingerItem;
import com.gugu42.rcmod.common.items.armor.HelipackItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RcItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RcMod.MOD_ID);

    public static final RcModItemGroup RCMOD_ITEM_GROUP = new RcModItemGroup("rcmod_item_group");

    // Blocks
    public static final RegistryObject<Item> CRATE_ITEM_BLOCK = ITEMS.register("crate_item_block", () -> new BlockItem(RcBlocks.CRATE_BLOCK.get(), new Item.Properties().group(RCMOD_ITEM_GROUP)));
    public static final RegistryObject<Item> AMMO_CRATE_ITEM_BLOCK = ITEMS.register("ammo_crate_item_block", () -> new BlockItem(RcBlocks.AMMO_CRATE_BLOCK.get(), new Item.Properties().group(RCMOD_ITEM_GROUP)));
    public static final RegistryObject<Item> TNT_CRATE_ITEM_BLOCK = ITEMS.register("tnt_crate_item_block", () -> new BlockItem(RcBlocks.TNT_CRATE_BLOCK.get(), new Item.Properties().group(RCMOD_ITEM_GROUP)));
    public static final RegistryObject<Item> VENDOR_ITEM_BLOCK = ITEMS.register("vendor_item_block", () -> new BlockItem(RcBlocks.VENDOR_BLOCK.get(), new Item.Properties().group(RCMOD_ITEM_GROUP).setISTER(() -> VendorItemStackTileEntityRenderer::new)));

    // Weapons
    public static final RegistryObject<Item> BLASTER = ITEMS.register("blaster", () -> new BlasterItem(new Item.Properties().maxStackSize(1).group(RCMOD_ITEM_GROUP).setISTER(() -> BlasterITSER::new)));
    public static final RegistryObject<Item> HARBINGER = ITEMS.register("harbinger", () -> new HarbingerItem(new Item.Properties().maxStackSize(1).group(RCMOD_ITEM_GROUP)));

    // Misc
    public static final RegistryObject<Item> BOLT = ITEMS.register("bolt", () -> new Item(new Item.Properties().maxStackSize(1).group(RCMOD_ITEM_GROUP)));
    public static final RegistryObject<Item> CLANK_HELIPACK = ITEMS.register("helipack", () -> new HelipackItem(new Item.Properties().maxStackSize(1).group(RCMOD_ITEM_GROUP)));
    public static final RegistryObject<Item> RETICLE = ITEMS.register("reticle", () -> new Item(new Item.Properties().maxStackSize(1).group(RCMOD_ITEM_GROUP)));

    public static class RcModItemGroup extends ItemGroup {

        public RcModItemGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(BOLT::get);
        }
    }
}
