package com.gugu42.rcmod.items;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.blocks.RcBlocks;
import com.gugu42.rcmod.tileentity.TileEntityShipFiller;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemShip extends Item {

	public ItemShip() {

	}

	//TODO - Fix itemShip
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player,
			World world, int x, int y, int z, int side, float xOffset,
			float yOffset, float zOffset) {
		if (!player.capabilities.isCreativeMode) {
			itemStack.shrink(1);
		}

		if (!world.isRemote) {
			y++;
			TileEntityShipFiller teShipFiller = null;
			int direction = MathHelper
					.floor((double) (player.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			world.setBlockState(new BlockPos(x, y, z), RcBlocks.SHIP.getDefaultState());
			boolean shouldPlaceBlock;

			for (int i = x - 2; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					for (int k = z - 2; k < z + 3; k++) {
						shouldPlaceBlock = true;
						if (shouldPlaceBlock) {
							if (world.isAirBlock(new BlockPos(i, j, k))) {
								world.setBlockState(new BlockPos(i, j, k), RcBlocks.SHIP_FILLER.getDefaultState(), 2);
								teShipFiller = (TileEntityShipFiller) world
										.getTileEntity(new BlockPos(i, j, k));
								if (teShipFiller != null) {
									teShipFiller.primary_x = x;
									teShipFiller.primary_y = y;
									teShipFiller.primary_z = z;
									
								} else {
									System.out.println("TileEntity is null !");
								}
							}
						}
					}
				}
			}

		}
		return false;
	}

	// This function rotates the relative coordinates accordingly to the given
	// direction
	public int[] rotXZByDir(int x, int y, int z, int dir) {
		if (dir == 0) {
			return new int[] { x, y, z };
		} else if (dir == 1) {
			return new int[] { -z, y, x };
		} else if (dir == 2) {
			return new int[] { -x, y, -z };
		} else {
			return new int[] { z, y, -x };
		}
	}

}
