package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpHandOver extends ConfigCommandTemplate{
    public ConfigWarpHandOver(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-hand-overing",
                "Allow-warp-hand-overing is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-hand-overing is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowHandover";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players give other players the ownership of a warps.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowHandover <true/false>";
    }
}
