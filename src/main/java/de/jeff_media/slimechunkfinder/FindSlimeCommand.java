package de.jeff_media.slimechunkfinder;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.List;

@CommandAlias("slimechunkfinder")
public class FindSlimeCommand extends BaseCommand {

    private static final SlimeChunkFinder main = SlimeChunkFinder.getInstance();

    @Default
    public static void toggle(Player player, String[] args) {
        boolean enabled = !main.hasEnabled(player);
        main.setEnabled(player, enabled);
        player.sendMessage(enabled ? Messages.ENABLED : Messages.DISABLED);
    }

}
