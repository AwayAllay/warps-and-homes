/*
 * WarpsAndHomes - Minecraft plugin
 * Copyright (C) 2024 AwayAllay
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
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

        }
    }
}
