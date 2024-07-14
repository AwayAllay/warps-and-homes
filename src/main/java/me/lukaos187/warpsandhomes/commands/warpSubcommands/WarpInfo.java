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
import java.util.List;
import java.util.Objects;

public class WarpInfo implements Subcommand{

    private final WarpFile warpFile;

    public WarpInfo(WarpFile warpFile){
        this.warpFile = warpFile;
    }


    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Displays some information for the given warp.";
    }

    @Override
    public String getUsage() {
        return "/warp info <warp-name>";
    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1){
            List<String> tab = new ArrayList<>();
            tab.add("info");
            return tab;
        } else if (argsLength == 2) {
            List<String> warps = warpFile.getWarps(player);

            if (warps == null)
                return null;

            return warps;
        }

        return null;
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length == 1){
            player.sendMessage(ChatColor.RED + "Please provide a warp-name!");
            player.sendMessage(ChatColor.GREEN + "Example: " + ChatColor.AQUA + "/warp info testwarp");
            return;
        }

        Warp warp = warpFile.getWarp(args[1]);

        if (warp == null){
            player.sendMessage(ChatColor.RED + "This warp does not exist!");
            return;
        }
        if (warp.isPrivate() && !warp.getOwner().getName().equalsIgnoreCase(player.getName())){
            player.sendMessage("This warp is " + ChatColor.AQUA + "private");
            return;
        }

        sendMessage(player, warp);
    }

    private void sendMessage(final Player player, final Warp warp) {

        String headerColor = "---------------[" + ChatColor.AQUA + warp.getName() + ChatColor.WHITE + "]---------------";
        //To get the actual length of the String without ColorCodes
        String header = "---------------[" + warp.getName() + "]---------------";

        player.sendMessage(headerColor);
        player.sendMessage(ChatColor.AQUA + "Warp-Location: " + ChatColor.GOLD + Math.round(warp.getLocation().getX()) + ", " +
                Math.round(warp.getLocation().getY()) + ", " + Math.round(warp.getLocation().getZ()));
        player.sendMessage(ChatColor.AQUA + "World: " + ChatColor.GOLD + Objects.requireNonNull(warp.getLocation().getWorld()).getEnvironment());
        player.sendMessage(ChatColor.AQUA + "Description: " + ChatColor.GOLD + warp.getDescription());
        player.sendMessage(ChatColor.AQUA + "Owner: " + ChatColor.GOLD + warp.getOwner().getName());

        String footer = "-".repeat(header.length());
        player.sendMessage(footer);

    }
}
