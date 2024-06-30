package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.commands.warpSubcommands.DeleteWarp;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpHandover;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpUpdate;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfirmGUI extends WarpMenu {

    private final String menuName;
    private final String warpName;
    private final String newOwner;

    public ConfirmGUI(Player player, WarpFile warpFile, String menuName, String warpName) {
        super(player, warpFile);
        this.menuName = menuName;
        this.warpName = warpName;
        this.newOwner = null;
    }
    public ConfirmGUI(Player player, WarpFile warpFile, String menuName, String warpName, String newOwner) {
        super(player, warpFile);
        this.menuName = menuName;
        this.warpName = warpName;
        this.newOwner = newOwner;
    }

    @Override
    public int slots() {
        return 27;
    }

    @Override
    public String name() {
        return menuName;
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {

        e.setCancelled(true);

        if (e.getCurrentItem().getType().equals(Material.LIME_BANNER)) {

            confirmAction();

        }
        new MyWarps(player, warpFile).open();

    }

    private void confirmAction() {

        if (name().equalsIgnoreCase(ChatColor.GRAY + "Update-Warp")) {
            //Update:
            String[] args = {"update", warpName};
            new WarpUpdate(warpFile).perform(player, args);
        } else if (name().equalsIgnoreCase(ChatColor.RED + "Delete-Warp")) {
            //Delete:
            String[] args = {"delete", warpName};
            new DeleteWarp(warpFile).perform(player, args);
        } else if (name().equalsIgnoreCase(ChatColor.AQUA + "Hand over")) {
            //Hand over:
            String[]args = {"hand-over", warpName, newOwner};
            new WarpHandover(warpFile).perform(player, args);
        }

    }

    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public void fill() {

        ItemStack confirm = new ItemStack(Material.LIME_BANNER);
        ItemMeta confirmMeta = confirm.getItemMeta();
        Objects.requireNonNull(confirmMeta).setDisplayName(ChatColor.GREEN + "Confirm");
        confirmMeta.setLore(new ArrayList<>(List.of("Click here to confirm ", "this action.")));
        confirm.setItemMeta(confirmMeta);

        ItemStack cancel = new ItemStack(Material.RED_BANNER);
        ItemMeta cancelMeta = cancel.getItemMeta();
        Objects.requireNonNull(cancelMeta).setDisplayName(ChatColor.RED + "Cancel");
        cancelMeta.setLore(new ArrayList<>(List.of("Click here to cancel ", "this action.")));
        cancel.setItemMeta(cancelMeta);

        inventory.setItem(12, confirm);
        inventory.setItem(14, cancel);
    }
}
