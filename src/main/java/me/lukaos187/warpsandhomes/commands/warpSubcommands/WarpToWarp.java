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

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import me.lukaos187.warpsandhomes.warpanimations.EndermanAnimation;
import me.lukaos187.warpsandhomes.warpanimations.PortalAnimation;
import me.lukaos187.warpsandhomes.warpanimations.SphereAnimation;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarpToWarp implements Subcommand {

    private final WarpFile warpFile;

    public WarpToWarp(WarpFile warpFile) {
        this.warpFile = warpFile;
    }

    @Override
    public String getName() {
        return "warp";
    }

    @Override
    public String getDescription() {
        return "Warps you to the given warp.";
    }

    @Override
    public String getUsage() {
        return "/warp warp <name>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 1) {

            Warp warp = warpFile.getWarp(args[1]);

            if (warp == null) {
                player.sendMessage(ChatColor.RED + "This warp does not exist!");
                return;
            }
            if (!warp.getOwner().equals(player) && warp.isPrivate()) {
                player.sendMessage("This warp is " + ChatColor.RED + "private " + ChatColor.RESET + "and owned by " +
                        ChatColor.AQUA + warp.getOwner().getName());
                return;
            }
            if (!playerCanWarp(player,warp)){
                return;
            }
            if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-animations")) {
                animate(warp, player);
            } else {
                if (WarpsAndHomes.getPlugin().getConfig().getBoolean("warp-cooldown"))
                    PlayerUtils.getCooldown().put(player.getUniqueId(), System.currentTimeMillis());

                player.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5F, 5F);
                player.teleport(warp.getLocation());
                player.sendMessage("Warped you to " + ChatColor.AQUA + warp.getName());
            }

        } else {
            player.sendMessage(ChatColor.RED + "Please provide a warp to warp to.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp warp <warp-name>");
        }
    }

    private boolean playerCanWarp(final Player player, final Warp warp) {

        if (!canDimensionalWarp(player, warp)){
            player.sendMessage(ChatColor.RED + "You can not warp to a warp that is in another dimension.");
            return false;
        }

        if (!canWarpAgain(player)){
            return false;
        }

        if (!canWarpAgainDamage(player)){
            return false;
        }


        return true;
    }

    private boolean canWarpAgainDamage(final Player player) {

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("has-damage-warp-cooldown")){

            if (!PlayerUtils.getLastDamage().containsKey(player.getUniqueId()))
                return true;

            long lastD = PlayerUtils.getLastDamage().get(player.getUniqueId());
            long cooldown = WarpsAndHomes.getPlugin().getConfig().getLong("damage-warp-cooldown") * 1000;

            if ((System.currentTimeMillis() - lastD) > cooldown){
                return true;
            }else {
                long timeLeftM = lastD + cooldown - System.currentTimeMillis();
                long timeLeftS = timeLeftM / 1000;
                player.sendMessage("You can warp again in " + ChatColor.AQUA + timeLeftS + ChatColor.RESET + " seconds");
                return false;
            }
        }
        return true;
    }

    private boolean canWarpAgain(final Player player) {

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("warp-cooldown")){

            if (!PlayerUtils.getCooldown().containsKey(player.getUniqueId()))
                return true;

            long lastD = PlayerUtils.getCooldown().get(player.getUniqueId());
            long cooldown = WarpsAndHomes.getPlugin().getConfig().getLong("cooldown-time") * 1000;

            if ((System.currentTimeMillis() - lastD) > cooldown){
                return true;
            }else {
                long timeLeftM = lastD + cooldown - System.currentTimeMillis();
                long timeLeftS = timeLeftM / 1000;
                player.sendMessage("You can warp again in " + ChatColor.AQUA + timeLeftS + ChatColor.RESET + " seconds");
                return false;
            }
        }
        return true;

    }


    private boolean canDimensionalWarp(final Player player, final Warp warp) {

        if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-crossdimentional-warping")) {

            try {
                return player.getWorld().getEnvironment().equals(warp.getLocation().getWorld().getEnvironment());
            }catch (NullPointerException e){
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Something went a little wrong...");
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Please try again");
                return false;
            }
        }
        return true;
    }

    private void animate(final Warp warp, final Player player) {

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("warp-cooldown"))
            PlayerUtils.getCooldown().put(player.getUniqueId(), System.currentTimeMillis());

        if (WarpsAndHomes.getPlugin().getConfig().getString("animation-type") == null){
            new EndermanAnimation(player, warp);
            System.out.println("[WarpsAndHomes] No animation provided in the config.yml!!!");
        }
        if (WarpsAndHomes.getPlugin().getConfig().getString("animation-type") == null){
            new EndermanAnimation(player, warp);
            System.out.println("[WarpsAndHomes] No animation provided in the config.yml!!!");
            player.sendMessage(ChatColor.RED + "[WarpsAndHomes] No animation provided in the config.yml!!!");
            return;
        }

        switch (Objects.requireNonNull(WarpsAndHomes.getPlugin().getConfig().getString("animation-type"))){

            case "ENDERMAN" -> new EndermanAnimation(player, warp);
            case "SPHERE" -> new SphereAnimation(player, warp);
            case "PORTAL" -> new PortalAnimation(player, warp);
            default -> {
                new EndermanAnimation(player, warp);
                System.out.println("[WarpsAndHomes] No animation provided in the config.yml!!!");
                player.sendMessage(ChatColor.RED + "[WarpsAndHomes] No animation provided in the config.yml!!!");
            }
        }
    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1) {
            List<String> tab = new ArrayList<>();
            tab.add("warp");
            return tab;
        } else if (argsLength == 2) {

            List<String> toReturn = new ArrayList<>();
            List<String> warps = warpFile.getWarps(player);
            if (warps != null) {
                toReturn.addAll(warps);
            }

            Bukkit.getOnlinePlayers().forEach(player1 -> {//Iterate online players

                List<String> pWarps = warpFile.getWarps(player1);//get warps of online

                if (pWarps != null) {//if the warps not null

                    for (String s : pWarps) {//iterate pWarps
                        Warp warp = warpFile.getWarp(s); //get warp by name

                        if (warp != null && !warp.isPrivate()) {//if warp is not private add it to the list
                            toReturn.add(warp.getName());
                        }
                    }
                }

            });

            return toReturn;
        }

        return null;
    }
}
