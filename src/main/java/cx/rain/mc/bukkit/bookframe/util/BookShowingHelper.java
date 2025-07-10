package cx.rain.mc.bukkit.bookframe.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.WritableBookMeta;

public class BookShowingHelper {

    public static boolean isBook(ItemStack item) {
        if (item == null) {
            return false;
        }

        var type = item.getType();
        return type.equals(Material.WRITTEN_BOOK) || type.equals(Material.WRITABLE_BOOK);
    }

    @SuppressWarnings("deprecation")
    public static void openBook(Player player, ItemStack item) {
        if (item.getType().equals(Material.WRITTEN_BOOK)) {
            player.openBook(item);
            return;
        }

        if (item.getType().equals(Material.WRITABLE_BOOK)) {
            var meta = item.getItemMeta();
            if (meta instanceof WritableBookMeta bookMeta) {
                var dummyBook = new ItemStack(Material.WRITTEN_BOOK);
                var m = dummyBook.getItemMeta();

                if (m instanceof BookMeta b) {
                    var pages = bookMeta.getPages();
                    b.setPages(pages);
                    b.setAuthor("Anonymous");
                    b.setTitle("Book");

                    dummyBook.setItemMeta(b);
                    player.openBook(dummyBook);
                }
            }
        }
    }
}
