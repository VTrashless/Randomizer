package tv.trashless.randomizer.utils;

import org.bukkit.Material;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;

public class Randomizer {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final HashMap<Material, Material> RANDOMIZED_ITEMS = new HashMap<>();
    private static final Config CONFIG = new Config("randomized_items");

    public static void loadRandomizedItems() {
        if (!CONFIG.getKeys(false).isEmpty()) {

            for (String key : CONFIG.getKeys(false)) {
                RANDOMIZED_ITEMS.put(Material.valueOf(key),Material.valueOf((String) CONFIG.get(key)));
            }

        } else {
            createRandomizedItems();
        }
    }

    public static void createRandomizedItems() {
        for (Material material : Material.values()) {
            int r;
            Material randomMaterial;

            do {
                r = RANDOM.nextInt(Material.class.getEnumConstants().length);
                randomMaterial = Material.class.getEnumConstants()[r];
            }
            while (!randomMaterial.isItem());
            RANDOMIZED_ITEMS.put(material, randomMaterial);

            try {
                CONFIG.set(material.name(), RANDOMIZED_ITEMS.get(material).name());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static HashMap<Material, Material> getRandomizedItems() {
        return RANDOMIZED_ITEMS;
    }
}
