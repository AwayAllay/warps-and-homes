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
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigCrossdimensionalWarping extends ConfigCommandTemplate{
    public ConfigCrossdimensionalWarping(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-crossdimentional-warping",
                "Crossdimensional-warping is " + ChatColor.GREEN + "activated",
                "Crossdimensional-warping is " + ChatColor.RED + "disabled");
    }

    @Override
    public String getName() {
        return "crossdimensional-warping";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players warp to warps that are in another dimension.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig crossdimensional-warping <true/false>";
    }
}
