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
package me.lukaos187.warpsandhomes.warpanimations;
//FIXME TRANSLATIONS NEEDED
import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.ConfigColorConverter;
import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.Warp;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Objects;
import java.util.Random;
/**This is the class for one of the many warping-animations. It will automatically play the animation by
 * creating an object with the constructor. After the animation finished, the player will be teleported to the given warp.
 * @Parameters: player = the player for the animation, warp = the warp he will be teleported to.
 * @Effect: Creates a ring in which the player is sucked and also a ring at the warps location where the player will be thrown out.*/
public class PortalAnimation {

    private final Player player;
    private final Warp warp;
    private final List<Color> skinColours;
    private Color particleColor;

    public PortalAnimation(Player player, Warp warp) {
        this.player = player;
        this.warp = warp;
        skinColours = PlayerUtils.getSkinColors().get(player.getUniqueId());
        ConfigColorConverter configColorConverter = new ConfigColorConverter(skinColours);
        particleColor = configColorConverter.getConfigColor();
        animation();
    }
    private void animation() {

        ring(player.getLocation().add(0, 0.2, 0));
        sinkPlayer();

    }
    private void sinkPlayer() {
        new BukkitRunnable(){
            private int count = 0;
            @Override
            public void run() {

                if (count < 12) {
                    player.teleport(player.getLocation().subtract(0, 0.28, 0));
                }else {
                    ring(warp.getLocation().add(0, 2, 0));
                    warpPlayer(player, warp);
                    cancel();
                }
                count++;
            }
        }.runTaskTimer(WarpsAndHomes.getPlugin(), 0, 1);
    }

    private void warpPlayer(final Player player, final Warp warp) {
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

        player.playSound(player, teleportSound, 5F, 5F);
        player.teleport(warp.getLocation().add(0, 2.5, 0));
        player.sendMessage("Warped you to " + ChatColor.AQUA + warp.getName());
    }


    private void ring(final Location location) {

        new BukkitRunnable() {
            private int counter = 0;
            @Override
            public void run() {

                if (counter > 3)
                    cancel();

                for (int i = 0; i < 360; i += 10) {
                    double angle = Math.toRadians(i);
                    double x = 0.75 * Math.cos(angle);
                    double z = 0.75 * Math.sin(angle);
                    spawnParticle(location.clone().add(x, 0, z));
                }

                counter++;
            }
        }.runTaskTimer(WarpsAndHomes.getPlugin(), 0, 10L);

    }

    private void spawnParticle(Location location) {

        Particle.DustOptions d;

        if (particleColor == null) {
            d = new Particle.DustOptions(randomColor(), 1.0F);
        } else {
            d = new Particle.DustOptions(particleColor, 1.0F);
        }
        Objects.requireNonNull(location.getWorld()).spawnParticle(Particle.REDSTONE,
                location, 10, d);
    }

    private Color randomColor() {
        if (skinColours == null){
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Please rejoin in a few seconds.");
            return Color.PURPLE;
        }
        int random = new Random().nextInt(skinColours.size());
        return skinColours.get(random);
    }

}
