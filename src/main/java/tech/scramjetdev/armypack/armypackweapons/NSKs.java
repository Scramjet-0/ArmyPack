package tech.scramjetdev.armypack.armypackweapons;

import org.bukkit.NamespacedKey;
import tech.scramjetdev.armypack.Main;

public enum NSKs {
    GUNID(new NamespacedKey(Main.getPlugin(), "GunType")),
    BULLETSCHARGED(new NamespacedKey(Main.getPlugin(), "BulletsCharged")),
    COOLDOWN(new NamespacedKey(Main.getPlugin(), "CoolDown"));

    private final NamespacedKey namespacedKey;

    NSKs(NamespacedKey namespacedKey) {
        this.namespacedKey = namespacedKey;
    }

    public NamespacedKey get() {
        return namespacedKey;
    }
}
