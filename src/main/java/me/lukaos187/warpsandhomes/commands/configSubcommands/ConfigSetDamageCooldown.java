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

public class ConfigSetDamageCooldown extends ConfigCommandTemplate{
    public ConfigSetDamageCooldown(WarpFile warpFile, SubcommandAdder subAdder, Translator translator) {
        super(warpFile, subAdder, "damage-warp-cooldown",
                "",
                "");
    }

    @Override
    public String getName() {
        return "setDamageCooldown";
    }

    @Override
    public String getDescription() {
        return "Sets the cooldown-time of the Damage-cooldown.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig setDamageCooldown <time in s>";
    }

    @Override
    public void perform(String[] args, CommandSender sender){

        if (args.length > 1){

            long cooldown;

            try {
                cooldown = Long.parseLong(args[1]);
            }catch (NumberFormatException e){
                sender.sendMessage(ChatColor.RED + "Please provide a number for the cooldown.");
                return;
            }
            if (cooldown <= 0){
                sender.sendMessage(ChatColor.RED + "Please provide a number greater than 0.");
                return;
            }

            WarpsAndHomes.getPlugin().getConfig().set("damage-warp-cooldown", cooldown);
            WarpsAndHomes.getPlugin().saveConfig();

            sender.sendMessage("Damage-warp-cooldown is now set to " + ChatColor.AQUA + cooldown +
                    ChatColor.RESET + " seconds.");

        }else {
            sender.sendMessage(ChatColor.RED + "Please provide a cooldown-time");
            sender.sendMessage("Use: " + ChatColor.AQUA + getUsage());
        }


    }
}
