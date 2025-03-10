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
package me.lukaos187.warpsandhomes.commands.warpSubcommands;
//FIXME TRANSLATIONS NEEDED
import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
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

        checkRequests(warp);

        if (!manageRequest(requester, warp))
            return;

        TextComponent message = new TextComponent("Click if you want to ");
        TextComponent mid = new TextComponent(" this offer or ");
        TextComponent last = new TextComponent(" the request.");


        TextComponent accept = new TextComponent(ChatColor.UNDERLINE + "accept");
        accept.setColor(net.md_5.bungee.api.ChatColor.GREEN);
        accept.setBold(true);

        Text hoverTextAccept = new Text(ChatColor.GREEN + "Hand over the ownership.");
        HoverEvent hoverAccept = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverTextAccept);
        accept.setHoverEvent(hoverAccept);
        accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp accept " + warp.getName() + " " + requester.getName()));


        TextComponent reject = new TextComponent(ChatColor.UNDERLINE + "reject");
        reject.setColor(net.md_5.bungee.api.ChatColor.RED);
        reject.setBold(true);

        Text hoverTextReject = new Text(ChatColor.RED + "Reject the request.");
        HoverEvent hoverReject = new HoverEvent(HoverEvent.Action.SHOW_TEXT, hoverTextReject);
        reject.setHoverEvent(hoverReject);

        reject.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp " + "reject " + warp.getName() + " " + requester.getName()));

        message.addExtra(accept);
        message.addExtra(mid);
        message.addExtra(reject);
        message.addExtra(last);

        sendMessage(message, requester, warpOwner, warp);

    }

    private void checkRequests(final Warp warp) {

        Map<UUID, Long> playerTimeRequests = PlayerUtils.getRequests().get(warp);


        if (playerTimeRequests == null) {
            Map<UUID, Long> request = new HashMap<>();
            PlayerUtils.getRequests().put(warp, request);
        }
    }

    private void putInRequests(final Player requester, final Warp warp){

        Map<UUID, Long> playerTimeRequests = PlayerUtils.getRequests().get(warp);

        if (!playerTimeRequests.containsKey(requester.getUniqueId())){
            playerTimeRequests.put(requester.getUniqueId(), System.currentTimeMillis());
        }

    }

    private boolean manageRequest(final Player requester, final Warp warp) {

            if (PlayerUtils.getRequests().get(warp).containsKey(requester.getUniqueId())) {

                long currentTime = System.currentTimeMillis();
                System.out.println(currentTime);
                long l = PlayerUtils.getRequests().get(warp).get(requester.getUniqueId());
                System.out.println(l);
                long elapsed = (currentTime - l) / 1000;
                System.out.println(elapsed);

                if (WarpsAndHomes.getPlugin().getConfig().getBoolean("has-request-cooldown")) {

                    long cooldown = WarpsAndHomes.getPlugin().getConfig().getLong("request-cooldown");

                    if (elapsed >= cooldown) {
                        PlayerUtils.getRequests().get(warp).remove(requester.getUniqueId());
                        putInRequests(requester, warp);
                        return true;

                    } else {
                        requester.sendMessage(ChatColor.RED + "You can send a request again in " + ChatColor.DARK_GRAY + (cooldown - elapsed) + ChatColor.RESET + " seconds.");
                        return false;
                    }

                } else {
                    return true;
                }


            }else {
                putInRequests(requester, warp);
            }

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
