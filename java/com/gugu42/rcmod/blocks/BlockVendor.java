package com.gugu42.rcmod.blocks;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.client.ClientProxy;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVendor extends Block {

	public BlockVendor(Material par2Material) {
		super(par2Material);
		this.setCreativeTab(RcMod.rcTab);
	}

	public boolean onBlockActivated(World par1World, int x, int y,
			int z, EntityPlayer par5EntityPlayer, int par6, float par7,
			float par8, float par9) {

		TileEntity tileEntity = par1World.getTileEntity(new BlockPos(x, y, z));
		if (tileEntity == null || par5EntityPlayer.isSneaking()) {
			return false;
		}
		
		par5EntityPlayer.openGui(RcMod.instance, 0, par1World, x, y, z);
		//par1World.playSoundAtEntity(par5EntityPlayer, "rcmod:MenuOpen", 1.0F, 1.0F);
		return true;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityVendor();
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.renderInventoryTESRId;
	}
}
