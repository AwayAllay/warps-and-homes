package me.lukaos187.warpsandhomes.listener;

import me.lukaos187.warpsandhomes.guis.WarpMenu;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class OnInventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.getCurrentItem() == null)
            return;
        if (event.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)){
            event.setCancelled(true);
            return;
        }

        InventoryHolder inventoryHolder = event.getInventory().getHolder();

        if (inventoryHolder instanceof WarpMenu){

            WarpMenu menu = (WarpMenu) inventoryHolder;
            menu.manageClicks(event);

        } /*else if (inventoryHolder instanceof WarpTextMenu) {

            WarpTextMenu warpTextMenu = (WarpTextMenu) inventoryHolder;
            warpTextMenu.manageClicks(event);

        }*/
    }
}
