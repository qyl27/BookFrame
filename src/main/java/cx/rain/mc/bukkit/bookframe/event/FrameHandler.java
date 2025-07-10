package cx.rain.mc.bukkit.bookframe.event;

import cx.rain.mc.bukkit.bookframe.util.BookShowingHelper;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class FrameHandler implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        var entity = event.getRightClicked();
        var player = event.getPlayer();

        if (entity instanceof ItemFrame frame) {
            if (player.isSneaking()) {
                return;
            }

            var item = frame.getItem();
            if (BookShowingHelper.isBook(item)) {
                frame.setRotation(frame.getRotation().rotateCounterClockwise());
                BookShowingHelper.openBook(player, item);
            }
        }
    }
}
