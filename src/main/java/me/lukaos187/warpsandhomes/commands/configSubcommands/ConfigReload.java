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
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ConfigReload extends ConfigCommandTemplate{
    public ConfigReload(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "custom-config-key",
                "Config.yml " + ChatColor.GREEN + "reloaded",
                ChatColor.RED + "Could not reload the config.yml");
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the config.yml.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig reload";
    }

    @Override
    public void perform(String[] args, CommandSender sender) {
        WarpsAndHomes.getPlugin().reloadConfig();
        sender.sendMessage("Config.yml " + ChatColor.GREEN + "reloaded");

        if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps") && !WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")){
            WarpsAndHomes.getPlugin().getConfig().set("allow-private-warps", true);
            WarpsAndHomes.getPlugin().getLogger().info("[WarpsAndHomes] public and private warps are disabled! Enabling private warps!");
            WarpsAndHomes.getPlugin().saveConfig();
        }
    }

}
