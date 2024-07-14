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
package me.lukaos187.warpsandhomes.util;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**This is the main saving file of the plugin. The warp file manages all things that have to do with the warps.
 * To use this file you first have to call setUp().
 * @Usage: To use this file call setUp() before using
 * @Important: If you changed anything about a warp. Call saveWarp().*/
public class WarpFile {

    private File file;
    private YamlConfiguration conf;


    /**Sets up the warps.yml file and puts it into a YamlConfiguration-Object*/
    public void setUp() {
        file = new File(
                Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("WarpsAndHomes")).getDataFolder(), "warps.yml");
        conf = YamlConfiguration.loadConfiguration(file);
    }

    /**This will save the warps.yml file*/
    public void saveFile() {
        try {
            conf.save(file);
        } catch (IOException e) {
            System.out.println("Could not save the warps.yml file!");
        }
    }

    /**This method will remove the given warp from both, the warps.yml file and the PDC-String (warps)*/
    public void removeWarpFromAll(final Warp warp, final Player player) {

        conf.set(warp.getName(), null);
        saveFile();
        removeWarpFromPDC(player, warp);

    }

    /**This method will only remove the name of the given warp from the players PDC. To remove the warp from both,
     * the warps.yml file and the PDC of the player, call the removeWarpFromAll-method.*/
    public void removeWarpFromPDC(final Player player, final Warp warp) {

        PersistentDataContainer data = player.getPersistentDataContainer();

        if (data.has(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING)) {

            String warps = data.get(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING);

            if (warp == null){
                System.out.println("No warps found.");
                return;
            }

            List<String> w = new ArrayList<>(Arrays.asList(Objects.requireNonNull(warps).split(",")));
            w.remove(warp.getName());

            String serializedWarps = String.join(",", w);
            data.set(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING, serializedWarps);

        }

    }

    /**This will add the name of the given warp to the String warps in the Persistent-Data.Container of the given player.
     * A list of the current warp-names, which the player owns, from the players PDC can be retrieved from calling the
     * getWarps(final Player player)-method.*/
    public void addWarpToPDC(final Warp warp, final Player player) {

        PersistentDataContainer data = player.getPersistentDataContainer();

        if (data.has(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING)) {

            String warps = data.get(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING);

            if (warp == null){
                System.out.println("No warps found.");
                return;
            }

            List<String> warpsList = new ArrayList<>(Arrays.asList(Objects.requireNonNull(warps).split(",")));
            warpsList.add(warp.getName());

            String serializedWarps = String.join(",", warpsList);
            data.set(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING, serializedWarps);

        } else {
            data.set(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING, warp.getName());

        }

    }

    /**Saves the given warp object to the warps.yml file. It can be retrieved by calling the getWarp(final String name)
     * method with the name of the warp.*/
    public void saveWarp(final Warp warp) {


        conf.set(warp.getName() + ".owner", warp.getOwner().getName());
        conf.set(warp.getName() + ".description", warp.getDescription());
        conf.set(warp.getName() + ".world", Objects.requireNonNull(warp.getLocation().getWorld()).getName());
        conf.set(warp.getName() + ".x", String.valueOf(warp.getLocation().getX()));
        conf.set(warp.getName() + ".y", String.valueOf(warp.getLocation().getY()));
        conf.set(warp.getName() + ".z", String.valueOf(warp.getLocation().getZ()));
        conf.set(warp.getName() + ".yaw", String.valueOf(warp.getLocation().getYaw()));
        conf.set(warp.getName() + ".pitch", String.valueOf(warp.getLocation().getPitch()));
        conf.set(warp.getName() + ".isPrivate", String.valueOf(warp.isPrivate()));
        conf.set(warp.getName() + ".material", String.valueOf(warp.getDisplayItem()));

        saveFile();

    }

    /**Returns a Warp-Object with all the data for the warp that responds to the given name. If the warp for the given
     * name does not exist, this will return null instead.*/
    public Warp getWarp(final String name) {

        if (conf.get(name) != null && conf.getString(name + ".world") != null) {

            Location loc = new Location(
                    Bukkit.getWorld(Objects.requireNonNull(conf.getString(name + ".world"))),
                    Double.parseDouble(Objects.requireNonNull(conf.getString(name + ".x"))),
                    Double.parseDouble(Objects.requireNonNull(conf.getString(name + ".y"))),
                    Double.parseDouble(Objects.requireNonNull(conf.getString(name + ".z")))

            );
            loc.setYaw((float) Double.parseDouble(Objects.requireNonNull(conf.getString(name + ".yaw"))));
            loc.setPitch((float) Double.parseDouble(Objects.requireNonNull(conf.getString(name + ".pitch"))));

            Player owner = Bukkit.getPlayer(Objects.requireNonNull(conf.getString(name + ".owner")));

            Material material;
            try {
                material = Material.valueOf(conf.getString(name + ".material"));
            }catch (IllegalArgumentException e) {
                material = null;
            }

            return new Warp(
                    name,
                    conf.getString(name + ".description"),
                    owner,
                    Boolean.parseBoolean(conf.getString(name + ".isPrivate")),
                    loc,
                    material);

        }
        return null;
    }

    /**Returns a List<String> of all the warp-names that the given player owns. Returns null if the players does not own a
     * warp yet. */
    @Nullable
    public List<String> getWarps(final Player player) {

        PersistentDataContainer data = player.getPersistentDataContainer();

        if (data.has(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING)) {

            String warps = data.get(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING);

            if (warps == null){
                System.out.println("No warps found.");
                return null;
            }

            return new ArrayList<>(Arrays.asList(Objects.requireNonNull(warps).split(",")));

        }
        return null;
    }

    /**Removes all the warps that are not in the warps.yml file from the warps-String in the Persistent-Data-Container
     * of the given player.*/
    public void removeSuperfluousWarps(@Nonnull final Player player) {

        List<String> warps = getWarps(player);

        if(warps == null)
            return;

        PersistentDataContainer data = player.getPersistentDataContainer();

        Iterator<String> iterator = Objects.requireNonNull(warps).iterator();

        while (iterator.hasNext()) {

            String warpName = iterator.next();

            if (getWarp(warpName) == null) {
                System.out.println("Removed warp " + warpName);
                iterator.remove();  // Entferne das Element sicher über den Iterator
            }

        }

        if (data.has(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING)) {

            String serializedWarps = String.join(",", warps);
            data.set(new NamespacedKey(WarpsAndHomes.getPlugin(), "warps"), PersistentDataType.STRING, serializedWarps);

        }

    }


}
