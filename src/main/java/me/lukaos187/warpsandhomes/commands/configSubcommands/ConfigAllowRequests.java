package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigAllowRequests extends ConfigCommandTemplate{
    public ConfigAllowRequests(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-requests",
                "Allow-warp-requests is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-requests is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowRequests";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players request the ownership of a warp.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowRequests <true/false>";
    }
}
