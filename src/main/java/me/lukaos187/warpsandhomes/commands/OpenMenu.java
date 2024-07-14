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
package me.lukaos187.warpsandhomes.commands;
//FIXME TRANSLATIONS NEEDED
import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.guis.MainMenu;
import me.lukaos187.warpsandhomes.guis.WarpMenu;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OpenMenu implements TabExecutor {

    private final WarpFile warpFile;

    public OpenMenu(WarpFile warpFile) {
        this.warpFile = warpFile;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)){
            sender.sendMessage("You have to be a player to open a menu!");
            return true;
        }
        if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-gui-per-chat")){
            sender.sendMessage(ChatColor.GRAY + "This was disabled by an operator.");
            return true;
        }
        new MainMenu(player, warpFile).open();
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1){
            return new ArrayList<>(List.of("warpMenu"));
        }
        return null;
    }
}
