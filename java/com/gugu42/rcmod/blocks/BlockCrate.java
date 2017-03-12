package com.gugu42.rcmod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.items.RcItems;


public class BlockCrate extends BlockFalling {
	
	public BlockCrate(Material par2Material) {
		super(par2Material);
		setCreativeTab(RcMod.rcTab);
	}
	
	@Override
	public int quantityDropped(Random par1Random)
    {
		int quantity = par1Random.nextInt(7);
        return quantity;
    }

	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return RcItems.bolt;
    }
}
