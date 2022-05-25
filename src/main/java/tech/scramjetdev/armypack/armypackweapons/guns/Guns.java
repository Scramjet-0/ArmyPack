package tech.scramjetdev.armypack.armypackweapons.guns;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import tech.scramjetdev.armypack.Main;
import tech.scramjetdev.armypack.armypackweapons.NSKs;
import tech.scramjetdev.armypack.armypackweapons.guns.bullets.BasicBullet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public enum Guns {
    REVOLVER(6, 0.1, 0.1, 300, 7 * 20, 1, 20, new BulletLaunch() {
        @Override
        void launch(double additionalPrecision, double additionalDamage, Player shooter, Vector vector, Location location) {
            new BasicBullet(additionalPrecision, additionalDamage, shooter, vector, location);
        }
    }, ()-> {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_HOE);
        Damageable itemMeta = (Damageable) itemStack.getItemMeta();
        Validate.isTrue(itemMeta != null);
        itemMeta.setDisplayName("§7Revolver");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("§k»§r Revolver §k«");
        lore.add("§fDamage : §4 0.5");
        lore.add("§rPlugin : ArmyPack");
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(NSKs.GUNID.get(), PersistentDataType.INTEGER, 0);
        itemMeta.getPersistentDataContainer().set(NSKs.BULLETSCHARGED.get(), PersistentDataType.INTEGER, 0);
        itemMeta.getPersistentDataContainer().set(NSKs.COOLDOWN.get(), PersistentDataType.LONG, System.currentTimeMillis());
        itemMeta.setDamage(itemStack.getType().getMaxDurability());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }),
    EAGLE(9, 0.2, 0.06, 150, 8 * 20, 1, 20, new BulletLaunch() {
        @Override
        void launch(double additionalPrecision, double additionalDamage, Player shooter, Vector vector, Location location) {
            new BasicBullet(additionalPrecision, additionalDamage, shooter, vector, location);
        }
    }, () -> {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_HOE);
        Damageable itemMeta = (Damageable) itemStack.getItemMeta();
        Validate.isTrue(itemMeta != null);
        itemMeta.setDisplayName("§7Desert Eagle");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("§k»§r Desert Eagle §k«");
        lore.add("§fDamage : §4 ");
        lore.add("§rPlugin : ArmyPack");
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(NSKs.GUNID.get(), PersistentDataType.INTEGER, 1);
        itemMeta.getPersistentDataContainer().set(NSKs.BULLETSCHARGED.get(), PersistentDataType.INTEGER, 0);
        itemMeta.getPersistentDataContainer().set(NSKs.COOLDOWN.get(), PersistentDataType.LONG, System.currentTimeMillis());
        itemMeta.setDamage(itemStack.getType().getMaxDurability());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }),
    AK47(30, 0.35, 0.04, 150, 10 * 20, 3, 3, new BulletLaunch() {
        @Override
        void launch(double additionalPrecision, double additionalDamage, Player shooter, Vector vector, Location location) {
            new BasicBullet(additionalPrecision, additionalDamage, shooter, vector, location);
        }
    }, ()-> {
        ItemStack itemStack = new ItemStack(Material.NETHERITE_HOE);
        Damageable itemMeta = (Damageable) itemStack.getItemMeta();
        Validate.isTrue(itemMeta != null);
        itemMeta.setDisplayName("§7Ak47");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        List<String> lore = new ArrayList<>();
        lore.add("§k»§r Ak47 §k«");
        lore.add("§fDamage : §4 ");
        lore.add("§rPlugin : ArmyPack");
        itemMeta.setLore(lore);
        itemMeta.getPersistentDataContainer().set(NSKs.GUNID.get(), PersistentDataType.INTEGER, 2);
        itemMeta.getPersistentDataContainer().set(NSKs.BULLETSCHARGED.get(), PersistentDataType.INTEGER, 0);
        itemMeta.getPersistentDataContainer().set(NSKs.COOLDOWN.get(), PersistentDataType.LONG, System.currentTimeMillis());
        itemMeta.setDamage(itemStack.getType().getMaxDurability());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    });
    private final int bulletCapacity;
    private final double additionalDamage;
    private final double additionalPrecisionRan;
    private final long coolDown;
    private final int reloadTick;
    private final int bulletsPerShot;
    private final int timeBetweenShotsInTick;
    private final BulletLaunch bulletLaunch;
    private final ItemStack gunItemStack;
    Guns(int bulletCapacity, double additionalDamage, double additionalPrecisionRan, long coolDown, int reloadTick, int bulletsPerShot, int timeBetweenShotsInTick, BulletLaunch bulletLaunch, Supplier<ItemStack> itemStackSupplier) {

        this.bulletCapacity = bulletCapacity;
        this.additionalDamage = additionalDamage;
        this.additionalPrecisionRan = additionalPrecisionRan;
        this.coolDown = coolDown;
        this.reloadTick = reloadTick;
        this.bulletsPerShot = bulletsPerShot;
        this.timeBetweenShotsInTick = timeBetweenShotsInTick;
        this.bulletLaunch = bulletLaunch;
        this.gunItemStack = itemStackSupplier.get();
    }
    public void execute(Action action, ItemStack itemStack, Player player) {
        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && System.currentTimeMillis() - getCoolDown(itemStack, player) > coolDown){
            fire(bulletsPerShot, itemStack, player);
        } else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK){
            reload(itemStack, player);
        }
    }
    private void fire(int i, ItemStack itemStack, Player player) {
        if (i <= 0 || getBulletsCharged(itemStack, player) <= 0) return;
        bulletLaunch.launch(additionalPrecisionRan, additionalDamage, player, player.getLocation().getDirection(), player.getEyeLocation());
        setBulletsCharged(getBulletsCharged(itemStack, player) - 1, itemStack, player);
        setCoolDown(itemStack, player);
        setItemDamage(getBulletsCharged(itemStack, player), bulletCapacity, itemStack, player);
        new BukkitRunnable() {
            @Override
            public void run() {
                fire(i - 1 , itemStack, player);
            }
        }.runTaskLater(Main.getPlugin(), timeBetweenShotsInTick);
    }

    private void reload(ItemStack itemStack, Player player) {
        if (getBulletsCharged(itemStack, player) == -999) return;
        setBulletsCharged(-999, itemStack, player);
        setItemReloading(true, itemStack, player);
        new BukkitRunnable() {
            @Override
            public void run() {
                setBulletsCharged(bulletCapacity, itemStack, player);
                setItemReloading(false, itemStack, player);
                setItemDamage(100, 100, itemStack, player);
            }
        }.runTaskLater(Main.getPlugin(), reloadTick);
    }

    // Useful
    private int getBulletsCharged(ItemStack itemStack, Player player) {
        Validate.isTrue(itemStack.getItemMeta() != null);
        Integer integer = itemStack.getItemMeta().getPersistentDataContainer().get(NSKs.BULLETSCHARGED.get(), PersistentDataType.INTEGER);
        Validate.isTrue(integer != null);
        return integer;
    }
    private void setBulletsCharged(int i, ItemStack itemStack, Player player) {
        Validate.isTrue(itemStack.getItemMeta() != null);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(NSKs.BULLETSCHARGED.get(), PersistentDataType.INTEGER, i);
        itemStack.setItemMeta(itemMeta);
    }

    private long getCoolDown(ItemStack itemStack, Player player) {
        Validate.isTrue(itemStack.getItemMeta() != null);
        Long aLong = itemStack.getItemMeta().getPersistentDataContainer().get(NSKs.COOLDOWN.get(), PersistentDataType.LONG);
        Validate.isTrue(aLong != null);
        return aLong;
    }

    private void setCoolDown(ItemStack itemStack, Player player) {
        Validate.isTrue(itemStack.getItemMeta() != null);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(NSKs.COOLDOWN.get(), PersistentDataType.LONG, System.currentTimeMillis());
        itemStack.setItemMeta(itemMeta);
    }

    private void setItemDamage(double bullets, double capacity, ItemStack itemStack, Player player) {
        int toDamage = itemStack.getType().getMaxDurability() - (int) (itemStack.getType().getMaxDurability() * (bullets / capacity));
        Damageable damageable = (Damageable) itemStack.getItemMeta();
        if (damageable == null) return;
        damageable.setDamage(toDamage);
        itemStack.setItemMeta(damageable);
    }

    private void setItemReloading(boolean bol, ItemStack itemStack, Player player) {
        Validate.isTrue(itemStack.getItemMeta() != null);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (bol) {
            itemMeta.addEnchant(Enchantment.OXYGEN, 1, true);
        } else {
            itemMeta.removeEnchant(Enchantment.OXYGEN);
        }
        itemStack.setItemMeta(itemMeta);
    }


    public ItemStack getGunItemStack() {
        return gunItemStack;
    }
}
