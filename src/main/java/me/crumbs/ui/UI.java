package me.crumbs.ui;

import org.bukkit.inventory.Inventory;

public class UI {
    public Inventory inv;
    public Div root;

    public UI(Inventory inv, Div root) {
        this.inv = inv;
        this.root = root;
    }

    public Div getClickedDiv(int i) {
        // left off here
        // convert slot index to vec2

        var div = root;
        var pos = div.options.position;
        var size = div.size;

        while (true) {

        }
    }
}

