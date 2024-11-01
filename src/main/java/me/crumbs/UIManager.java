package me.crumbs;

import me.crumbs.ui.Div;
import me.crumbs.ui.UI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.HashMap;

public class UIManager implements Listener {
    private static UIManager instance;
    public HashMap<Player, UI> active;

    public UIManager() throws Exception {
        if (instance != null) throw new Exception("Only 1 UIManger instance can be open at a time");
        active = new HashMap<>();
        instance = this;
    }

    public static UIManager getInstance() { return instance; }


    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!active.containsKey((Player) e.getWhoClicked())) return;
        var player = (Player) e.getWhoClicked();
        var clickedInv = e.getClickedInventory();
        if (clickedInv.equals(active.get(player).inv)) {
            var iSlot = e.getSlot();

        }
    }
}






















