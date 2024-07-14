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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarpDisplayItems {

    public static List<ItemStack> getItems(){

        List<ItemStack> toReturn = new ArrayList<>();

        List<Material> materials = getMaterials();

        for (Material m : materials) {
            toReturn.add(new ItemStack(m));
        }

        return toReturn;
    }

    @Nonnull
    private static List<Material> getMaterials() {

        List<Material> materials = Arrays.asList(
                Material.OAK_PLANKS, Material.STONE, Material.IRON_INGOT, Material.GOLD_INGOT, Material.DIAMOND,
                Material.NETHERITE_INGOT, Material.NETHERITE_PICKAXE, Material.NETHERITE_SWORD, Material.NETHERITE_AXE,
                Material.NETHERITE_SHOVEL, Material.NETHERITE_HOE, Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE,
                Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS, Material.TORCH, Material.RED_BED, Material.BOW,
                Material.ARROW, Material.CROSSBOW, Material.SHIELD, Material.FISHING_ROD, Material.BUCKET,
                Material.WATER_BUCKET, Material.LAVA_BUCKET, Material.REDSTONE, Material.COMPASS, Material.CLOCK,
                Material.PAPER, Material.BOOK, Material.BOOKSHELF, Material.ENCHANTING_TABLE, Material.ANVIL,
                Material.FLOWER_POT, Material.SHEARS, Material.FLINT_AND_STEEL, Material.ENDER_PEARL, Material.ENDER_EYE,
                Material.GOLDEN_APPLE, Material.ENCHANTED_GOLDEN_APPLE, Material.CARROT, Material.POTATO, Material.WHEAT,
                Material.SUGAR_CANE, Material.MELON_SLICE, Material.PUMPKIN, Material.JACK_O_LANTERN, Material.MELON,
                Material.HONEY_BLOCK, Material.SLIME_BLOCK, Material.TNT, Material.LEVER, Material.STONE_BUTTON,
                Material.STONE_PRESSURE_PLATE, Material.RAIL, Material.POWERED_RAIL, Material.HOPPER, Material.CHEST,
                Material.ENDER_CHEST, Material.SHULKER_BOX, Material.BEACON, Material.ELYTRA, Material.END_CRYSTAL,
                Material.POTION, Material.SPLASH_POTION, Material.LINGERING_POTION, Material.CRAFTING_TABLE, Material.FURNACE,
                Material.COAL, Material.NETHER_QUARTZ_ORE, Material.BRICK, Material.OAK_DOOR, Material.IRON_DOOR,
                Material.GRINDSTONE, Material.CAMPFIRE, Material.LANTERN, Material.LADDER, Material.TRIPWIRE_HOOK,
                Material.PISTON, Material.OBSERVER, Material.DISPENSER, Material.HOPPER_MINECART, Material.TNT_MINECART,
                Material.MELON, Material.DARK_OAK_PRESSURE_PLATE, Material.MAGMA_BLOCK, Material.SOUL_SAND,
                Material.GLOWSTONE, Material.BREWING_STAND, Material.LADDER, Material.SLIME_BLOCK, Material.HONEY_BLOCK,
                Material.OAK_TRAPDOOR, Material.IRON_TRAPDOOR, Material.CAULDRON, Material.MINECART, Material.OAK_FENCE,
                Material.BIRCH_FENCE
        );

        return materials;
    }

}
