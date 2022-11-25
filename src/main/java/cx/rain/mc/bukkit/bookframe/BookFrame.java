package cx.rain.mc.bukkit.bookframe;

import cx.rain.mc.bukkit.bookframe.event.EventPlayerInteractEntity;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BookFrame extends JavaPlugin {
    public static final String VERSION = "@version@";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("BookFrame ver: " + VERSION);

        Bukkit.getPluginManager().registerEvents(new EventPlayerInteractEntity(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("BookFrame is disabled.");
    }
}
