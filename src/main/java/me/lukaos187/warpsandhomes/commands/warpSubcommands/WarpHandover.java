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
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WarpHandover implements Subcommand{

    private final WarpFile warpFile;

    public WarpHandover(WarpFile warpFile){
        this.warpFile = warpFile;
    }


    @Override
    public String getName() {
        return "hand-over";
    }

    @Override
    public String getDescription() {
        return "Changes the owner of the given warp to the given owner.";
    }

    @Override
    public String getUsage() {
        return "/warp hand-over <name> <new-owner>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (args.length > 1){

            if (!isAllCorrect(player, args))
                return;


            Warp warp = warpFile.getWarp(args[1]);
            Player newOwner = Bukkit.getPlayer(args[2]);


            warp.setOwner(newOwner);
            warpFile.saveWarp(warp);

            removeWarp(player, newOwner, warp);

            player.sendMessage(ChatColor.AQUA + warp.getName() + ChatColor.RESET + " is now handed over to "
                    + ChatColor.DARK_GRAY + newOwner.getName());


        }else {
            player.sendMessage(ChatColor.RED + "Please provide a warp to hand over.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp hand-over <name> <new-owner>");
        }

    }

    private boolean isAllCorrect(final Player player, final String[] args) {

        Warp test = warpFile.getWarp(args[1]);

        if (args.length < 3){
            player.sendMessage(ChatColor.RED + "Please provide a owner to hand over to.");
            player.sendMessage("Use: " + ChatColor.AQUA + "/warp hand-over <name> <new-owner>");
            return false;
        }

        if (Bukkit.getPlayer(args[2]) == null){
            player.sendMessage(ChatColor.RED + "This player has to be online.");
            return false;
        }

        if (test == null){
            player.sendMessage(ChatColor.RED + "This warp does not exist.");
            return false;
        }

        if (!test.getOwner().equals(player)){
            player.sendMessage(ChatColor.RED + "Warps can only be handed over by their owner.");
            player.sendMessage("Ask " + ChatColor.AQUA + test.getOwner().getName() + ChatColor.RESET + " to hand this" +
                    " warp over.");
            return false;
        }

        return true;
    }

    private void removeWarp(final Player player, final Player newOwner, final Warp warp) {

        warpFile.removeWarpFromPDC(player, warp);
        warpFile.addWarpToPDC(warp, newOwner);

        newOwner.playSound(newOwner, Sound.UI_TOAST_CHALLENGE_COMPLETE, 5F, 2F);
        newOwner.sendMessage(ChatColor.DARK_GRAY + player.getName() + ChatColor.RESET + " just gave you the ownership of the warp " + ChatColor.AQUA + warp.getName());
    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1){
            List<String> tab = new ArrayList<>();
            tab.add("hand-over");
            return tab;
        } else if (argsLength == 2) {
            List<String> warps = warpFile.getWarps(player);

            if (warps == null)
                return null;

            return warps;
        }
        return null;
    }
}
