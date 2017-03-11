package com.gugu42.rcmod.entity.projectiles;

import com.gugu42.rcmod.RcMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityVisibombAmmo extends EntityThrowable {
	private int ticksInAir;
	private EntityPlayer entityFiring;
	private int entityFiringID = -1;
	public String firingEntityName;

	

	public EntityVisibombAmmo(World par1World) {
		super(par1World);
		double speed = 0.5;
		this.motionX *= speed;
		this.motionY *= speed;
		this.motionZ *= speed;
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ,
				0.0001f, 0.05F);
		
	}

	public EntityVisibombAmmo(World par1World, EntityPlayer par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
		double speed = 0.5;
		this.motionX *= speed;
		this.motionY *= speed;
		this.motionZ *= speed;
		this.entityFiring = (EntityPlayer) par2EntityLivingBase;
		this.firingEntityName = entityFiring.getDisplayName().getFormattedText();
		if (entityFiring instanceof EntityPlayerMP) {
			EntityPlayerMP targetRot = (EntityPlayerMP) entityFiring;

			setRotation(-targetRot.rotationYaw, -targetRot.rotationPitch);
		}
	}

	/*
	 * public EntityVisibombAmmo(World par1World, double par2, double par4,
	 * double par6) { super(par1World, par2, par4, par6); }
	 */

	@Override
	public void entityInit() {

	}

	@Override
	protected void onImpact(RayTraceResult movingobjectposition) {
		if (movingobjectposition.entityHit != null) {
			this.world.createExplosion(this, this.posX, this.posY,
					this.posZ, 0.1F, true);
			movingobjectposition.entityHit.attackEntityFrom(
					DamageSource.causeThrownDamage(this, this.getThrower()),
					RcMod.config.get("weapon_damage", "visibomb", 12).getInt());
			// movingobjectposition.entityHit.setFire(5);
			//TODO - particle
			/*this.world.spawnParticle("snowballpoof", this.posX, this.posY,
					this.posZ, 0.0D, 0.0D, 0.0D);*/
			this.setDead();
		} else {
			this.world.createExplosion(this, this.posX, this.posY,
					this.posZ, 0.0F, true);
			this.setDead();
		}
		// this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ,
		// 0.0F, true);
		// this.setDead();
	}

	protected float getGravityVelocity() {
		return 0.0F;
	}

	public void onUpdate() {
		// if (this.entityFiring == null || this.entityFiring.isDead) {
		// this.setDead();
		// }
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
		super.onUpdate();
		++this.ticksInAir;
		
		if(!this.world.isRemote){
			if(this.getThrower() == null || this.getThrower().isDead){
				this.setDead();
			}
		}

		if (entityFiring instanceof EntityPlayerMP) {
			EntityPlayerMP targetRot = (EntityPlayerMP) entityFiring;

			setRotation(-targetRot.rotationYaw, -targetRot.rotationPitch);
		} else {

		}

		// setRotation(-mc.thePlayer.rotationYaw, -mc.thePlayer.rotationPitch);

		this.motionX = (double) (MathHelper.sin(this.rotationYaw / 180.0F
				* (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4f);
		this.motionZ = (double) (MathHelper.cos(this.rotationYaw / 180.0F
				* (float) Math.PI)
				* MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * 0.4f);
		this.motionY = (double) (MathHelper.sin((this.rotationPitch + this
				.prevRotationPitch) / 180.0F * (float) Math.PI) * 0.4f);

		if (this.ticksInAir == 200) {
			this.setDead();
		}
	}

	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		// entityFiringID = nbt.getInteger("entityFiringID");
	}

	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		// nbt.setInteger("entityFiringID", entityFiringID);
		return nbt;
	}

	/*
	 * public int getFiringEntityID() { return entityFiringID; }
	 */
}