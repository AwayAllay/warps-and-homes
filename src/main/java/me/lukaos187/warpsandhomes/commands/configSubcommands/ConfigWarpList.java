package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigWarpList extends ConfigCommandTemplate{
    public ConfigWarpList(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-listing",
                "Allow-warp-listing is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-listing is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowListing";
    }

    @Override
    public String getDescription() {
        return "Allows/Not allows players to list all their warps and all the public ones of other players.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowListing <true/false>";
    }
}
