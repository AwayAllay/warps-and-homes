package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigHasRequestCooldown extends ConfigCommandTemplate{
    public ConfigHasRequestCooldown(WarpFile warpFile, SubcommandAdder subAdder) {
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
