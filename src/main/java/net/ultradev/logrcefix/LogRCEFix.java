package net.ultradev.logrcefix;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LogRCEFix extends JavaPlugin implements Listener {
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().matches("(?s).*\\$\\{jndi:.+}.*")) {
            System.out.println("WARNING: " + event.getPlayer().getName() + " attempted to exploit the log RCE.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().matches("(?s).*\\$\\{jndi:.+}.*")) {
            System.out.println("WARNING: " + event.getPlayer().getName() + " attempted to exploit the log RCE.");
            event.setCancelled(true);
        }
    }
}
