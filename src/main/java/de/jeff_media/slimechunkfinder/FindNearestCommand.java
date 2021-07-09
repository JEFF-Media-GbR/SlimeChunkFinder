package de.jeff_media.slimechunkfinder;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("slimechunkfinder")
public class FindNearestCommand extends BaseCommand {

    private static final SlimeChunkFinder main = SlimeChunkFinder.getInstance();

    @Default
    public static void findNearest(Player player, String[] args) {

        final List<Chunk> chunks = SlimeChunkFinder.getSlimeChunks(player.getWorld(), player);

        if(chunks.isEmpty()) {
            player.sendMessage("§cCould not find any slime chunks nearby...");
            return;
        }

        Chunk chunk = chunks.get(0);

        player.sendMessage("Found chunk.");
        player.sendMessage("Distance: " + SlimeChunkFinder.chunkToLocation(chunk).distance(player.getLocation()));
        player.sendMessage("Chunk: " + SlimeChunkFinder.chunkToLocation(chunk));

    }

}
