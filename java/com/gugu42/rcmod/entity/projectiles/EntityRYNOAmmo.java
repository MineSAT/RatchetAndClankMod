package com.gugu42.rcmod.entity.projectiles;

import java.util.List;

import com.gugu42.rcmod.RcMod;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRYNOAmmo extends EntityThrowable {
	public boolean         homing             = true;
	private int            ticksAlive         = 0;
	protected EntityLiving target;
	protected EntityPlayer shootingEntity;
	protected Entity       predefTarget;
	public boolean         shouldUpdateTarget = true;

	public EntityRYNOAmmo(World par1World) {
		super(par1World);
	}

	public EntityRYNOAmmo(World par1World, Entity par2Entity) {
		super(par1World);

	}

	public EntityRYNOAmmo(World par1World, EntityPlayer par3EntityPlayer) {
		super(par1World, par3EntityPlayer);
		this.shootingEntity = par3EntityPlayer;
	}

	public EntityRYNOAmmo(World par1World, EntityPlayer par3EntityPlayer, Entity target) {
		super(par1World, par3EntityPlayer);
		this.shootingEntity = par3EntityPlayer;
		this.predefTarget = target;
		this.shouldUpdateTarget = false;
	}

	protected void entityInit() {
	}

	protected float getGravityVelocity() {
		return 0.0F;
	}

	protected float func_70182_d() {
		return 1.2F;
	}

	protected float func_70183_g() {
		return -0.0F;
	}

	public void onUpdate() {
		super.onUpdate();
		this.ticksAlive += 1;
		if (this.ticksAlive >= 300) {
			setDead();
			this.ticksAlive = 0;
		}

		if (predefTarget != null) {
			this.target = (EntityLiving) predefTarget;
		}
		
		//TODO - Fix the spawn particle
		//this.world.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		//this.worldObj.spawnParticle("flame", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);

		if (this.ticksAlive >= 5) {
			if ((this.target == null) || (this.target.velocityChanged) || (!this.target.canEntityBeSeen(this)) || this.target.isDead || this.target.getEntityData().getInteger("missilesTargeting") != this.hashCode()) {
				if (shouldUpdateTarget)
					this.target = this.getNearestEntity();
			}
		}

		if (this.target != null) {
			double d = this.target.getEntityBoundingBox().minX + (this.target.getEntityBoundingBox().maxX - this.target.getEntityBoundingBox().minX) / 2.0D - this.posX;
			double d1 = this.target.getEntityBoundingBox().minY + (this.target.getEntityBoundingBox().maxY - this.target.getEntityBoundingBox().minY) / 2.0D - this.posY;
			double d2 = this.target.getEntityBoundingBox().minZ + (this.target.getEntityBoundingBox().maxZ - this.target.getEntityBoundingBox().minZ) / 2.0D - this.posZ;
			this.setThrowableHeading(d, d1, d2, 0.9F, 0.0F);
			this.posY += (0.5 * Math.sin(this.ticksAlive));
			this.posX += (0.5 * Math.cos(this.ticksAlive));
		}
		
		float f4 = 0.99F;
		float f6 = 0.05F;

		if (!this.homing) {
			this.motionX *= f4;
			this.motionY *= f4;
			this.motionZ *= f4;
			this.motionY -= f6;
		}
		
		
		
	}

	private EntityLiving getTarget(double d, double d1, double d2, double d3) {
		double d4 = -1.0D;
		EntityLiving entityliving = null;
		List list = this.world.getEntitiesWithinAABBExcludingEntity(getThrower(), this.getEntityBoundingBox().expand(16.0D, 16.0D, 16.0D));

		for (int i = 0; i < list.size(); i++) {
			EntityLiving entityliving1 = (EntityLiving) list.get(i);

			if (entityliving1 != getThrower()) {
				double d5 = entityliving1.getDistance(d, d1, d2);

				if (((d3 < 0.0D) || (d5 < d3 * d3)) && ((d4 == -1.0D) || (d5 < d4)) && (entityliving1.canEntityBeSeen(this))) {
					d4 = d5;
					entityliving = entityliving1;
				}
			}
		}

		return entityliving;
	}

	public boolean validTarget(EntityLiving entityliving) {
		if (entityliving.equals(getThrower())) {
			return false;
		}

		return true;
	}

	protected void onImpact(RayTraceResult movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			int b0 = RcMod.config.get("weapon_damage", "ryno", 20).getInt();

			if (movingobjectposition.entityHit != this.getThrower())
				movingobjectposition.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(this.shootingEntity), b0);
			if (movingobjectposition.entityHit.getEntityData().getInteger("missilesTargeting") != 0) {
				movingobjectposition.entityHit.getEntityData().setInteger("missilesTargeting", 0);
			}
			if (!this.world.isRemote) {
				setDead();
			}
		}

		for (int i = 0; i < 8; i++) {
			//this.world.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}

		if (!this.world.isRemote) {
			setDead();
		}

		if ((!this.isDead) && (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) && (!this.world.isRemote))
			setDead();
	}

	private EntityLiving getNearestEntity() {
		EntityLiving target = null;
		float explosionSize = 10.0F;
		explosionSize *= 2.0F;
		int i = MathHelper.floor(this.posX - explosionSize - 1.0D);
		int j = MathHelper.floor(this.posX + explosionSize + 1.0D);
		int k = MathHelper.floor(this.posY - explosionSize - 1.0D);
		int l1 = MathHelper.floor(this.posY + explosionSize + 1.0D);
		int i2 = MathHelper.floor(this.posZ - explosionSize - 1.0D);
		int j2 = MathHelper.floor(this.posZ + explosionSize + 1.0D);

		if (!this.world.isRemote) {
			List list = this.world.getEntitiesWithinAABBExcludingEntity(getThrower(), new AxisAlignedBB(i, k, i2, j, l1, j2));

			for (int k2 = 0; k2 < list.size(); k2++) {
				Entity entity = (Entity) list.get(k2);
				if (((entity instanceof EntityLiving)) && (((EntityLiving) entity).canEntityBeSeen(this))) {
					target = (EntityLiving) entity;
					if (target.getEntityData().getInteger("missilesTargeting") == 0) {
						target.getEntityData().setInteger("missilesTargeting", this.hashCode());
						return target;
					}
				}
			}
		}
		return target;
	}
}