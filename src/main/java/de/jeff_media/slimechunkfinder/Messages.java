package de.jeff_media.slimechunkfinder;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Messages {

    public static String ENABLED, DISABLED;

    public static void init(String language) {
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(new File(SlimeChunkFinder.getInstance().getDataFolder(),language+".yml"));

        ENABLED = yaml.getString("enabled");
        DISABLED = yaml.getString("disabbled");
    }
}
