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
import me.lukaos187.warpsandhomes.util.translationUtils.Translator;
import org.bukkit.ChatColor;

public class ConfigHasRequestCooldown extends ConfigCommandTemplate{
    public ConfigHasRequestCooldown(WarpFile warpFile, SubcommandAdder subAdder, Translator translator) {
        super(warpFile, subAdder, "has-request-cooldown",
                "Request-cooldown is now " + ChatColor.GREEN + "enabled",
                "Request-cooldown is now " + ChatColor.RED + "disabled");
    }

    @Override
    public String getName() {
        return "hasRequestCooldown";
    }

    @Override
    public String getDescription() {
        return "En-/Disabled a cooldown for requesting warps.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig hasRequestCooldown <true/false>";
    }
}
