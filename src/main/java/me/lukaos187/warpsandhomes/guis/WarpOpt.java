package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.*;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WarpOpt extends WarpMenu{

    private final String name;

    public WarpOpt(Player player, WarpFile warpFile, String warpName) {
        super(player, warpFile);
        this.name = warpName;
    }

    @Override
    public int slots() {
        return 4*9;
    }

    @Override
    public String name() {
        return ChatColor.GOLD + name;
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {
        e.setCancelled(true);
        switch (Objects.requireNonNull(e.getCurrentItem()).getType()){

            case BARRIER -> new MyWarps(player, warpFile).open();

            case END_PORTAL_FRAME -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                String[]args = {"warp", name};
                new WarpToWarp(warpFile).perform(player, args);
            }
            case TNT -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                String[]args = {"delete", name};
                new DeleteWarp(warpFile).perform(player, args);
                new MyWarps(player, warpFile).open();
            }
            case COMPASS -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                String[]args = {"update", name};
                new WarpUpdate(warpFile).perform(player, args);
            }
            case GREEN_DYE -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                String[]args = {"unlock", name};
                new WarpUnlock(warpFile).perform(player, args);
            }
            case RED_DYE -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                String[]args = {"lock", name};
                new WarpLock(warpFile).perform(player, args);
            }

            case PLAYER_HEAD -> new HandoverToPlayer(player, warpFile, name).open();
            case WRITTEN_BOOK -> {/*TODO DESCRIBE OPTION*/

            }
            case ANVIL -> {/*TODO RENAME OPTION*/
            }
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


        setItem("allow-public-warps", Material.GREEN_DYE, ChatColor.GREEN + "Unlock",
                new ArrayList<>(List.of("Click here to set this ", "warp to public.")), 19);


        setItem("allow-private-warps", Material.RED_DYE, ChatColor.AQUA + "Lock",
                new ArrayList<>(List.of("Click here to set this ", "warp to private.")), 21);


        setItem("allow-warp-hand-overing", Material.PLAYER_HEAD, ChatColor.GOLD + "Handover",
                new ArrayList<>(List.of("Click here to change the ", "owner of this warp.")), 25);


        setItem("allow-warp-describing", Material.WRITTEN_BOOK, ChatColor.BLUE + "Describe",
                new ArrayList<>(List.of("Click here to describe ", "this warp.")), 14);


        setItem("allow-warp-renaming", Material.ANVIL, ChatColor.GRAY + "Rename",
                new ArrayList<>(List.of("Click here to rename ", "this warp.")), 12);


        setItem(null, Material.BARRIER, ChatColor.RED + "" + ChatColor.BOLD + "Back",
                new ArrayList<>(List.of("Click here to go to ", "the previous menu.")), 31);
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
