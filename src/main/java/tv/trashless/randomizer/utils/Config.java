package tv.trashless.randomizer.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Config {
    private final File file;
    private final YamlConfiguration config;

    public Config(String configName) {
        File dir = new File("./plugins/Randomizer/");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        file = new File(dir, configName + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public boolean contains(String path) {
        return config.contains(path);
    }

    public void set(String path, Object value) throws IOException {
        config.set(path, value);
    }

    public Object get(String path) {
        if (contains(path)) return config.get(path);
        return null;
    }

    public void save() throws IOException {
        config.save(file);
    }

    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }
}
