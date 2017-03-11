package com.gugu42.rcmod.capabilities.bolt;

public interface IBolt {

	public boolean consumeBolts(int amount);
	
	public void addBolt(int amount);
	
	public void giveMaxBolts();
	
	public int getCurrentBolt();
	
	public void setCurrentBolt(int currentBolt);
	
	public int getMaxBolts();
	
	public void setMaxBolts(int maxBolts);
}
