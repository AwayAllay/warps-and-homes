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
package me.lukaos187.warpsandhomes;

import me.lukaos187.warpsandhomes.commands.ConfigCommandManager;
import me.lukaos187.warpsandhomes.commands.OpenMenu;
import me.lukaos187.warpsandhomes.commands.WarpCommandManager;
import me.lukaos187.warpsandhomes.listener.*;
import me.lukaos187.warpsandhomes.util.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class WarpsAndHomes extends JavaPlugin {

    private static WarpsAndHomes plugin;
    private WarpFile warpFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        setup();
    }

    /**The setup logic*/
    private void setup() {
        //Set up the File for the warp
        warpFile = new WarpFile();
        warpFile.setUp();
        //Set up the classes
        classesSetup(warpFile);
        SubcommandAdder subAdder = new SubcommandAdder(warpFile);
        //Register the commands
        Objects.requireNonNull(getCommand("warp")).setExecutor(new WarpCommandManager(warpFile, subAdder));
        Objects.requireNonNull(getCommand("wahConfig")).setExecutor(new ConfigCommandManager(warpFile, subAdder));
        if (getConfig().getBoolean("allow-warp-gui-per-chat"))
            Objects.requireNonNull(getCommand("warpMenu")).setExecutor(new OpenMenu(warpFile));
        //Register the commands
        getServer().getPluginManager().registerEvents(new OnJoin(warpFile), this);
        getServer().getPluginManager().registerEvents(new OnQuit(), this);
        getServer().getPluginManager().registerEvents(new OnDamage(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClick(), this);
        //Check the config.yml file for potential setting mistake
        if (!getConfig().getBoolean("allow-private-warps") && !getConfig().getBoolean("allow-public-warps")){
            new Messages(this).checkConfigErrorNuke();
            getLogger().info("[WarpsAndHomes] public and private warps are disabled! Enabling private warps!");
            getConfig().set("allow-private-warps", true);
            saveConfig();
        }
        //Send the hello message
        sendHello();
    }

    private void classesSetup(final WarpFile warpFile) {

        PlayerUtils.getSkinColors().clear();

        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player != null) {
                warpFile.removeSuperfluousWarps(player);
                PlayerUtils.getSkinColors().put(player.getUniqueId(), new SkinColorExtractor(player).getSkinColors());
            }
        });

    }

    /**The message that will be sent when the server enables the plugin (if enabled in config.yml)*/
    private void sendHello() {
        if (getConfig().getBoolean("allow-goodbye-and-hello-message")) {
            new Messages(this).randomHelloMessage();
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
            new Messages(this).randomGoodbyeMessage();
        }
    }

    /**The getter for the instance of the Main class.
     * @Returns an instance of the Main class.*/
    public static WarpsAndHomes getPlugin() {
        return plugin;
    }
}
