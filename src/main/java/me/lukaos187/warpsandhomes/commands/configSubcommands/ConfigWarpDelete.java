package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpDelete extends ConfigCommandTemplate {

    public ConfigWarpDelete(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-deleting",
                "Allow-warp-deleting is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-deleting is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowDeleting";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players delete their warps.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowDeleting <true/false>";
    }
}
