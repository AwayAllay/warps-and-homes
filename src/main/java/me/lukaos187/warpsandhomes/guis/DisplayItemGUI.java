package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.util.HeadGetter;
import me.lukaos187.warpsandhomes.util.WarpDisplayItems;
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

public class DisplayItemGUI extends WarpMenu{

    private int currentPage = 0;
    private final List<ItemStack> items = WarpDisplayItems.getItems();
    private ItemStack displayItem;
    private final List<String> description;
    private final boolean isPrivate;

    public DisplayItemGUI(Player player, WarpFile warpFile,
                          ItemStack displayItem, List<String> description, boolean isPrivate) {
        super(player, warpFile);
        this.isPrivate = isPrivate;
        this.description = description;
        this.displayItem = displayItem;
    }

    @Override
    public int slots() {
        return 54;
    }

    @Override
    public String name() {
        return ChatColor.LIGHT_PURPLE + "Choose your displayed Item";
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {

        e.setCancelled(true);

        ItemStack currentItem = e.getCurrentItem();

        String displayName = ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(currentItem).getItemMeta()).getDisplayName());

        switch (displayName) {
            case "Next" -> {
                if ((currentPage + 1) * 45 < items.size()) {
                    currentPage++;
                    player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                    fill();
                }
            }
            case "Previous" -> {
                if (currentPage > 0) {
                    currentPage--;
                    player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                    fill();
                }
            }
            case "Back" -> new SetwarpMenu(player, warpFile, displayItem, description, isPrivate).open();
            default -> {
                player.playSound(player, Sound.UI_BUTTON_CLICK, 5F, 5F);
                displayItem = currentItem;
                player.sendMessage("Displayitem is set to " + ChatColor.AQUA + currentItem.getType().name());
            }

        }
    }

    @Override
    public Sound openingSound() {
        return Sound.UI_BUTTON_CLICK;
    }

    @Override
    public void fill() {
        inventory.clear();
        optionBar();

        for (int i = 0; i < 45; i++) {
            int index = currentPage * 45 + i;
            if (index >= items.size())
                break;

            ItemStack item = items.get(index);
            inventory.setItem(i, item);
        }
    }

    private void optionBar() {
        ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta m = filler.getItemMeta();
        Objects.requireNonNull(m).setDisplayName(" ");
        filler.setItemMeta(m);

        for (int i = 53; i > 44; i--) {
            inventory.setItem(i, filler);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Back");
        closeMeta.setLore(new ArrayList<>(List.of("Click here to return ", "to the previous menu.")));
        close.setItemMeta(closeMeta);
        inventory.setItem(49, close);

        ItemStack nextPage = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5" +
                "taW5lY3JhZnQubmV0L3RleHR1cmUvYWM5YzY3YTlmMTY4NWNkMWRhNDNlODQxZmU3ZWJiMTdmNmFmNmVhMTJhN2UxZjI3MjJmN" +
                "WU3ZjA4OThkYjlmMyJ9fX0=");
        ItemMeta meta = nextPage.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Next");
        nextPage.setItemMeta(meta);
        inventory.setItem(50, nextPage);

        ItemStack lastPage = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5" +
                "taW5lY3JhZnQubmV0L3RleHR1cmUvMWExZWYzOThhMTdmMWFmNzQ3NzAxNDUxN2Y3ZjE0MWQ4ODZkZjQxYTMyYzczOGNjOGE4M" +
                "2ZiNTAyOTdiZDkyMSJ9fX0=");
        ItemMeta metaLast = lastPage.getItemMeta();
        metaLast.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Previous");
        lastPage.setItemMeta(metaLast);
        inventory.setItem(48, lastPage);
    }
}
