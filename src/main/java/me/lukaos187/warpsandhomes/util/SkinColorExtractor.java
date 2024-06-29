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
            Bukkit.getPlayer(uuid).sendMessage(ChatColor.RED + "Something went wrong. Please rejoin in a few seconds.");
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