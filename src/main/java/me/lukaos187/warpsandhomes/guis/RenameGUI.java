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
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpRename;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class RenameGUI {

    private final Player player;
    private final ItemStack warpItem;
    private final Warp warp;
    private final WarpFile warpFile;
    public RenameGUI(Player player, ItemStack warpItem, Warp warp, WarpFile warpFile) {
        this.player = player;
        this.warpItem = warpItem;
        this.warp = warp;
        this.warpFile = warpFile;
    }

    public void open(){

        new AnvilGUI.Builder()
                .plugin(WarpsAndHomes.getPlugin())
                .title(ChatColor.GOLD + "Rename warp")
                .itemLeft(warpItem)
                .onClick((slot, stateSnapshot) -> {

                    if (slot != AnvilGUI.Slot.OUTPUT){
                        return Collections.emptyList();
                    }

                    return Arrays.asList(
                            AnvilGUI.ResponseAction.close(),
                            AnvilGUI.ResponseAction.run(() -> rename(stateSnapshot.getText()))
                    );


                })
                .open(player);

    }

    private void rename(final String newName) {

        String[] args = {"rename", warp.getName(), newName};
        new WarpRename(warpFile).perform(player, args);

        new WarpOpt(player, warpFile, warpFile.getWarp(newName), warpItem).open();
    }


}
