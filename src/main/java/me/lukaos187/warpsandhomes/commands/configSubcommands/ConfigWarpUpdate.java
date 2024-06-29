package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpUpdate extends ConfigCommandTemplate{
    public ConfigWarpUpdate(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-updating",
                "Allow-warp-updating is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-updating is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowUpdating";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to set the location of their warps new.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowUpdating <true/false>";
    }
}
