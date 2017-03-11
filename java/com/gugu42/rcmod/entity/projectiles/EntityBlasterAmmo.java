package com.gugu42.rcmod.entity.projectiles;

import com.gugu42.rcmod.RcMod;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBlasterAmmo extends EntityThrowable {
	
	
	public EntityBlasterAmmo(World par1World) {
		super(par1World);
	}

	public EntityBlasterAmmo(World par1World, EntityLiving par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}

	public EntityBlasterAmmo(World par1World, EntityPlayer par2EntityLiving) {
		super(par1World, par2EntityLiving);
	}
	
	public EntityBlasterAmmo(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (result.entityHit != null) {
			int dmg = RcMod.config.get("weapon_damage", "blaster", 4).getInt();
			
			
			result.entityHit.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)this.getThrower()), dmg);
			if (!this.world.isRemote) {
				setDead();
			}
		}

		for (int i = 0; i < 8; i++) {
			/*this.world.spawnParticle("snowballpoof", this.posX, this.posY,
					this.posZ, 0.0D, 0.0D, 0.0D);*/
		}

		if (!this.world.isRemote) {
			setDead();
		}

		if ((!this.isDead)
				&& (result.typeOfHit == RayTraceResult.Type.BLOCK)
				&& (!this.world.isRemote))
			setDead();
	}
	
	@Override
    protected float getGravityVelocity()
    {
        return 0.00F;
    }
}