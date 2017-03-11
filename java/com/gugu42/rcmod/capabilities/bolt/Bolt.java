package com.gugu42.rcmod.capabilities.bolt;

public class Bolt implements IBolt
{

	private int currentBolt = 0;
	private int maxBolts = 999999999;
	
	@Override
	public boolean consumeBolts(int amount) {
		boolean sufficient = amount <= this.currentBolt;

		if (sufficient) {
			this.currentBolt -= amount;
		} else {
			return false;
		}

		return sufficient;
	}

	@Override
	public void addBolt(int amount) {
		this.currentBolt += amount;
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
	}

	@Override
	public int getMaxBolts() {
		return maxBolts;
	}

	@Override
	public void setMaxBolts(int maxBolts) {
		this.maxBolts = maxBolts;
	}

}
