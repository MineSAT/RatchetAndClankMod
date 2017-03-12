package com.gugu42.rcmod.network.packets;

import java.io.UnsupportedEncodingException;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.blocks.RcBlocks;
import com.gugu42.rcmod.shipsys.ShipWaypoint;
import com.gugu42.rcmod.tileentity.TileEntityShip;
import com.gugu42.rcmod.tileentity.TileEntityShipFiller;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class PacketShipTeleportation extends AbstractPacket {

	private String waypointData;
	private int originX;
	private int originY;
	private int originZ;

	public PacketShipTeleportation() {
		waypointData = "";
		originX = 0;
		originY = 0;
		originZ = 0;
	}

	public PacketShipTeleportation(String data, int x, int y, int z) {
		this.waypointData = data;
		this.originX = x;
		this.originY = y;
		this.originZ = z;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		try {
			buffer.writeInt(waypointData.length());
			buffer.writeBytes(waypointData.getBytes("UTF-8"));
			buffer.writeInt(originX);
			buffer.writeInt(originY);
			buffer.writeInt(originZ);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		int dataLength = buffer.readInt();
		ByteBuf textData = buffer.readBytes(dataLength);
		this.originX = buffer.readInt();
		this.originY = buffer.readInt();
		this.originZ = buffer.readInt();
		try {
			waypointData = new String(textData.array(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void handleClientSide(EntityPlayer player) {

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		ShipWaypoint wp = new ShipWaypoint(waypointData);

		player.world.removeTileEntity(new BlockPos(originX, originY, originZ));
		player.world.setBlockState(new BlockPos(originX, originY, originZ), Blocks.AIR.getDefaultState());

		Block bl = player.world.getBlockState(new BlockPos(originX, originY + 1, originZ)).getBlock();
		bl.breakBlock(player.world, new BlockPos(originX, originY + 1, originZ), bl.getDefaultState());

		player.setPositionAndUpdate(wp.posX + 5, wp.posY, wp.posZ);
		World world = player.world;
		int x = wp.posX;
		int y = wp.posY;
		int z = wp.posZ;

		y -= 1;
		if (!world.isRemote) {
			y++;
			TileEntityShipFiller teShipFiller = null;
			int direction = MathHelper
					.floor((double) (player.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
			world.setBlockState(new BlockPos(x, y, z), RcBlocks.SHIP.getDefaultState(), direction);
			TileEntityShip te = (TileEntityShip) world.getTileEntity(new BlockPos(x, y, z));
			te.isLanding = true;
			te.renderY = 540;
			te.pitch = 90.0f;
			te.renderDoorRot = 56.0f;
			
			NBTTagCompound tag = new NBTTagCompound();
			te.writeToNBT(tag);
			
			//TODO - Fix packet sending
			//RcMod.rcModPacketHandler.sendToAll(new S35PacketUpdateTileEntity(x, y, z, 1, tag));
			
			
			boolean shouldPlaceBlock;

			for (int i = x - 2; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					for (int k = z - 2; k < z + 3; k++) {
						shouldPlaceBlock = true;
						if (shouldPlaceBlock) {
							if (world.getBlockState(new BlockPos(i, j, k)).getBlock() == Blocks.AIR) {
								world.setBlockState(new BlockPos(i, j, k), RcBlocks.SHIP_FILLER.getDefaultState(), 0);
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
	}

}
