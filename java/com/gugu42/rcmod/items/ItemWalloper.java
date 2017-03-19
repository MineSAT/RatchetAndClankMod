package com.gugu42.rcmod.items;

import com.gugu42.rcmod.TNTCrateExplosion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;

public class ItemWalloper extends ItemRcWeap {
	
	public ItemWalloper() {
		super();
		this.heldType = 0;
		this.maxStackSize = 1;
		this.useAmmo = false;
	}

	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer, int par4) {
		int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer,
				par1ItemStack, par2World, j, true);
		
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			return;
		}
		j = event.getCharge();

		float f = (float) j / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;
		
		

		if(f >= 1.0f){
			f = 1.0F;
		}
		
		if (f == 1.0F) {
			if (!par2World.isRemote) {
				TNTCrateExplosion explo = new TNTCrateExplosion(par2World, par3EntityPlayer, par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, 2.0f);
				explo.doExplosionA();
				explo.doExplosionB(true);
				
				if(par3EntityPlayer instanceof EntityPlayerMP) {
					EntityPlayerMP player = (EntityPlayerMP)par3EntityPlayer;
				
					player.motionY = 0.1f;
					player.motionX = -(Math.sin(Math.toRadians(player.getRotationYawHead())) * 5);
					player.motionZ = (Math.cos(Math.toRadians(player.getRotationYawHead())) * 5);
					//TODO - Fix networking issues
					/*player.playerNetServerHandler
					.sendPacket(new S12PacketEntityVelocity(player));*/
				}
				
				
				
			}
		}
	}

	public ItemStack onEaten(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	public int getMaxItemUseDuration(ItemStack par1ItemStack) {
		return 72000;
	}

    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.BOW;
    }

	//TODO - Fix walloper use
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
		player.setActiveHand(hand);

		return new ActionResult(EnumActionResult.FAIL, par1ItemStack);
	}
}
