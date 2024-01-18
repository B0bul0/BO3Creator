package me.bobulo.bo3.manager;

import com.google.common.collect.ImmutableList;
import me.bobulo.bo3.BO3CreatorPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public class StructureManager {

    private final BO3CreatorPlugin plugin;

    public StructureManager(BO3CreatorPlugin plugin) {
        this.plugin = plugin;
    }

    public File getStructureDirectory() {
        return new File(plugin.getDataFolder(), "BO3");
    }

    @NotNull
    public List<File> getStructureFiles() {
        File[] files = getStructureDirectory().listFiles((dir, name) -> name.endsWith(".bo3"));
        if (files == null) {
            return ImmutableList.of();
        }

        return ImmutableList.copyOf(files);
    }

    public File getStructureFile(String name) {
        return new File(plugin.getDataFolder(), name + ".bo3");
    }

}
