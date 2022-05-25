package tech.scramjetdev.armypack.armypackweapons.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerGetShotEvent extends Event {

    private final Player shooter;
    private final Player victim;
    private final Location victimDeathLoc;

    public PlayerGetShotEvent(Player shooter, Player victim, Location victimDeathLoc) {
        this.shooter = shooter;
        this.victim = victim;
        this.victimDeathLoc = victimDeathLoc;
    }

    private static final HandlerList HANDLERS = new HandlerList();
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public Player getShooter() {
        return shooter;
    }

    public Player getVictim() {
        return victim;
    }

    public Location getVictimDeathLoc() {
        return victimDeathLoc;
    }
}
