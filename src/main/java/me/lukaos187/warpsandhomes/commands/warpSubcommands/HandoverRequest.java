package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.*;

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
            if (warpOwner.equals(player)){
                player.sendMessage(ChatColor.RED + "You can not send a request to yourself.");
                return;
            }
            if (warp.isPrivate() && !WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warp-requests")){
                player.sendMessage(ChatColor.RED + "You can not request a private warp!");
                return;
            }

            sendRequest(warpOwner, player, warp);


        }else {
            player.sendMessage(ChatColor.RED + "Please provide a warp to request.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp request <name>");
        }
    }

    private void sendRequest(final Player warpOwner, final Player requester, final Warp warp) {

        if (!manageRequest(warpOwner, requester, warp))
            return;

        TextComponent message = new TextComponent("Click if you want to ");
        TextComponent mid = new TextComponent(" this offer or ");
        TextComponent last = new TextComponent(" the request.");

        TextComponent accept = new TextComponent(ChatColor.UNDERLINE + "accept");
        accept.setColor(net.md_5.bungee.api.ChatColor.GREEN);
        accept.setBold(true);
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp accept " + warp.getName() + " " + requester.getName()));

        TextComponent reject = new TextComponent(ChatColor.UNDERLINE + "reject");
        reject.setColor(net.md_5.bungee.api.ChatColor.RED);
        reject.setBold(true);
        reject.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reject " + requester.getName() + warpOwner.getName()));

        message.addExtra(accept);
        message.addExtra(mid);
        message.addExtra(reject);
        message.addExtra(last);

        sendMessage(message, requester, warpOwner, warp);

    }

    private boolean manageRequest(final Player warpOwner, final Player requester, final Warp warp) {
        return true;
    }

    private void sendMessage(final TextComponent message, final Player player, final Player warpOwner, final Warp warp) {
        String header = "-------------[WarpsAndHomes-Request]-------------";
        String coloredH = "-------------[" + ChatColor.GREEN + "WarpsAndHomes-Request" + ChatColor.RESET + "]-------------";
        String footer = "-".repeat(header.length());

        warpOwner.sendMessage(coloredH);
        warpOwner.sendMessage(ChatColor.DARK_GRAY + player.getName() + ChatColor.RESET + " requests your warp: " + warp.getName());
        warpOwner.playSound(warpOwner, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 9F, 3F);
        warpOwner.spigot().sendMessage(message);
        warpOwner.sendMessage(footer);

        player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
        player.sendMessage(ChatColor.GREEN + "Request" + ChatColor.RESET + " was sent to " + warpOwner.getName());
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

            if (owningWarps != null){

                if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warp-requests")){

                    owningWarps.forEach(w -> {

                            Warp warp = warpFile.getWarp(w);
                            if (!warp.isPrivate())
                                warps.add(w);
                    });

                }else {
                    warps.addAll(owningWarps);
                }

            }
        });

        return warps;
    }
}
