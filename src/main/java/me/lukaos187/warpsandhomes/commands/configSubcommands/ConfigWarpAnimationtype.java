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
//FIXME TRANSLATIONS NEEDED
import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import me.lukaos187.warpsandhomes.util.translationUtils.Translator;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigWarpAnimationtype extends ConfigCommandTemplate {
    public ConfigWarpAnimationtype(WarpFile warpFile, SubcommandAdder subAdder, Translator translator) {
        super(warpFile, subAdder, "animation-type",
                "Animation set",
                "Something went horrible wrong..."
        );
    }

    @Override
    public String getName() {
        return "setWarpAnimation";
    }

    @Override
    public String getDescription() {
        return "Sets the warp-animation to the given one.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig setWarpAnimation <animation-type>";
    }

    @Override
    public void perform(String[] args, CommandSender sender) {

        if (args.length > 1) {

            String type = args[1];
            if (type == null) {
                sender.sendMessage(ChatColor.RED + "No valid animation-type!");
                return;
            }

            WarpsAndHomes.getPlugin().getConfig().set("animation-type", type.toUpperCase());
            WarpsAndHomes.getPlugin().saveConfig();

            sender.sendMessage("Warp-animation is now set to animation " + ChatColor.AQUA + type.toUpperCase());

        } else {
            sender.sendMessage(ChatColor.RED + "Please provide a animation-type!");
        }

    }

    @Override
    public List<String> getArgs(int argsLength) {
        if (argsLength == 1) {
            return new ArrayList<>(Arrays.asList("setWarpAnimation"));
        } else if (argsLength == 2) {
            //TODO IMPORTANT FOR NEW ANIMATIONS!
            return new ArrayList<>(Arrays.asList("ENDERMAN", "SPHERE", "PORTAL"));
        }
        return null;
    }
}
