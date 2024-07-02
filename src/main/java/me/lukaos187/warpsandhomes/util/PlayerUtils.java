package me.lukaos187.warpsandhomes.util;

import org.bukkit.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerUtils {

    private static Map<UUID, List<Color>> skinColors = new HashMap<>();
    private static Map<UUID, Long> lastDamage = new HashMap<>();
    private static Map<UUID, Long> cooldown = new HashMap<>();
    //private static Map<String, List<UUID>> requests = new HashMap<>();

    public static Map<UUID, Long> getLastDamage() {
        return lastDamage;
    }

    public static Map<UUID, List<Color>> getSkinColors() {
        return skinColors;
    }

    public static Map<UUID, Long> getCooldown() {
        return cooldown;
    }

   /* public static Map<String, List<UUID>> getRequests() {
        return requests;
    }*/ //TODO implement this!!!
}
