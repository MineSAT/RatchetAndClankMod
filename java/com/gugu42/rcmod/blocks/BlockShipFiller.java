package com.gugu42.rcmod.blocks;

import com.gugu42.rcmod.tileentity.TileEntityShipFiller;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockShipFiller extends Block implements ITileEntityProvider {

	public BlockShipFiller(Material p_i45394_1_) {
		super(p_i45394_1_);
		// TODO Auto-generated constructor stub
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		TileEntityShipFiller tileEntity = (TileEntityShipFiller) worldIn.getTileEntity(pos);
		if (tileEntity == null || playerIn.isSneaking()) {
			return false;
		}

		tileEntity.activated(playerIn, worldIn);

		return true;

	}

	@Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (worldIn.getTileEntity(pos) instanceof TileEntityShipFiller) {
			TileEntityShipFiller tileEntity = (TileEntityShipFiller) worldIn
					.getTileEntity(pos);
			if (tileEntity != null) {
				worldIn.setBlockToAir(new BlockPos(tileEntity.primary_x, tileEntity.primary_y,
						tileEntity.primary_z));
				worldIn.removeTileEntity(new BlockPos(tileEntity.primary_x,
						tileEntity.primary_y, tileEntity.primary_z));
			}

			worldIn.removeTileEntity(pos);
		}
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
		TileEntityShipFiller tileEntity = (TileEntityShipFiller) world
				.getTileEntity(pos);
		if (tileEntity != null) {
			if (world.getTileEntity(new BlockPos(tileEntity.primary_x, tileEntity.primary_y,
					tileEntity.primary_z)) == null) {
				tileEntity.getWorld().setBlockToAir(pos);
				tileEntity.getWorld().removeTileEntity(pos);
			}
		}
	}

	@Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
		return false;
		// return true;
	}

	@Override
    public boolean isOpaqueCube(IBlockState state)
    {
		return false;
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityShipFiller();
	}

}
