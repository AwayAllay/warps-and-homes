package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HandoverRequest implements Subcommand{

    private final WarpFile warpFile;

    public HandoverRequest(WarpFile warpFile) {
        this.warpFile = warpFile;
    }

    @Override
    public String getName() {
        return "request";
    }

    @Override
    public String getDescription() {
        return "Sends a request for a hand over of this warp to its owner.";
    }

    @Override
    public String getUsage() {
        return "/warp request <name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 1){

            Warp warp = warpFile.getWarp(args[1]);

            if (warp == null){
                player.sendMessage(ChatColor.RED + "This warp does not exist.");
                return;
            }

            Player warpOwner = warp.getOwner();

            if (warpOwner == null){
                player.sendMessage(ChatColor.RED + "The owner is not online at the moment.");
                return;
            }

            sendRequest(warpOwner, player, warp);


        }else {
            player.sendMessage(ChatColor.RED + "Please provide a warp to request.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp request <name>");
        }
    }

    private void sendRequest(final Player warpOwner, final Player player, final Warp warp) {

        TextComponent in = new TextComponent("Click if you want to ");
        TextComponent mid = new TextComponent(" this offer or ");
        TextComponent last = new TextComponent(" the request.");

        TextComponent accept = new TextComponent("accept");
        accept.setColor(net.md_5.bungee.api.ChatColor.GREEN);
        accept.setBold(true);
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/accept " + player.getName() + warp.getName()));

        TextComponent reject = new TextComponent("reject");
        reject.setColor(net.md_5.bungee.api.ChatColor.RED);
        reject.setBold(true);
        reject.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reject " + player.getName() + warpOwner.getName()));

        warpOwner.sendMessage(ChatColor.AQUA + player.getName() + " requests your warp: " + warp.getName());
        warpOwner.sendMessage();

    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {
        if (argsLength == 1){
            List<String> tab = new ArrayList<>();
            tab.add("request");
            return tab;
        } else if (argsLength == 2) {
            return getTab(player);
        }
        return null;
    }

    private List<String> getTab(final Player player) {

        List<Player> onlinePlayers = new ArrayList<>();

        Bukkit.getOnlinePlayers().forEach(online -> {

            if (!online.equals(player)){
                onlinePlayers.add(online);
            }

        });

        List<String> warps = new ArrayList<>();

        onlinePlayers.forEach(online -> {

            List<String> owningWarps = warpFile.getWarps(online);

            if (owningWarps != null)
                warps.addAll(owningWarps);
        });

        return warps;
    }
}
