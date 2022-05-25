package tech.scramjetdev.armypack.armypackweapons.guns.bullets;

import org.apache.commons.lang.Validate;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class BasicBullet extends Bullet{
    Particle.DustTransition dustTransition = new Particle.DustTransition(Color.fromRGB(255, 72, 0), Color.GRAY, 0.5F);

    public BasicBullet(double additionalPrecisionRan,double additionalDamage, Player shooter, Vector vector, Location location) {
        super(0.5, 200, 0.01, additionalPrecisionRan, additionalDamage,0.28, shooter, vector, location);
    }

    @Override
    void launchBulletAnimation() {

    }

    @Override
    void bulletAnimation() {
        Validate.isTrue(currentLoc.getWorld() != null);
        currentLoc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, currentLoc, 1, dustTransition);
    }

    @Override
    void bulletHitEntity() {
        Validate.isTrue(currentLoc.getWorld() != null);
        BlockData redStoneBlockData = Material.REDSTONE_BLOCK.createBlockData();
        currentLoc.getWorld().spawnParticle(Particle.BLOCK_DUST, currentLoc, 10, damage / 2.5, damage / 2.5, damage / 2.5, redStoneBlockData);
    }

    @Override
    void bulletHitBlock() {
        Validate.isTrue(currentLoc.getWorld() != null);
        BlockData blockData = tempBlock.getBlockData();
        currentLoc.getWorld().spawnParticle(Particle.BLOCK_DUST, currentLoc, 10, damage / 2.5, damage / 2.5, damage / 2.5, blockData);
    }
}
