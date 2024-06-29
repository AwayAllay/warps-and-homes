package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.util.HeadGetter;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyWarps extends WarpMenu {


    private int currentPage = 0;
    private final List<Warp> warps = new ArrayList<>();

    public MyWarps(Player player, WarpFile warpFile) {
        super(player, warpFile);

        List<String> warpNames = warpFile.getWarps(player);
        warpNames.forEach(s -> {
            Warp warp = warpFile.getWarp(s);
            if (warp != null)
                warps.add(warp);
        });
    }

    @Override
    public int slots() {
        return 9 * 6;
    }

    @Override
    public String name() {
        return ChatColor.RED + "My Warps";
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {

        e.setCancelled(true);

        ItemStack currentItem = e.getCurrentItem();

        String displayName = ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(currentItem).getItemMeta()).getDisplayName());

        switch (displayName) {
            case "Next" -> {
                if ((currentPage + 1) * 45 < warps.size()) {
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
            case "Back" -> new WarpChoice(player, warpFile).open();
            default -> new WarpOpt(player, warpFile, displayName, currentItem).open();
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
            if (index >= warps.size())
                break;

            Warp warp = warps.get(index);

            ItemStack warpItem = getWarpItem(warp);

            inventory.setItem(i, warpItem);
        }


    }

    private ItemStack getWarpItem(final Warp warp) {

        ItemStack warpItem = new ItemStack(Material.BARRIER);;
        try {
            if (warp.getLocation().getWorld().getEnvironment().equals(World.Environment.NORMAL)) {

                warpItem = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5" +
                        "lY3JhZnQubmV0L3RleHR1cmUvMjU0ODUwMzFiMzdmMGQ4YTRmM2I3ODE2ZWI3MTdmMDNkZTg5YTg3ZjZhNDA2MDJhZWY" +
                        "1MjIyMWNkZmFmNzQ4OCJ9fX0=");

            } else if (warp.getLocation().getWorld().getEnvironment().equals(World.Environment.NETHER)) {

                warpItem = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5" +
                        "lY3JhZnQubmV0L3RleHR1cmUvNmM3YjgwMGRkNDNmMzBiZGM3YjA2ZjZiNTUxNmQzMGU2ZDk3YzAzOWNhOTk1ZDdkOTY" +
                        "zZGU1YjQ5NzdiNjcyMyJ9fX0=");

            } else if (warp.getLocation().getWorld().getEnvironment().equals(World.Environment.THE_END)) {

                warpItem = HeadGetter.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5" +
                        "lY3JhZnQubmV0L3RleHR1cmUvZTQ4ZDc5NjhjMjNkNTM3YmZlYWVkNmRlN2NkYmU3MzMwYWZlNDA1ZTAwYzViZDNlZWQ" +
                        "4YWIyOTFmZDU5YzVlZCJ9fX0=");

            }

        } catch (NullPointerException e) {
            warpItem = new ItemStack(Material.BARRIER);
        }

        return addMeta(warpItem, warp);
    }

    private ItemStack addMeta(final ItemStack warpItem, final Warp warp) {
        ItemMeta meta = warpItem.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName(ChatColor.AQUA + warp.getName());

        String s = warp.isPrivate() ? ChatColor.RED + "private" : ChatColor.GREEN + "public";

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Location: " + ChatColor.GOLD + Math.round(warp.getLocation().getX()) +
                " " + Math.round(warp.getLocation().getY()) + " " + Math.round(warp.getLocation().getZ()));
        lore.add(ChatColor.GRAY + "Dimension: " + ChatColor.GREEN + Objects.requireNonNull(warp.getLocation().getWorld()).getEnvironment());
        lore.add(ChatColor.GRAY + "Privacy: " + s);

        meta.setLore(lore);
        warpItem.setItemMeta(meta);
        return warpItem;
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
