package com.gugu42.rcmod;

import net.minecraft.block.SoundType;
import net.minecraft.util.SoundEvent;

public class RcCustomStepSound extends SoundType {

	private SoundEvent placeSound;
	private SoundEvent stepSound;

	public RcCustomStepSound(String par1Str, float par2, float par3,
			SoundEvent placeSound, SoundEvent stepSound) {
		super(par2, par3, placeSound, stepSound, placeSound, stepSound, stepSound);
		this.placeSound = placeSound;
		this.stepSound = stepSound;
	}

}
