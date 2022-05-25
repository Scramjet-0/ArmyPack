package tech.scramjetdev.armypack.armypackweapons.guns.bullets;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import tech.scramjetdev.armypack.Main;
import tech.scramjetdev.armypack.armypackweapons.events.PlayerGetShotEvent;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public abstract class Bullet extends BukkitRunnable {
    private int checkN = 45;
    private double detectPlayerRadius = 0.2;
    // Specific to the type of bullet
    final double damage;
    private final int lifeTime;
    // Specific to the bullet code
    private final long millisBulletCreation;
    // Specific to the bullet creation
    private final Player shooter;
    private final Vector speedVector;
    final Location currentLoc;
    // re-used objects
    Block tempBlock;


    public Bullet( double damage, int lifeTime, double precisionRan, double additionalPrecisionRan,double additionalDamage, double speed, Player shooter, Vector vector, Location location) {
        this.damage = damage + additionalDamage;
        this.lifeTime = lifeTime;
        this.millisBulletCreation = System.currentTimeMillis();
        this.shooter = shooter;
        this.speedVector = createSpeedVector(vector, speed, precisionRan + additionalPrecisionRan);
        this.currentLoc = location;

        launchBulletAnimation();
        runTaskTimer(Main.getPlugin(), 0, 2);
        shooter.sendMessage("Bullet sended");
    }
    private Vector createSpeedVector(Vector vector, double speed, double precisionRan) {
        double x = ThreadLocalRandom.current().nextDouble(-precisionRan, precisionRan);
        double y = ThreadLocalRandom.current().nextDouble(-precisionRan, precisionRan);
        double z = ThreadLocalRandom.current().nextDouble(-precisionRan, precisionRan);
        return vector.clone().normalize().multiply(speed).rotateAroundX(x).rotateAroundY(y).rotateAroundZ(z);
    }

    abstract void launchBulletAnimation();
    abstract void bulletAnimation();
    abstract void bulletHitEntity();
    abstract void bulletHitBlock();

    @Override
    public void run() {
        for (int i = 0; i < checkN; ++i) {
            currentLoc.add(speedVector);
            if (bulletShouldStop()){
                cancel();
                return;
            }
        }
        bulletAnimation();
    }

    private boolean bulletShouldStop() {
        // Check is the bullet is at the end of his life
        if (System.currentTimeMillis() - millisBulletCreation > lifeTime) return true;
        // Check if the bullet is hitting a block
        tempBlock = currentLoc.getBlock();
        if (!tempBlock.isPassable() && tempBlock.getBoundingBox().contains(currentLoc.toVector())) {
            bulletHitBlock();
            return true;
        }
        // Check if the bullet is hitting a player
        Validate.isTrue(currentLoc.getWorld() != null, "ArmyPack → Bullet → bulletShouldStop() → world is null");
        for (Entity entity : currentLoc.getWorld().getNearbyEntities(currentLoc, detectPlayerRadius, detectPlayerRadius, detectPlayerRadius)) {
            if (entity instanceof LivingEntity && entity != shooter) {
                boolean headshot = false;
                double diff = currentLoc.getY() - ((LivingEntity) entity).getEyeLocation().getY();
                if ((diff < 0.5 && diff >= 0) || (diff > -0.2 && diff <= 0)) {
                    headshot = true;
                }
                bulletDamageLivingEntity((LivingEntity) entity, headshot);
                return true;
            }
        }

        return false;

    }

    private void bulletDamageLivingEntity(LivingEntity livingEntity, boolean isHeadShot) {
        double damageToDo = damage;
        if (isHeadShot) damageToDo = (damageToDo * 1.5);
        if (livingEntity.isDead()) return;
        if (livingEntity.getHealth() - (damageToDo + 1) < 0) {
            livingEntity.setHealth(0);
        } else {
            livingEntity.damage(1);
            livingEntity.setHealth(livingEntity.getHealth() - damageToDo);
            if (livingEntity instanceof Mob mob) {
                mob.setTarget(shooter);
            }
            shooter.sendMessage(String.valueOf(damageToDo));
        }
        bulletHitEntity();
    }
}
