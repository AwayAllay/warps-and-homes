package me.lukaos187.warpsandhomes.guis;

import me.lukaos187.warpsandhomes.util.HeadGetter;
import me.lukaos187.warpsandhomes.util.Warp;
import me.lukaos187.warpsandhomes.util.WarpFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OtherPlayersGUI extends WarpMenu{
    private final List<Player> onlinePlayers = new ArrayList<>();
    private int currentPage = 0;
    public OtherPlayersGUI(Player player, WarpFile warpFile) {
        super(player, warpFile);
        onlinePlayers.addAll(Bukkit.getOnlinePlayers());
    }

    @Override
    public int slots() {
        return 54;
    }

    @Override
    public String name() {
        return ChatColor.BLUE + "Choose a player";
    }

    @Override
    public void manageClicks(InventoryClickEvent e) {

        e.setCancelled(true);

        String displayName = ChatColor.stripColor(Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem()).getItemMeta()).getDisplayName());

        switch (displayName){

            case "Back" -> new WarpChoice(player, warpFile).open();
            case "Next" -> {
                if ((currentPage + 1) * 45 < onlinePlayers.size()) {
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
            default -> new PublicWarpsOnlinePlayerGUI(player, warpFile, Bukkit.getPlayer(displayName)).open();
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

            if (index >= onlinePlayers.size())
                break;

            Player onlinePlayer = onlinePlayers.get(index);

            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
            skullMeta.setOwningPlayer(onlinePlayer);
            skullMeta.setDisplayName(ChatColor.GREEN + onlinePlayer.getName());
            skullMeta.setLore(new ArrayList<>(List.of("Click to see the warps ", "of " + onlinePlayer.getName())));
            playerHead.setItemMeta(skullMeta);

            inventory.setItem(i, playerHead);
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
