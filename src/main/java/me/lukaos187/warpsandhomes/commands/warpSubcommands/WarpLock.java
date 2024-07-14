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

public class WarpLock implements Subcommand{

    private final WarpFile warpFile;

    public WarpLock(WarpFile warpFile){
        this.warpFile = warpFile;
    }


    @Override
    public String getName() {
        return "lock";
    }

    @Override
    public String getDescription() {
        return "Sets the given warp to private.";
    }

    @Override
    public String getUsage() {
        return "/warp lock <name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 1){

            Warp warp = warpFile.getWarp(args[1]);

            if (!allIsCorrect(player, warp))
                return;

            warp.setPrivate(true);

            warpFile.saveWarp(warp);
            player.sendMessage(ChatColor.AQUA + warp.getName() + ChatColor.RESET + " is now set to " + ChatColor.RED + "private");


        }else {
            player.sendMessage(ChatColor.RED + "Please provide a warp to lock.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp lock <name>");
        }


    }

    private boolean allIsCorrect(final Player player, final Warp warp) {

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
        if (warp.isPrivate()){
            player.sendMessage("This warp is already " + ChatColor.RED + "private");
            return false;
        }

        return true;
    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1){
            List<String> tab = new ArrayList<>();
            tab.add("lock");
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
