package de.jeff_media.slimechunkfinder;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

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

    public BossBar getBossBar(Player player) {
        if(bossbars.containsKey(player.getUniqueId())) {
            return bossbars.get(player.getUniqueId());
        }
        BossBar bossbar = Bukkit.createBossBar("Nearest Slime Chunk", BarColor.GREEN, BarStyle.SEGMENTED_20);
        bossbar.addPlayer(player);
        bossbar.setVisible(true);
        bossbars.put(player.getUniqueId(),bossbar);
        return bossbar;
    }

    @Override
    public void onEnable() {
        PaperCommandManager commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new FindSlimeCommand());

    }

    public static boolean isSlimeChunk(Chunk chunk) {
        return chunk.isSlimeChunk();
    }

    public static List<Chunk> getSlimeChunks(Player player) {
        List<Chunk> chunks = new ArrayList<>();
        for(Chunk chunk : player.getWorld().getLoadedChunks()) {
            if(isSlimeChunk(chunk)) chunks.add(chunk);
        }
        Location loc = player.getLocation();
        chunks.sort(Comparator.comparingDouble(o -> o.getBlock(0, 0, 0).getLocation().distanceSquared(loc)));
        return chunks;
    }

    public static Location chunkToLocation(Chunk chunk, double y) {
        World world = chunk.getWorld();
        int x = chunk.getX()*16;
        int z = chunk.getZ()*16;
        return new Location(world, x+8, y, z+8);
    }


}
