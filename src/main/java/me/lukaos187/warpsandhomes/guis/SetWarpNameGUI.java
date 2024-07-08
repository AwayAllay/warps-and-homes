package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.SetWarp;
import me.lukaos187.warpsandhomes.util.HeadGetter;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class SetWarpNameGUI {

    //TODO implement warpitem
    private final Player player;
    private String warpName;
    private final List<String> description;
    private final ItemStack displayItem;
    private final boolean isPrivate;
    private final WarpFile warpFile;

    public SetWarpNameGUI(Player player, List<String> description, ItemStack displayItem, boolean isPrivate, WarpFile warpFile) {
        this.player = player;
        this.description = description;
        this.isPrivate = isPrivate;
        this.warpFile = warpFile;

        if (displayItem == null){
            this.displayItem = getWarpItem();
        }else {
            this.displayItem = addMeta(displayItem);
        }
    }

    public void open(){

        new AnvilGUI.Builder()
                .plugin(WarpsAndHomes.getPlugin())
                .title(ChatColor.GOLD + "Rename warp")
                .itemLeft(displayItem)
                .onClick((slot, stateSnapshot) -> {

                    if (slot != AnvilGUI.Slot.OUTPUT){
                        return Collections.emptyList();
                    }

                    if (!ChatColor.stripColor(stateSnapshot.getText()).equalsIgnoreCase("") && !stateSnapshot.getText().trim().isEmpty()) {
                        return Arrays.asList(
                                AnvilGUI.ResponseAction.close(),
                                AnvilGUI.ResponseAction.run(() -> setName(stateSnapshot.getText()))
                        );
                    } else {
                        return Arrays.asList(AnvilGUI.ResponseAction.replaceInputText("Try again"));
                    }


                })
                .open(player);

    }

    private void setName(final String text) {

        String name = text.replaceAll(" ", "");

        String desc;

        if (description != null) {
            desc = String.join(" ", description);
        }else {
            desc = ChatColor.GRAY + "No description specified";
        }

        if (warpFile.getWarp(name) != null || name.trim().isEmpty() || name.isEmpty() || name.equalsIgnoreCase("§b")){

            String[] words = desc.split("\\s+");
            List<String> description = Arrays.asList(words);

            player.sendMessage(ChatColor.RED + "This name already exists as a warp, or is not valid! Try a different name!");
            new SetWarpNameGUI(player, description, displayItem, isPrivate, warpFile).open();
            return;
        }

        String[] args = {"set", name, String.valueOf(isPrivate), String.valueOf(displayItem.getType()), desc};

        new SetWarp(warpFile).perform(player, args);

    }

    private ItemStack getWarpItem() {

        ItemStack warpItem = new ItemStack(Material.BARRIER);;
        try {
            if (player.getWorld().getEnvironment().equals(World.Environment.NORMAL)) {

                warpItem = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5" +
                        "lY3JhZnQubmV0L3RleHR1cmUvMjU0ODUwMzFiMzdmMGQ4YTRmM2I3ODE2ZWI3MTdmMDNkZTg5YTg3ZjZhNDA2MDJhZWY" +
                        "1MjIyMWNkZmFmNzQ4OCJ9fX0=");

            } else if (player.getWorld().getEnvironment().equals(World.Environment.NETHER)) {

                warpItem = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5" +
                        "lY3JhZnQubmV0L3RleHR1cmUvNmM3YjgwMGRkNDNmMzBiZGM3YjA2ZjZiNTUxNmQzMGU2ZDk3YzAzOWNhOTk1ZDdkOTY" +
                        "zZGU1YjQ5NzdiNjcyMyJ9fX0=");

            } else if (player.getWorld().getEnvironment().equals(World.Environment.THE_END)) {

                warpItem = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5" +
                        "lY3JhZnQubmV0L3RleHR1cmUvZTQ4ZDc5NjhjMjNkNTM3YmZlYWVkNmRlN2NkYmU3MzMwYWZlNDA1ZTAwYzViZDNlZWQ" +
                        "4YWIyOTFmZDU5YzVlZCJ9fX0=");

            }

        } catch (NullPointerException e) {
            warpItem = new ItemStack(Material.BARRIER);
        }

        return addMeta(warpItem);

    }

    private ItemStack addMeta(final ItemStack warpItem) {

        ItemMeta meta = warpItem.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(ChatColor.AQUA + "");


        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Location: " + ChatColor.GOLD + Math.round(player.getLocation().getX()) +
                " " + Math.round(player.getLocation().getY()) + " " + Math.round(player.getLocation().getZ()));
        lore.add(ChatColor.GRAY + "Dimension: " + ChatColor.GREEN + Objects.requireNonNull(player.getLocation().getWorld()).getEnvironment());
        if (isPrivate){
            lore.add(ChatColor.GRAY + "Privacy: " + ChatColor.RED + "private");
        }else {
            lore.add(ChatColor.GRAY + "Privacy: " + ChatColor.GREEN + "public");
        }
        lore.add(ChatColor.AQUA + "" + ChatColor.BOLD + "Description: ");

        String desc = String.join(" ", description);

        lore.add(ChatColor.WHITE + desc);

        meta.setLore(lore);
        warpItem.setItemMeta(meta);
        return warpItem;

    }

}
