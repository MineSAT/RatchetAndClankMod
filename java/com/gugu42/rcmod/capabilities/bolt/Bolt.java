package com.gugu42.rcmod.capabilities.bolt;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.network.packets.PacketBolts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class Bolt implements IBolt
{

	private int currentBolt = 0;
	private int maxBolts = 999999999;
	
	private EntityPlayerMP player;
	
	@Override
	public boolean consumeBolts(int amount) {
		boolean sufficient = amount <= this.currentBolt;
		
		if (sufficient) {
			this.currentBolt -= amount;
		} else {
			return false;
		}

		this.sync();
		
		return sufficient;
	}

	@Override
	public void addBolt(int amount) {
		this.currentBolt += amount;
		this.sync();
	}

	@Override
	public void giveMaxBolts() {
		this.currentBolt = this.maxBolts;
	}

	@Override
	public int getCurrentBolt() {
		return currentBolt;
	}

	@Override
	public void setCurrentBolt(int currentBolt) {
		this.currentBolt = currentBolt;
		this.sync();
	}

	@Override
	public int getMaxBolts() {
		return maxBolts;
	}

	@Override
	public void setMaxBolts(int maxBolts) {
		this.maxBolts = maxBolts;
		this.sync();
	}

	@Override
	public final void sync() {
		
		if(this.player == null)
			return;
		
		PacketBolts packetBolts = new PacketBolts(this.maxBolts, this.currentBolt);		
		RcMod.rcModPacketHandler.sendTo(packetBolts, player);
	}

	@Override
	public void setPlayer(EntityPlayerMP player) {
		this.player = player;
	}

}
