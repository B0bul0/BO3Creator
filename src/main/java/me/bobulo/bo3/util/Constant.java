package me.bobulo.bo3.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Constant {

    public static final String PREFIX = "§b[BO3Creator]§a ";

    public static final ItemStack WAND_ITEM = createWandItem();

    public static ItemStack createWandItem() {
        ItemStack itemStack = new ItemStack(Material.GOLD_AXE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName("§eBO3 Wand");

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
