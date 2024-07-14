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

import java.util.List;
import java.util.Objects;
import java.util.Random;

/**This is the class for one of the many warping-animations. It will automatically play the animation by
 * creating an object with the constructor. After the animation finished, the player will be teleported to the given warp.
 * @Parameters: player = the player for the animation, warp = the warp he will be teleported to.
 * @Effect: Creates the same particle effect with the player as the one when an endeman teleports.*/
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
