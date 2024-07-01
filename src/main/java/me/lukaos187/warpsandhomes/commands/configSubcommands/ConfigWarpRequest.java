package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpRequest extends ConfigCommandTemplate{
    public ConfigWarpRequest(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-requests",
                "Allow-warp-requests is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-requests is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowWarpRequests";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to request the ownership of a other player´s warp.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowWarpRequests <true/false>";
    }
}
