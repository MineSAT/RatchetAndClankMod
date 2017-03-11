package com.gugu42.rcmod;

import java.util.ArrayList;
import java.util.List;

import com.gugu42.rcmod.handler.ExtendedPlayerTooltips;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class ResetTooltipsCommand implements ICommand {
	private List aliases;

	public ResetTooltipsCommand() {
		this.aliases = new ArrayList();
		this.aliases.add("resetTooltips");
	}

	@Override
	public int compareTo(ICommand arg0) {
		return 0;
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return "resetTooltips";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "resetTooltips";
	}

	@Override
	public List<String> getAliases() {
		return this.aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) sender;

			ExtendedPlayerTooltips props = ExtendedPlayerTooltips.get(player);
			if (props != null) {
				props.resetAllTips();
			}
		}
		
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return null;
	}
}
