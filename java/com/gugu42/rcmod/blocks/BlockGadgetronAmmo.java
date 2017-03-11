package com.gugu42.rcmod.blocks;

import java.util.Random;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.items.RcItems;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockGadgetronAmmo extends BlockFalling {

	public static boolean fallInstantly;


	public BlockGadgetronAmmo(Material par2Material) {
		super(par2Material);
		setCreativeTab(RcMod.rcTab);
	}

	@Override
	public int quantityDropped(Random par1Random) {
		int quantity = par1Random.nextInt(2) + 1;
		return quantity;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		switch (rand.nextInt(8)) {  //This has to change once you implement more weapons
		case 0:
			return RcItems.ammoBlaster;
		case 1:
			return RcItems.ammoBombGlove;
		case 2:
			return RcItems.ammoDecoyGlove;
		case 3:
			return RcItems.ammoDevastator;
//		case 4:
//			return RcItems.ammoDroneDevice;    These weapons are not yet implemented
//		case 5:
//			return RcItems.ammoGloveOfDoom;
		case 4:
			return RcItems.ammoMineGlove;
		case 5:
			return RcItems.ammoPyrocitor;
		case 6:
			return RcItems.ammoRyno;
//		case 9:
//			return RcItems.ammoTeslaClaw;      Not yet implemented, shouldn't drop ammo
		case 7:
			return RcItems.ammoVisibombGun;
		default:
			return RcItems.ammoBlaster;
		}
	}
}
