package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigAllowAnimations extends ConfigCommandTemplate{
    public ConfigAllowAnimations(WarpFile warpFile, SubcommandAdder subAdder) {

        super(warpFile, subAdder, "allow-warp-animations",
                "Warp-animations are now " + ChatColor.GREEN + "enabled",
                "Warp-animations are now " + ChatColor.RED + "disabled");
    }

    @Override
    public String getName() {
        return "allowWarpAnimation";
    }

    @Override
    public String getDescription() {
        return "En-/Disables the warping animation.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowWarpAnimation <true/false>";
    }
}
