package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.*;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarpOpt extends WarpMenu{

    private final String warpName;
    private final ItemStack warpItem;

    public WarpOpt(Player player, WarpFile warpFile, String warpName, ItemStack warpItem) {
        super(player, warpFile);
        this.warpName = warpName;
        this.warpItem = warpItem;
    }

    @Override
    public int slots() {
        return 4*9;
    }

    @Override
    public String name() {
        return ChatColor.GOLD + warpName;
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {
        e.setCancelled(true);
        switch (Objects.requireNonNull(e.getCurrentItem()).getType()){

            case BARRIER -> new PaginatedWarpsGUI(player, warpFile).open();

            case END_PORTAL_FRAME -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                String[]args = {"warp", warpName};
                new WarpToWarp(warpFile).perform(player, args);
            }
            case TNT -> new ConfirmGUI(player, warpFile, ChatColor.RED + "Delete-Warp", warpName).open();
            case COMPASS -> new ConfirmGUI(player, warpFile, ChatColor.GRAY + "Update-Warp", warpName).open();
            case ENDER_CHEST -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);

                if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")) {
                    String[] args = {"unlock", warpName};
                    new WarpUnlock(warpFile).perform(player, args);
                    fill();
                }
            }
            case OAK_SIGN -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);

                if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps")) {
                    String[] args = {"lock", warpName};
                    new WarpLock(warpFile).perform(player, args);
                    fill();
                }
            }

            case PLAYER_HEAD -> new HandoverGUI(player, warpFile, warpFile.getWarp(warpName)).open();
            case WRITTEN_BOOK -> new DescribeGUI(player, warpItem, warpFile.getWarp(warpName), warpFile).open();
            case ANVIL -> new RenameGUI(player, warpItem, warpFile.getWarp(warpName), warpFile).open();
        }
    }

    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public void fill() {
        fillWithGlass();

        //1.Warp 2.Delete 3.Update 4.Unlock 5.Lock 6.Handover 7.Describe 8.Rename

        setItem(null, Material.END_PORTAL_FRAME, ChatColor.LIGHT_PURPLE + "Warp",
                new ArrayList<>(List.of("Click here to warp ", "to this warp.")), 10);


        setItem("allow-warp-deleting", Material.TNT, ChatColor.RED + "Delete",
                new ArrayList<>(List.of("Click here to ", "delete this warp.")), 16);


        setItem("allow-warp-updating", Material.COMPASS, ChatColor.GREEN + "Update",
                new ArrayList<>(List.of("Click here to ", "update this warp.")), 23);


        //TODO BOTH
        Warp warp = warpFile.getWarp(warpName);
        setPrivacyItems(warp);

        setItem(null, Material.GRASS_BLOCK, ChatColor.AQUA + "" + ChatColor.BOLD + "Display-Item",
                new ArrayList<>(List.of("Click here to set a item for this ", "warp, which will be displayed ", "in your list of warps.")),
                21);

        setItem("allow-warp-hand-overing", Material.PLAYER_HEAD, ChatColor.GOLD + "Handover",
                new ArrayList<>(List.of("Click here to change the ", "owner of this warp.")), 25);


        setItem("allow-warp-describing", Material.WRITTEN_BOOK, ChatColor.BLUE + "Describe",
                new ArrayList<>(List.of("Click here to describe ", "this warp.")), 14);


        setItem("allow-warp-renaming", Material.ANVIL, ChatColor.GRAY + "Rename",
                new ArrayList<>(List.of("Click here to rename ", "this warp.")), 12);


        setItem(null, Material.BARRIER, ChatColor.RED + "" + ChatColor.BOLD + "Back",
                new ArrayList<>(List.of("Click here to go to ", "the previous menu.")), 31);
    }

    private void setPrivacyItems(final Warp warp) {

        List<String> lore = new ArrayList<>();
        if (!warp.isPrivate()) {

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

            if (!WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps")){
                lore.add("This warp is private.");
            }else {
                lore.add("This warp is private. Make it public by ");
                lore.add("clicking on this item.");
            }

            setItem(null, Material.ENDER_CHEST, ChatColor.RED + "" + ChatColor.BOLD + "private",
                    lore, 11);
        }

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
