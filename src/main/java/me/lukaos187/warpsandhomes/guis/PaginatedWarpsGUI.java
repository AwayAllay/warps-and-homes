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

import me.lukaos187.warpsandhomes.util.HeadGetter;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//FIXME TRANSLATIONS NEEDED
public class PaginatedWarpsGUI extends WarpMenu{

    private final Player target;

    private int currentPage = 0;
    private final List<Warp> warps = new ArrayList<>();

    /**Constructor for the MyWarps-Menu (only the warps of the player)*/
    public PaginatedWarpsGUI(Player inventoryOwner, WarpFile warpFile) {
        super(inventoryOwner, warpFile);
        target = null;

        List<String> warpNames = warpFile.getWarps(player);

        warpNames.forEach(s -> {

            Warp warp = warpFile.getWarp(s);
            if (warp != null)
                warps.add(warp);

        });
    }

    /**Constructor for showing the warps of another player*/
    public PaginatedWarpsGUI(Player player, WarpFile warpFile, Player target) {
        super(player, warpFile);
        this.target = target;

        List<String> warpNames = warpFile.getWarps(target);
        warpNames.forEach(s -> {
            Warp warp = warpFile.getWarp(s);
            if (warp != null && !warp.isPrivate())
                warps.add(warp);
        });
    }

    @Override
    public int slots() {
        return 9*6;
    }

    @Override
    public String name() {

        if (target == null){
            return ChatColor.RED + "My Warps";
        }else {
            return ChatColor.DARK_GRAY + "Public warps of " + target.getName();
        }
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {
        e.setCancelled(true);

        ItemStack currentItem = e.getCurrentItem();

        String displayName = ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(currentItem).getItemMeta()).getDisplayName());

        switch (displayName) {
            case "Next" -> {
                if ((currentPage + 1) * 45 < warps.size()) {
                    currentPage++;
                    player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                    fill();
                }
            }
            case "Previous" -> {
                if (currentPage > 0) {
                    currentPage--;
                    player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                    fill();
                }
            }
            case "Back" -> {
                if (target == null){
                    new WarpChoice(player, warpFile).open();
                }else {
                    new OtherPlayersGUI(player, warpFile).open();
                }
            }
            default -> {
                if (target == null){
                    new WarpOpt(player, warpFile, warpFile.getWarp(displayName), currentItem).open();
                }else {
                    Warp warp = warpFile.getWarp(displayName);
                    if (warp == null)
                        return;

                    new StrangerWarpGUI(player, warpFile, warp).open();
                }
            }
        }
    }

    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public void fill() {
        inventory.clear();
        optionBar();
        for (int i = 0; i < 45; i++) {
            int index = currentPage * 45 + i;
            if (index >= warps.size())
                break;

            Warp warp = warps.get(index);
            ItemStack warpItem;

            if (warp.getDisplayItem() == null)
                warpItem = getWarpItem(warp);
            else
                warpItem = addMeta(new ItemStack(warp.getDisplayItem()), warp);

            inventory.setItem(i, warpItem);
        }
    }

    private ItemStack getWarpItem(final Warp warp) {

        ItemStack warpItem = new ItemStack(Material.BARRIER);;
        try {
            if (warp.getLocation().getWorld().getEnvironment().equals(World.Environment.NORMAL)) {

                warpItem = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5" +
                        "lY3JhZnQubmV0L3RleHR1cmUvMjU0ODUwMzFiMzdmMGQ4YTRmM2I3ODE2ZWI3MTdmMDNkZTg5YTg3ZjZhNDA2MDJhZWY" +
                        "1MjIyMWNkZmFmNzQ4OCJ9fX0=");

            } else if (warp.getLocation().getWorld().getEnvironment().equals(World.Environment.NETHER)) {

                warpItem = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5" +
                        "lY3JhZnQubmV0L3RleHR1cmUvNmM3YjgwMGRkNDNmMzBiZGM3YjA2ZjZiNTUxNmQzMGU2ZDk3YzAzOWNhOTk1ZDdkOTY" +
                        "zZGU1YjQ5NzdiNjcyMyJ9fX0=");

            } else if (warp.getLocation().getWorld().getEnvironment().equals(World.Environment.THE_END)) {

                warpItem = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5" +
                        "lY3JhZnQubmV0L3RleHR1cmUvZTQ4ZDc5NjhjMjNkNTM3YmZlYWVkNmRlN2NkYmU3MzMwYWZlNDA1ZTAwYzViZDNlZWQ" +
                        "4YWIyOTFmZDU5YzVlZCJ9fX0=");

            }

        } catch (NullPointerException e) {
            warpItem = new ItemStack(Material.BARRIER);
        }

        return addMeta(warpItem, warp);

    }

    private ItemStack addMeta(final ItemStack warpItem, final Warp warp) {

        ItemMeta meta = warpItem.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(ChatColor.AQUA + warp.getName());


        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Location: " + ChatColor.GOLD + Math.round(warp.getLocation().getX()) +
                " " + Math.round(warp.getLocation().getY()) + " " + Math.round(warp.getLocation().getZ()));
        lore.add(ChatColor.GRAY + "Dimension: " + ChatColor.GREEN + Objects.requireNonNull(warp.getLocation().getWorld()).getEnvironment());
        if (warp.isPrivate()){
            lore.add(ChatColor.GRAY + "Privacy: " + ChatColor.RED + "private");
        }else {
            lore.add(ChatColor.GRAY + "Privacy: " + ChatColor.GREEN + "public");
        }
        lore.add(ChatColor.AQUA + "" + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.WHITE + warp.getDescription());

        meta.setLore(lore);
        warpItem.setItemMeta(meta);
        return warpItem;

    }

    private void optionBar() {
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta m = filler.getItemMeta();
        Objects.requireNonNull(m).setDisplayName(" ");
        filler.setItemMeta(m);

        for (int i = 53; i > 44; i--) {
            inventory.setItem(i, filler);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Back");
        closeMeta.setLore(new ArrayList<>(List.of("Click here to return ", "to the previous menu.")));
        close.setItemMeta(closeMeta);
        inventory.setItem(49, close);

        ItemStack nextPage = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5" +
                "taW5lY3JhZnQubmV0L3RleHR1cmUvYWM5YzY3YTlmMTY4NWNkMWRhNDNlODQxZmU3ZWJiMTdmNmFmNmVhMTJhN2UxZjI3MjJmN" +
                "WU3ZjA4OThkYjlmMyJ9fX0=");
        ItemMeta meta = nextPage.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Next");
        nextPage.setItemMeta(meta);
        inventory.setItem(50, nextPage);

        ItemStack lastPage = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5" +
                "taW5lY3JhZnQubmV0L3RleHR1cmUvMWExZWYzOThhMTdmMWFmNzQ3NzAxNDUxN2Y3ZjE0MWQ4ODZkZjQxYTMyYzczOGNjOGE4M" +
                "2ZiNTAyOTdiZDkyMSJ9fX0=");
        ItemMeta metaLast = lastPage.getItemMeta();
        metaLast.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Previous");
        lastPage.setItemMeta(metaLast);
        inventory.setItem(48, lastPage);
    }
}
