package tv.trashless.randomizer.utils;

import org.bukkit.Material;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Randomizer {
    private static final Random RANDOM = new Random();

    private static final HashMap<String, Object> SETTINGS = new HashMap<>();
    private static final HashMap<Material, Material> RANDOMIZED_ITEMS = new HashMap<>();

    private static final Config SETTINGS_CONFIG = new Config("randomizer_settings");
    private static final Config RANDOMIZED_ITEMS_CONFIG = new Config("randomized_items");

    /***
     * Load settings from config file or create them
     */
    public static void loadSettings() {
        SETTINGS.put("randomize_block_drops", false);
        SETTINGS.put("randomize_mob_drops", false);
        //SETTINGS.put("randomize_loot_tables");
        //SETTINGS.put("randomize_recipes");
        //SETTINGS.put("randomize_trades");
        //SETTINGS.put("randomize_fishing");
        SETTINGS.put("randomizer_type", true);

        for (String setting : SETTINGS.keySet()) {

            if (SETTINGS_CONFIG.contains(setting)) {
                SETTINGS.put(setting, SETTINGS_CONFIG.get(setting));
            } else {
                createBooleanSetting(setting);
            }
        }
    }

    /***
     * Create a boolean setting
     */
    public static void createBooleanSetting(String setting) {
        SETTINGS.put(setting, false);

        try {
            SETTINGS_CONFIG.set(setting, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Create a setting of any type
     */
    public static void createSetting(String setting, Object value) {
        SETTINGS.put(setting, value);

        try {
            SETTINGS_CONFIG.set(setting, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * Checks whether a setting exists within the config file or not
     */
    public static boolean containsSetting(String setting) {
        return SETTINGS_CONFIG.contains(setting);
    }

    /***
     * Returns the value of a boolean setting
     */
    public static boolean getBooleanSetting(String setting) {
        return (boolean) SETTINGS.getOrDefault(setting, false);
    }

    /***
     * Returns the value of any setting
     */
    public static Object getSetting(String setting) {
        return SETTINGS.getOrDefault(setting, false);
    }

    /***
     * Toggles the value of a boolean setting
     */
    public static boolean toggleSetting(String setting) {
        boolean newState = !(boolean) SETTINGS.getOrDefault(setting, false);
        SETTINGS.put(setting, newState);

        try {
            SETTINGS_CONFIG.set(setting, newState);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newState;
    }

    public static void saveSettings() {
        try {
            SETTINGS_CONFIG.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadRandomizedItems() {
        if (!RANDOMIZED_ITEMS_CONFIG.getKeys(false).isEmpty()) {

            for (String key : RANDOMIZED_ITEMS_CONFIG.getKeys(false)) {
                RANDOMIZED_ITEMS.put(Material.valueOf(key),Material.valueOf((String) RANDOMIZED_ITEMS_CONFIG.get(key)));
            }

        } else {
            createRandomizedItems();
        }
    }

    public static Material getRandomItem() {
        int rndm = RANDOM.nextInt(Material.class.getEnumConstants().length);
        Material randomMaterial;

        do {
            rndm = RANDOM.nextInt(Material.class.getEnumConstants().length);
            randomMaterial = Material.class.getEnumConstants()[rndm];
        }
        while (!randomMaterial.isItem());

        return randomMaterial;
    }

    public static HashMap<Material, Material> getRandomizedItems() {
        return RANDOMIZED_ITEMS;
    }

    public static void createRandomizedItems() {
        int rndm;
        Material randomMaterial;

        for (Material material : Material.values()) {

            if (material.isItem()) {

                do {
                    rndm = RANDOM.nextInt(Material.class.getEnumConstants().length);
                    randomMaterial = Material.class.getEnumConstants()[rndm];
                }
                while (!randomMaterial.isItem());
                RANDOMIZED_ITEMS.put(material, randomMaterial);

                try {
                    RANDOMIZED_ITEMS_CONFIG.set(material.name(), randomMaterial.name());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void saveRandomizedItems() {
        try {
            RANDOMIZED_ITEMS_CONFIG.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getActiveType() {
        if (Randomizer.getBooleanSetting("randomizer_type")) {
            return "predictable";
        } else {
            return "chaotic";
        }
    }

    public static String toggleActiveType() {
        toggleSetting("randomizer_type");
        return getActiveType();
    }
}
