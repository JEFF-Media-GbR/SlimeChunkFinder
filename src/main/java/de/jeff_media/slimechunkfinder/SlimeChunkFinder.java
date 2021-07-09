package de.jeff_media.slimechunkfinder;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class SlimeChunkFinder extends JavaPlugin {

    private static SlimeChunkFinder instance;
    private final NamespacedKey enabledKey = new NamespacedKey(this, "enabled");
    @Getter HashMap<UUID, BossBar> bossbars = new HashMap<>();

    public static SlimeChunkFinder getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        this.instance = this;
    }

    public boolean hasEnabled(Player player) {
        return player.getPersistentDataContainer().has(enabledKey, PersistentDataType.BYTE);
    }

    public void setEnabled(Player player, boolean enabled) {
        if(enabled) {
            player.getPersistentDataContainer().set(enabledKey, PersistentDataType.BYTE, (byte) 1);
        } else {
            player.getPersistentDataContainer().remove(enabledKey);
        }

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        new SlimeTask().runTaskTimer(this, 1, 1);
    }

    public BossBar getBossBar()

    @Override
    public void onEnable() {
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new FindNearestCommand());

    }

    public static boolean isSlimeChunk(Chunk chunk) {
        return chunk.isSlimeChunk();
    }

    public static List<Chunk> getSlimeChunks(World world, Player player) {
        List<Chunk> chunks = new ArrayList<>();
        for(Chunk chunk : world.getLoadedChunks()) {
            if(isSlimeChunk(chunk)) chunks.add(chunk);
        }
        Location loc = player.getLocation();
        chunks.sort(Comparator.comparingDouble(o -> o.getBlock(0, 0, 0).getLocation().distanceSquared(loc)));
        return chunks;
    }

    public static Location chunkToLocation(Chunk chunk) {
        World world = chunk.getWorld();
        int x = chunk.getX()*16;
        int y = 64;
        int z = chunk.getZ()*16;
        return new Location(world, x, y, z);
    }


}
