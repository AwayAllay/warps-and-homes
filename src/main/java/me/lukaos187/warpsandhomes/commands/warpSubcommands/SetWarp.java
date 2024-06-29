package me.lukaos187.warpsandhomes.commands.warpSubcommands;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SetWarp implements Subcommand {

    private final WarpFile warpFile;

    public SetWarp(WarpFile warpFile) {
        this.warpFile = warpFile;
    }


    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getDescription() {
        return "Sets a warp at your location.";
    }

    @Override
    public String getUsage() {
        return "/warp set <name> <isPrivate?> <description>";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("enable-max-player-warps")){
            if (warpFile.getWarps(player).size() >=
                    WarpsAndHomes.getPlugin().getConfig().getLong("max-player-warps")){
                player.sendMessage(ChatColor.RED + "You have reached your maximum amount of " + ChatColor.AQUA +
                        WarpsAndHomes.getPlugin().getConfig().get("max-player-warps") + ChatColor.RED + " warps." );
                player.sendMessage("Please delete some warps before you can set new ones.");
                return;
            }
        }

        initializeWarp(player, args);
    }

    @Override
    public List<String> getArgs(int argsLength, Player player) {

        if (argsLength == 1) {
            List<String> tab = new ArrayList<>();
            tab.add("set");
            return tab;
        } else if (argsLength == 3) {
            List<String> tab = new ArrayList<>();
            if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps"))//Only private warps if enabled in config
                tab.add("true");
            if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps"))//Only public warps if enabled in config
                tab.add("false");
            return tab;
        }

        return null;
    }

    private void initializeWarp(final Player player, final String[] args) {

        if (args.length > 1) {

            String name = args[1];

            if (warpFile.getWarp(name) != null) {
                player.sendMessage("This warp is already owned by " + ChatColor.AQUA + warpFile.getWarp(name).getOwner().getName());
                return;
            }

            Warp warp = buildWarp(player, args, name);

            if (warp == null)
                return;

            warpFile.saveWarp(warp);
            warpFile.addWarpToPDC(warp, player);

            player.sendMessage("Saved warp " + ChatColor.AQUA +
                    name);
            if (WarpsAndHomes.getPlugin().getConfig().getBoolean("enable-max-player-warps")){
                player.sendMessage("You have " + ChatColor.AQUA +
                        (WarpsAndHomes.getPlugin().getConfig().getLong("max-player-warps") - warpFile.getWarps(player).size())
                                + ChatColor.RESET + " warps left to set.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "Please provide more arguments!");
            player.sendMessage("Example: " + ChatColor.AQUA + "/warp set <name> <isPrivate?> <description>");
        }
    }

    private Warp buildWarp(final Player owner, final String[] args, final String warpName) {

        if (getIsPrivate(args, owner) == null)
            return null;


        boolean isPrivate = Boolean.parseBoolean(getIsPrivate(args, owner));
        String desc = ChatColor.GRAY + "No description specified";

        if (args.length > 2) {

            if (getIsPrivate(args, owner) == null)
                return null;

            isPrivate = Boolean.parseBoolean(getIsPrivate(args, owner));

            if (args.length > 3) {

                StringBuilder sb = new StringBuilder();
                for (int i = 3; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                desc = sb.toString().trim();
            }
        }

        Warp warp = new Warp(warpName, desc, owner, isPrivate, owner.getLocation());
        return warp;
    }

    private String getIsPrivate(String[] args, Player player) {
        boolean allowPrivateWarps = WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps");
        boolean allowPublicWarps = WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps");

        if (!allowPrivateWarps && !allowPublicWarps) {
            WarpsAndHomes.getPlugin().getServer().getLogger().info(ChatColor.RED + "[WarpsAndHomes] ONLY ONE PUBLIC MODIFIER IS ALLOWED TO BE FALSE!");
            WarpsAndHomes.getPlugin().getServer().getLogger().info(ChatColor.RED + "[WarpsAndHomes] PLEASE CHECK THE CONFIG.YML!");
            WarpsAndHomes.getPlugin().getServer().getLogger().info(ChatColor.RED + "[WarpsAndHomes] SETTING WARPS IS NOW DISABLED!");
            return null;
        }

        boolean b = args.length >= 3 && args[2] != null && Boolean.parseBoolean(args[2]);

        if (b) {
            if (allowPrivateWarps) {
                return "true";
            } else {
                player.sendMessage(ChatColor.RED + "Private warps are disabled!");
                return null;
            }
        } else {
            if (args.length < 3 || args[2] == null) {
                if (!allowPublicWarps) {
                    if (allowPrivateWarps) {
                        return "true";
                    } else {
                        player.sendMessage(ChatColor.RED + "Private warps are disabled!");
                        return null;
                    }
                }
            }
            if (allowPublicWarps) {
                return "false";
            } else {
                player.sendMessage(ChatColor.RED + "Public warps are disabled!");
                return null;
            }
        }
    }



}
