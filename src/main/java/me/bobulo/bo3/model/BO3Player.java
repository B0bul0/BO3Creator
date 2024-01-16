package me.bobulo.bo3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BO3Player {

    @NotNull
    private UUID playerUuid;

    private BO3Location center;
    private BO3Location pos1;
    private BO3Location pos2;

    public BO3Player(@NotNull UUID playerUuid) {
        this.playerUuid = playerUuid;
    }

    public void setPos(int posId, BO3Location location) {
        switch (posId) {
            case 1:
                this.pos1 = location;
                break;
            case 2:
                this.pos2 = location;
                break;
            default:
                throw new IllegalArgumentException("Invalid posId: " + posId);
        }
    }
}
