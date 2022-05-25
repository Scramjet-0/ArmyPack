package tech.scramjetdev.armypack.armypackweapons.guns;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import tech.scramjetdev.armypack.armypackweapons.NSKs;
import tech.scramjetdev.armypack.armypackweapons.guns.GunManager;

public class GunListener implements Listener {
    GunManager gunManager = new GunManager();

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getItemMeta() != null && event.getItem().getItemMeta().getPersistentDataContainer().has(NSKs.GUNID.get(), PersistentDataType.INTEGER)) {
            Integer integer = event.getItem().getItemMeta().getPersistentDataContainer().get(NSKs.GUNID.get(), PersistentDataType.INTEGER);
            if (integer == null) return;
            event.setCancelled(true);
            gunManager.fire(event.getAction(), event.getItem(), event.getPlayer(), event.getPlayer().getEyeLocation(), event.getPlayer().getLocation().getDirection(), integer);
        }
    }
}
