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
import me.lukaos187.warpsandhomes.commands.warpSubcommands.*;
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

public class WarpOpt extends WarpMenu{

    private final Warp warp;
    private final ItemStack warpItem;

    public WarpOpt(Player player, WarpFile warpFile, Warp warp, ItemStack warpItem) {
        super(player, warpFile);
        this.warp = warp;
        this.warpItem = warpItem;
    }

    @Override
    public int slots() {
        return 4*9;
    }

    @Override
    public String name() {
        return ChatColor.GOLD + warp.getName();
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {
        e.setCancelled(true);
        switch (Objects.requireNonNull(e.getCurrentItem()).getType()){

            case BARRIER -> new PaginatedWarpsGUI(player, warpFile).open();

            case END_PORTAL_FRAME -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                String[]args = {"warp", warp.getName()};
                new WarpToWarp(warpFile).perform(player, args);
            }
            case TNT -> new ConfirmGUI(player, warpFile, ChatColor.RED + "Delete-Warp", warp.getName()).open();
            case COMPASS -> new ConfirmGUI(player, warpFile, ChatColor.GRAY + "Update-Warp", warp.getName()).open();
            case ENDER_CHEST -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);

                if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")) {
                    String[] args = {"unlock", warp.getName()};
                    new WarpUnlock(warpFile).perform(player, args);
                    fill();
                }
            }
            case OAK_SIGN -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);

                if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps")) {
                    String[] args = {"lock", warp.getName()};
                    new WarpLock(warpFile).perform(player, args);
                    fill();
                }
            }

            case PLAYER_HEAD -> new HandoverGUI(player, warpFile, warpFile.getWarp(warp.getName())).open();
            case WRITTEN_BOOK -> new DescribeGUI(player, warpItem, warpFile.getWarp(warp.getName()), warpFile).open();
            case ANVIL -> new RenameGUI(player, warpItem, warpFile.getWarp(warp.getName()), warpFile).open();
            case GRASS_BLOCK -> {
                player.playSound(player,Sound.UI_BUTTON_CLICK, 5F, 5F);
                new DisplayItemGUI(player, warpFile, warpFile.getWarp(warp.getName()), warpItem).open();
            }
        }
    }

    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public void fill() {
        fillWithGlass();

        //1.Warp 2.Delete 3.Update 4.Unlock 5.Lock 6.Handover 7.Describe 8.Rename

        setItem(null, Material.END_PORTAL_FRAME, ChatColor.LIGHT_PURPLE + "Warp",
                new ArrayList<>(List.of("Click here to warp ", "to this warp.")), 10);


        setItem("allow-warp-deleting", Material.TNT, ChatColor.RED + "Delete",
                new ArrayList<>(List.of("Click here to ", "delete this warp.")), 16);


        setItem("allow-warp-updating", Material.COMPASS, ChatColor.GREEN + "Update",
                new ArrayList<>(List.of("Click here to ", "update this warp.")), 23);

        setPrivacyItems();

        setItem(null, Material.GRASS_BLOCK, ChatColor.AQUA + "" + ChatColor.BOLD + "Display-Item",
                new ArrayList<>(List.of("Click here to set a item for this ", "warp, which will be displayed ", "in your list of warps.")),
                21);

        setItem("allow-warp-hand-overing", Material.PLAYER_HEAD, ChatColor.GOLD + "Handover",
                new ArrayList<>(List.of("Click here to change the ", "owner of this warp.")), 25);


        setItem("allow-warp-describing", Material.WRITTEN_BOOK, ChatColor.BLUE + "Describe",
                new ArrayList<>(List.of("Click here to describe ", "this warp.")), 14);


        setItem("allow-warp-renaming", Material.ANVIL, ChatColor.GRAY + "Rename",
                new ArrayList<>(List.of("Click here to rename ", "this warp.")), 12);


        setItem(null, Material.BARRIER, ChatColor.RED + "" + ChatColor.BOLD + "Back",
                new ArrayList<>(List.of("Click here to go to ", "the previous menu.")), 31);
    }

    private void setPrivacyItems() {

        List<String> lore = new ArrayList<>();
        if (!warp.isPrivate()) {

            if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps")){
                lore.add("This warp is public.");
            }else {
                lore.add("This warp is public. Make it private by ");
                lore.add("clicking on this item.");
            }

            setItem(null, Material.OAK_SIGN, ChatColor.GREEN + "" + ChatColor.BOLD + "public",
                    lore, 19);
        }
        else {

            if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")){
                lore.add("This warp is private.");
            }else {
                lore.add("This warp is private. Make it public by ");
                lore.add("clicking on this item.");
            }

            setItem(null, Material.ENDER_CHEST, ChatColor.RED + "" + ChatColor.BOLD + "private",
                    lore, 19);
        }

    }

    private void setItem(String configKey, Material material, String displayName, List<String> lore, int index){

        ItemStack disabledOption = new ItemStack(Material.IRON_BARS);
        ItemMeta dOMeta = disabledOption.getItemMeta();
        Objects.requireNonNull(dOMeta).setDisplayName(ChatColor.GRAY + "Disabled option");
        dOMeta.setLore(new ArrayList<>(List.of(ChatColor.GRAY + "This option was disabled ", ChatColor.GRAY + "in the config.yml")));
        disabledOption.setItemMeta(dOMeta);

        if (configKey == null || WarpsAndHomes.getPlugin().getConfig().getBoolean(configKey)) {
            ItemStack item = new ItemStack(material);
            ItemMeta itemMeta = item.getItemMeta();
            Objects.requireNonNull(itemMeta).setDisplayName(displayName);
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            inventory.setItem(index, item);
        }else {
            inventory.setItem(index, disabledOption);
        }

    }
}
