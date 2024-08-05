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
import me.lukaos187.warpsandhomes.commands.configSubcommands.*;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import me.lukaos187.warpsandhomes.util.translationUtils.Translator;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommandManager implements TabExecutor {

    private final List<ConfigSubcommand> subcommands = new ArrayList<>();
    private final WarpFile warpFile;
    private final SubcommandAdder subAdder;
    private final Translator translator;

    public ConfigCommandManager(WarpFile warpFile, SubcommandAdder subAdder, Translator translator) {
        this.warpFile = warpFile;
        this.subAdder = subAdder;
        this.translator = translator;
        addConfigSubcommands();
    }

    private void addConfigSubcommands() {
        subcommands.add(new ConfigWarpDelete(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpDescribe(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpHandOver(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpInfo(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpList(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpRename(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpUpdate(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpPublic(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpPrivate(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpClear(warpFile, subAdder, translator));
        subcommands.add(new ConfigReload(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpParticleColor(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpAnimationtype(warpFile, subAdder, translator));
        subcommands.add(new ConfigAllowAnimations(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpMax(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpMaxWarps(warpFile, subAdder, translator));
        subcommands.add(new ConfigDamageCooldown(warpFile, subAdder, translator));
        subcommands.add(new ConfigDamageCooldownPlayer(warpFile, subAdder, translator));
        subcommands.add(new ConfigSetDamageCooldown(warpFile, subAdder, translator));
        subcommands.add(new ConfigCrossdimensionalWarping(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpCooldown(warpFile, subAdder, translator));
        subcommands.add(new ConfigSetCooldown(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpSound(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpRequest(warpFile, subAdder, translator));
        subcommands.add(new ConfigWarpRequest(warpFile, subAdder, translator));
        subcommands.add(new ConfigAllowPrivateRequests(warpFile, subAdder, translator));
        subcommands.add(new ConfigHasRequestCooldown(warpFile, subAdder, translator));
        subcommands.add(new ConfigSetRequestCooldown(warpFile, subAdder, translator));
        subcommands.add(new ConfigAllowGUIChat(warpFile, subAdder, translator));
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        manage(sender, args);

        return true;
    }

    private void manage(final CommandSender sender, final String[] args) {

        // /warp <subcommand> <name> <fu> <bar>
        if (!sender.hasPermission("warpsandhomes.admin")){
            sender.sendMessage("You do not have permission to execute this command!");
            return;
        }

        if (args.length > 0) {

            if (sender instanceof CommandBlock) {
                return;
            }

            for (ConfigSubcommand command : subcommands) {
                if (args[0].equalsIgnoreCase(command.getName())) {
                    command.perform(args, sender);
                    break;
                }
            }

        } else {
            sendInfo(sender);
            sendMissingArgsMessage(sender);
        }

    }

    private void sendMissingArgsMessage(final CommandSender sender) {
        sender.sendMessage("Please provide more arguments to use that command!");
    }

    private void sendInfo(final CommandSender sender) {

        String header = "---------------[WarpsAndHomes-wahConfig]---------------";
        String coloredH = "---------------[" + ChatColor.AQUA + "WarpsAndHomes-wahConfig" + ChatColor.RESET + "]---------------";

        sender.sendMessage(coloredH);
        for (ConfigSubcommand command : subcommands) {
            sender.sendMessage(ChatColor.AQUA + command.getUsage() + " - " + ChatColor.GOLD + command.getDescription());
        }
        String footer = "-".repeat(header.length());
        sender.sendMessage(footer);
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof CommandBlock)
            return null;


        if (args.length == 1) {

            ArrayList<String> sub = new ArrayList<>();
            subcommands.forEach(subcommand -> {
                if (subcommand != null && subcommand.getName() != null)
                    sub.add(subcommand.getName());
            });

            return sub;

        } else if (args.length == 2) {

            for (ConfigSubcommand s : subcommands) {
                if (s != null && s.getName() != null && s.getName().equalsIgnoreCase(args[0]))
                    return s.getArgs(2);
            }

        } else if (args.length == 3) {

            for (ConfigSubcommand s : subcommands) {
                if (s != null && s.getName() != null && s.getName().equalsIgnoreCase(args[0]))
                    return s.getArgs(3);
            }

        } else if (args.length == 4) {

            for (ConfigSubcommand s : subcommands) {
                if (s != null && s.getName() != null && s.getName().equalsIgnoreCase(args[0]))
                    return s.getArgs(4);
            }
        }


        return null;
    }
}
