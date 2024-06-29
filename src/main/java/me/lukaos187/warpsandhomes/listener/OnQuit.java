package me.lukaos187.warpsandhomes.listener;

import me.lukaos187.warpsandhomes.util.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        Player player = e.getPlayer();

        PlayerUtils.getSkinColors().remove(player.getUniqueId());

    }
}
