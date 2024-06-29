package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigWarpParticleColor extends ConfigCommandTemplate {
    public ConfigWarpParticleColor(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "particle-colour",
                "",
                "");
    }

    @Override
    public String getName() {
        return "setParticleColour";
    }

    @Override
    public String getDescription() {
        return "Sets the colour of the warp animation to the given one.";
    }

    @Override
    public String getUsage() {
        return "/wahconfig setParticleColour <colour>";
    }

    @Override
    public void perform(String[] args, CommandSender sender) {

        if (args.length > 1) {

            String colour = args[1];

            WarpsAndHomes.getPlugin().getConfig().set("particle-colour", colour.toUpperCase());
            sender.sendMessage("Particle-colour set to " + ChatColor.AQUA + colour.toUpperCase());
            WarpsAndHomes.getPlugin().saveConfig();

        } else {
            sender.sendMessage(ChatColor.RED + "please provide a colour.");
            sender.sendMessage("Use: " + ChatColor.AQUA + "/wahconfig particleColour <colour>");
        }

    }

    @Override
    public List<String> getArgs(int argsLength) {

        if (argsLength == 1) {
            return new ArrayList<>(Arrays.asList("setParticleColour"));
        } else if (argsLength == 2) {
            List<String> colours = new ArrayList<>();
            colours.add("PLAYER");
            Field[] fields = Color.class.getFields();

            for (Field field : fields) {
                if (field.getType() == Color.class) {
                    colours.add(field.getName());
                }
            }
            return colours;
        }

        return null;
    }
}
