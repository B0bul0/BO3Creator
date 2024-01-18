package me.bobulo.bo3;

import lombok.Getter;
import me.bobulo.bo3.command.*;
import me.bobulo.bo3.listener.PlayerListener;
import me.bobulo.bo3.listener.WandListener;
import me.bobulo.bo3.manager.PlayerManager;
import me.bobulo.bo3.manager.StructureManager;
import me.bobulo.bo3.util.CommandUtils;
import me.bobulo.bo3.writer.BO3CreatorWriter;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BO3CreatorPlugin extends JavaPlugin {

    @Getter
    private PlayerManager playerManager;

    @Getter
    private BO3CreatorWriter bo3CreatorWriter;

    @Getter
    private StructureManager structureManager;

    @Override
    public void onEnable() {

        this.playerManager = new PlayerManager();
        this.structureManager = new StructureManager(this);
        this.bo3CreatorWriter = new BO3CreatorWriter(this);

        // Register listeners
        registerEvents(new WandListener(this));
        registerEvents(new PlayerListener(this));

        // Register commands
        registerCommand(new CenterCommand(this));
        registerCommand(new CreateCommand(this));
        registerCommand(new WandCommand(this));
        registerCommand(new PosCommand(this, 1));
        registerCommand(new PosCommand(this, 2));
        registerCommand(new ListCommand(this));
        registerCommand(new AliasCommand(this));

        getLogger().info("BO3Creator has been enabled!");
    }

    @Override
    public void onDisable() {
        if (this.playerManager != null) {
            this.playerManager.clear();
        }

        getLogger().info("BO3Creator has been disabled!");
    }

    public void registerEvents(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public void registerCommand(Command command) {
        CommandUtils.registerCommand(command, this);
    }

}
