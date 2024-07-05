package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigAllowPrivateRequests extends ConfigCommandTemplate{
    public ConfigAllowPrivateRequests(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-private-warp-requests",
                "Allow-private-warp-requests is now set to " + ChatColor.GREEN + "true",
                "Allow-private-warp-requests is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowPrivateRequests";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to request the ownership of a other player´s private warp.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowPrivateRequests <true/false>";
    }
}
