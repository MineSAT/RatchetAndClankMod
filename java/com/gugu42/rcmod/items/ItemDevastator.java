package com.gugu42.rcmod.items;

import java.util.List;

import com.gugu42.rcmod.entity.projectiles.EntityRYNOAmmo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemDevastator extends ItemRcGun {

	public ItemDevastator() {
		super();
//		this.useTargetingSystem = true;
		this.useAmmo = true;
		this.maxAmmo = 20;
		this.ammoPrice = 50;
		this.setMaxDamage(this.maxAmmo);
		this.hasEquipSound = true;
		this.weaponName = "devastator";
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
		if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
			if (!world.isRemote) {
				if (player.getEntityData().getInteger(
						"devastatorCooldown") <= 0) {
					player.getEntityData().setInteger(
							"devastatorCooldown", 20);
					player.getEntityData().setBoolean(
							"devastatorFired", true);
					if (!player.capabilities.isCreativeMode) {
						if (maxAmmo - par1ItemStack.getItemDamage() > 0) {
							par1ItemStack.damageItem(1, player);
						}
					}
				}
			}
		}
		
		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
	}

	//TODO - Fix expanding BBs
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World,
			Entity par3Entity, int par4, boolean par5) {
		super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
		
		if (par5 && par3Entity instanceof EntityPlayer && !par2World.isRemote) {
			EntityPlayer player = (EntityPlayer) par3Entity;
			Entity target = null;
			/*List entityTagetList = par2World.getEntitiesWithinAABB(
					Entity.class,
					player.getCollisionBoundingBox().expand(48.0D, 48.0D, 48.0D));
			for (int i = 0; i < entityTagetList.size(); i++) {
				Entity entityTarget = (Entity) entityTagetList.get(i);
				if (entityTarget != player
						&& entityTarget instanceof EntityLivingBase) {
					EntityLivingBase entityLivingTarget = (EntityLivingBase) entityTarget;
					Vec3d vec3 = player.getLook(1.0F).normalize();
					Vec3d vec31 = new Vec3d(
									entityLivingTarget.posX - player.posX,
									entityLivingTarget.getCollisionBoundingBox().minY
											+ (double) (entityLivingTarget.height / 2.0F)
											- (player.posY + (double) player
													.getEyeHeight()),
									entityLivingTarget.posZ - player.posZ);
					double d0 = vec31.lengthVector();
					vec31 = vec31.normalize();
					double d1 = vec3.dotProduct(vec31);
					if (d1 > 1.0D - 0.025D / d0
							&& player.canEntityBeSeen(entityLivingTarget)) {
						target = entityLivingTarget;
					} else {
						target = null;
					}

				}
			}*/

//			if (par3Entity.getEntityData().getBoolean("devastatorFired")) {
				
				if (par3Entity.getEntityData().getBoolean("devastatorFired")) {
					if (target != null) {
						fireRocket(par2World, player, target);
						par3Entity.getEntityData().setBoolean(
								"devastatorFired", false);
					} else {
						fireRocket(par2World, player);
						par3Entity.getEntityData().setBoolean(
								"devastatorFired", false);
					}
//				}
			}

		}

		if (par3Entity.getEntityData().getInteger("devastatorCooldown") > 0) {
			par3Entity.getEntityData()
					.setInteger(
							"devastatorCooldown",
							par3Entity.getEntityData().getInteger(
									"devastatorCooldown") - 1);
		}

	}

	public void fireRocket(World world, EntityPlayer player) {
		EntityRYNOAmmo rocket = new EntityRYNOAmmo(world, player);
		world.spawnEntity(rocket);
		player.world.playSound(null, new BlockPos(player.posX, player.posY, player.posZ), new SoundEvent(new ResourceLocation("rcmod:DevastatorShot")), SoundCategory.MASTER, 1.0f, 1.0f);
	}

	public void fireRocket(World world, EntityPlayer player, Entity target) {
		EntityRYNOAmmo rocket = new EntityRYNOAmmo(world, player, target);
		world.spawnEntity(rocket);
		player.world.playSound(null, new BlockPos(player.posX, player.posY, player.posZ), new SoundEvent(new ResourceLocation("rcmod:DevastatorShot")), SoundCategory.MASTER, 1.0f, 1.0f);
	}

}
