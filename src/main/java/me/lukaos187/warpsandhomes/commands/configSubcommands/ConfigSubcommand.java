package me.lukaos187.warpsandhomes.commands.configSubcommands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface ConfigSubcommand {

    String getName();
    String getDescription();
    String getUsage();
    void perform(String[] args, CommandSender sender);
    List<String> getArgs(int argsLength);
}
