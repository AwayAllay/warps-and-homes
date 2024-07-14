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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class HeadGetter {

    /**Returns a playerHead as an Itemstack for the given base64 String.
     * @Returns: An Itemstack for the given playerHead. */
    public static ItemStack getHead(String base64) {

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        if (base64 == null || base64.isEmpty()) return skull;

        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        if (skullMeta == null) return skull;

        try {
            // Create a new GameProfile
            UUID uuid = UUID.randomUUID();
            Object profile = Class.forName("com.mojang.authlib.GameProfile").getConstructor(UUID.class, String.class).newInstance(uuid, null);

            // Get the properties field of the GameProfile
            Field profileField = profile.getClass().getDeclaredField("properties");
            profileField.setAccessible(true);
            Object properties = profileField.get(profile);

            // Create a new Property and add it to the properties
            Object property = Class.forName("com.mojang.authlib.properties.Property").getConstructor(String.class, String.class).newInstance("textures", base64);
            properties.getClass().getMethod("put", Object.class, Object.class).invoke(properties, "textures", property);

            // Set the profile field in the SkullMeta
            Field profileFieldMeta = skullMeta.getClass().getDeclaredField("profile");
            profileFieldMeta.setAccessible(true);
            profileFieldMeta.set(skullMeta, profile);

        } catch (Exception e) {
            return new ItemStack(Material.BARRIER);
        }

        skullMeta.setDisplayName("House Head");
        skull.setItemMeta(skullMeta);
        return skull;

    }
}
