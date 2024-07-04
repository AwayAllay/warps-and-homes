package me.lukaos187.warpsandhomes.commands;

import me.lukaos187.warpsandhomes.commands.warpSubcommands.Subcommand;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpHandover;
import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AcceptRequest implements Subcommand {

    private final WarpFile warpFile;

    public AcceptRequest(WarpFile warpFile) {
        this.warpFile = warpFile;
    }

    @Override
    public String getName() {
        return "accept";
    }

    @Override
    public String getDescription() {
        return "Accepts to hand the ownership of a warp over to another player";
    }

    @Override
    public String getUsage() {
        return "/warp accept <warp-name> <player-name>";
    }

    // requester -> /warp request ....
    //text message
    // owner -> accept
    // owner -> /warp accept + warp.getName() + requester.name
    @Override
    public void perform(Player owner, String[] args) {

        if (args.length > 2){

            Player requester = Bukkit.getPlayer(args[2]);
            Warp warp = warpFile.getWarp(args[1]);

            if (requester == null || warp == null) {
                owner.sendMessage(ChatColor.RED + "Please try again.");
                owner.sendMessage("Use the command like this: " + ChatColor.AQUA + "/warp accept <warpName> <requesterName>");
                return;
            }

            if (!warp.getOwner().equals(owner)){
                requester.sendMessage(ChatColor.RED + "You can not accept the requests of other people!");
                return;
            }

            handover(warp, requester, owner);


        } else if (args.length == 2) {
            owner.sendMessage(ChatColor.RED + "Please provide a name for the warp you want to hand over.");
            owner.sendMessage("Use the command like this: " + ChatColor.AQUA + "/warp accept <warpName> <requesterName>");
        } else {
            owner.sendMessage(ChatColor.RED + "Please provide a name for the requester you want to hand over to and a warp.");
            owner.sendMessage("Use the command like this: " + ChatColor.AQUA + "/warp accept <warpName> <requesterName>");
        }

    }

    private void handover(final Warp warp, final Player newOwner, final Player owner) {

        String[]args = {"hand-over" ,warp.getName(), newOwner.getName()};

        owner.playSound(owner, Sound.UI_TOAST_CHALLENGE_COMPLETE, 5F, 2F);

        new WarpHandover(warpFile).perform(owner, args);
        manageAccept(newOwner, warp);
    }

    private void manageAccept(final Player newOwner, final Warp warp) {

        Map<UUID, Long> pTR = PlayerUtils.getRequests().get(warp);
        if (pTR == null)
            return;

        if (!pTR.containsKey(newOwner.getUniqueId()))
            return;

        pTR.remove(newOwner.getUniqueId());

    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1){
            return new ArrayList<>(List.of("accept"));

        } else if (argsLength == 2) {
            List<String> warps = warpFile.getWarps(player);

            if (warps == null)
                return null;

            return warps;
        }else if (argsLength == 3){

            List<String> playerNames = new ArrayList<>();
            Bukkit.getOnlinePlayers().forEach(online ->  playerNames.add(online.getName()));

            return playerNames;
        }

        return null;
    }
}
