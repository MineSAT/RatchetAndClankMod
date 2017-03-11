package com.gugu42.rcmod.shipsys;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class ShipWaypointRemoveCommand implements ICommand {

	public List aliases;

	public ShipWaypointRemoveCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("removeRcWaypoint");
	}

	@Override
	public int compareTo(ICommand o) {
		return 0;
	}

	@Override
	public String getName() {
		return "removeRcWaypoint";
	}

	@Override
	public String getUsage(ICommandSender p_71518_1_) {
		return "removeRcWaypoint <Name>";
	}

	@Override
	public List getAliases() {
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) {

		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;

			if (args.length == 1) {
				String name = args[0];
				if (ShipSystem.isNameTaken(name)) {
					ShipSystem.removeWaypoint(name);
					//TODO - Fix message sending
					//sender.addChatMessage(new ChatComponentText("This waypoint was successfully removed !."));
				} else {
					//sender.addChatMessage(new ChatComponentText("This waypoint does not exist."));
				}
			} else {
				//sender.addChatMessage(new ChatComponentText("Correct usage : /removeRcWaypoint <Name>"));
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
