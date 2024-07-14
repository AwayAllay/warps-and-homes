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
//FIXME TRANSLATIONS NEEDED
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
