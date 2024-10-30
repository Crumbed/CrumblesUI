package me.crumbs.ui;

import me.crumbs.utils.Cell;
import me.crumbs.utils.Tuple;
import me.crumbs.utils.Vec2;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Window {
    // 0: x, 1: y
    public final Vec2 size;
    public Window[] children;
    public HashMap<Window, Vec2> childrenPositions;
    public final Options options;

    private Window(Vec2 size, ArrayList<Window> children, Options options) {
        this.size = size;
        this.children = children.toArray(Window[]::new);
        this.options = options;
        childrenPositions = new HashMap<>();

        for (var w : this.children) {
            childrenPositions.put(w, w.options.position);
        }
    }

    /**
    * @param size   null if determined during build
    */
    public static Builder window(Vec2 size, Window... body) {
        var builder = new Builder(size);
        builder.body(body);

        return builder;
    }

    public String render(Inventory inv) {
        var invHeight = inv.getSize() / 9;
        if (size.x() > 9 || invHeight < size.y()) return "Window is too large for given container";
        var pos = options.position.y() * invHeight + options.position.x() + 1;
        var bg = (options.background == null) ? new ItemStack(Material.AIR) : options.background;

        var fullBuffer = new ArrayList<Cell<ItemStack>>();
        for (var i = 0; i < inv.getSize(); i++) {
            fullBuffer.add(new Cell<>(new ItemStack(Material.AIR)));
        }

        var buffer = new ArrayList<Cell<ItemStack>>();
        for (var y = 0; y < size.y(); y++) {
            for (var x = 0; x < size.x(); x++) {
                var i = pos + 9 * y - 1 + x;
                buffer.add(fullBuffer.get(i));
                buffer.getLast().inner = bg;
            }
        }

        for (var child : children) {
            if (child.size.x() > size.x() || child.size.y() > size.y()) return "Child window is too large for parent";
            var childPos = child.options.position.y() * size.y() + child.options.position.x() + 1;
            var childBuffer = new ArrayList<Cell<ItemStack>>();

            for (var y = 0; y < child.size.y(); y++) {
                for (var x = 0; x < child.size.x(); x++) {
                    childBuffer.add(buffer.get(childPos + size.x() * y - 1 + x));
                }
            }

            var ret = child.render(childBuffer);
            if (ret != null) return ret;
        }

        for (var i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, fullBuffer.get(i).inner);
        }

        return null;
    }

    private String render(ArrayList<Cell<ItemStack>> buffer) {
        var bg = (options.background == null) ? new ItemStack(Material.AIR) : options.background;
        for (var cell : buffer) {
            cell.inner = bg;
        }

        for (var child : children) {
            if (child.size.x() > size.x() || child.size.y() > size.y()) return "Child window is too large for parent";
            var childPos = child.options.position.y() * size.y() + child.options.position.x() + 1;
            var childBuffer = new ArrayList<Cell<ItemStack>>();

            for (var y = 0; y < child.size.y(); y++) {
                for (var x = 0; x < child.size.x(); x++) {
                    childBuffer.add(buffer.get(childPos + size.x() * y - 1 + x));
                }
            }

            var ret = child.render(childBuffer);
            if (ret != null) return ret;
        }

        return null;
    }



    public static class Builder {
        public Vec2 size;
        public ArrayList<Window> children = null;

        /**
        * @param size   null if determined during build
        */
        public Builder(Vec2 size) {
            this.size = size;
        }

        public Builder body(Window... body) {
            if (children == null) {
                children = new ArrayList<>(Arrays.asList(body));
                return this;
            }
            children.addAll(Arrays.asList(body));
            return this;
        }

        public Window build(Options ops) {
            return new Window(size, children, ops);
        }
    }
}























