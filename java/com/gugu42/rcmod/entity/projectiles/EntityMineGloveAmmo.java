package com.gugu42.rcmod.entity.projectiles;

import java.util.List;

import com.gugu42.rcmod.TNTCrateExplosion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityMineGloveAmmo extends EntityThrowable implements IProjectile {

	// When the mine touches the ground, it become active
	public boolean isActive;

	public EntityMineGloveAmmo(World par1World) {
		super(par1World);
		this.isActive = false;
	}

	public EntityMineGloveAmmo(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
		this.isActive = false;
	}

	public EntityMineGloveAmmo(World par1World, EntityPlayer par2EntityPlayer) {
		super(par1World, par2EntityPlayer);
		this.isActive = false;
	}

	public EntityMineGloveAmmo(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
		this.isActive = false;
	}

	@Override
	protected void onImpact(RayTraceResult movingobjectposition) {
		if (movingobjectposition != null) {
			if (movingobjectposition.typeOfHit == RayTraceResult.Type.ENTITY) {

				// this.explode();
			}
			if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK) {
				this.motionX = 0;
				this.motionY = 0;
				this.motionZ = 0;
				this.isActive = true;
			}

		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.30F;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (isActive) {
			List entityTagetList = this.world.getEntitiesWithinAABB(
					Entity.class, this.getEntityBoundingBox().expand(0.5D, 0.5D, 0.5D));
			for (int i = 0; i < entityTagetList.size(); i++) {
				Entity entityTarget = (Entity) entityTagetList.get(i);
				if (entityTarget != null && entityTarget != this) {
					this.explode();
				}
			}
		}
	}

	private void explode() {
		TNTCrateExplosion explosion = new TNTCrateExplosion(this.world,
				this, this.posX, this.posY, this.posZ, 2.0f);
		explosion.doExplosionA();
		explosion.doExplosionB(true);
		/*this.world.playSoundAtEntity(this, "rcmod:BombGloveExplosion",
				10.0f, 1.0f);*/
		this.setDead();
	}

}
