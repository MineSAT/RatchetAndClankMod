package com.gugu42.rcmod.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTaunter extends ItemRcWeap
{

    public ItemTaunter()
    {
        super();
        this.useAmmo = false;
        this.weaponName = "taunter";
        this.hasEquipSound = true;
    }


	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		super.onItemRightClick(world, player, hand);
		
		ItemStack par1ItemStack = player.getHeldItem(hand);
		
		world.playSound(null, new BlockPos(player.posX, player.posY, player.posZ), new SoundEvent(new ResourceLocation("rcmod:TaunterSound")), SoundCategory.NEUTRAL, 1.0f, 1.0f);


        if(!world.isRemote)
        {
            float size = 32f;
            List<Entity> entities = world
                    .getEntitiesWithinAABBExcludingEntity(player,
                            player.getCollisionBoundingBox().expand(size, size,
                                    size));
            for (Entity e : entities)
            {
                if(e instanceof EntityCreature)
                {
                    EntityCreature mob = (EntityCreature) e;
                    //TODO - Fix taunter aggro
                    //mob.setPathToEntity(mob.world.getPathToEntity(mob,
                    //       player, size, true, true, true, true));
                }
                if(e instanceof EntityLiving)
                {
                    EntityLiving living = (EntityLiving) e;
                    living.getNavigator().clearPathEntity();
                    living.getNavigator().setPath(
                            living.getNavigator().getPathToEntityLiving(
                                    player), 1.0);
                }
            }
        }
        
		return new ActionResult(EnumActionResult.SUCCESS, par1ItemStack);
    }
    
    @Override
	public void onUpdate(ItemStack stack, World w, Entity ent, int i,
			boolean flag) {
		super.onUpdate(stack, w, ent, i, flag);
	}
}
