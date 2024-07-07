package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpDescribe;
import me.lukaos187.warpsandhomes.util.WarpFile;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SetWarpDescGUI {

    private final Player player;
    private final WarpFile warpFile;
    private final ItemStack displayItem;
    private final List<String> description;
    private String desc;
    private final boolean isPrivate;


    public SetWarpDescGUI(Player player, WarpFile warpFile, ItemStack displayItem, List<String> description, boolean isPrivate){

        this.isPrivate = isPrivate;
        this.description = description;
        this.displayItem = displayItem;
        this.warpFile = warpFile;
        this.player = player;
        if (description != null) {
            desc = String.join(" ", description);
        }else {
            desc = ChatColor.GRAY + "No description specified";
        }
    }
    public void open(){

        ItemStack des = new ItemStack(Material.PAPER);
        ItemMeta meta = des.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(desc);
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

        //FIXME Do not allow "" or "      "

        String[] wordsArray = newDescr.split("\\s+");
        List<String> wordsList = Arrays.asList(wordsArray);

        new SetwarpMenu(player, warpFile, displayItem, wordsList, isPrivate).open();
    }

}
