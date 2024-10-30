package me.crumbs.ui;

import me.crumbs.utils.Vec2;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Options {
    public Vec2 position;
    public ItemStack background;

    public Options() {
        position = null;
        background = null;
    }

    public Options pos(int posX, int posY) {
        position = new Vec2(posX, posY);
        return this;
    }

    public Options bg(ItemStack backgroundItem) {
        background = backgroundItem;
        return this;
    }

    public Options bg(Material mat, String name, ArrayList<String> lore) {
        var item = new ItemStack(mat);
        var meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        background = item;

        return this;
    }
}
