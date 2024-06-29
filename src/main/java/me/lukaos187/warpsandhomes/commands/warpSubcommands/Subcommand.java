package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import org.bukkit.entity.Player;

import java.util.List;

public interface Subcommand {

    String getName();
    String getDescription();
    String getUsage();
    void perform(Player player, String[] args);
    List<String> getArgs(int argsLength, Player player);

}
