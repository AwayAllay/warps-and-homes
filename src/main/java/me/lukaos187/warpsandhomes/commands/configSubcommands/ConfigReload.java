package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ConfigReload extends ConfigCommandTemplate{
    public ConfigReload(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "custom-config-key",
                "Config.yml " + ChatColor.GREEN + "reloaded",
                ChatColor.RED + "Could not reload the config.yml");
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the config.yml.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig reload";
    }

    @Override
    public void perform(String[] args, CommandSender sender) {
        WarpsAndHomes.getPlugin().reloadConfig();
        sender.sendMessage("Config.yml " + ChatColor.GREEN + "reloaded");

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps") && WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")){
            WarpsAndHomes.getPlugin().getConfig().set("allow-private-warps", true);
            WarpsAndHomes.getPlugin().getLogger().info("[WarpsAndHomes] public and private warps are disabled! Enabling private warps!");
        }
    }

}
