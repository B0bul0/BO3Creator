package me.bobulo.bo3.command;

import me.bobulo.bo3.BO3CreatorPlugin;
import me.bobulo.bo3.util.Constant;
import me.bobulo.bo3.util.Pageable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.util.List;

public class ListCommand extends Command {

    private final BO3CreatorPlugin plugin;

    public ListCommand(BO3CreatorPlugin plugin) {
        super("bo3list");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!sender.hasPermission("bo3creator.create")) {
            sender.sendMessage("§cYou don't have permission to use this command!");
            return false;
        }

        List<File> structureFiles = plugin.getStructureManager().getStructureFiles();

        if (structureFiles.isEmpty()) {
            sender.sendMessage(Constant.PREFIX + "§cThere are no BO3 structures!");
            return false;
        }

        Pageable<File> pageable = new Pageable<>(structureFiles, 8);

        int page = 0;

        if (args.length > 0) {
            try {
                page = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException e) {
                sender.sendMessage(Constant.PREFIX + "§cOnly numbers are allowed for page number!");
                return false;
            }
        }

        if (page < 0 || page >= pageable.getPageCount()) {
            sender.sendMessage(Constant.PREFIX + "§cPage number must be between 1 and " + pageable.getPageCount() + "!");
            return false;
        }

        sender.sendMessage(Constant.PREFIX + "§eBO3 Structures §7(Page " + (page + 1) + "/" + pageable.getPageCount() + ")");
        for (File file : pageable.getPage(page)) {
            sender.sendMessage("§e- §f" + file.getName().replace(".bo3", ""));
        }

        if (pageable.getPageCount() > 1) {
            sender.sendMessage("§7Use /bo3list <page> to view other pages!");
        }

        return false;
    }

}
