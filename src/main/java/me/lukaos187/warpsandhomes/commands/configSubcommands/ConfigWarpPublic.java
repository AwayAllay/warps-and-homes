package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpPublic extends ConfigCommandTemplate{
    public ConfigWarpPublic(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-public-warps",
                "Allow-public-warps is now set to " + ChatColor.GREEN + "true",
                "Allow-public-warps is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowPublicWarps";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to have public warps. (Existing warps will not be affected)";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowPublicWarps <true/false>";
    }
}
