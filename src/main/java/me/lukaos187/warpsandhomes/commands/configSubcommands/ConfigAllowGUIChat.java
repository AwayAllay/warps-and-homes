package me.lukaos187.warpsandhomes.commands.configSubcommands;

import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;

public class ConfigAllowGUIChat extends ConfigCommandTemplate{
    public ConfigAllowGUIChat(WarpFile warpFile, SubcommandAdder subAdder) {
        super(warpFile, subAdder, "allow-warp-gui-per-chat",
                "Allow-warp-gui-per-chat is now set to " + ChatColor.GREEN + "true",
                "Allow-warp-gui-per-chat is now set to " + ChatColor.RED + "false");
    }

    @Override
    public String getName() {
        return "allowChatGUI";
    }

    @Override
    public String getDescription() {
        return "En-/Disables if players can open the GUI per chat. (Reload server for best experience)";
    }

    @Override
    public String getUsage() {
        return "/wahconfig allowChatGUI <true/false>";
    }
}
