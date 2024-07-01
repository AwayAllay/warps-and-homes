package me.lukaos187.warpsandhomes.commands;

import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AcceptRequest extends BukkitCommand {

    private final WarpFile warpFile;

    protected AcceptRequest(@NotNull String name, WarpFile warpFile) {
        super(name);
        this.warpFile = warpFile;
    }
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {

        if (!(sender instanceof Player player)){
            sender.sendMessage(ChatColor.RED + "You have to be a player to execute this!");
            return true;
        }

        Player oldOwner = player;
        Player requester = Bukkit.getPlayer(args[0]);
        Warp warp = warpFile.getWarp(args[1]);

        if (requester == null || warp == null) {
            sender.sendMessage(ChatColor.RED + "Please try again.");
            return true;
        }

        //TODO finish me
        player.sendMessage("Accept");

        return false;
    }
}
