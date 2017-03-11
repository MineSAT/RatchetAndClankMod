package com.gugu42.rcmod.blocks;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.shipsys.ShipSystem;
import com.gugu42.rcmod.tileentity.TileEntityShipPlatform;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockShipPlatform extends Block {

	public BlockShipPlatform() {
		super(Material.IRON);
	}

	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (playerIn.isSneaking()) {
			return false;
		}

		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TileEntityShipPlatform) {
			TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
			if (tileEntity.ownerName == "") {
				tileEntity.setOwnerName(playerIn.getDisplayName().getFormattedText());
			}
		}

		playerIn.openGui(RcMod.instance, 4, worldIn, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityShipPlatform();
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
		if (!world.isRemote) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof TileEntityShipPlatform) {
				TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
				
				
				//TODO - Fix chat
				if (player.getDisplayName().equals(tileEntity.ownerName) && ShipSystem.isNameTaken(tileEntity.wpName)) {
					ShipSystem.removeWaypoint(tileEntity.wpName);
					//player.addChatMessage(new ChatComponentText("Your waypoint \"" + tileEntity.wpName + "\" was deleted !"));
					return world.setBlockToAir(pos);
				} else if (player.capabilities.isCreativeMode){
					//player.addChatMessage(new ChatComponentText("This waypoint does not belong to you ! Since you're in creative, I let you break it. Please tell " + tileEntity.ownerName + " that you did it."));
					return world.setBlockToAir(pos);
				} else {
					//player.addChatMessage(new ChatComponentText("This waypoint does not belong to you !!!"));
					return false;
				}
			} else {
				return world.setBlockToAir(pos);
			}
		} else {
			return false;
		}
		
		
	}

	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);

		TileEntity te = worldIn.getTileEntity(pos);
		if (te instanceof TileEntityShipPlatform) {
			TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
			tileEntity.setOwnerName("");
			tileEntity.setWpName("Waypoint name");
		}

	}

}
