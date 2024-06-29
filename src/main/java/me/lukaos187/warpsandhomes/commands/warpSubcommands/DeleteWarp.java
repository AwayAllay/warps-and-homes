package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DeleteWarp implements Subcommand {

    private final WarpFile warpFile;

    public DeleteWarp(WarpFile warpFile){
        this.warpFile = warpFile;
    }



    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public String getDescription() {
        return "Deletes the given warp.";
    }

    @Override
    public String getUsage() {
        return "/warp delete <name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 1) {

            Warp warp = warpFile.getWarp(args[1]);

            if (warp == null){
                player.sendMessage(ChatColor.RED + "This warp does not exist.");
                return;
            }
            if (!warp.getOwner().equals(player)){
                player.sendMessage(ChatColor.RED + "Warps can only be deleted by their owner.");
                player.sendMessage("To delete this warp you have to ask " + ChatColor.AQUA + warp.getOwner().getName());
                return;
            }

            deleteWarp(player, warp);

        } else {
            player.sendMessage(ChatColor.RED + "Please provide a warp to delete.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp delete <name>");
        }


    }

    private void deleteWarp(final Player player, final Warp warp) {

        warpFile.removeWarpFromAll(warp, player);
        player.sendMessage(ChatColor.RED + "Removed " + ChatColor.RESET + "warp " + ChatColor.AQUA + warp.getName());

    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1){
            List<String> tab = new ArrayList<>();
            tab.add("delete");
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
