package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigDamageCooldownPlayer extends ConfigCommandTemplate{
    public ConfigDamageCooldownPlayer(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "damage-cooldown-player",
                 "Player-damage-cooldown is now " + ChatColor.GREEN + "activated",
                "Player-damage-cooldown is now " + ChatColor.RED + "disabled");
    }

    @Override
    public String getName() {
        return "damageCooldownPlayer";
    }

    @Override
    public String getDescription() {
        return "En-/Disables if the damage-warp-cooldown only will be activated when a player hits another player.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig damageCooldownPlayer <true/false>";
    }
}
