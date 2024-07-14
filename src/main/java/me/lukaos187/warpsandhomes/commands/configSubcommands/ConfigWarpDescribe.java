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

public class ConfigWarpDescribe extends ConfigCommandTemplate {

    public ConfigWarpDescribe(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-describing",
                "Allow-warp-describing is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-describing is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowDescribing";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players describe their warps again after initializing.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowDescribing <true/false>";
    }
}
