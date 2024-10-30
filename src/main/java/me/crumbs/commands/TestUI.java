package me.crumbs.commands;

import me.crumbs.ui.Options;
import me.crumbs.ui.Window;
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

        var window = new Window.Builder(new Vec2(9, 3))
            .body(
                Window.window(new Vec2(3, 3),
                    Window.window(new Vec2(1, 1)).build(new Options()
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
        window.render(inv);

        player.openInventory(inv);
        return true;
    }
}
