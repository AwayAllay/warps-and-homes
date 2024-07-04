package me.lukaos187.warpsandhomes.commands;

import me.lukaos187.warpsandhomes.commands.warpSubcommands.Subcommand;
import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RejectRequest implements Subcommand {

    private final WarpFile warpFile;
    public RejectRequest(WarpFile warpFile) {
        this.warpFile = warpFile;
    }


    @Override
    public String getName() {
        return "reject";
    }

    @Override
    public String getDescription() {
        return "Rejects to hand the ownership of a warp over to another player";
    }

    @Override
    public String getUsage() {
        return "/warp reject <warp-name> <player-name>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length > 2){

            Player requester = Bukkit.getPlayer(args[2]);
            Warp warp = warpFile.getWarp(args[1]);

            if (requester == null || warp == null) {
                player.sendMessage(ChatColor.RED + "Please try again.");
                player.sendMessage("Use the command like this: " + ChatColor.AQUA + "/warp reject <warpName> <requesterName>");
                return;
            }
            if (!warp.getOwner().equals(player)){
                requester.sendMessage(ChatColor.RED + "You can not reject the requests of other people!");
                return;
            }

            reject(warp, requester, player);


        } else if (args.length == 2) {
            player.sendMessage(ChatColor.RED + "Please provide a name for the warp you want to reject.");
            player.sendMessage("Use the command like this: " + ChatColor.AQUA + "/warp reject <warpName> <requesterName>");
        } else {
            player.sendMessage(ChatColor.RED + "Please provide a name for the requester you want to reject and a warp.");
            player.sendMessage("Use the command like this: " + ChatColor.AQUA + "/warp reject <warpName> <requesterName>");
        }
    }

    private void reject(final Warp warp, final Player requester, final Player owner) {

        owner.sendMessage(ChatColor.RED + "Rejected " + ChatColor.RESET + "the request of " + ChatColor.DARK_GRAY + requester.getName());
        requester.sendMessage(ChatColor.DARK_GRAY + owner.getName() + ChatColor.RED + " rejected " + ChatColor.RESET + "your request for " + warp.getName());
        manageReject(requester, warp);
    }
    private void manageReject(final Player requester, final Warp warp) {

        Map<UUID, Long> pTR = PlayerUtils.getRequests().get(warp);
        if (pTR == null)
            return;

        if (!pTR.containsKey(requester.getUniqueId()))
            return;

        pTR.remove(requester.getUniqueId());
    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {
        return null;
    }
}
