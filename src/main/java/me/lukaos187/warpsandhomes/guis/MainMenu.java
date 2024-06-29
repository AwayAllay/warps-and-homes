package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.util.HeadGetter;
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

public class MainMenu extends WarpMenu {


    public MainMenu(Player player, WarpFile warpFile) {
        super(player, warpFile);
    }

    @Override
    public int slots() {
        return 3 * 9;
    }

    @Override
    public String name() {
        return ChatColor.BLUE + "WarpsAndHomes - Option";
    }

    @Override
    public void fill() {

        ItemStack home = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubm" +
                "V0L3RleHR1cmUvMTJkN2E3NTFlYjA3MWUwOGRiYmM5NWJjNWQ5ZDY2ZTVmNTFkYzY3MTI2NDBhZDJkZmEwM2RlZmJiNjh" +
                "hN2YzYSJ9fX0=");
        ItemMeta metaH = home.getItemMeta();
        Objects.requireNonNull(metaH).setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "Home");
        metaH.setLore(new ArrayList<>(List.of("Click here to get to ", "your homes.")));
        home.setItemMeta(metaH);

        ItemStack warps = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubm" +
                "V0L3RleHR1cmUvNzk4ODVlODIzZmYxNTkyNjdjYmU4MDkwOTNlMzNhNDc2ZTI3NDliNjU5OGNhNGEyYTgxZWU2OTczODAz" +
                "ZmI2NiJ9fX0=");
        ItemMeta meta = warps.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "Warps");
        meta.setLore(new ArrayList<>(List.of("Click here to get to ", "all the warps.")));
        warps.setItemMeta(meta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Close");
        closeMeta.setLore(new ArrayList<>(List.of("Click here to close ", "the inventory.")));
        close.setItemMeta(closeMeta);

        fillWithGlass();

        inventory.setItem(12, home);
        inventory.setItem(14, warps);
        inventory.setItem(26, close);


    }

    @Override
    public void manageClicks(InventoryClickEvent e) {

        e.setCancelled(true);

        switch (ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getDisplayName())) {
            case "Home" -> player.sendMessage("Home");
            case "Warps" -> new WarpChoice(player, warpFile).open();
            case "Close" -> {
                player.closeInventory();
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
            }
        }
    }


    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

}
