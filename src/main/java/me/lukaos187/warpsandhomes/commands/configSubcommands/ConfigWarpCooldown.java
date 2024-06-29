package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpCooldown extends ConfigCommandTemplate{
    public ConfigWarpCooldown(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "warp-cooldown",
                "Warping-cooldown is now " + ChatColor.GREEN + "activated",
                "Warping-cooldown is now " + ChatColor.RED + "disabled");
    }

    @Override
    public String getName() {
        return "warping-cooldown";
    }

    @Override
    public String getDescription() {
        return "En-/Disables the warp-cooldown.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig warping-cooldown <true/false>";
    }
}
