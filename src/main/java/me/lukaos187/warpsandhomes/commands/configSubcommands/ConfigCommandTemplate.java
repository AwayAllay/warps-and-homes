package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ConfigCommandTemplate implements ConfigSubcommand {

    protected final WarpFile warpFile;
    protected final SubcommandAdder subAdder;
    private final String configKey;
    private final String trueMessage;
    private final String falseMessage;

    public ConfigCommandTemplate(WarpFile warpFile, SubcommandAdder subAdder, String configKey, String trueMessage, String falseMessage) {
        this.warpFile = warpFile;
        this.subAdder = subAdder;
        this.configKey = configKey;
        this.trueMessage = trueMessage;
        this.falseMessage = falseMessage;
    }

    public void defaultPerform(String[] args, CommandSender sender) {

        if (args.length > 1) {

            if (args[1].equalsIgnoreCase("true")) {

                if (test(true, sender))
                    return;

                WarpsAndHomes.getPlugin().getConfig().set(configKey, true);
                WarpsAndHomes.getPlugin().saveConfig();

                subAdder.reloadSubs(warpFile);

                sender.sendMessage(trueMessage);

            } else if (args[1].equalsIgnoreCase("false")) {

                if (test(false, sender))
                    return;

                WarpsAndHomes.getPlugin().getConfig().set(configKey, false);
                WarpsAndHomes.getPlugin().saveConfig();

                subAdder.reloadSubs(warpFile);

                sender.sendMessage(falseMessage);
            } else {
                sendUsageMessage(sender);
            }

        } else {
            sendUsageMessage(sender);
        }
    }

    private boolean test(boolean b, CommandSender sender) {

        if (configKey.equalsIgnoreCase("allow-private-warps") && !b &&
                !WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")){
            sender.sendMessage(ChatColor.RED + "You can not disable both private and public warps.");
            return true;
        }
        if (configKey.equalsIgnoreCase("allow-public-warps") && !b &&
                !WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps")){
            sender.sendMessage(ChatColor.RED + "You can not disable both private and public warps.");
            return true;
        }
        if (WarpsAndHomes.getPlugin().getConfig().getBoolean(configKey) == b) {
            sender.sendMessage(ChatColor.RED + configKey + " is already set to " + b + "!");
            return true;
        }
        return false;
    }

    private void sendUsageMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "Please provide true/false!");
        sender.sendMessage("Use:" + ChatColor.AQUA + " " + getUsage());
    }

    @Override
    public List<String> getArgs(int argsLength) {

        if (argsLength == 2) {
            return new ArrayList<>(Arrays.asList("true", "false"));
        }
        return null;
    }

    @Override
    public void perform(String[] args, CommandSender sender) {
        defaultPerform(args, sender);
    }
}
