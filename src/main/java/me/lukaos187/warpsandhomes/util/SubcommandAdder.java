package me.lukaos187.warpsandhomes.util;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.*;

import java.util.ArrayList;
import java.util.List;

public class SubcommandAdder {

    private List<Subcommand> subcommands = new ArrayList<>();
    private final WarpFile warpFile;

    public SubcommandAdder(WarpFile warpFile) {
        this.warpFile = warpFile;
    }

    public void reloadSubs(final WarpFile warpFile) {

        //TODO implement in Interface
        if (!subcommands.isEmpty())
            subcommands.clear();

        subcommands.add(new SetWarp(warpFile));
        subcommands.add(new WarpToWarp(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-informing"))
            subcommands.add(new WarpInfo(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-listing"))
            subcommands.add(new WarpList(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-deleting"))
            subcommands.add(new DeleteWarp(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-updating"))
            subcommands.add(new WarpUpdate(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-private-warps"))
            subcommands.add(new WarpLock(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-public-warps"))
            subcommands.add(new WarpUnlock(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-describing"))
            subcommands.add(new WarpDescribe(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-renaming"))
            subcommands.add(new WarpRename(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-hand-overing"))
            subcommands.add(new WarpHandover(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-clearing"))
            subcommands.add(new WarpClear(warpFile));
    }

    public List<Subcommand> getSubcommands() {
        return subcommands;
    }
}
