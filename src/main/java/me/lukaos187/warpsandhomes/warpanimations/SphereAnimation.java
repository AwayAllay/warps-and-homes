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
 * @Effect: Creates a sphere in which the player vanishes by teleporting.*/
public class SphereAnimation {

    private final Player player;
    private final Warp warp;
    private final List<Color> skinColours;
    private final Random randomizer = new Random();
    private Color particleColor;

    public SphereAnimation(Player player, Warp warp) {
        this.player = player;
        this.warp = warp;
        skinColours = PlayerUtils.getSkinColors().get(player.getUniqueId());
        ConfigColorConverter configColorConverter = new ConfigColorConverter(skinColours);
        particleColor = configColorConverter.getConfigColor();
        animation();
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
    }

    private void animation() {

        double exRadi = 2.3;
        double maxRadi = 5;
        int expandSteps = 10;

        new BukkitRunnable() {
            private int count = 0;
            @Override
            public void run() {
                if (count < 30) {
                    Particle.DustOptions d;

                    if (particleColor == null) {
                        d = new Particle.DustOptions(randomColor(), 1.0F);
                    } else {
                        d = new Particle.DustOptions(particleColor, 1.0F);
                    }
                    Objects.requireNonNull(player.getWorld()).spawnParticle(Particle.REDSTONE,
                            player.getLocation(), 10, 0.3, 1, 0.3, d);
                }else{
                    createExplosionEffect(player.getLocation(), exRadi, maxRadi, expandSteps);
                    warpPlayer();
                    cancel();
                }
                count++;
            }
        }.runTaskTimer(WarpsAndHomes.getPlugin(), 0, 1L);


    }

    private Color randomColor() {
        if (skinColours == null){
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Please rejoin in a few seconds.");
            return Color.PURPLE;
        }
        int random = randomizer.nextInt(skinColours.size());
        return skinColours.get(random);
    }

    private void createExplosionEffect(Location location, double initialRadius, double maxRadius, int steps) {
        new BukkitRunnable() {
            double radius = initialRadius;
            int step = 0;
            @Override
            public void run() {
                if (step >= steps) {
                    this.cancel();
                    return;
                }
                createSphere(location, radius);
                radius += (maxRadius - initialRadius) / steps;
                step++;
            }
        }.runTaskTimer(WarpsAndHomes.getPlugin(), 0, 1);
    }

    /**Spawns a sphere made of particles around the player.*/
    private void createSphere(Location location, double radius) {
        World world = location.getWorld();
        double step = Math.PI / 40; // Decrease steps for more particles
        for (double theta = 0; theta <= Math.PI; theta += step) {
            double sinTheta = Math.sin(theta);
            double cosTheta = Math.cos(theta);
            for (double phi = 0; phi <= 2 * Math.PI; phi += step) {
                double x = radius * sinTheta * Math.cos(phi);
                double y = radius * sinTheta * Math.sin(phi);
                double z = radius * cosTheta;
                Location particleLocation = location.clone().add(x, y + 1, z);
                try {
                    Particle.DustOptions d;

                    if (particleColor == null) {
                        d = new Particle.DustOptions(randomColor(), 1.0F);
                    } else {
                        d = new Particle.DustOptions(particleColor, 1.0F);
                    }
                    Objects.requireNonNull(world).spawnParticle(Particle.REDSTONE, particleLocation, 1, d);

                }catch (NullPointerException e) {
                    System.out.println("Could not spawn particle.");
                }
            }
        }
    }
}
