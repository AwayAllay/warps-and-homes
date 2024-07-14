/*
 * WarpsAndHomes - Minecraft plugin
 * Copyright (C) 2024 AwayAllay
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpRename implements Subcommand{

    private final WarpFile warpFile;

    public WarpRename(WarpFile warpFile){
        this.warpFile = warpFile;
    }

    @Override
    public String getName() {
        return "rename";
    }

    @Override
    public String getDescription() {
        return "Renames the warp to the given name.";
    }

    @Override
    public String getUsage() {
        return "/warp rename <name> <new-name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 1){
            if (!isAllCorrect(args, player))
                return;

            Warp warp = warpFile.getWarp(args[1]);
            String newName = args[2];


            warpFile.removeWarpFromAll(warp, player);

            warp.setName(newName);

            warpFile.saveWarp(warp);
            warpFile.addWarpToPDC(warp, player);

            player.sendMessage(ChatColor.AQUA + args[1] + ChatColor.RESET + " is now set to " + ChatColor.AQUA + newName);


        }else {
            player.sendMessage(ChatColor.RED + "Please provide a warp to rename.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp rename <name> <new-name>");
        }
    }

    private boolean isAllCorrect(final String[] args, final Player player) {

        Warp warp = warpFile.getWarp(args[1]);

        if (args.length < 3){
            player.sendMessage(ChatColor.RED + "Please provide a new name.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp rename <name> <new-name>");
            return false;
        }

        if (warpFile.getWarp(args[2]) != null){
            player.sendMessage(ChatColor.RED + "This warp already exists. Please choose a different name.");
            return false;
        }

        if (warp == null){
            player.sendMessage(ChatColor.RED + "This warp does not exist.");
            return false;
        }
        if (!warp.getOwner().equals(player)){
            player.sendMessage(ChatColor.RED + "Warps can only be locked by their owner.");
            player.sendMessage("Ask " + ChatColor.AQUA + warp.getOwner().getName() + ChatColor.RESET + " to lock this" +
                    " warp.");
            return false;
        }

        return true;
    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1){
            List<String> tab = new ArrayList<>();
            tab.add("rename");
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
