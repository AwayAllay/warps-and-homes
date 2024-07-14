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

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import org.bukkit.Color;

import java.util.List;
import java.util.Random;

public class ConfigColorConverter {

    private Color pColor;
    private final List<Color> skinColours;
    private final Random random;

    public ConfigColorConverter(List<Color> skinColours) {
        this.skinColours = skinColours;
        random = new Random();
        String color = WarpsAndHomes.getPlugin().getConfig().getString("particle-colour").toUpperCase();
        assignColor(color);
    }

    public Color getConfigColor(){
        return pColor;
    }

    private void assignColor(final String color) {
        try {
            pColor = getColor(color);
        } catch (IllegalArgumentException e) {
            WarpsAndHomes.getPlugin().getServer().getLogger().info("[WarpsAndHomes] Unknown color " +
                    "in config.yml for particle color!");
            pColor = Color.ORANGE;
        }
    }

    private Color getColor(final String color) throws IllegalArgumentException {

        return switch (color) {
            case "RED" -> Color.RED;
            case "BLUE" -> Color.BLUE;
            case "GREEN" -> Color.GREEN;
            case "YELLOW" -> Color.YELLOW;
            case "PURPLE" -> Color.PURPLE;
            case "ORANGE" -> Color.ORANGE;
            case "WHITE" -> Color.WHITE;
            case "BLACK" -> Color.BLACK;
            case "AQUA" -> Color.AQUA;
            case "FUCHSIA" -> Color.FUCHSIA;
            case "GRAY" -> Color.GRAY;
            case "LIME" -> Color.LIME;
            case "MAROON" -> Color.MAROON;
            case "NAVY" -> Color.NAVY;
            case "OLIVE" -> Color.OLIVE;
            case "SILVER" -> Color.SILVER;
            case "TEAL" -> Color.TEAL;
            case "PLAYER" -> null;
            default -> throw new IllegalArgumentException(color);
        };

    }
}
