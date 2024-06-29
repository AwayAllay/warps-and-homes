package me.lukaos187.warpsandhomes;

import me.lukaos187.warpsandhomes.commands.ConfigCommandManager;
import me.lukaos187.warpsandhomes.commands.OpenMenu;
import me.lukaos187.warpsandhomes.commands.WarpCommandManager;
import me.lukaos187.warpsandhomes.listener.*;
import me.lukaos187.warpsandhomes.util.PlayerUtils;
import me.lukaos187.warpsandhomes.util.SkinColorExtractor;
import me.lukaos187.warpsandhomes.util.SubcommandAdder;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class WarpsAndHomes extends JavaPlugin {

    private static WarpsAndHomes plugin;
    private WarpFile warpFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        plugin = this;
        setup();
    }

    private void setup() {


        warpFile = new WarpFile();

        //Set up the File for the warp
        warpFile.setUp();
        PlayerUtils.getSkinColors().clear();


        Bukkit.getOnlinePlayers().forEach(player -> {
            warpFile.removeSuperfluousWarps(player);
            PlayerUtils.getSkinColors().put(player.getUniqueId(), new SkinColorExtractor(player).getSkinColors());
        });

        SubcommandAdder subAdder = new SubcommandAdder(warpFile);

        Objects.requireNonNull(getCommand("warp")).setExecutor(new WarpCommandManager(warpFile, subAdder));
        Objects.requireNonNull(getCommand("wahConfig")).setExecutor(new ConfigCommandManager(warpFile, subAdder));
        Objects.requireNonNull(getCommand("warpMenu")).setExecutor(new OpenMenu(warpFile));
        getServer().getPluginManager().registerEvents(new OnJoin(warpFile), this);
        getServer().getPluginManager().registerEvents(new OnQuit(), this);
        getServer().getPluginManager().registerEvents(new OnDamage(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);

        sendHello();
    }

    private void sendHello() {
        if (getConfig().getBoolean("allow-goodbye-and-hello-message")) {
            getServer().getLogger().info("[WarpsAndHomes] Hello!");
            getServer().getLogger().info("[WarpsAndHomes] Plugin is now enabled!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        warpFile.saveFile();
        sendGoodbye();
    }

    private void sendGoodbye() {
        if (getConfig().getBoolean("allow-goodbye-and-hello-message")) {
            getServer().getLogger().info("[WarpsAndHomes] Goodbye!");
            getServer().getLogger().info("[WarpsAndHomes] Plugin is now disabled!");
        }
    }

    public static WarpsAndHomes getPlugin() {
        return plugin;
    }
}
