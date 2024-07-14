/*
 * WarpsAndHomes - Minecraft plugin
 * Copyright (C) 2024 AwayAllay
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 * details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */
package me.lukaos187.warpsandhomes.util;

import me.lukaos187.warpsandhomes.WarpsAndHomes;
import me.lukaos187.warpsandhomes.commands.AcceptRequest;
import me.lukaos187.warpsandhomes.commands.RejectRequest;
import me.lukaos187.warpsandhomes.commands.warpSubcommands.*;

import java.util.ArrayList;
import java.util.List;

public class SubcommandAdder {

    private List<Subcommand> subcommands = new ArrayList<>();
    private final WarpFile warpFile;

    public SubcommandAdder(WarpFile warpFile) {
        this.warpFile = warpFile;
    }

    /**This will reload all the warp subcommands*/
    public void reloadSubs(final WarpFile warpFile) {

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

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-requests"))
            subcommands.add(new HandoverRequest(warpFile));

        if (WarpsAndHomes.getPlugin().getConfig().getBoolean("allow-warp-requests")) {
            subcommands.add(new AcceptRequest(warpFile));
            subcommands.add(new RejectRequest(warpFile));
        }
    }

    public List<Subcommand> getSubcommands() {
        return subcommands;
    }
}
