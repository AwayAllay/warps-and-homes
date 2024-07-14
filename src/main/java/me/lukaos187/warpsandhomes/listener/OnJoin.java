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

import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.SkinColorExtractor;
import me.lukaos187.warpsandhomes.util.WarpFile;
import me.lukaos187.warpsandhomes.util.translationUtils.PlayerLanguageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    private final WarpFile warpFile;
    public OnJoin(WarpFile warpFile) {
        this.warpFile = warpFile;
    }

    @EventHandler
    @SuppressWarnings("ConstantValue")
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();

        if (player != null) {
            warpFile.removeSuperfluousWarps(e.getPlayer());
            PlayerUtils.getSkinColors().put(e.getPlayer().getUniqueId(), new SkinColorExtractor(player).getSkinColors());
        }
    }
}
