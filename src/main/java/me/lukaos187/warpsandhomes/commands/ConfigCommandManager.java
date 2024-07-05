package me.lukaos187.warpsandhomes.commands;

import me.lukaos187.warpsandhomes.commands.configSubcommands.*;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommandManager implements TabExecutor {

    private final List<ConfigSubcommand> subcommands = new ArrayList<>();
    private final WarpFile warpFile;
    private final SubcommandAdder subAdder;

    public ConfigCommandManager(WarpFile warpFile, SubcommandAdder subAdder) {
        this.warpFile = warpFile;
        this.subAdder = subAdder;
        addConfigSubcommands();
    }

    private void addConfigSubcommands() {
        subcommands.add(new ConfigWarpDelete(warpFile, subAdder));
        subcommands.add(new ConfigWarpDescribe(warpFile, subAdder));
        subcommands.add(new ConfigWarpHandOver(warpFile, subAdder));
        subcommands.add(new ConfigWarpInfo(warpFile, subAdder));
        subcommands.add(new ConfigWarpList(warpFile, subAdder));
        subcommands.add(new ConfigWarpRename(warpFile, subAdder));
        subcommands.add(new ConfigWarpUpdate(warpFile, subAdder));
        subcommands.add(new ConfigWarpPublic(warpFile, subAdder));
        subcommands.add(new ConfigWarpPrivate(warpFile, subAdder));
        subcommands.add(new ConfigWarpClear(warpFile, subAdder));
        subcommands.add(new ConfigReload(warpFile, subAdder));
        subcommands.add(new ConfigWarpParticleColor(warpFile, subAdder));
        subcommands.add(new ConfigWarpAnimationtype(warpFile, subAdder));
        subcommands.add(new ConfigAllowAnimations(warpFile, subAdder));
        subcommands.add(new ConfigWarpMax(warpFile, subAdder));
        subcommands.add(new ConfigWarpMaxWarps(warpFile, subAdder));
        subcommands.add(new ConfigDamageCooldown(warpFile, subAdder));
        subcommands.add(new ConfigDamageCooldownPlayer(warpFile, subAdder));
        subcommands.add(new ConfigSetDamageCooldown(warpFile, subAdder));
        subcommands.add(new ConfigCrossdimensionalWarping(warpFile, subAdder));
        subcommands.add(new ConfigWarpCooldown(warpFile, subAdder));
        subcommands.add(new ConfigSetCooldown(warpFile, subAdder));
        subcommands.add(new ConfigWarpSound(warpFile, subAdder));
        subcommands.add(new ConfigWarpRequest(warpFile, subAdder));
        subcommands.add(new ConfigWarpRequest(warpFile, subAdder));
        subcommands.add(new ConfigAllowPrivateRequests(warpFile, subAdder));
        subcommands.add(new ConfigHasRequestCooldown(warpFile, subAdder));
        subcommands.add(new ConfigSetRequestCooldown(warpFile, subAdder));
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        manage(sender, args);

        return true;
    }

    private void manage(final CommandSender sender, final String[] args) {

        // /warp <subcommand> <name> <fu> <bar>
        if (!sender.hasPermission("warpsandhomes.admin")){
            sender.sendMessage("You do not have permission to execute this command!");
            return;
        }

        if (args.length > 0) {

            if (sender instanceof CommandBlock) {
                return;
            }

            for (ConfigSubcommand command : subcommands) {
                if (args[0].equalsIgnoreCase(command.getName())) {
                    command.perform(args, sender);
                    break;
                }
            }

        } else {
            sendInfo(sender);
            sendMissingArgsMessage(sender);
        }

    }

    private void sendMissingArgsMessage(final CommandSender sender) {
        sender.sendMessage("Please provide more arguments to use that command!");
    }

    private void sendInfo(final CommandSender sender) {

        String header = "---------------[WarpsAndHomes-wahConfig]---------------";
        String coloredH = "---------------[" + ChatColor.AQUA + "WarpsAndHomes-wahConfig" + ChatColor.RESET + "]---------------";

        sender.sendMessage(coloredH);
        for (ConfigSubcommand command : subcommands) {
            sender.sendMessage(ChatColor.AQUA + command.getUsage() + " - " + ChatColor.GOLD + command.getDescription());
        }
        String footer = "-".repeat(header.length());
        sender.sendMessage(footer);
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof CommandBlock)
            return null;


        if (args.length == 1) {

            ArrayList<String> sub = new ArrayList<>();
            subcommands.forEach(subcommand -> {
                if (subcommand != null && subcommand.getName() != null)
                    sub.add(subcommand.getName());
            });

            return sub;

        } else if (args.length == 2) {

            for (ConfigSubcommand s : subcommands) {
                if (s != null && s.getName() != null && s.getName().equalsIgnoreCase(args[0]))
                    return s.getArgs(2);
            }

        } else if (args.length == 3) {

            for (ConfigSubcommand s : subcommands) {
                if (s != null && s.getName() != null && s.getName().equalsIgnoreCase(args[0]))
                    return s.getArgs(3);
            }

        } else if (args.length == 4) {

            for (ConfigSubcommand s : subcommands) {
                if (s != null && s.getName() != null && s.getName().equalsIgnoreCase(args[0]))
                    return s.getArgs(4);
            }
        }


        return null;
    }
}
