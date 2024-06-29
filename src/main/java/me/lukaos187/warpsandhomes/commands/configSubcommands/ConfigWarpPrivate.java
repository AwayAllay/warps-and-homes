package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpPrivate extends ConfigCommandTemplate{
    public ConfigWarpPrivate(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-private-warps",
                "Allow-private-warps is now set to " + ChatColor.GREEN + "true",
                "Allow-private-warps is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowPrivateWarps";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to have private warps. (Existing warps will not be affected)";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowPrivateWarps <true/false>";
    }
}
