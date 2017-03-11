package com.gugu42.rcmod.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.projectiles.EntityDecoyGloveAmmo;

public class ItemDecoyGlove extends ItemRcGun{

    private int ammo;
    private int cooldown;
    
    public ItemDecoyGlove() {
        super();
        this.ammo = 20;
        this.maxAmmo = 20;
        this.weaponName = "bombGlove";
        this.hasAmmoImage = true;
        this.ammoPrice = 10;
        this.cooldown = 20;
        this.setMaxDamage(maxAmmo);
        this.maxStackSize = 1;
        this.setCreativeTab(RcMod.rcTab);
    }

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
        if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
            if (!world.isRemote) {
                if (cooldown <= 0) {
                    EntityDecoyGloveAmmo decoy = new EntityDecoyGloveAmmo(
                            world, player, player.rotationYawHead);
                    world.spawnEntity(decoy);
                    par1ItemStack.damageItem(1, player);
                    cooldown = 20;
                    player.swingArm(hand);
                }
            }
        }
		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
    }

    public boolean canHarvestBlock(Block par1Block) {
        return false;
    }

    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        return 0.0f;
    }

    public void onUpdate(ItemStack stack, World w, Entity ent, int i,
            boolean flag) {
        if (cooldown > 0) {
            cooldown--;
        }

    }

    public void onPlayerStoppedUsing(ItemStack stack, World w,
            EntityPlayer player, int i) {
    }
    
}