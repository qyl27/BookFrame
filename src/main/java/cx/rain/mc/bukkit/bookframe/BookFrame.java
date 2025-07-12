package cx.rain.mc.bukkit.bookframe;

import cx.rain.mc.bukkit.bookframe.event.BlockHandler;
import cx.rain.mc.bukkit.bookframe.event.FrameHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BookFrame extends JavaPlugin {
    public static final String VERSION = "1.3.0";

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("BookFrame ver: " + VERSION);

        Bukkit.getPluginManager().registerEvents(new FrameHandler(), this);
        Bukkit.getPluginManager().registerEvents(new BlockHandler(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("BookFrame is disabled.");
    }
}
