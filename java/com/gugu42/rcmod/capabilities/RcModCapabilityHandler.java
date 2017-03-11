package com.gugu42.rcmod.capabilities;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.capabilities.bolt.BoltProvider;
import com.gugu42.rcmod.capabilities.suckcannon.SuckCannonProvider;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RcModCapabilityHandler {

	public static final ResourceLocation BOLT_CAPA = new ResourceLocation(RcMod.MODID, "bolt");
	public static final ResourceLocation SUCK_CANNON_CAPA = new ResourceLocation(RcMod.MODID, "suckcannon");
	
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent.Entity event)
    {
        if (!(event.getEntity() instanceof EntityPlayer)) return;

        event.addCapability(BOLT_CAPA, new BoltProvider());
        event.addCapability(SUCK_CANNON_CAPA, new SuckCannonProvider());
    }
	
}
