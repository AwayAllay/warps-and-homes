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

    /**The setup logic*/
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

        if (getConfig().getBoolean("allow-warp-gui-per-chat"))
            Objects.requireNonNull(getCommand("warpMenu")).setExecutor(new OpenMenu(warpFile));

        getServer().getPluginManager().registerEvents(new OnJoin(warpFile), this);
        getServer().getPluginManager().registerEvents(new OnQuit(), this);
        getServer().getPluginManager().registerEvents(new OnDamage(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);

        if (getConfig().getBoolean("allow-private-warps") && getConfig().getBoolean("allow-public-warps")){
            getConfig().set("allow-private-warps", true);
            getLogger().info("[WarpsAndHomes] public and private warps are disabled! Enabling private warps!");
        }

        sendHello();
    }

    /**The message that will be sent when the server enables the plugin (if enabled in config.yml)*/
    private void sendHello() {
        if (getConfig().getBoolean("allow-goodbye-and-hello-message")) {
            getServer().getLogger().info("*--------------------------------------------------*");
            getServer().getLogger().info("|      Welcome to WarpsAndHomes - Have fun!!!      |");
            getServer().getLogger().info("*--------------------------------------------------*");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        warpFile.saveFile();
        sendGoodbye();
    }

    /**The message that will be sent when the server disables the plugin (if enabled in config.yml)*/
    private void sendGoodbye() {
        if (getConfig().getBoolean("allow-goodbye-and-hello-message")) {
            getServer().getLogger().info("*--------------------------------------------------*");
            getServer().getLogger().info("|            [WarpsAndHomes] Goodbye!!!            |");
            getServer().getLogger().info("*--------------------------------------------------*");
        }
    }

    /**The getter for the instance of the Main class.
     * @Returns an instance of the Main class.*/
    public static WarpsAndHomes getPlugin() {
        return plugin;
    }
}
