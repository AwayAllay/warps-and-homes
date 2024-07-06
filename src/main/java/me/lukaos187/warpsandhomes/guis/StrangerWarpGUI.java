package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.HandoverRequest;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.WarpToWarp;
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

public class StrangerWarpGUI extends WarpMenu{

    private final Warp warp;
    public StrangerWarpGUI(Player player, WarpFile warpFile, Warp warp) {
        super(player, warpFile);
        this.warp = warp;
    }

    @Override
    public int slots() {
        return 27;
    }

    @Override
    public String name() {
        return ChatColor.GREEN + warp.getOwner().getName() + ChatColor.GRAY + " - " + ChatColor.AQUA + warp.getName();
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {

        e.setCancelled(true);

        switch (e.getCurrentItem().getType()){

            case END_PORTAL_FRAME -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                String[]args = {"warp", warp.getName()};
                new WarpToWarp(warpFile).perform(player, args);
            }
            case DIAMOND_BOOTS -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                String[]args = {"request", warp.getName()};
                new HandoverRequest(warpFile).perform(player, args);
            }
            case BARRIER -> new PaginatedWarpsGUI(player, warpFile, warp.getOwner()).open();

        }

    }

    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public void fill() {

        fillWithGlass();

        setItem(null, Material.END_PORTAL_FRAME, ChatColor.LIGHT_PURPLE + "Warp",
                new ArrayList<>(List.of("Click here to warp ", "to this warp.")), 12);
        setItem("allow-warp-requests", Material.DIAMOND_BOOTS, ChatColor.AQUA + "Request",
                new ArrayList<>(List.of("Click here to request the ", "ownership of this warp.")), 14);
        setItem(null, Material.BARRIER, ChatColor.RED + "" + ChatColor.BOLD + "Back",
                new ArrayList<>(List.of("Click here to go to ", "the previous menu.")), 26);

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
