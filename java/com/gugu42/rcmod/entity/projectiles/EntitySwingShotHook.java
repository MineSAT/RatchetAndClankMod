package com.gugu42.rcmod.entity.projectiles;

import java.util.List;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.blocks.RcBlocks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySwingShotHook extends EntityThrowable {

	public boolean shouldPullPlayer = false;
	public EntityPlayer thrower;

	public int returnTime = 30;
	public int timeLived;

	public double startX;
	public double startY;
	public double startZ;
	
	private static final DataParameter<Float> POW_VALUE = EntityDataManager.<Float>createKey(Entity.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> START_X = EntityDataManager.<Float>createKey(Entity.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> START_Y = EntityDataManager.<Float>createKey(Entity.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> START_Z = EntityDataManager.<Float>createKey(Entity.class, DataSerializers.FLOAT);

	public EntitySwingShotHook(World par1World) {
		super(par1World);

	}

	public EntitySwingShotHook(World par1World,
			EntityLivingBase par2EntityLivingBase) {
		super(par1World, par2EntityLivingBase);
		this.timeLived = 0;
		this.shouldPullPlayer = false;

		if (par2EntityLivingBase instanceof EntityPlayerMP) {
			this.thrower = (EntityPlayerMP) par2EntityLivingBase;
		} else {
			this.thrower = (EntityPlayer) par2EntityLivingBase;
		}
	}

	public EntitySwingShotHook(World par1World, double par2, double par4,
			double par6) {
		super(par1World, par2, par4, par6);
	}
	
	@Override
	protected void entityInit() {
		this.dataManager.set(POW_VALUE, 0.0f);
		this.dataManager.set(START_X, 0.0f);
		this.dataManager.set(START_Y, 0.0f);
		this.dataManager.set(START_Z, 0.0f);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (!this.world.isRemote) {
			timeLived++;
		}

		if (getPowValue() < 2D) {
			setPowValue((float)(getPowValue() + 0.05));
		}

		if (thrower != null && !thrower.isDead) {
			setStartPosition();
		}

		if (shouldPullPlayer) {

			if (thrower instanceof EntityPlayerMP) {

				EntityPlayerMP throwerMP = (EntityPlayerMP) thrower;

				// Vec3 playerPos = throwerMP.getPosition(1.0f);
				Vec3d playerPos = new Vec3d(
								throwerMP.posX,
								throwerMP.posY
										+ (throwerMP.getEyeHeight() - throwerMP
												.getDefaultEyeHeight()),
								throwerMP.posZ);
				
				Vec3d entPos = new Vec3d(
						this.posX, this.posY, this.posZ);

				Vec3d a = playerPos.subtract(entPos);

				Vec3d b = new Vec3d(a.xCoord, a.yCoord, a.zCoord);
				double suckingPower = 5;
				customKnockBack(throwerMP, 0, b.xCoord * suckingPower, b.yCoord
						* suckingPower, b.zCoord * suckingPower);

				//TODO - Fix network packets
				//throwerMP.playerNetServerHandler
				//		.sendPacket(new S12PacketEntityVelocity(throwerMP));

			}

			List entityTagetList = this.world.getEntitiesWithinAABB(
					Entity.class, this.getCollisionBoundingBox().expand(0.3D, 0.3D, 0.3D));
			for (int i = 0; i < entityTagetList.size(); i++) {
				Entity entityTarget = (Entity) entityTagetList.get(i);
				if (entityTarget != null && entityTarget == thrower) {
					this.setDead();
					if (thrower instanceof EntityPlayerMP) {

						EntityPlayerMP throwerMP = (EntityPlayerMP) thrower;

						throwerMP.motionX = 0;
						throwerMP.motionZ = 0;
						throwerMP.motionY = 0;

						/*throwerMP.playerNetServerHandler
								.sendPacket(new S12PacketEntityVelocity(
										throwerMP));*/
					}

					thrower.getEntityData()
							.setBoolean("canFireSwingshot", true);
				}
			}
		}

		if (timeLived >= returnTime && !shouldPullPlayer) {
			returnToThrower();
			List entityTagetList = this.world.getEntitiesWithinAABB(
					Entity.class, this.getCollisionBoundingBox().expand(0.3D, 0.3D, 0.3D));
			for (int i = 0; i < entityTagetList.size(); i++) {
				Entity entityTarget = (Entity) entityTagetList.get(i);
				if (entityTarget != null && entityTarget == thrower) {
					this.setDead();
					thrower.getEntityData()
							.setBoolean("canFireSwingshot", true);
				}
			}

		}

		if (timeLived >= 70 && !shouldPullPlayer) {
			this.setDead();
			thrower.getEntityData().setBoolean("canFireSwingshot", true);
		}

		if (timeLived >= 120) {
			this.setDead();
			thrower.getEntityData().setBoolean("canFireSwingshot", true);
		}

	}

	@Override
	protected void onImpact(RayTraceResult mop) {
		if (mop != null) {
			if (mop.typeOfHit != null
					&& mop.typeOfHit == RayTraceResult.Type.BLOCK) {
				if (world.getBlockState(new BlockPos(mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ())) != null
						&& world
								.getBlockState(new BlockPos(mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ())).getBlock() == RcBlocks.VERSA_TARGET_GREEN) {
					shouldPullPlayer = true;
					motionX = 0;
					motionY = 0;
					motionZ = 0;

					this.posX = mop.hitVec.xCoord;
					this.posY = mop.hitVec.yCoord;
					this.posZ = mop.hitVec.zCoord;
					//TODO-- sound
					//this.world.playSoundAtEntity(this.thrower, "rcmod:SwingShotHook", 1.0f, 1.0f);
				} else {
					returnToThrower();
				}
			}
		}

	}

	protected void setStartPosition() {
		setStartCoordinates(thrower.posX, thrower.posY + 1, thrower.posZ);
	}

	public void setStartCoordinates(double x, double y, double z) {
		setStartX((float)x);
		setStartY((float)y);
		setStartZ((float)z);
		updateEntPos();
	}

	private void updateEntPos() {
		posX = getStartX() + (getEndX() - getStartX());
		posY = getStartY() + (getEndY() - getStartY());
		posZ = getStartZ() + (getEndZ() - getStartZ());
	}

	protected void returnToThrower() {

		if (thrower == null) {
			this.setDead();
		} else {
			double newX = thrower.posX - this.posX;
			double newY = thrower.posY - this.posY;
			double newZ = thrower.posZ - this.posZ;
			setThrowableHeading(newX, newY + 0.5d, newZ, 1,
					0.0F);
		}

	}

	public void customKnockBack(EntityLivingBase par1Entity, float par2,
			double par3, double par4, double par5) {
		// if (par1Entity.worldObj.rand.nextDouble() >=
		// par1Entity.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).getAttributeValue())
		// {
		par1Entity.isAirBorne = true;
		float f1 = MathHelper.sqrt(par3 * par3 + par5 * par5);
		float f2 = 0.4F;
		par1Entity.motionX /= 2.0D;
		par1Entity.motionY /= 2.0D;
		par1Entity.motionZ /= 2.0D;
		par1Entity.motionX += par3 / (double) f1 * (double) f2;
		par1Entity.motionY += par4 / (double) f1 * (double) f2;
		par1Entity.motionZ += par5 / (double) f1 * (double) f2;

		if (par1Entity.motionY > 0.4000000059604645D) {
			par1Entity.motionY = 0.4000000059604645D;
		}
		// }
	}

	protected float getGravityVelocity() {
		return 0.00F;
	}

	@SideOnly(Side.CLIENT)
	public Vec3d getPosition(float par1) {
		if (par1 == 1.0F) {
			return new Vec3d(this.posX,
					this.posY, this.posZ);
		} else {
			double d0 = this.prevPosX + (this.posX - this.prevPosX)
					* (double) par1;
			double d1 = this.prevPosY + (this.posY - this.prevPosY)
					* (double) par1;
			double d2 = this.prevPosZ + (this.posZ - this.prevPosZ)
					* (double) par1;
			return new Vec3d(d0, d1, d2);
		}
	}

	public double getRopeAbsLength() {
		return Math.sqrt((getEndX() - getStartX()) * (getEndX() - getStartX())
				+ (getEndY() - getStartY()) * (getEndY() - getStartY())
				+ (getEndZ() - getStartZ()) * (getEndZ() - getStartZ()));
	}

	public int getSegmentCount() {
		return (int) Math.rint(getRopeAbsLength() / 0.5D);
	}

	public void setStartX(float input) {
		if (!world.isRemote)
			this.dataManager.set(START_X, input);
	}

	public void setStartY(float input) {
		if (!world.isRemote)
			this.dataManager.set(START_Y, input);
	}

	public void setStartZ(float input) {
		if (!world.isRemote)
			this.dataManager.set(START_Z, input);
	}

	public float getStartX() {
		return dataManager.get(START_X);
	}

	public float getStartY() {
		return dataManager.get(START_Y);
	}

	public float getStartZ() {
		return dataManager.get(START_Z);
	}

	private double getEndZ() {

		return this.posZ;
	}

	private double getEndY() {

		return this.posY;
	}

	private double getEndX() {

		return this.posX;
	}

	public float getPowValue() {
		return dataManager.get(POW_VALUE);
	}

	public void setPowValue(float input) {
		if (!world.isRemote)
			this.dataManager.set(POW_VALUE, input);
	}

	public double[] getCoordsAtRelativeLength(float relativeDistance) {
		double[] result = new double[3];
		result[0] = getStartX()
				+ ((getEndX() - getStartX()) * relativeDistance);
		result[2] = getStartZ()
				+ ((getEndZ() - getStartZ()) * relativeDistance);

		if ((relativeDistance *= 2) < 1) {
			result[1] = getStartY()
					+ ((getEndY() - getStartY()) * (0.5 * Math.pow(
							relativeDistance, getPowValue())));
		} else {
			result[1] = getStartY()
					+ ((getEndY() - getStartY()) * (1 - 0.5 * Math.abs(Math
							.pow(2 - relativeDistance, getPowValue()))));
		}

		return result;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		setStartX(compound.getFloat("startX"));
		setStartY(compound.getFloat("startY"));
		setStartZ(compound.getFloat("startZ"));
		setPowValue(compound.getFloat("ropePOWvalue"));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		compound.setFloat("startX", getStartX());
		compound.setFloat("startY", getStartY());
		compound.setFloat("startZ", getStartZ());
		compound.setFloat("ropePOWvalue", getPowValue());
	}

}
