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
        var entity = event.getRightClicked();
        var player = event.getPlayer();

        if (entity instanceof ItemFrame frame) {
            if (player.isSneaking()) {
                return;
            }

            var item = frame.getItem();
            if (item.getType().equals(Material.WRITTEN_BOOK)) {
                frame.setRotation(frame.getRotation().rotateCounterClockwise());
                player.openBook(item);
            }

            if (item.getType().equals(Material.WRITABLE_BOOK)) {
                frame.setRotation(frame.getRotation().rotateCounterClockwise());

                // It has item meta, ignore may NPE warn.
                var meta = item.getItemMeta();

                // It must be a BookMeta.
                if (meta instanceof BookMeta bookMeta) {
                    var writtenBook = new ItemStack(Material.WRITTEN_BOOK);
                    var writtenMeta = writtenBook.getItemMeta();

                    if (writtenMeta instanceof BookMeta writtenBookMeta) {
                        var pages = bookMeta.getPages();
                        writtenBookMeta.setPages(pages);
                        writtenBookMeta.setAuthor("Anonymous");
                        writtenBookMeta.setTitle("Book");

                        writtenBook.setItemMeta(writtenBookMeta);
                        player.openBook(writtenBook);
                    }
                }
            }
        }
    }
}
