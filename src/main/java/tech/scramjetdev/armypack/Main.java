package tech.scramjetdev.armypack;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tech.scramjetdev.armypack.armypackweapons.Weapons;
import tech.scramjetdev.armypack.armypackweapons.commands.GetWeaponCommand;

public class Main extends JavaPlugin {
    private static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        registerCommands();
        new Weapons();
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    private void registerCommands() {
        this.getCommand("getweapon").setExecutor(new GetWeaponCommand());
    }
}
