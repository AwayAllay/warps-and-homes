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
//FIXME TRANSLATIONS NEEDED
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HandoverGUI extends PaginatedPlayerListGUI {

    private final Warp warp;
    public HandoverGUI(Player player, WarpFile warpFile, Warp warp) {
        super(player, warpFile);
        this.warp = warp;
    }

    @Override
    protected List<String> getPlayerHeadLore(Player player) {
        return new ArrayList<>(List.of("Click to hand this ", "warp over to " + player.getName()));
    }

    @Override
    protected void handleBackClick() {
        new WarpOpt(player, warpFile, warp, new ItemStack(Material.IRON_BARS)).open();
    }

    @Override
    protected void handlePlayerHeadClick(String playerName) {
        new ConfirmGUI(player, warpFile, ChatColor.AQUA + "Hand over", warp.getName(), playerName).open();
    }

    @Override
    public String name() {
        return ChatColor.BLUE + "Select new owner";
    }
}
