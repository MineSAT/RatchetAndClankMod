package com.gugu42.rcmod.entity.projectiles;

import com.gugu42.rcmod.TNTCrateExplosion;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBombGloveAmmo extends EntityThrowable implements IProjectile {

	public EntityBombGloveAmmo(World par1World) {
		super(par1World);
	}

	public EntityBombGloveAmmo(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	public EntityBombGloveAmmo(World par1World, EntityPlayer par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	public EntityBombGloveAmmo(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(RayTraceResult movingobjectposition) {
		if (movingobjectposition != null) {
			if(movingobjectposition.typeOfHit == RayTraceResult.Type.ENTITY){
				this.explode();
			}
			if(movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK){
				this.explode();
			}
			
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.15F;
	}

	private void explode() {
		TNTCrateExplosion explosion = new TNTCrateExplosion(this.world,
				this, this.posX, this.posY, this.posZ, 3.0f);
		explosion.doExplosionA(false);
		explosion.doExplosionB(true, false);
		//this.world.playSoundAtEntity(this, "rcmod:BombGloveExplosion",
		//		10.0f, 1.0f);
		this.setDead();
	}

}