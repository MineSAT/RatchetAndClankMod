package com.gugu42.rcmod.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
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
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer){
		System.out.println("Right click");
		
		if(!par2World.isRemote){
			 List<Entity> entities = par2World.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ, par3EntityPlayer.posX + 1, par3EntityPlayer.posY + 1, par3EntityPlayer.posZ + 1).expand(16, 16, 16));
			 for(Entity e : entities){
				 System.out.println("Right click 2 ");
				 if(e instanceof EntityLiving){
					 System.out.println("Right click 3 ");
					 EntityLiving ent = (EntityLiving)e;
					 //if(ent.canEntityBeSeen(par3EntityPlayer)){
						 par3EntityPlayer.getEntityData().setDouble("tesla_claw_target_x", ent.posX);
						 par3EntityPlayer.getEntityData().setDouble("tesla_claw_target_y", ent.posY);
						 par3EntityPlayer.getEntityData().setDouble("tesla_claw_target_z", ent.posZ);
						 System.out.println("Right click 4 ");
						 return par1ItemStack;
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
		
		
		return par1ItemStack;
	}

}
