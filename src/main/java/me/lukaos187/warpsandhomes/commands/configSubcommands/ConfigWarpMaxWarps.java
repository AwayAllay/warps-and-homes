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
package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigWarpMaxWarps extends ConfigCommandTemplate{
    public ConfigWarpMaxWarps(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "max-player-warps",
                "Bla set",
                "Something went horrible wrong..."
        );
    }

    @Override
    public String getName() {
        return "setMaxWarps";
    }

    @Override
    public String getDescription() {
        return "Sets the maximum warp-amount a player can have.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig setMaxWarps <maximum>";
    }

    @Override
    public void perform(String[] args, CommandSender sender) {

        if (args.length > 1) {
            long amount;

            if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("enable-max-player-warps")){
                sender.sendMessage("enable-max-player-warps is disabled in the config.yml!" +
                        " So you can not set a warp-maximum!");
                return;
            }

            try {
                amount = Long.parseLong(args[1]);
            }catch (NumberFormatException e){
                sender.sendMessage(ChatColor.RED + "Please provide a max-warp-number!");
                return;
            }
            if (amount <= 0){
                sender.sendMessage(ChatColor.RED + "Please do not provide negative numbers or 0!");
                return;
            }

            WarpsAndHomes.getPlugin().getConfig().set("max-player-warps", amount);
            WarpsAndHomes.getPlugin().saveConfig();
            sender.sendMessage("New warp-amount is " + ChatColor.AQUA + amount);

        } else {
            sender.sendMessage(ChatColor.RED + "Please provide a maximum amount!");
        }

    }

    @Override
    public List<String> getArgs(int argsLength) {
        if (argsLength == 1) {
            return new ArrayList<>(Arrays.asList("setMaxWarps"));
        }
        return null;
    }
}
