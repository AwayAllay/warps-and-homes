package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigCrossdimensionalWarping extends ConfigCommandTemplate{
    public ConfigCrossdimensionalWarping(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-crossdimentional-warping",
                "Crossdimensional-warping is " + ChatColor.GREEN + "activated",
                "Crossdimensional-warping is " + ChatColor.RED + "disabled");
    }

    @Override
    public String getName() {
        return "crossdimensional-warping";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players warp to warps that are in another dimension.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig crossdimensional-warping <true/false>";
    }
}
