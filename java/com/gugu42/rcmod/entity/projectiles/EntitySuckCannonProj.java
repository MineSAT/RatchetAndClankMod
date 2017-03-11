package com.gugu42.rcmod.entity.projectiles;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.OwnableEntity;
import com.gugu42.rcmod.network.packets.PacketUpdateOwnerID;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntitySuckCannonProj extends EntityThrowable implements OwnableEntity
{

	private int ownerID;

	public EntitySuckCannonProj(World par1World)
	{
		super(par1World);
		this.setSize(2, 2);
	}

	public EntitySuckCannonProj(World par1World, EntityLiving par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	public EntitySuckCannonProj(World par1World, EntityPlayer par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	public EntitySuckCannonProj(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}

	public void setOwnerID(int ownerID)
	{
		this.ownerID = ownerID;
		if(!world.isRemote)
			RcMod.rcModPacketHandler.sendToAll(new PacketUpdateOwnerID(this, ownerID));
	}
	
	@Override
	protected void onImpact(RayTraceResult movingobjectposition)
	{
		if(movingobjectposition.typeOfHit == RayTraceResult.Type.ENTITY)
		{
    		if(movingobjectposition.entityHit != null && movingobjectposition.entityHit != this.getRidingEntity() && movingobjectposition.entityHit.getEntityId() != ownerID)
    		{
    			if(!this.world.isRemote)
    			{
    				setDead();
    				//if(world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
    					world.newExplosion(this, posX, posY, posZ, 1f, false, true);
    				movingobjectposition.entityHit.attackEntityFrom(DamageSource.GENERIC, RcMod.config.get("weapon_damage", "suck_cannon", 10).getInt());
    			}
    		}
    		else
    			return;
		}

		if(!this.world.isRemote)
		{
			setDead();
			//if(world.getGameRules().getGameRuleBooleanValue("mobGriefing"))
				world.newExplosion(this, posX, posY, posZ, 1f, false, true);
			
			if(this.getRidingEntity() != null)
				getRidingEntity().attackEntityFrom(DamageSource.GENERIC, 0xDEADBEEF);
		}
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.025F;
	}
	
	public void updateRidden()
	{
		super.updateRidden();
		this.getRidingEntity().posX = this.posX;
		this.getRidingEntity().posY = this.posY;
		this.getRidingEntity().posZ = this.posZ;
	}

	@Override
	public int getOwnerID()
	{
		return ownerID;
	}
}
