package tech.scramjetdev.armypack.armypackweapons;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public enum GunsItems {
    REVOLVER(()-> {
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
        itemMeta.getPersistentDataContainer().set(NSKs.GUNID.get(), PersistentDataType.STRING, "revolver");
        itemMeta.getPersistentDataContainer().set(NSKs.BULLETSCHARGED.get(), PersistentDataType.INTEGER, 0);
        itemMeta.getPersistentDataContainer().set(NSKs.COOLDOWN.get(), PersistentDataType.LONG, System.currentTimeMillis());
        itemMeta.setDamage(itemStack.getType().getMaxDurability());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }),
    DEAGLE(()-> {
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
        itemMeta.getPersistentDataContainer().set(NSKs.GUNID.get(), PersistentDataType.STRING, "deagle");
        itemMeta.getPersistentDataContainer().set(NSKs.BULLETSCHARGED.get(), PersistentDataType.INTEGER, 0);
        itemMeta.getPersistentDataContainer().set(NSKs.COOLDOWN.get(), PersistentDataType.LONG, System.currentTimeMillis());
        itemMeta.setDamage(itemStack.getType().getMaxDurability());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }),
    AK47(()-> {
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
        itemMeta.getPersistentDataContainer().set(NSKs.GUNID.get(), PersistentDataType.STRING, "ak47");
        itemMeta.getPersistentDataContainer().set(NSKs.BULLETSCHARGED.get(), PersistentDataType.INTEGER, 0);
        itemMeta.getPersistentDataContainer().set(NSKs.COOLDOWN.get(), PersistentDataType.LONG, System.currentTimeMillis());
        itemMeta.setDamage(itemStack.getType().getMaxDurability());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    });
    final Supplier<ItemStack> itemStackSupplier;

    GunsItems(Supplier<ItemStack> itemStackSupplier) {
        this.itemStackSupplier = itemStackSupplier;
    }

    public ItemStack get(int amount) {
        ItemStack itemStack = itemStackSupplier.get();
        itemStack.setAmount(amount);
        return itemStack;
    }
}
