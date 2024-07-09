package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpDescribe;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpRename;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class DescribeGUI {
    private final Player player;
    private final ItemStack warpItem;
    private final Warp warp;
    private final WarpFile warpFile;
    public DescribeGUI(Player player, ItemStack warpItem, Warp warp, WarpFile warpFile) {
        this.player = player;
        this.warpItem = warpItem;
        this.warp = warp;
        this.warpFile = warpFile;
    }

    public void open(){

        ItemStack des = new ItemStack(Material.PAPER);
        ItemMeta meta = des.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(warp.getDescription());
        des.setItemMeta(meta);

        new AnvilGUI.Builder()
                .plugin(WarpsAndHomes.getPlugin())
                .title(ChatColor.GOLD + "Write a description")
                .itemLeft(des)
                .onClick((slot, stateSnapshot) -> {

                    if (slot != AnvilGUI.Slot.OUTPUT){
                        return Collections.emptyList();
                    }

                    return Arrays.asList(
                            AnvilGUI.ResponseAction.close(),
                            AnvilGUI.ResponseAction.run(() -> descriptionChange(stateSnapshot.getText()))
                    );


                })
                .open(player);

    }

    private void descriptionChange(final String newDescr) {

        String[] args = {"describe", warp.getName(), newDescr};
        new WarpDescribe(warpFile).perform(player, args);

        new WarpOpt(player, warpFile, warp, warpItem).open();
    }

}
