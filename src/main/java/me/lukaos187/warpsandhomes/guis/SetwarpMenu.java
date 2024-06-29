package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SetwarpMenu extends WarpMenu{
    public SetwarpMenu(Player player, WarpFile warpFile) {
        super(player, warpFile);
    }

    @Override
    public int slots() {
        return 5*9;
    }

    @Override
    public String name() {
        return ChatColor.DARK_BLUE + "Set warp";
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {

    }

    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public void fill() {

    }
}
