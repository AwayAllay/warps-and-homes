package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpClear extends ConfigCommandTemplate{
    public ConfigWarpClear(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-clearing",
                "Allow-warp-clearing is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-clearing is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowClearing";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to delete all their warps at once.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowClearing <true/false>";
    }
}
