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

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpPrivate extends ConfigCommandTemplate{
    public ConfigWarpPrivate(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-private-warps",
                "Allow-private-warps is now set to " + ChatColor.GREEN + "true",
                "Allow-private-warps is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowPrivateWarps";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to have private warps. (Existing warps will not be affected)";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowPrivateWarps <true/false>";
    }
}
