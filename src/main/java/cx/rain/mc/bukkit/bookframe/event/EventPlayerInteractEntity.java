package cx.rain.mc.bukkit.bookframe.event;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class EventPlayerInteractEntity implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        if (entity instanceof ItemFrame) {
            ItemFrame frame = (ItemFrame) entity;

            ItemStack item = frame.getItem();
            if (item.getType().equals(Material.WRITTEN_BOOK)) {
                Player player = event.getPlayer();
                if (player.isSneaking()) {
                    return;
                }

                frame.setRotation(frame.getRotation().rotateCounterClockwise());
                player.openBook(item);
            }
        }
    }
}
