package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class OtherPlayersGUI extends PaginatedPlayerListGUI{

    public OtherPlayersGUI(Player player, WarpFile warpFile) {
        super(player, warpFile);
    }

    @Override
    protected List<String> getPlayerHeadLore(Player player) {
        return List.of("Click to see the warps", "of " + player.getName());
    }

    @Override
    protected void handleBackClick() {
        new WarpChoice(player, warpFile).open();
    }

    @Override
    protected void handlePlayerHeadClick(String playerName) {
        new PaginatedWarpsGUI(player, warpFile, Bukkit.getPlayer(playerName)).open();
    }

    @Override
    public String name() {
        return ChatColor.AQUA + "Choose a player";
    }
}
