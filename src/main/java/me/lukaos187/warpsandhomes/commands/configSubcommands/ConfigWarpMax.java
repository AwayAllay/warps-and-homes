package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpMax extends ConfigCommandTemplate{
    public ConfigWarpMax(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "enable-max-player-warps",
                "Maximum for warps " + ChatColor.GREEN + "enabled",
                "Maximum for warps " + ChatColor.RED + "disabled");
    }

    @Override
    public String getName() {
        return "hasMaxWarps";
    }

    @Override
    public String getDescription() {
        return "En-/Disables a maximum amount of warps, players can have.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig hasMaxWarps <true/false>";
    }
}
