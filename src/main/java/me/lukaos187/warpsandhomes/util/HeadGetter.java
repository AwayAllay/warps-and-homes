package me.lukaos187.warpsandhomes.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class HeadGetter {

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
