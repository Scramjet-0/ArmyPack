package tech.scramjetdev.armypack.armypackweapons.guns;


import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class GunManager {


    public void fire(Action action, ItemStack itemStack, Player player, Location location, Vector vector, int id) {
        if (id < 0 || id >= Guns.values().length) return;
        Guns.values()[id].execute(action, itemStack, player);
    }
}
