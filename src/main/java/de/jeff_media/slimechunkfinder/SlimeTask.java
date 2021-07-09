package de.jeff_media.slimechunkfinder;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SlimeTask extends BukkitRunnable {

    private static final SlimeChunkFinder main= SlimeChunkFinder.getInstance();

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!main.hasEnabled(player)) continue;

        }
    }
}
