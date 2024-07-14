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

import me.lukaos187.warpsandhomes.commands.warpSubcommands.Subcommand;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
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

public class WarpCommandManager implements TabExecutor {
    private final SubcommandAdder subAdder;

    public WarpCommandManager(WarpFile warpFile, SubcommandAdder subAdder) {
        this.subAdder = subAdder;
        subAdder.reloadSubs(warpFile);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        manage(sender, args);

        return true;
    }

    private void manage(final CommandSender sender, final String[] args) {

        // /warp <subcommand> <name> <fu> <bar>

        if (args.length > 0) {

            if (!(sender instanceof Player player)) {
                sender.sendMessage("You have to be a player to use the warp options!");
                return;
            }

            for (Subcommand command : subAdder.getSubcommands()) {
                if (args[0].equalsIgnoreCase(command.getName())) {
                    command.perform(player, args);
                    break;
                }
            }

        } else {
            sendInfo(sender);
        }

    }

    private void sendInfo(final CommandSender sender) {

        String headerColor = "---------------[" + ChatColor.AQUA + "WarpsAndHomes-warp" + ChatColor.WHITE + "]---------------";
        String header = "---------------[WarpsAndHomes-warp]---------------";

        sender.sendMessage(headerColor);
        for (Subcommand command : subAdder.getSubcommands()) {
            sender.sendMessage(ChatColor.AQUA + command.getUsage() + " - " + ChatColor.GOLD + command.getDescription());
        }
        String footer = "-".repeat(header.length());
        sender.sendMessage(footer);
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player))
            return null;


        if (args.length == 1) {

            ArrayList<String> sub = new ArrayList<>();
            subAdder.getSubcommands().forEach(subcommand -> {
                if (subcommand != null && subcommand.getName() != null)
                    sub.add(subcommand.getName());
            });

            return sub;

        } else if (args.length == 2) {

            for (Subcommand s : subAdder.getSubcommands()) {
                if (s != null && s.getName() != null && s.getName().equalsIgnoreCase(args[0]))
                    return s.getArgs(2, player);
            }

        } else if (args.length == 3) {

            for (Subcommand s : subAdder.getSubcommands()) {
                if (s != null && s.getName() != null && s.getName().equalsIgnoreCase(args[0]))
                    return s.getArgs(3, player);
            }

        } else if (args.length == 4) {

            for (Subcommand s : subAdder.getSubcommands()) {
                if (s != null && s.getName() != null && s.getName().equalsIgnoreCase(args[0]))
                    return s.getArgs(4, player);
            }
        }


        return null;
    }
}
