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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpList implements Subcommand {

    private final WarpFile warpFile;

    public WarpList(WarpFile warpFile){
        this.warpFile = warpFile;
    }


    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Lists all the warps.";
    }

    @Override
    public String getUsage() {
        return "/warp list <player-name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length == 2) {

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                player.sendMessage(ChatColor.RED + "This player is not online!");
                return;
            }

            listWarps(player, target);

        } else {
            listWarps(player, null);
        }


    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1) {
            List<String> tab = new ArrayList<>();
            tab.add("list");
            return tab;
        }

        return null;
    }


    private boolean checkForWarps(final Player target) {

        if (warpFile.getWarps(target) == null)
            return false;

        return true;
    }

    private void listWarps(final Player player, final Player target) {

        Player actualTarget = (target == null) ? player : target;

        List<String> warps = warpFile.getWarps(actualTarget);

        if (!checkForWarps(actualTarget)){
            if (actualTarget == player) {
                player.sendMessage(ChatColor.RED + "You do not have any warps yet.");
            } else {
                player.sendMessage(ChatColor.RED + "This player does not have any warps yet.");
            }
            return;
        }

        String header = "---------------[" + ((actualTarget.equals(player)) ? "MY WARPS" : actualTarget.getName() + " WARPS") + "]---------------";
        String headerColor = "---------------[" + ChatColor.AQUA + ((actualTarget == player) ? "MY WARPS" : actualTarget.getName() + " WARPS") + ChatColor.WHITE + "]---------------";

        player.sendMessage(headerColor);
        warps.forEach(s -> {
            Warp warp = warpFile.getWarp(s);

            if (warp == null) return;

            boolean isPrivate = warp.isPrivate();
            String privateStatus = isPrivate ? ChatColor.RED + "private" : ChatColor.GREEN + "public";

            if (isPrivate && actualTarget != player) {
                player.sendMessage(ChatColor.DARK_GRAY + "(" + privateStatus + ChatColor.DARK_GRAY + ") "
                        + ChatColor.GRAY + "This warp is private!");
            } else {
                player.sendMessage(ChatColor.DARK_GRAY + "(" + privateStatus + ChatColor.DARK_GRAY + ") "
                        + ChatColor.WHITE + "Warp: " + ChatColor.AQUA + s + ChatColor.WHITE + " - "
                        + ChatColor.AQUA + warp.getDescription());
            }
        });

        String footer = "-".repeat(header.length());
        player.sendMessage(footer);
    }
}
