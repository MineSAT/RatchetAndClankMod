package com.gugu42.rcmod.common.network;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.common.network.packets.BoltSyncPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class RcNetwork {
    public static final String PROTOCOL_VERSION = "1";

    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(RcMod.MOD_ID, "channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void registerPackets() {
        CHANNEL.messageBuilder(BoltSyncPacket.class, 0)
            .encoder(BoltSyncPacket::encode)
            .decoder(BoltSyncPacket::decode)
            .consumer(BoltSyncPacket::handle)
            .add();
    }
}
