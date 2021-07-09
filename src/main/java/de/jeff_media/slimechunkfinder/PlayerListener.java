package de.jeff_media.slimechunkfinder;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private static final SlimeChunkFinder main = SlimeChunkFinder.getInstance();

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        main.setEnabled(event.getPlayer(),false);
        main.getBossbars().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        main.setEnabled(event.getPlayer(), false);
    }
}
