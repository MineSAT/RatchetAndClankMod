package com.gugu42.rcmod.common.capabilities;

import com.gugu42.rcmod.common.network.RcNetwork;
import com.gugu42.rcmod.common.network.packets.BoltSyncPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;

public class RcModCapability implements IRcModCapability {

    @CapabilityInject(IRcModCapability.class)
    public static final Capability<IRcModCapability> RCMOD_CAP = null;

    private int bolts;
    public boolean isUsingHelipack = true;

    @Override
    public int getBolts() {
        return bolts;
    }

    @Override
    public void addBolts(int amount) {
        this.bolts += amount;
    }

    @Override
    public void setBolts(int amount) {
        this.bolts = amount;
    }

    @Override
    public void sync(PlayerEntity player) {
        if (!player.world.isRemote) {
            RcNetwork.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new BoltSyncPacket(this.bolts));
        }
    }

    @Override
    public boolean isUsingHelipack() {
        return isUsingHelipack;
    }

    @Override
    public void setUsingHelipack(boolean using) {
        this.isUsingHelipack = using;
    }

    public RcModCapability() {
        this.bolts = 0;
    }

    public static class RcModCapabilityStorage implements Capability.IStorage<IRcModCapability> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IRcModCapability> capability, IRcModCapability instance, Direction side) {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("bolts", instance.getBolts());
            return tag;
        }

        @Override
        public void readNBT(Capability<IRcModCapability> capability, IRcModCapability instance, Direction side, INBT nbt) {
            if(nbt instanceof CompoundNBT) {
                CompoundNBT tag = (CompoundNBT)nbt;
                instance.setBolts(tag.getInt("bolts"));
            }
        }
    }

    public static class RcModCapabilityFactory implements Callable<IRcModCapability> {
        @Override
        public IRcModCapability call() throws Exception {
            return new RcModCapability();
        }
    }
}
