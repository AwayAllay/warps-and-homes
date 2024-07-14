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
package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.HeadGetter;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarpChoice extends WarpMenu{
    //FIXME TRANSLATIONS NEEDED
    public WarpChoice(Player player, WarpFile warpFile) {
        super(player, warpFile);
    }

    @Override
    public int slots() {
        return 3 * 9;
    }

    @Override
    public String name() {
        return ChatColor.BLUE + "Warps";
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {

        e.setCancelled(true);

        if (ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getDisplayName()).equalsIgnoreCase("My Warps")){
            System.out.println("w");
            new PaginatedWarpsGUI(player, warpFile).open();
        } else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Other Warps")) {
            new OtherPlayersGUI(player, warpFile).open();
        } else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Back")) {
            new MainMenu(player, warpFile).open();
        } else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Set")) {
            if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")) {
                new SetwarpMenu(player, warpFile, null, null, false).open();
            }else
                new SetwarpMenu(player, warpFile, null, null, true).open();
        }

    }

    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public void fill() {

        ItemStack myWarps = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5" +
                "taW5lY3JhZnQubmV0L3RleHR1cmUvMjY0ZDNjYTEyMDZlOTIxYzY2YTJjZWY3NGI4NTQxNzA1NDFlNGVlOWFiZThmYTY3OGNmY" +
                "WY5NjQ5NjRhMTZhMiJ9fX0=");
        ItemMeta myMeta = myWarps.getItemMeta();
        Objects.requireNonNull(myMeta).setDisplayName(ChatColor.BOLD + "" + ChatColor.RED + "My Warps");
        myMeta.setLore(new ArrayList<>(List.of("Click here to get to ", "your warps.")));
        myWarps.setItemMeta(myMeta);

        ItemStack publicW = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5" +
                "taW5lY3JhZnQubmV0L3RleHR1cmUvYTFiNDJjNWY4OTMyMDk4MTY3YjFlNDEzMjZjNzVhODU1ZmExMDdjZDExODc5YWIyZTgzY" +
                "zlmYTJkYzNhZDlmYiJ9fX0=");
        ItemMeta pMeta = publicW.getItemMeta();
        Objects.requireNonNull(pMeta).setDisplayName(ChatColor.BOLD + "" + ChatColor.GREEN + "Other Warps");
        pMeta.setLore(new ArrayList<>(List.of("Click here to see the", "warps of the other players.")));
        publicW.setItemMeta(pMeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        Objects.requireNonNull(closeMeta).setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Back");
        closeMeta.setLore(new ArrayList<>(List.of("Click here to return ", "to the previous menu.")));
        close.setItemMeta(closeMeta);

        ItemStack setWarp = new ItemStack(Material.BEACON);
        ItemMeta setWarpMeta = setWarp.getItemMeta();
        Objects.requireNonNull(setWarpMeta).setDisplayName(ChatColor.AQUA + "Set");
        setWarpMeta.setLore(new ArrayList<>(List.of("Set a warp at your location ", "with the given settings")));
        setWarp.setItemMeta(setWarpMeta);

        fillWithGlass();

        inventory.setItem(12, myWarps);
        inventory.setItem(14, publicW);
        inventory.setItem(18, setWarp);
        inventory.setItem(26, close);
    }
}
