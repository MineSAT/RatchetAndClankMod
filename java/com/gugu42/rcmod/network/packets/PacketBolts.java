package com.gugu42.rcmod.network.packets;

import com.gugu42.rcmod.capabilities.bolt.BoltProvider;
import com.gugu42.rcmod.capabilities.bolt.IBolt;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public class PacketBolts extends AbstractPacket{

	private int maxBolts, bolts;
	
	public PacketBolts(){
		
	}
	
	public PacketBolts(int maxBolts, int bolts){
		this.maxBolts = maxBolts;
		this.bolts = bolts;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(maxBolts);
		buffer.writeInt(bolts);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.maxBolts = buffer.readInt();
		this.bolts = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		IBolt props = player.getCapability(BoltProvider.BOLT_CAP, null);
		props.setMaxBolts(this.maxBolts);
		props.setCurrentBolt(this.bolts);
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		IBolt props = player.getCapability(BoltProvider.BOLT_CAP, null);
		props.setMaxBolts(this.maxBolts);
		props.setCurrentBolt(this.bolts);
	}

}
