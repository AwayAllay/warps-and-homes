package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HandoverGUI extends PaginatedPlayerListGUI {

    private final Warp warp;
    public HandoverGUI(Player player, WarpFile warpFile, Warp warp) {
        super(player, warpFile);
        this.warp = warp;
    }

    @Override
    protected List<String> getPlayerHeadLore(Player player) {
        return new ArrayList<>(List.of("Click to hand this ", "warp over to " + player.getName()));
    }

    @Override
    protected void handleBackClick() {
        new WarpOpt(player, warpFile, warp, new ItemStack(Material.IRON_BARS)).open();
    }

    @Override
    protected void handlePlayerHeadClick(String playerName) {
        new ConfirmGUI(player, warpFile, ChatColor.AQUA + "Hand over", warp.getName(), playerName).open();
    }

    @Override
    public String name() {
        return ChatColor.BLUE + "Select new owner";
    }
}
