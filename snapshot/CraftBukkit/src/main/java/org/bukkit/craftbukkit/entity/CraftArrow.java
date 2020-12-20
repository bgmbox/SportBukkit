package org.bukkit.craftbukkit.entity;

import com.google.common.base.Preconditions;
import net.minecraft.server.EntityArrow;

import org.apache.commons.lang.Validate;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.projectiles.ProjectileSource;

public class CraftArrow extends AbstractProjectile implements Arrow {

    public CraftArrow(CraftServer server, EntityArrow entity) {
        super(server, entity);
    }

    @Override
    public double getDamage() {
        return getHandle().getDamage();
    }

    @Override
    public void setDamage(double damage) {
        getHandle().setDamage(damage);
    }

    public void setKnockbackStrength(int knockbackStrength) {
        Validate.isTrue(knockbackStrength >= 0, "Knockback cannot be negative");
        getHandle().setKnockbackStrength(knockbackStrength);
    }

    public int getKnockbackStrength() {
        return getHandle().knockbackStrength;
    }

    public boolean isCritical() {
        return getHandle().isCritical();
    }

    public void setCritical(boolean critical) {
        getHandle().setCritical(critical);
    }

    public ProjectileSource getShooter() {
        return getHandle().projectileSource;
    }

    public void setShooter(ProjectileSource shooter) {
        if (shooter instanceof LivingEntity) {
            getHandle().shooter = ((CraftLivingEntity) shooter).getHandle();
        } else {
            getHandle().shooter = null;
        }
        getHandle().projectileSource = shooter;
    }

    @Override
    public boolean isInBlock() {
        return getHandle().inGround;
    }

    @Override
    public Block getAttachedBlock() {
        if (!isInBlock()) {
            return null;
        }

        EntityArrow handle = getHandle();
        return getWorld().getBlockAt(handle.h, handle.at, handle.au); // PAIL: rename tileX, tileY, tileZ
    }

    @Override
    public PickupStatus getPickupStatus() {
        return convertPickupStatus(getHandle().fromPlayer);
    }

    @Override
    public void setPickupStatus(PickupStatus status) {
        getHandle().fromPlayer = convertPickupStatus(status);
    }

    public static PickupStatus convertPickupStatus(EntityArrow.PickupStatus nms) {
        switch(nms) {
            case DISALLOWED: return PickupStatus.DISALLOWED;
            case ALLOWED: return PickupStatus.ALLOWED;
            case CREATIVE_ONLY: return PickupStatus.CREATIVE_ONLY;
            default: throw new IllegalStateException();
        }
    }

    public static EntityArrow.PickupStatus convertPickupStatus(PickupStatus bukkit) {
        switch(bukkit) {
            case DISALLOWED: return EntityArrow.PickupStatus.DISALLOWED;
            case ALLOWED: return EntityArrow.PickupStatus.ALLOWED;
            case CREATIVE_ONLY: return EntityArrow.PickupStatus.CREATIVE_ONLY;
            default: throw new IllegalStateException();
        }
    }

    @Override
    public EntityArrow getHandle() {
        return (EntityArrow) entity;
    }

    @Override
    public String toString() {
        return "CraftArrow";
    }

    public EntityType getType() {
        return EntityType.ARROW;
    }
}
