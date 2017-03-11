package com.gugu42.rcmod.network.packets;

import java.io.UnsupportedEncodingException;

import com.gugu42.rcmod.shipsys.ShipSystem;
import com.gugu42.rcmod.tileentity.TileEntityShipPlatform;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class PacketEditWaypoint extends AbstractPacket {

	public String wpData;

	public PacketEditWaypoint() {

	}

	public PacketEditWaypoint(String wpData) {
		this.wpData = wpData;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		try {
			buffer.writeInt(wpData.length());
			buffer.writeBytes(wpData.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		int dataLength = buffer.readInt();
		ByteBuf textData = buffer.readBytes(dataLength);

		try {
			wpData = new String(textData.array(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		String[] infos = wpData.split(" ");
		if (infos.length == 5) {
			TileEntity te = player.world.getTileEntity(new BlockPos(Integer.parseInt(infos[2]), Integer.parseInt(infos[3]), Integer.parseInt(infos[4])));
			if (te instanceof TileEntityShipPlatform) {
				TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
				tileEntity.setWpName(infos[1]);
				ShipSystem.renameWaypoint(infos[0], infos[1]);
			}
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		String[] infos = wpData.split(" ");
		if (infos.length == 5) {
			if (ShipSystem.isNameTaken(infos[1])) {
				//TODO - Fix chat
				//player.addChatMessage(new ChatComponentText("This new name is already taken !"));
			} else {
				TileEntity te = player.world.getTileEntity(new BlockPos(Integer.parseInt(infos[2]), Integer.parseInt(infos[3]), Integer.parseInt(infos[4])));
				if (te instanceof TileEntityShipPlatform) {
					TileEntityShipPlatform tileEntity = (TileEntityShipPlatform) te;
					tileEntity.setWpName(infos[1]);
					ShipSystem.renameWaypoint(infos[0], infos[1]);
					//TODO - Fix chat
					//player.addChatMessage(new ChatComponentText("Waypoint was renamed !"));
				}
			}
		}
	}

}