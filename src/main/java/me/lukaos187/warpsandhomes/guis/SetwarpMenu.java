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
    private ItemStack displayItem;
    private boolean isPrivate;

    public SetwarpMenu(Player player, WarpFile warpFile, ItemStack displayItem, List<String> description, boolean isPrivate) {
        super(player, warpFile);

        this.displayItem = displayItem;
        this.description = description;
        name = null;
        this.isPrivate = isPrivate;

        //allow-private-warps, allow-public-warps
        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps") && !isPrivate)
            isPrivate = false;
        else
            isPrivate = WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps");


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
            case LIME_DYE ->{
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);

                if (description == null){
                    description = new ArrayList<>(List.of(ChatColor.GRAY + "No description specified."));
                }

                new SetWarpNameGUI(player, description, displayItem, isPrivate, warpFile).open();
            }
            case OAK_SIGN -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps")){
                    isPrivate = true;
                    fill();
                }
            }
            case ENDER_CHEST -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")){
                    isPrivate = false;
                    fill();
                }
            }
            case WRITTEN_BOOK -> new SetWarpDescGUI(player, warpFile, displayItem, description, isPrivate).open();
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

        setItem(null, Material.BARRIER, ChatColor.RED + "" + ChatColor.BOLD + "Cancel",
                new ArrayList<>(List.of("Click here to cancel the ", "warp-creation.")), 35);

        setItem(null, Material.LIME_DYE, ChatColor.GREEN + "" + ChatColor.BOLD + "Set",
                new ArrayList<>(List.of(ChatColor.WHITE + "Click here to set the warp.")), 31);

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps") && !isPrivate) {

            List<String> lore = new ArrayList<>();


            if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps")){
                lore.add("This warp is public.");
            }else {
                lore.add("This warp is public. Make it private by ");
                lore.add("clicking on this item.");
            }

            setItem(null, Material.OAK_SIGN, ChatColor.GREEN + "" + ChatColor.BOLD + "public",
                    lore, 11);
        }
        else {

            List<String> lore = new ArrayList<>();


            if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")){
                lore.add("This warp is private.");
            }else {
                lore.add("This warp is private. Make it public by ");
                lore.add("clicking on this item.");
            }

            setItem(null, Material.ENDER_CHEST, ChatColor.RED + "" + ChatColor.BOLD + "private",
                    lore, 11);
        }

        setItem(null, Material.GRASS_BLOCK, ChatColor.AQUA + "" + ChatColor.BOLD + "Display-Item",
                new ArrayList<>(List.of("Click here to set a item for this ", "warp, which will be displayed ", "in your list of warps.")),
                13);
        setItem(null, Material.WRITTEN_BOOK, ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Description",
                new ArrayList<>(List.of("Click here to leave a description ", "for this warp.")), 15);


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
