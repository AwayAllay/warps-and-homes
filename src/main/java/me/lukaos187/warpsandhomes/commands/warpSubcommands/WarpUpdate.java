package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpUpdate implements Subcommand{

    private final WarpFile warpFile;

    public WarpUpdate(WarpFile warpFile){
        this.warpFile = warpFile;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return "Updates the given warp to your location.";
    }

    @Override
    public String getUsage() {
        return "/warp update <name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 1){

            Warp warp = warpFile.getWarp(args[1]);
            if (warp == null){
                player.sendMessage(ChatColor.RED + "This warp does not exist.");
                return;
            }
            if (!warp.getOwner().equals(player)){
                player.sendMessage(ChatColor.RED + "Warps can only be updated by their owner.");
                player.sendMessage("Ask " + ChatColor.AQUA + warp.getOwner().getName() + ChatColor.RESET + " to " +
                        "update this warp.");
                return;
            }

            Location oldLoc = warp.getLocation();

            warp.setLocation(player.getLocation());

            warpFile.saveWarp(warp);
            player.sendMessage(ChatColor.GREEN + "Updated " + ChatColor.RESET + "warp " + ChatColor.AQUA + warp.getName() +
                    ChatColor.RESET + " from " + ChatColor.GRAY + Math.round(oldLoc.getX()) + ", " + Math.round(oldLoc.getY()) + ", " +
                    Math.round(oldLoc.getZ()) + ChatColor.RESET + " to " + ChatColor.GOLD + Math.round(warp.getLocation().getX()) +
                    ", " + Math.round(warp.getLocation().getY()) + ", " + Math.round(warp.getLocation().getZ()));

        }else {
            player.sendMessage(ChatColor.RED + "Please provide a name for the warp you want to update.");
            player.sendMessage("Use the command like this: " + ChatColor.AQUA + "/warp update <name>");
        }


    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1){
            List<String> tab = new ArrayList<>();
            tab.add("update");
            return tab;
        } else if (argsLength == 2) {
            List<String> warps = warpFile.getWarps(player);

            if (warps == null)
                return null;

            return warps;
        }


        return null;
    }
}
