package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarpClear implements Subcommand{

    private final WarpFile warpFile;

    public WarpClear(WarpFile warpFile) {
        this.warpFile = warpFile;
    }
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "Deletes all your warps.";
    }

    @Override
    public String getUsage() {
        return "/warp clear";
    }

    @Override
    public void perform(Player player, String[] args) {

        List<String> warps = warpFile.getWarps(player);
        if (!(warps == null) && !warps.isEmpty()){

            warps.forEach(s -> {

                Warp warp = warpFile.getWarp(s);
                if (warp == null)
                    return;
                player.sendMessage(ChatColor.RED + "Cleared " + ChatColor.RESET + "warp " + ChatColor.RED + warp.getName());
                warpFile.removeWarpFromAll(warp, player);

            });

            player.sendMessage(ChatColor.GREEN + "Finished clearing!");
        }



    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {
        if (argsLength == 1){
            return new ArrayList<>(Arrays.asList("clear"));
        }
        return null;
    }
}
