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
package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class OtherPlayersGUI extends PaginatedPlayerListGUI{

    public OtherPlayersGUI(Player player, WarpFile warpFile) {
        super(player, warpFile);
    }

    @Override
    protected List<String> getPlayerHeadLore(Player player) {
        return List.of("Click to see the warps", "of " + player.getName());
    }

    @Override
    protected void handleBackClick() {
        new WarpChoice(player, warpFile).open();
    }

    @Override
    protected void handlePlayerHeadClick(String playerName) {
        new PaginatedWarpsGUI(player, warpFile, Bukkit.getPlayer(playerName)).open();
    }

    @Override
    public String name() {
        return ChatColor.AQUA + "Choose a player";
    }
}
