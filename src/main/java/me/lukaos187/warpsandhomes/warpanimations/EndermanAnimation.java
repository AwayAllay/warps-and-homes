package me.lukaos187.warpsandhomes.warpanimations;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.ConfigColorConverter;
import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.Warp;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EndermanAnimation {

    private final Player player;
    private final Warp warp;
    private final Color particleColor;
    private final Random random;
    private final List<Color> skinColors;

    public EndermanAnimation(Player player, Warp warp) {
        this.player = player;
        this.warp = warp;
        random = new Random();
        skinColors = PlayerUtils.getSkinColors().get(player.getUniqueId());
        ConfigColorConverter configColorConverter = new ConfigColorConverter(skinColors);
        particleColor = configColorConverter.getConfigColor();
        animation();
    }

    private void animation() {

        spawnParticles(player.getLocation());
        warpPlayer();

    }

    private void warpPlayer() {

        Sound teleportSound;
        String soundS = WarpsAndHomes.getPlugin().getConfig().getString("warping-sound");
        if (soundS == null){
            teleportSound = Sound.ENTITY_ENDERMAN_TELEPORT;
            WarpsAndHomes.getPlugin().getServer().getLogger().info(ChatColor.RED + "[WarpsAndHomes] Incorrect sound in the config.yml!");
        }else {
            try {
                teleportSound = Sound.valueOf(soundS.toUpperCase());
            }catch (IllegalArgumentException e){
                teleportSound = Sound.ENTITY_ENDERMAN_TELEPORT;
                WarpsAndHomes.getPlugin().getServer().getLogger().info(ChatColor.RED + "[WarpsAndHomes] Incorrect sound in the config.yml!");
            }
        }

        player.teleport(warp.getLocation());
        player.playSound(player, teleportSound, 5F, 5F);
        player.sendMessage("Warped you to " + ChatColor.AQUA + warp.getName());

        spawnParticles(player.getLocation());

    }

    private void spawnParticles(Location location) {

        for (int i = 0; i < 30; i++) {

            Particle.DustOptions d;

            if (particleColor == null) {
                d = new Particle.DustOptions(randomColor(), 1.0F);
            } else {
                d = new Particle.DustOptions(particleColor, 1.0F);
            }
            Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.REDSTONE,
                    location, 10, 0.3, 1, 0.3, d);
        }

    }

    private Color randomColor() {
        if (skinColors == null){
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Please rejoin in a few seconds.");
            return Color.PURPLE;
        }
        int r = random.nextInt(skinColors.size());
        return skinColors.get(r);
    }

}