package me.bobulo.bo3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BO3Block {

    @NotNull
    public static BO3Block fromLocation(@NotNull Location location) {
        return fromBlock(location.getBlock());
    }

    @NotNull
    public static BO3Block fromBlock(@NotNull Block block) {
        return new BO3Block(BO3Location.fromBlock(block), block.getType(), block.getData());
    }

    @NotNull
    private BO3Location bo3Location;

    @NotNull
    private Material material;

    private byte data;

}
