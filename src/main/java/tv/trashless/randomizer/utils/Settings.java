package tv.trashless.randomizer.utils;

import java.io.IOException;

public class Settings {
    private static final Config SETTINGS_CONFIG = new Config("settings");

    public static void loadAll() {
        if (!load("randomize_block_drops")) {
            save("randomize_block_drops", false);
        }

        if (!load("randomize_mob_drops")) {
            save("randomize_mob_drops", false);
        }
    }

    public static boolean load(String path) {
        if (SETTINGS_CONFIG.contains(path)) {
            return (boolean) SETTINGS_CONFIG.get(path);
        }
        return false;
    }

    public static void saveAll() {
        save("randomize_block_drops", load("randomize_block_drops"));
        save("randomize_mob_drops", load("randomize_mob_drops"));
    }

    public static void save(String path, boolean state) {
        try {
            SETTINGS_CONFIG.set(path, state);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Config getConfig() {
        return SETTINGS_CONFIG;
    }
}
