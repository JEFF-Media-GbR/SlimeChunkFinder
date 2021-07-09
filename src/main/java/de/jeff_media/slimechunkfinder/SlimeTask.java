package de.jeff_media.slimechunkfinder;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SlimeTask extends BukkitRunnable {

    private static final SlimeChunkFinder main= SlimeChunkFinder.getInstance();

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!main.hasEnabled(player)) continue;
            BossBar bossbar = main.getBossBar(player);
            List<Chunk> chunks = SlimeChunkFinder.getSlimeChunks(player);
            if(chunks.isEmpty()) {
                bossbar.setProgress(0);
                continue;
            }
            Chunk chunk = chunks.get(0);
            Location loc = SlimeChunkFinder.chunkToLocation(chunk, player.getLocation().getY());
            bossbar.setProgress(getProgress(loc.distance(player.getLocation())));
        }
    }

    private static double getProgress(double distance) {
        double progress = 1;
        progress -= (distance/160);
        return progress < 0 ? 0 : progress;
    }
}
