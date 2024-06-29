package me.lukaos187.warpsandhomes.listener;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnDamage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("has-damage-warp-cooldown"))
            return;

        //Only if damage cooldown activated
        if (event.getEntity() instanceof Player) {

            Player player = (Player) event.getEntity();

            // Damage by other player?
            if (event instanceof EntityDamageByEntityEvent) {

                EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) event;

                if (entityDamageByEntityEvent.getDamager() instanceof Player) {//damage by player

                    PlayerUtils.getLastDamage().put(player.getUniqueId(), System.currentTimeMillis());

                } else {//Damage by other entity

                    if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("damage-cooldown-player")) {
                        PlayerUtils.getLastDamage().put(player.getUniqueId(), System.currentTimeMillis());
                    }
                }

            } else {//Damage by all source

                if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("damage-cooldown-player")) {
                    PlayerUtils.getLastDamage().put(player.getUniqueId(), System.currentTimeMillis());
                }
            }
        }
    }
}
