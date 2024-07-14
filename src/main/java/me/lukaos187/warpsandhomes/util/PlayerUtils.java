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
package me.lukaos187.warpsandhomes.util;

import org.bukkit.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerUtils {

    private static Map<UUID, List<Color>> skinColors = new HashMap<>();
    private static Map<UUID, Long> lastDamage = new HashMap<>();
    private static Map<UUID, Long> cooldown = new HashMap<>();
    private static Map<Warp, Map<UUID, Long>> requests = new HashMap<>();

    public static Map<UUID, Long> getLastDamage() {
        return lastDamage;
    }

    /**Returns a List of the given players skin-colors. */
    public static Map<UUID, List<Color>> getSkinColors() {
        return skinColors;
    }

    public static Map<UUID, Long> getCooldown() {
        return cooldown;
    }

    public static Map<Warp, Map<UUID, Long>> getRequests() {
        return requests;
    }
}
