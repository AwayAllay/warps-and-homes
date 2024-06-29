package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpInfo extends ConfigCommandTemplate{

    public ConfigWarpInfo(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-informing",
                "Allow-warp-informing is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-informing is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowInforming";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to get information about warps.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowInforming <true/false>";
    }
}
