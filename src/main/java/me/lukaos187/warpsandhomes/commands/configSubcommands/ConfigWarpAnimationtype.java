package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigWarpAnimationtype extends ConfigCommandTemplate {
    public ConfigWarpAnimationtype(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "animation-type",
                "Animation set",
                "Something went horrible wrong..."
        );
    }

    @Override
    public String getName() {
        return "setWarpAnimation";
    }

    @Override
    public String getDescription() {
        return "Sets the warp-animation to the given one.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig setWarpAnimation <animation-type>";
    }

    @Override
    public void perform(String[] args, CommandSender sender) {

        if (args.length > 1) {

            String type = args[1];
            if (type == null) {
                sender.sendMessage(ChatColor.RED + "No valid animation-type!");
                return;
            }

            WarpsAndHomes.getPlugin().getConfig().set("animation-type", type.toUpperCase());
            WarpsAndHomes.getPlugin().saveConfig();

            sender.sendMessage("Warp-animation is now set to animation " + ChatColor.AQUA + type.toUpperCase());

        } else {
            sender.sendMessage(ChatColor.RED + "Please provide a animation-type!");
        }

    }

    @Override
    public List<String> getArgs(int argsLength) {
        if (argsLength == 1) {
            return new ArrayList<>(Arrays.asList("setWarpAnimation"));
        } else if (argsLength == 2) {
            //TODO IMPORTANT FOR NEW ANIMATIONS!
            return new ArrayList<>(Arrays.asList("ENDERMAN", "SPHERE", "PORTAL"));
        }
        return null;
    }
}
