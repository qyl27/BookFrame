package cx.rain.mc.bukkit.bookframe.event;

import cx.rain.mc.bukkit.bookframe.util.BookShowingHelper;
import org.bukkit.Material;
import org.bukkit.block.data.type.ChiseledBookshelf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

@SuppressWarnings("deprecation")
public class BlockHandler implements Listener {
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEvent event) {
        var player = event.getPlayer();
        var block = event.getClickedBlock();

        if (block == null) {
            return;
        }

        if (block.getType() != Material.CHISELED_BOOKSHELF) {
            return;
        }

        if (!(block.getState() instanceof org.bukkit.block.ChiseledBookshelf bookshelf)) {
            return;
        }

        if (player.isSneaking()) {
            return;
        }

        var face = event.getBlockFace();
        var data = block.getBlockData();
        if (!(data instanceof ChiseledBookshelf bookshelfData)) {
            return;
        }

        if (bookshelfData.getFacing() != face) {
            return;
        }

        var clicked = event.getClickedPosition();
        if (clicked == null) {
            return;
        }

        var clickedSlot = bookshelf.getSlot(clicked);
        if (clickedSlot == -1) {
            return;
        }

        var playerInventory = player.getInventory();
        if (playerInventory.getItemInMainHand().isEmpty()) {
            return;
        }

        var inventory = bookshelf.getInventory();
        var item = inventory.getItem(clickedSlot);
        if (BookShowingHelper.isBook(item)) {
            BookShowingHelper.openBook(player, item);
            event.setCancelled(true);
        }
    }
}
