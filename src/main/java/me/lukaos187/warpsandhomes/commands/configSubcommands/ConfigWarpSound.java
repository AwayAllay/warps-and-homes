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
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConfigWarpSound extends ConfigCommandTemplate{
    public ConfigWarpSound(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "warping-sound",
                "Animation set",
                "Something went horrible wrong..."
        );
    }

    @Override
    public String getName() {
        return "setWarpSound";
    }

    @Override
    public String getDescription() {
        return "Sets the warp-sound to the given one.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig setWarpSound <sound>";
    }

    @Override
    public void perform(String[] args, CommandSender sender) {

        if (args.length > 1) {

            Sound teleportSound;
            String soundS = args[1];
            if (soundS == null){
                teleportSound = Sound.ENTITY_ENDERMAN_TELEPORT;
                sender.sendMessage(ChatColor.RED + "[WarpsAndHomes] Incorrect sound!");
            }else {
                try {
                    teleportSound = Sound.valueOf(soundS.toUpperCase());
                }catch (IllegalArgumentException e){
                    sender.sendMessage(ChatColor.RED + "[WarpsAndHomes] Incorrect sound!");
                    return;
                }
            }

            WarpsAndHomes.getPlugin().getConfig().set("warping-sound", teleportSound.toString());
            WarpsAndHomes.getPlugin().saveConfig();

            sender.sendMessage("Warp-sound is now set to sound " + ChatColor.AQUA + teleportSound);

        } else {
            sender.sendMessage(ChatColor.RED + "Please provide a sound!");
        }

    }

    @Override
    public List<String> getArgs(int argsLength) {
        if (argsLength == 1) {
            return new ArrayList<>(Arrays.asList("setWarpSound"));
        } else if (argsLength == 2) {
            List<String> soundList = Arrays.stream(Sound.values())
                    .map(Enum::name)
                    .collect(Collectors.toList());
            return soundList;
        }
        return null;
    }
}
