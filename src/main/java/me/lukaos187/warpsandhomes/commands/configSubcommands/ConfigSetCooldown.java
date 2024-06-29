package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ConfigSetCooldown extends ConfigCommandTemplate{
    public ConfigSetCooldown(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "cooldown-time",
                "",
                "");
    }

    @Override
    public String getName() {
        return "setWarpCooldown";
    }

    @Override
    public String getDescription() {
        return "Sets the cooldown-time of the Warp-cooldown.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig setWarpCooldown <time in s>";
    }

    @Override
    public void perform(String[] args, CommandSender sender){

        if (args.length > 1){

            long cooldown;

            try {
                cooldown = Long.parseLong(args[1]);
            }catch (NumberFormatException e){
                sender.sendMessage(ChatColor.RED + "Please provide a number for the cooldown.");
                return;
            }
            if (cooldown <= 0){
                sender.sendMessage(ChatColor.RED + "Please provide a number greater than 0.");
                return;
            }

            WarpsAndHomes.getPlugin().getConfig().set("cooldown-time", cooldown);
            WarpsAndHomes.getPlugin().saveConfig();

            sender.sendMessage("Warp-cooldown is now set to " + ChatColor.AQUA + cooldown +
                    ChatColor.RESET + " seconds.");

        }else {
            sender.sendMessage(ChatColor.RED + "Please provide a cooldown-time");
            sender.sendMessage("Use: " + ChatColor.AQUA + getUsage());
        }


    }
}
