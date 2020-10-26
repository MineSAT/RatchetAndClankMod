package com.gugu42.rcmod.common.handler;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.common.RcItems;
import com.gugu42.rcmod.common.capabilities.IRcModCapability;
import com.gugu42.rcmod.common.capabilities.RcModCapability;
import com.gugu42.rcmod.common.capabilities.RcModCapabilityProvider;
import com.gugu42.rcmod.common.items.armor.ClankBackpackItem;
import com.gugu42.rcmod.common.network.RcNetwork;
import com.gugu42.rcmod.common.network.packets.BoltSyncPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Optional;

public class RcEventHandler {

    @SubscribeEvent
    public static void attachEntityCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(ResourceLocation.tryCreate("rcmod:rcmodcapa"), new RcModCapabilityProvider());
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event)
    {
        if (event.isWasDeath()) {
            Optional<IRcModCapability> capability = event.getOriginal().getCapability(RcModCapability.RCMOD_CAP).resolve();
            if (capability.isPresent()) {
                IRcModCapability newCapa = capability.get();
                newCapa.setBolts(capability.get().getBolts());
                if (!event.getPlayer().world.isRemote) {
                    newCapa.sync(event.getPlayer());
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinWorldEvent event)
    {
        if (!event.getWorld().isRemote && event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            player.getCapability(RcModCapability.RCMOD_CAP).resolve().ifPresent(iRcModCapability -> iRcModCapability.sync(player));
        }
    }

    @SubscribeEvent
    public static void onItemPickup(EntityItemPickupEvent event) {
        Item item = event.getItem().getItem().getItem();
        PlayerEntity player = event.getPlayer();
        Optional<IRcModCapability> capa = player.getCapability(RcModCapability.RCMOD_CAP).resolve();
        if (capa.isPresent()) {
            IRcModCapability capability = capa.get();
            if (item == RcItems.BOLT.get())
            {
//            event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer, "rcmod:BoltCollect", 0.3f, 1.0f);
                player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), new SoundEvent(new ResourceLocation("rcmod:bolt_collect")), SoundCategory.MASTER, 0.3f, 1.0f);

                if (!event.getPlayer().world.isRemote) {
                    capability.addBolts(25);
                    capability.sync(event.getPlayer());
                }

                event.getItem().remove();
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        if (!(event.getEntity() instanceof PlayerEntity))
            return;

        PlayerEntity player = (PlayerEntity)event.getEntity();

        if (player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof ClankBackpackItem) {
            ClankBackpackItem backpackItem = (ClankBackpackItem)player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();

            double xMot = 0.0d;
            double yMot = 0.0d;
            double zMot = 0.0d;

            if (player.isSneaking()) {
                yMot = backpackItem.getJumpBoostFactor();
            }

            if (player.isSprinting()) {
                xMot = Math.cos(Math.toRadians(player.rotationYawHead + 90.0F)) * backpackItem.getSprintBoostFactor();
                zMot = Math.sin(Math.toRadians(player.rotationYawHead + 90.0F)) * backpackItem.getSprintBoostFactor();
            }

            player.setMotion((player.getMotion().add(xMot, yMot, zMot)));
        }
    }

    @SubscribeEvent
    public static void onRenderLiving(RenderLivingEvent.Post event) {
//        if (event.getEntity().world.isRemote) {
//            event.getMatrixStack().rotate(event.getRenderer().getRenderManager().getCameraOrientation());
//            event.getMatrixStack().translate(0, event.getEntity().getSize(Pose.STANDING).height / 2, 0);
//            Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(RcItems.RETICLE.get()), ItemCameraTransforms.TransformType.GUI, 15728880, 0, event.getMatrixStack(), event.getBuffers());
//        }
    }
}
