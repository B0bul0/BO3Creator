package me.bobulo.bo3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BO3Location {

    @NotNull
    public static BO3Location fromLocation(@NotNull Location location) {
        return new BO3Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    @NotNull
    public static BO3Location fromBlock(@NotNull Block block) {
        return new BO3Location(block.getWorld(), block.getX(), block.getY(), block.getZ());
    }

    @NotNull
    private World world;
    private int x;
    private int y;
    private int z;

    @NotNull
    public Block toBlock() {
        return world.getBlockAt(x, y, z);
    }

    @NotNull
    public Location toLocation() {
        return new Location(world, x, y, z);
    }

    @NotNull
    public String toString() {
        return "(" + world.getName() + ", " + x + ", " + y + ", " + z + ")";
    }

}
