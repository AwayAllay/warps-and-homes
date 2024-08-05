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
import org.bukkit.Color;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigWarpParticleColor extends ConfigCommandTemplate {
    public ConfigWarpParticleColor(WarpFile warpFile, SubcommandAdder subAdder, Translator translator) {
        super(warpFile, subAdder, "particle-colour",
                "",
                "");
    }

    @Override
    public String getName() {
        return "setParticleColour";
    }

    @Override
    public String getDescription() {
        return "Sets the colour of the warp animation to the given one.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig setParticleColour <colour>";
    }

    @Override
    public void perform(String[] args, CommandSender sender) {

        if (args.length > 1) {

            String colour = args[1];

            WarpsAndHomes.getPlugin().getConfig().set("particle-colour", colour.toUpperCase());
            sender.sendMessage("Particle-colour set to " + ChatColor.AQUA + colour.toUpperCase());
            WarpsAndHomes.getPlugin().saveConfig();

        } else {
            sender.sendMessage(ChatColor.RED + "please provide a colour.");
            sender.sendMessage("Use: " + ChatColor.AQUA + getUsage());
        }

    }

    @Override
    public List<String> getArgs(int argsLength) {

        if (argsLength == 1) {
            return new ArrayList<>(Arrays.asList("setParticleColour"));
        } else if (argsLength == 2) {
            List<String> colours = new ArrayList<>();
            colours.add("PLAYER");
            Field[] fields = Color.class.getFields();

            for (Field field : fields) {
                if (field.getType() == Color.class) {
                    colours.add(field.getName());
                }
            }
            return colours;
        }

        return null;
    }
}
