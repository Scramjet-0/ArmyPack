package tech.scramjetdev.armypack.armypackweapons;

import org.bukkit.Bukkit;
import tech.scramjetdev.armypack.Main;
import tech.scramjetdev.armypack.armypackweapons.guns.GunListener;

public class Weapons {
    public Weapons() {
        Bukkit.getPluginManager().registerEvents(new GunListener(), Main.getPlugin());
    }
}
