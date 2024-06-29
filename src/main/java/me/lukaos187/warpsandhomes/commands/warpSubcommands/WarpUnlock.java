package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpUnlock implements Subcommand{

    private final WarpFile warpFile;

    public WarpUnlock(WarpFile warpFile){
        this.warpFile = warpFile;
    }

    @Override
    public String getName() {
        return "unlock";
    }

    @Override
    public String getDescription() {
        return "Sets the given warp to public.";
    }

    @Override
    public String getUsage() {
        return "/warp unlock <name>";
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
                player.sendMessage(ChatColor.RED + "Warps can only be locked by their owner.");
                player.sendMessage("Ask " + ChatColor.AQUA + warp.getOwner().getName() + ChatColor.RESET + " to unlock this" +
                        " warp.");
                return;
            }
            if (!warp.isPrivate()){
                player.sendMessage("This warp is already " + ChatColor.GREEN + "public");
                return;
            }
            warp.setPrivate(false);

            warpFile.saveWarp(warp);
            player.sendMessage(ChatColor.AQUA + warp.getName() + ChatColor.RESET + " is now set to " + ChatColor.GREEN + "public");


        }else {
            player.sendMessage(ChatColor.RED + "Please provide a warp to unlock.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp unlock <name>");
        }
    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1){
            List<String> tab = new ArrayList<>();
            tab.add("unlock");
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
