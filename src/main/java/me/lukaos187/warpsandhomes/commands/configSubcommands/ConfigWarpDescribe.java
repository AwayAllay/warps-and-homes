package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpDescribe extends ConfigCommandTemplate {

    public ConfigWarpDescribe(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-describing",
                "Allow-warp-describing is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-describing is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowDescribing";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players describe their warps again after initializing.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowDescribing <true/false>";
    }
}
