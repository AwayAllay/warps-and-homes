package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ConfigSetRequestCooldown extends ConfigCommandTemplate{
    public ConfigSetRequestCooldown(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "request-cooldown",
                "",
                "");
    }

    @Override
    public String getName() {
        return "setRequestCooldown";
    }

    @Override
    public String getDescription() {
        return "Sets the cooldown-time of the Request-cooldown.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig setRequestCooldown <time in s>";
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

            WarpsAndHomes.getPlugin().getConfig().set("request-cooldown", cooldown);
            WarpsAndHomes.getPlugin().saveConfig();

            sender.sendMessage("Request-warp-cooldown is now set to " + ChatColor.AQUA + cooldown +
                    ChatColor.RESET + " seconds.");

        }else {
            sender.sendMessage(ChatColor.RED + "Please provide a cooldown-time");
            sender.sendMessage("Use: " + ChatColor.AQUA + getUsage());
        }


    }
}
