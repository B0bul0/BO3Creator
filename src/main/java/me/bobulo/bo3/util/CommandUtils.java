package me.bobulo.bo3.util;

import org.bukkit.command.Command;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommandUtils {

    public static void registerCommand(@NotNull Command command, @NotNull Plugin plugin) {
        try {
            Method commandMap = plugin.getServer().getClass().getMethod("getCommandMap");

            Object cmdmap = commandMap.invoke(plugin.getServer());
            Method register = cmdmap.getClass().getMethod("register", String.class, Command.class);
            register.invoke(cmdmap, plugin.getName(), command);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
