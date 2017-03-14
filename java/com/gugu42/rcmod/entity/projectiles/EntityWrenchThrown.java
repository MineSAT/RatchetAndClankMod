package com.gugu42.rcmod.entity.projectiles;

import java.util.List;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.blocks.RcBlocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

public class EntityWrenchThrown extends EntityThrowable implements
		IThrowableEntity {

	private boolean isReturningToOwner = false;

	private ItemStack itemThrown;

	public EntityWrenchThrown(World world) {
		super(world);
	}

	public EntityWrenchThrown(World p_i1777_1_, EntityLivingBase p_i1777_2_) {
		super(p_i1777_1_, p_i1777_2_);
	}

	public EntityWrenchThrown(World p_i1777_1_, EntityLivingBase p_i1777_2_,
			ItemStack itemstack) {
		super(p_i1777_1_, p_i1777_2_);
		this.itemThrown = itemstack;
	}

	@Override
	public void setThrower(Entity entity) {

	}

	@Override
	protected void onImpact(RayTraceResult mop) {
		if (!this.world.isRemote) {
			if (this.ticksExisted > 1) {
				if (mop.entityHit != null
						&& mop.entityHit instanceof EntityLiving) {
					if (mop.entityHit != this.getThrower()) {
						mop.entityHit.attackEntityFrom(DamageSource
								.causePlayerDamage((EntityPlayer) this
										.getThrower()), RcMod.config.get("weapon_damage", "wrench_thrown", 5).getInt());
						this.setReturningToOwner(true);
					}
				} else if (world
						.getBlockState(new BlockPos(mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ())).getBlock() != null) {
					if (world.getBlockState(new BlockPos(mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ())).getBlock() == RcBlocks.CRATE
							|| world.getBlockState(new BlockPos(mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ())).getBlock() == RcBlocks.TNT_CRATE) {
						//Continue
					} else {
						this.setReturningToOwner(true);
					}
				}
			}
		}

	}

	public void setReturningToOwner(boolean bool) {
		this.isReturningToOwner = bool;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.ticksExisted >= 25) {
			this.setReturningToOwner(true);
		}

		if (!this.world.isRemote) {
			if (this.ticksExisted >= 60) {
				if (this.getThrower() instanceof EntityPlayer) {
					EntityPlayer owner = (EntityPlayer) this.getThrower();
					if (itemThrown != null) {
						world.spawnEntity(new EntityItem(world,
								this.posX, this.posY, this.posZ, itemThrown));
					}

				}
				this.setDead();
			}
		}

		if (!this.world.isRemote) {
			if (this.getThrower() == null || this.getThrower().isDead) {
				this.setDead();
			}
		}

		if (this.isReturningToOwner) {
			returnToOwner();
		}

		if (this.isReturningToOwner) {
			List entityTagetList = this.world.getEntitiesWithinAABB(
					Entity.class, this.getEntityBoundingBox().expand(1.0D, 1.0D, 1.0D));
			for (int i = 0; i < entityTagetList.size(); i++) {
				Entity entityTarget = (Entity) entityTagetList.get(i);
				if (entityTarget != null
						&& entityTarget instanceof EntityPlayer) {
					EntityPlayer owner = (EntityPlayer) entityTarget;
					if (itemThrown != null) {
						int slot = owner.inventory.getFirstEmptyStack();
						if(slot >= 0){
							owner.inventory.mainInventory.set(slot, itemThrown.copy());
							this.setDead();
						} else {
							world.spawnEntity(new EntityItem(
							world, this.posX, this.posY, this.posZ,
							itemThrown));
							this.setDead();
						}
					}
					this.setDead();
				}
			}
		}
		
		if(!world.isRemote)
		{
    		destroyBoltCrates();
    		destroyTNTCrates();
		}

	}

	@Override
	protected float getGravityVelocity() {
		return 0.00F;
	}
	
	private void destroyBoltCrates() {
		int x = MathHelper.floor(posX);
		int y = MathHelper.floor(posY);
		int z = MathHelper.floor(posZ);
		Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
		if (block == RcBlocks.CRATE) {
			block.dropBlockAsItem(world, new BlockPos(x, y, z),
					RcBlocks.CRATE.getDefaultState(), 0);
			this.world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
		}
	}

	private void destroyTNTCrates() {
		int x = MathHelper.floor(posX);
		int y = MathHelper.floor(posY);
		int z = MathHelper.floor(posZ);
		Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
		if (block == RcBlocks.TNT_CRATE) {
			block.dropBlockAsItem(world, new BlockPos(x, y, z),
					RcBlocks.TNT_CRATE.getDefaultState(), 0);
			this.world.setBlockState(new BlockPos(x, y, z), Blocks.AIR.getDefaultState());
		}
	}

	public void returnToOwner() {
		if (!this.world.isRemote) {
			if (this.getThrower() == null) {
				this.setDead();
			} else {
				double newX = this.getThrower().posX - this.posX;
				double newY = (this.getThrower().posY + 1) - this.posY;
				double newZ = this.getThrower().posZ - this.posZ;
				setThrowableHeading(newX, newY, newZ, 1.5F, 0.0F);
			}
		}
	}

}