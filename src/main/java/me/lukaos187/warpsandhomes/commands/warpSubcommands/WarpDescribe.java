package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpDescribe implements Subcommand{

    private final WarpFile warpFile;

    public WarpDescribe(WarpFile warpFile){
        this.warpFile = warpFile;
    }


    @Override
    public String getName() {
        return "describe";
    }

    @Override
    public String getDescription() {
        return "Sets the given description to the given warp.";
    }

    @Override
    public String getUsage() {
        return "/warp describe <name>";
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

            setDesc(warp, player, args);

        }else {
            player.sendMessage(ChatColor.RED + "Please provide a warp to describe.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp describe <name> <description>");
        }

    }

    private void setDesc(final Warp warp, final Player player, final String[] args) {

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            sb.append(args[i]).append(" ");
        }
        String description = sb.toString().trim();

        if (description.equalsIgnoreCase(""))
            description = ChatColor.GRAY + "No description specified";

        warp.setDescription(description);

        warpFile.saveWarp(warp);
        player.sendMessage("The description of " + ChatColor.AQUA + warp.getName() + ChatColor.RESET + " is now set to "
                + ChatColor.AQUA + warp.getDescription());


    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1){
            List<String> tab = new ArrayList<>();
            tab.add("describe");
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
