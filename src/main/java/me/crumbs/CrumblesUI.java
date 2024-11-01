package me.crumbs;

import me.crumbs.commands.TestUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class CrumblesUI extends JavaPlugin {

    @Override
    public void onEnable() {
        try { new UIManager(); } catch (Exception ignored) {}

        createCmd("testui", new TestUI());
    }

    @Override
    public void onDisable() {
    }

    private void createCmd(String name, CommandExecutor exe) { this.getCommand(name).setExecutor(exe); }
}
