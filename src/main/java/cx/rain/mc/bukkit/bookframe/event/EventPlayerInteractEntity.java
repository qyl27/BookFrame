package cx.rain.mc.bukkit.bookframe.event;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class EventPlayerInteractEntity implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();

        if (entity instanceof ItemFrame) {
            ItemFrame frame = (ItemFrame) entity;

            if (player.isSneaking()) {
                return;
            }

            ItemStack item = frame.getItem();
            if (item.getType().equals(Material.WRITTEN_BOOK)) {
                frame.setRotation(frame.getRotation().rotateCounterClockwise());
                player.openBook(item);
            }

            if (item.getType().equals(Material.WRITABLE_BOOK)) {
                frame.setRotation(frame.getRotation().rotateCounterClockwise());

                ItemMeta meta = item.getItemMeta();
                // I believe the meta of writable book is BookMeta.
                assert meta instanceof BookMeta;
                BookMeta bookMeta = (BookMeta) meta;

                ItemStack tmpStack = new ItemStack(Material.WRITTEN_BOOK);
                tmpStack.setItemMeta(bookMeta);
                player.openBook(tmpStack);
            }
        }
    }
}
