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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SkinColorExtractor {

    private final UUID uuid;

    public SkinColorExtractor(Player player) {
        this.uuid = player.getUniqueId();
    }

    private BufferedImage getSkin(String uuid) {
        try {
            //URL
            URL url = new URL("https://crafatar.com/skins/" + uuid);
            //URL connection verbindung öffnen
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //http -> get gibt mir was (image of skin)
            conn.setRequestMethod("GET");
            //in stream aus connection
            InputStream in = conn.getInputStream();
            //zieht stream, in nen Buffered image
            BufferedImage image = ImageIO.read(in);
            in.close();
            conn.disconnect();
            return image;
        } catch (Exception e) {

            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                player.sendMessage(ChatColor.RED + "Something went wrong. Please rejoin in a few seconds.");
            }
        }
        return null;
    }

    private List<java.awt.Color> extractSkinColors(BufferedImage image) {

        List<java.awt.Color> colorSet = new ArrayList<>();

        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                int pixel = image.getRGB(x, y);
                java.awt.Color color = new java.awt.Color(pixel, true);
                if (color.getAlpha() == 0)//looks if pixel is transparent
                    continue;
                colorSet.add(color);
            }
        }
        return new ArrayList<>(colorSet);
    }

    /**
     * This will give you a list of the colors of the skin of the player which is given by creating an instance of this class.
     *
     * @Returns: A List<Color> with all the colors of the player´s skin. (Not the transparent ones)
     */
    public List<Color> getSkinColors() {
        if (uuid != null) {

            BufferedImage skin = getSkin(uuid.toString());
            if (skin != null) {

                List<java.awt.Color> awtColors = extractSkinColors(skin);
                List<Color> bukkitColors = new ArrayList<>();

                for (java.awt.Color awtColor : awtColors) {
                    bukkitColors.add(convertToBukkitColor(awtColor));
                }
                return bukkitColors;
            }
        }
        return new ArrayList<>();
    }

    private Color convertToBukkitColor(java.awt.Color awtColor) {
        return Color.fromRGB(awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue());
    }
}
