package com.gugu42.rcmod.entity.projectiles;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.items.RcItems;

import cpw.mods.fml.common.registry.IThrowableEntity;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityWrenchThrown extends EntityThrowable implements IThrowableEntity {

	public EntityPlayer owner;
	private boolean isReturningToOwner;
	private int maxTicksBeforeReturn = 20;
	private int ticksLived;

	public EntityWrenchThrown(World par1World) {
		super(par1World);
		this.isReturningToOwner = false;
		this.ticksLived = 0;
	}

	public EntityWrenchThrown(World par1World,
			EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
		this.isReturningToOwner = false;
		this.ticksLived = 0;
		this.owner = (EntityPlayer) par2EntityLivingBase;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop) {
		if (ticksLived <= 1) {

		} else {
			if (mop.entityHit != null && mop.entityHit == owner) {
				this.setDead();
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, posX,
						posY, posZ, new ItemStack(RcItems.omniwrench3000, 1)));
			} else if (worldObj.getBlockId(mop.blockX, mop.blockY, mop.blockZ) == RcMod.crateID || worldObj.getBlockId(mop.blockX, mop.blockY, mop.blockZ) == RcMod.tntCrateID) {
				//Continue
			} else {
				this.setReturningToOwner(true);
			}
		}
	}

	public void onUpdate() {
		super.onUpdate();
		ticksLived++;
		if (ticksLived == maxTicksBeforeReturn) {
			this.setReturningToOwner(true);
		}
		if (this.isReturningToOwner()) {
			returnToOwner();
		}

		if (ticksLived == (maxTicksBeforeReturn * 2) + 2) {
			this.setDead();
		}

		destroyBoltCrates();
		destroyTNTCrates();
	}

	protected void returnToOwner() {
		if (owner == null) {
			this.setDead();
		} else {
			double newX = owner.posX - this.posX;
			double newY = owner.posY - this.posY;
			double newZ = owner.posZ - this.posZ;
			setThrowableHeading(newX, newY, newZ, this.func_70182_d(), 0.0F);
		}
	}

	public boolean isReturningToOwner() {
		return isReturningToOwner;
	}

	public void setReturningToOwner(boolean isReturningToOwner) {
		this.isReturningToOwner = isReturningToOwner;
	}

	private void destroyBoltCrates() {
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(posY);
		int z = MathHelper.floor_double(posZ);
		int blockID = worldObj.getBlockId(x, y, z);
		Block block = Block.blocksList[blockID];
		if (block == RcMod.crate) {
			block.dropBlockAsItem(worldObj, x, y, z,
					this.worldObj.getBlockMetadata(x, y, z), 0);
			this.worldObj.destroyBlock(x, y, z, false);
		}
	}

	private void destroyTNTCrates() {
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(posY);
		int z = MathHelper.floor_double(posZ);
		int blockID = worldObj.getBlockId(x, y, z);
		Block block = Block.blocksList[blockID];
		if (block == RcMod.tntCrate) {
			block.dropBlockAsItem(worldObj, x, y, z,
					this.worldObj.getBlockMetadata(x, y, z), 0);
			this.worldObj.destroyBlock(x, y, z, false);
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.00F;
	}

	@Override
	public void setThrower(Entity entity) {
		
	}

}
