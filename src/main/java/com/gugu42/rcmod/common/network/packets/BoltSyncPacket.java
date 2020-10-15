package com.gugu42.rcmod.common.network.packets;

import com.gugu42.rcmod.common.capabilities.IRcModCapability;
import com.gugu42.rcmod.common.capabilities.RcModCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class BoltSyncPacket {
    public int value;

    public BoltSyncPacket(int val) {
        this.value = val;
    }

    public static void encode(BoltSyncPacket packet, PacketBuffer buffer) {
        buffer.writeInt(packet.value);
    }

    public static BoltSyncPacket decode(PacketBuffer buffer) {
        int value = buffer.readInt();
        return new BoltSyncPacket(value);
    }

    public static void handle(BoltSyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            if (player.getCapability(RcModCapability.RCMOD_CAP).resolve().isPresent()) {
                IRcModCapability capability = player.getCapability(RcModCapability.RCMOD_CAP).resolve().get();
                capability.setBolts(packet.value);
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
