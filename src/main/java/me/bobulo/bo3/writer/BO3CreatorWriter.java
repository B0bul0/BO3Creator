package me.bobulo.bo3.writer;

import lombok.AllArgsConstructor;
import me.bobulo.bo3.BO3CreatorPlugin;
import me.bobulo.bo3.model.BO3Block;
import me.bobulo.bo3.model.BO3Location;
import me.bobulo.bo3.model.BO3Structure;
import org.apache.commons.lang3.Validate;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@AllArgsConstructor
public class BO3CreatorWriter {

    private static final String BLOCK_LINE = "Block(%s,%s,%s,%s)";

    private final BO3CreatorPlugin plugin;

    public File createBO3(@NotNull Player player, @NotNull BO3Structure bo3Structure) throws IOException {
        Validate.notEmpty(bo3Structure.getName(), "Name is null or empty!");
        Validate.notNull(bo3Structure.getPos1(), "Pos1 is not set!");
        Validate.notNull(bo3Structure.getPos2(), "Pos2 is not set!");
        Validate.notNull(bo3Structure.getCenter(), "Center is not set!");

        File file = plugin.getStructureManager().getStructureFile(bo3Structure.getName());
        if (file.exists()) {
            throw new IllegalArgumentException("File already exists!");
        }

        if (file.isDirectory()) {
            throw new IllegalArgumentException("File is a directory!");
        }

        // create BO3 parent folder if not exists
        file.getParentFile().mkdirs();

        // create BO3 file if not exists
        file.createNewFile();

        Block center = bo3Structure.getCenter().toBlock();

        List<BO3Block> blocks = bo3Structure.getCuboidBlocks();

        try (PrintWriter writer = new PrintWriter(file)) {

            writer.println("Author: " + player.getName());

            for (BO3Block block : blocks) {

                // ignore air blocks
                if (block.getMaterial() == Material.AIR) {
                    continue;
                }

                BO3Location bo3Location = block.getBo3Location();

                String material = block.getMaterial().name();
                if (block.getData() != 0) {
                    material = material + ":" + block.getData();
                }

                int x = bo3Location.getX() - center.getX();
                int y = bo3Location.getY() - center.getY();
                int z = bo3Location.getZ() - center.getZ();

                writer.println(String.format(BLOCK_LINE, x, y, z, material));

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        plugin.getLogger().info("Created BO3 " + bo3Structure.getName() + ".bo3");

        return file;
    }

}
