package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigDamageCooldown extends ConfigCommandTemplate{
    public ConfigDamageCooldown(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "has-damage-warp-cooldown",
                "Damage-cooldown is now " + ChatColor.GREEN + "activated",
                "Damage-cooldown is now " + ChatColor.RED + "disabled");
    }

    @Override
    public String getName() {
        return "damageCooldown";
    }

    @Override
    public String getDescription() {
        return "En-/Disables the damage-warp-cooldown.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig damageCooldown <true/false>";
    }
}
