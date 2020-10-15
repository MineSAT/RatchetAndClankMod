package com.gugu42.rcmod.common.handler;

import com.gugu42.rcmod.common.RcItems;
import com.gugu42.rcmod.common.capabilities.IRcModCapability;
import com.gugu42.rcmod.common.capabilities.RcModCapability;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class RcTickHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START)
        {
            Vector3d motion = event.player.getMotion();
            if (!event.player.isOnGround() && motion.getY() < 0.0d)
            {
                Optional<IRcModCapability> capa = event.player.getCapability(RcModCapability.RCMOD_CAP).resolve();
                if (capa.isPresent()) {
                    IRcModCapability capability = capa.get();
                    if (capability.isUsingHelipack()) {
                        event.player.setMotion(motion.getX(), motion.getY() * 0.7d, motion.getZ());
                        event.player.fallDistance = 0.0f;
                    }
                }
            }
        }

        if (event.phase == TickEvent.Phase.END)
        {
            World world = event.player.world;

            List<ItemEntity> entities = world.getEntitiesWithinAABB(ItemEntity.class,
                    new AxisAlignedBB(
                            new BlockPos(event.player.getPosX() - 16.0D, event.player.getPosY() - 16.0D, event.player.getPosZ() - 16.0D),
                            new BlockPos(event.player.getPosX() + 16.0D, event.player.getPosY() + 16.0D, event.player.getPosZ() + 16.0D)));

            for (int i = 0; i < entities.size(); i++) {
                updateItemEntity(entities.get(i));
            }
        }
    }

    private static void updateItemEntity(ItemEntity entity) {
        double closeness = 16.0D;
        PlayerEntity player = entity.world.getClosestPlayer(entity, closeness);
        if(entity.getAge() > 15)
        {
            if ((player != null) && entity.getItem().getItem() == RcItems.BOLT.get())
            {
                Vector3d vector3d = new Vector3d(player.getPosX() - entity.getPosX(), player.getPosY() + (double)player.getEyeHeight() / 2.0D - entity.getPosY(), player.getPosZ() - entity.getPosZ());
                double d1 = vector3d.lengthSquared();
                if (d1 < 128.0D) {
                    double d2 = 1.0D - Math.sqrt(d1) / 7.0D;

                    if (entity.getMotion().getY() == 0.0f) {
                        entity.setMotion(entity.getMotion().add(0.1f - entity.world.rand.nextFloat() * 0.2f, 0.4f, 0.1f - entity.world.rand.nextFloat() * 0.2f));
                    } else {
                        entity.setMotion(entity.getMotion().add(vector3d.normalize().scale(d2 * d2 * 1.5D)));
                    }
                }
            }
        }
    }
}
