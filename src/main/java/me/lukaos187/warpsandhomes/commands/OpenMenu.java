package me.lukaos187.warpsandhomes.commands;

import me.lukaos187.warpsandhomes.guis.MainMenu;
import me.lukaos187.warpsandhomes.guis.WarpMenu;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OpenMenu implements TabExecutor {

    private final WarpFile warpFile;

    public OpenMenu(WarpFile warpFile) {
        this.warpFile = warpFile;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)){
            sender.sendMessage("You have to be a player to open a menu!");
            return true;
        }
        new MainMenu(player, warpFile).open();
        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1){
            return new ArrayList<>(List.of("warpMenu"));
        }
        return null;
    }
}
