package com.gugu42.rcmod.shipsys;

import java.util.ArrayList;
import java.util.List;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.network.packets.PacketNewWaypoint;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class ShipWaypointCommand implements ICommand {

	public List aliases;

	public ShipWaypointCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("addRcWaypoint");
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "addRcWaypoint";
	}

	@Override
	public String getUsage(ICommandSender p_71518_1_) {
		return "addRcWaypoint <Name> <PosX> <PosY> <PosZ> <Is Private ( true / false )>";
	}

	@Override
	public List getAliases() {
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;

			if (args.length == 5) {
				String name = args[0];
				int posX = Integer.parseInt(args[1]);
				int posY = Integer.parseInt(args[2]);
				int posZ = Integer.parseInt(args[3]);
				boolean isPrivate = Boolean.parseBoolean(args[4]);

				String wpData = new ShipWaypoint(name, posX, posY,
						posZ, player.getDisplayName().getFormattedText(), isPrivate).toString();
				
				if (!ShipSystem.isNameTaken(name)) {
					RcMod.rcModPacketHandler.sendToAll(new PacketNewWaypoint(wpData));
				} else {
					//TODO - Fix message sending
					//sender.sendMessage(new ChatComponentText(
					//		"Name is already taken !"));
				}
			} else {
				//TODO - Fix message sending
				//sender.addChatMessage(new ChatComponentText(
				//		"Correct usage : /addRcWaypoint <Name> <PosX> <PosY> <PosZ> <Is Private ( true / false )>"));
			}

		}

	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender p_71519_1_) {
		return true;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {

		return false;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		// TODO Auto-generated method stub
		return null;
	}

}
