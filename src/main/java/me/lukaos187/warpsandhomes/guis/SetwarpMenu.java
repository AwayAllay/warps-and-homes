package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetwarpMenu extends WarpMenu{

    private String name;
    private List<String> description;
    //TODO implement displayitem
    private Material displayItem;
    private boolean isPrivate;

    public SetwarpMenu(Player player, WarpFile warpFile) {
        super(player, warpFile);
    }

    @Override
    public int slots() {
        return 4*9;
    }

    @Override
    public String name() {
        return ChatColor.DARK_BLUE + "Set warp";
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {

        e.setCancelled(true);

        switch (e.getCurrentItem().getType()){

            case BARRIER -> new WarpChoice(player, warpFile).open();
            case LIME_DYE -> new SetWarpNameGUI(player, description, displayItem, isPrivate, warpFile).open();



        }

    }

    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public void fill() {

        // public/private option, ItemForWarp, Description?, Name

        for (int i = 27; i < 36; i++) {
            setItem(null, Material.GRAY_STAINED_GLASS_PANE, " ", new ArrayList<>(), i);
        }

        setItem(null, Material.BARRIER, ChatColor.BOLD + "" + ChatColor.RED + "Cancel",
                new ArrayList<>(List.of("Click here to cancel the ", "warp-creation.")), 35);
        setItem(null, Material.LIME_DYE, ChatColor.BOLD + "" + ChatColor.GREEN + "Set",
                new ArrayList<>(List.of(ChatColor.GREEN + "Click here to set the warp.")), 31);

    }

    private void setItem(String configKey, Material material, String displayName, List<String> lore, int index){

        ItemStack disabledOption = new ItemStack(Material.IRON_BARS);
        ItemMeta dOMeta = disabledOption.getItemMeta();
        Objects.requireNonNull(dOMeta).setDisplayName(ChatColor.GRAY + "Disabled option");
        dOMeta.setLore(new ArrayList<>(List.of(ChatColor.GRAY + "This option was disabled ", ChatColor.GRAY + "in the config.yml")));
        disabledOption.setItemMeta(dOMeta);

        if (configKey == null || WarpsAndHomes.getPlugin().getConfig().getBoolean(configKey)) {
            ItemStack item = new ItemStack(material);
            ItemMeta itemMeta = item.getItemMeta();
            Objects.requireNonNull(itemMeta).setDisplayName(displayName);
            itemMeta.setLore(lore);
            item.setItemMeta(itemMeta);
            inventory.setItem(index, item);
        }else {
            inventory.setItem(index, disabledOption);
        }

    }
}
