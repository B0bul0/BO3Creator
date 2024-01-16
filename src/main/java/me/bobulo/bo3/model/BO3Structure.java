package me.bobulo.bo3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BO3Structure {

    @NotNull
    private String name;

    @NotNull
    private BO3Location center;

    @NotNull
    private BO3Location pos1;

    @NotNull
    private BO3Location pos2;

    @NotNull
    public List<BO3Block> getCuboidBlocks() {
        List<BO3Block> blocks = new ArrayList<>();

        int x = Math.min(this.pos1.getX(), this.pos2.getX());
        int y = Math.min(this.pos1.getY(), this.pos2.getY());
        int z = Math.min(this.pos1.getZ(), this.pos2.getZ());
        int X = Math.max(this.pos1.getX(), this.pos2.getX());
        int Y = Math.max(this.pos1.getY(), this.pos2.getY());
        int Z = Math.max(this.pos1.getZ(), this.pos2.getZ());

        World world = this.pos1.getWorld();

        for (int bX = x; bX <= X; ++bX) {
            for (int bY = y; bY <= Y; ++bY) {
                for (int bZ = z; bZ <= Z; ++bZ) {
                    blocks.add(BO3Block.fromBlock(world.getBlockAt(bX, bY, bZ)));
                }
            }
        }

        return blocks;
    }

}
