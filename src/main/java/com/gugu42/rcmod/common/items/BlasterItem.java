package com.gugu42.rcmod.common.items;

import com.gugu42.rcmod.common.entities.projectiles.BlasterProjectileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public class BlasterItem extends RcWeaponItem {
    public BlasterItem(Properties properties) {
        super(properties);
        this.maxAmmo = 200;
        properties.maxDamage(this.maxAmmo);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        super.onItemRightClick(world, player, hand);

        ItemStack par1ItemStack = player.getHeldItem(hand);

        if (maxAmmo - par1ItemStack.getDamage() > 0) {
            if (!world.isRemote) {
                BlasterProjectileEntity bullet = new BlasterProjectileEntity(world, player);
                Vector3d vector3d = player.getLook(1.0F);
                Vector3f vector3f = new Vector3f(vector3d);
                bullet.shoot(vector3f.getX(), vector3f.getY(), vector3f.getZ(), 1.5f, 0);
                world.addEntity(bullet);
                par1ItemStack.damageItem(1, player, null);
            }
        }

        return new ActionResult(ActionResultType.PASS, par1ItemStack);
    }
}
