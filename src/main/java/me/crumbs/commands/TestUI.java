package me.crumbs.commands;

import me.crumbs.UIManager;
import me.crumbs.ui.Options;
import me.crumbs.ui.Div;
import me.crumbs.ui.UI;
import me.crumbs.utils.Vec2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestUI implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Must be a player to execute this command!");
            return true;
        }

        var root = new Div.Builder(new Vec2(9, 3))
            .body(
                Div.div(new Vec2(3, 3),
                    Div.div(new Vec2(1, 1)).build(new Options()
                        .pos(1, 1)
                        .bg(Material.GREEN_STAINED_GLASS_PANE, ChatColor.GREEN + "Button", null)
                    )
                ).build(new Options()
                    .pos(3, 0)
                    .bg(Material.RED_STAINED_GLASS_PANE, " ", null)
                )
            ).build(new Options()
                .pos(0, 0)
            );

        var inv = Bukkit.createInventory(player, 27);
        root.render(inv);

        player.openInventory(inv);
        UIManager.getInstance()
            .activeWindows
            .put(player, new UI(inv, root));
        return true;
    }
}
