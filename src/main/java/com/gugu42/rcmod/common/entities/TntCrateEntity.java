package com.gugu42.rcmod.common.entities;

import com.gugu42.rcmod.common.RcEntities;
import com.gugu42.rcmod.common.world.TntCrateExplosion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SExplosionPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.Explosion;
import net.minecraft.world.ExplosionContext;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class TntCrateEntity extends Entity {
    private int fuse = 80;
    public final int BEEP_RATE = 25;

    public TntCrateEntity(EntityType<?> entityTypeIn, World worldIn) {
        super(entityTypeIn, worldIn);
    }

    public TntCrateEntity(World world, double x, double y, double z) {
        super(RcEntities.TNT_CRATE_ENTITY.get(), world);
        this.setPosition(x, y, z);
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.fuse = 80;
    }

    @Override
    public void tick() {
        --this.fuse;

        if (!this.hasNoGravity()) {
            this.setMotion(this.getMotion().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getMotion());
        this.setMotion(this.getMotion().scale(0.98D));
        if (this.onGround) {
            this.setMotion(this.getMotion().mul(0.7D, -0.5D, 0.7D));
        }

        if (this.fuse % BEEP_RATE == 0 && this.fuse > 0) {
            if (!this.world.isRemote)
                this.world.playSound(null, this.getPosX(), this.getPosY(), this.getPosZ(), new SoundEvent(new ResourceLocation("rcmod:tntcrate.countdown")), SoundCategory.HOSTILE, 1.0f, 1.0f);
        }

        if (this.fuse <= 0) {
            this.remove();
            if (!this.world.isRemote) {
                this.explode();
            }
        }
    }

    public void explode() {
        this.world.playSound(null, this.getPosX(), this.getPosY(), this.getPosZ(), new SoundEvent(new ResourceLocation("rcmod:tntcrate.explosion")), SoundCategory.HOSTILE, 1.0f, 1.0f);
        // TODO : Replace this with my own explosion
//        this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), 4.0F, Explosion.Mode.NONE);
        TntCrateExplosion explosion = new TntCrateExplosion(this.world, this, (DamageSource)null, (ExplosionContext)null, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), 4.0f, false, Explosion.Mode.BREAK);
        net.minecraftforge.event.ForgeEventFactory.onExplosionStart(this.world, explosion);
        explosion.doExplosionA();
        explosion.doExplosionB(true);

        if (!this.world.isRemote) {
            ServerWorld sWorld = (ServerWorld)this.world;
            for(ServerPlayerEntity serverplayerentity : sWorld.getPlayers()) {
                if (serverplayerentity.getDistanceSq(this.getPosX(), this.getPosY(), this.getPosZ()) < 4096.0D) {
                    serverplayerentity.connection.sendPacket(new SExplosionPacket(this.getPosX(), this.getPosY(), this.getPosZ(), 4.0f, explosion.getAffectedBlockPositions(), explosion.getPlayerKnockbackMap().get(serverplayerentity)));
                }
            }
        }
    }

    @Override
    protected void registerData() {

    }

    @Override
    protected void readAdditional(CompoundNBT compound) {

    }

    @Override
    protected void writeAdditional(CompoundNBT compound) {

    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public int getFuse() {
        return fuse;
    }

    public void setFuse(int fuse) {
        this.fuse = fuse;
    }
}
