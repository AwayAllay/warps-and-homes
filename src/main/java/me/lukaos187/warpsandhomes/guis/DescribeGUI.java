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
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpDescribe;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpRename;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class DescribeGUI {
    private final Player player;
    private final ItemStack warpItem;
    private final Warp warp;
    private final WarpFile warpFile;
    public DescribeGUI(Player player, ItemStack warpItem, Warp warp, WarpFile warpFile) {
        this.player = player;
        this.warpItem = warpItem;
        this.warp = warp;
        this.warpFile = warpFile;
    }

    public void open(){

        ItemStack des = new ItemStack(Material.PAPER);
        ItemMeta meta = des.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(warp.getDescription());
        des.setItemMeta(meta);

        new AnvilGUI.Builder()
                .plugin(WarpsAndHomes.getPlugin())
                .title(ChatColor.GOLD + "Write a description")
                .itemLeft(des)
                .onClick((slot, stateSnapshot) -> {

                    if (slot != AnvilGUI.Slot.OUTPUT){
                        return Collections.emptyList();
                    }

                    return Arrays.asList(
                            AnvilGUI.ResponseAction.close(),
                            AnvilGUI.ResponseAction.run(() -> descriptionChange(stateSnapshot.getText()))
                    );


                })
                .open(player);

    }

    private void descriptionChange(final String newDescr) {

        String[] args = {"describe", warp.getName(), newDescr};
        new WarpDescribe(warpFile).perform(player, args);

        new WarpOpt(player, warpFile, warp, warpItem).open();
    }

}
