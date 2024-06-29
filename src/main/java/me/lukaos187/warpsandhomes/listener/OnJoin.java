package me.lukaos187.warpsandhomes.listener;

import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.SkinColorExtractor;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoin implements Listener {

    private final WarpFile warpFile;

    public OnJoin(WarpFile warpFile) {
        this.warpFile = warpFile;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();

        warpFile.removeSuperfluousWarps(e.getPlayer());
        PlayerUtils.getSkinColors().put(e.getPlayer().getUniqueId(), new SkinColorExtractor(player).getSkinColors());
    }
}
