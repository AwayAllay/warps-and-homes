package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Objects;

public abstract class WarpMenu implements InventoryHolder {

    protected Player player;
    protected Inventory inventory;
    protected WarpFile warpFile;

    public WarpMenu(Player player, WarpFile warpFile) {
        this.player = player;
        this.warpFile = warpFile;
    }

    public abstract int slots();
    public abstract String name();
    public abstract void manageClicks(InventoryClickEvent e);
    public abstract Sound openingSound();
    public abstract void fill();

    public void open(){

        if (openingSound() != null)
            player.playSound(player, openingSound(), 5F, 5F);

        inventory = Bukkit.createInventory(this, slots(), name());
        fill();
        player.openInventory(inventory);

    }

    protected void fillWithGlass(){
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta metaI = item.getItemMeta();
        Objects.requireNonNull(metaI).setDisplayName(" ");
        item.setItemMeta(metaI);
        for (int i = 0; i < slots(); i++) {
            inventory.setItem(i, item);
        }
    }

    @Override
    public @NonNull Inventory getInventory(){
        return inventory;
    }

}
