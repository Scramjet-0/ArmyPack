package tech.scramjetdev.armypack.armypackweapons.guns;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public abstract class BulletLaunch {
    abstract void launch(double additionalPrecision,double additionalDamage, Player shooter, Vector vector, Location location);

}
