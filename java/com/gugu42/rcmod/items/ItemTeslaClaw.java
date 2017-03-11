package com.gugu42.rcmod.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class ItemTeslaClaw extends ItemRcGun{

	public ItemTeslaClaw() {
		super();
		this.weaponName = "teslaClaw";
		this.hasAmmoImage = true;
		this.heldType = 1;
		this.hasEquipSound = true;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		super.onUpdate(stack, w, ent, i, flag);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
		if(!world.isRemote){
			 List<Entity> entities = world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(player.posX, player.posY, player.posZ, player.posX + 1, player.posY + 1, player.posZ + 1).expand(16, 16, 16));
			 for(Entity e : entities){
				 if(e instanceof EntityLiving){
					 EntityLiving ent = (EntityLiving)e;
					 //if(ent.canEntityBeSeen(par3EntityPlayer)){
						 player.getEntityData().setDouble("tesla_claw_target_x", ent.posX);
						 player.getEntityData().setDouble("tesla_claw_target_y", ent.posY);
						 player.getEntityData().setDouble("tesla_claw_target_z", ent.posZ);
							return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
					/* } else {
						 par3EntityPlayer.getEntityData().setDouble("tesla_claw_target_x", par3EntityPlayer.posX);
						 par3EntityPlayer.getEntityData().setDouble("tesla_claw_target_y", par3EntityPlayer.posY);
						 par3EntityPlayer.getEntityData().setDouble("tesla_claw_target_z", par3EntityPlayer.posZ);
						 System.out.println("Right click 5 ");
						 return par1ItemStack;
					 }*/
				 }
			 }
		
		}
		
		
		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
	}

}
