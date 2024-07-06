package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.SetWarp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SetWarpNameGUI {

    //TODO implement warpitem
    private final Player player;
    private String warpName;
    private final List<String> description;
    private final Material displayItem;
    private final boolean isPrivate;
    private final WarpFile warpFile;

    public SetWarpNameGUI(Player player, List<String> description, Material displayItem, boolean isPrivate, WarpFile warpFile) {
        this.player = player;
        this.description = description;
        this.displayItem = displayItem;
        this.isPrivate = isPrivate;
        this.warpFile = warpFile;
    }

    public void open(){

        new AnvilGUI.Builder()
                .plugin(WarpsAndHomes.getPlugin())
                .title(ChatColor.GOLD + "Rename warp")
                .itemLeft(new ItemStack(Material.PAPER))
                .onClick((slot, stateSnapshot) -> {

                    if (slot != AnvilGUI.Slot.OUTPUT){
                        return Collections.emptyList();
                    }

                    return Arrays.asList(
                            AnvilGUI.ResponseAction.close(),
                            AnvilGUI.ResponseAction.run(() -> setName(stateSnapshot.getText()))
                    );


                })
                .open(player);

    }

    private void setName(final String text) {

        String desc = String.join(" ", description);
        String name = text.replaceAll(" ", "");

        String[] args = {"set", name, String.valueOf(isPrivate), desc};

        new SetWarp(warpFile).perform(player, args);

    }

}
