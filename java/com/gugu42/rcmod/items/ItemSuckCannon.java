package com.gugu42.rcmod.items;

import java.util.List;

import com.gugu42.rcmod.capabilities.suckcannon.ISuckCannon;
import com.gugu42.rcmod.capabilities.suckcannon.SuckCannonProvider;
import com.gugu42.rcmod.entity.projectiles.EntitySuckCannonProj;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemSuckCannon extends ItemRcWeap
{

    public ItemSuckCannon()
    {
        super();
        this.maxAmmo = 8;
        this.setMaxDamage(maxAmmo);
        this.heldType = 1;
        this.maxStackSize = 1;
    }

    public void onUpdate(ItemStack stack, World w, Entity owner, int i, boolean held)
    {
        if(owner instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)owner;
            ISuckCannon props = player.getCapability(SuckCannonProvider.SUCK_CANNON_CAP, null);
            int loaded = props.getStack().size();
            if(loaded != maxAmmo - stack.getItemDamage())
                stack.setItemDamage(maxAmmo - loaded);
            
            
            if(owner.getEntityData().getInteger("suckCannonRotation") > 0){
            	int cd = owner.getEntityData().getInteger("suckCannonRotation");
            	owner.getEntityData().setInteger("suckCannonRotation", cd - 1);
            	if(cd <= 3){
            		player.getEntityData().setBoolean("isUsingSuckCannon", false);
            	}
            }
            
        }
    }

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack stack = player.getHeldItem(hand);
		
        if(stack.getItemDamage() == 0)
    		return new ActionResult(EnumActionResult.FAIL, stack);
        
        double radius = 10;
        List<EntityLiving> entities = world.getEntitiesWithinAABB(EntityLiving.class, player.getCollisionBoundingBox().expand(radius, radius, radius));

        player.getEntityData().setBoolean("isUsingSuckCannon", true);
        player.getEntityData().setInteger("suckCannonRotation", 5);
        
        for(int i = 0; i < entities.size(); i++)
        {
            Vec3d look = player.getLookVec();
            EntityLiving entity = entities.get(i);

            if(entity.canEntityBeSeen(player))
            {
                Vec3d playerPos = new Vec3d(player.posX, player.posY + (player.getEyeHeight() - player.getDefaultEyeHeight()), player.posZ);
                Vec3d entPos = new Vec3d(entity.posX, entity.posY, entity.posZ);

                if(entity instanceof EntityDragon || entity instanceof EntityWither)
                    continue;

                Vec3d a = playerPos.subtract(entPos).normalize();

                double dotProduct = a.dotProduct(look);
                double angle = Math.acos(dotProduct);

                if(angle < 0.25 * Math.PI && angle > -0.25 * Math.PI)
                {
                    if(entity.getDistanceToEntity(player) >= 2.5)
                    {
                        Vec3d b = new Vec3d(a.xCoord, a.yCoord, a.zCoord);
                        double suckingPower = 5;
                        customKnockBack(entity, 0, b.xCoord * suckingPower, b.yCoord * suckingPower, b.zCoord * suckingPower);
                    }
                    else
                    {
                        entity.setDead();
                        entity.world.removeEntity(entity);
                        stack.damageItem(-1, player);
                        if(!world.isRemote)
                        {
                        	ISuckCannon props = player.getCapability(SuckCannonProvider.SUCK_CANNON_CAP, null);
                            props.pushToStack(entity);
                        }
                    }
                }
            }
        }

		return new ActionResult(EnumActionResult.SUCCESS, stack);
    }

    public boolean onEntitySwing(EntityLivingBase owner, ItemStack stack)
    {
        if(owner instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)owner;
            ISuckCannon props = player.getCapability(SuckCannonProvider.SUCK_CANNON_CAP, null);
            NBTTagCompound compound = props.popStack();
            if(compound == null)
                return false;

            stack.damageItem(1, owner);
            if(owner.world.isRemote)
                return true;
          

            EntityLiving e = (EntityLiving)EntityList.createEntityFromNBT(compound, owner.world);
            if(e != null)
            {
                if(!compound.getBoolean("hadCustomTagName"))
                    e.setCustomNameTag("");
                EntitySuckCannonProj proj = new EntitySuckCannonProj(player.world, player);

                
                //TODO - Fix suck cannon mount
                //e.mountEntity(proj);
                
                proj.setPosition(player.posX, player.posY + 1, player.posZ);
                e.setPosition(player.posX, player.posY + 1, player.posZ);
                owner.world.spawnEntity(proj);
                owner.world.spawnEntity(e);
                proj.setOwnerID(owner.getEntityId());
                //TODO - Fix sounds
                //owner.world.playSoundAtEntity(owner, "rcmod:SuckCannonShot", 1.0f, 1.0f);
            }
        }
        return true;
    }

    public void customKnockBack(EntityLivingBase par1Entity, float par2, double par3, double par4, double par5)
    {
        if(par1Entity.world.rand.nextDouble() >= par1Entity.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getAttributeValue())
        {
            par1Entity.isAirBorne = true;
            float f1 = MathHelper.sqrt(par3 * par3 + par5 * par5);
            float f2 = 0.4F;
            par1Entity.motionX /= 2.0D;
            par1Entity.motionY /= 2.0D;
            par1Entity.motionZ /= 2.0D;
            par1Entity.motionX -= par3 / (double)f1 * (double)f2;
            par1Entity.motionY -= par4 / (double)f1 * (double)f2;
            par1Entity.motionZ -= par5 / (double)f1 * (double)f2;

            if(par1Entity.motionY > 0.4000000059604645D)
            {
                par1Entity.motionY = 0.4000000059604645D;
            }
        }
    }
}
