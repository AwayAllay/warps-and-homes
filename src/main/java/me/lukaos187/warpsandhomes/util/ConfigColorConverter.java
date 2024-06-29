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
