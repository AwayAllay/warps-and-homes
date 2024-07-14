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

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**This is the warp class. One object represents a warp that includes all the needed information, such as the owner, the location, the name, the description...*/
public class Warp {

    private String name;
    private String description;
    private Player owner;
    private boolean isPrivate;
    private Location location;
    private Material displayItem;

    public Warp(@Nonnull String name, String description, Player owner, boolean isPrivate, Location location, Material displayItem) {
        this.name = Objects.requireNonNull(name);
        this.description = description;
        this.owner = owner;
        this.isPrivate = isPrivate;
        this.location = location;
        this.displayItem = displayItem;
    }

    /**Gets the name of the warp.*/
    @Nonnull
    public String getName() {
        return name;
    }

    /**Sets the name of the warp.*/
    public void setName(String name) {
        this.name = name;
    }

    /**Gets the description of the warp.*/
    public String getDescription() {
        return description;
    }

    /**Sets the description of the warp.*/
    public void setDescription(String description) {
        this.description = description;
    }

    /**Gets the owner of the warp.*/
    public Player getOwner() {
        return owner;
    }

    /**Sets the owner of the warp.*/
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**Gets if the warp is set to private (true) or public (false).*/
    public boolean isPrivate() {
        return isPrivate;
    }

    /**Sets this warp to private(true) or public(false).*/
    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    /**Gets the location of the warp*/
    public Location getLocation() {
        return location;
    }

    /**Sets the location of the warp*/
    public void setLocation(Location location) {
        this.location = location;
    }

    /**Returns the saved material for the warp.*/
    @Nullable
    public Material getDisplayItem(){
        return displayItem;
    }

    /**Sets the displayItem-Material for this warp.*/
    public void setDisplayItem(Material newItem){
        this.displayItem = newItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warp warp = (Warp) o;
        return name.equals(warp.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
