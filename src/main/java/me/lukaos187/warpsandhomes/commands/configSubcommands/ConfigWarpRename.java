package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpRename extends ConfigCommandTemplate{
    public ConfigWarpRename(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-renaming",
                "Allow-warp-renaming is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-renaming is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowRenaming";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to rename their warps.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowRenaming <true/false>";
    }
}
