package com.gugu42.rcmod.blocks;

import java.util.Random;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.entity.EntityTNTCrate;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockTNTCrate extends Block {

    public BlockTNTCrate(Material material) {
        super(material);
        this.setCreativeTab(RcMod.rcTab);
    }


    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);

        if (world.isBlockIndirectlyGettingPowered(pos) != 0) {
            this.onBlockDestroyedByPlayer(world, pos, state);
            world.setBlockToAir(pos);
        }
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random) {
        return 0;
    }

    /**
     * Called upon the block being destroyed by an explosion
     */
    public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (!world.isRemote) {
            EntityTNTCrate entitytntcrate = new EntityTNTCrate(world,
                    (double) ((float) pos.getX() + 0.5F),
                    (double) ((float) pos.getY() + 0.5F),
                    (double) ((float) pos.getZ() + 0.5F));
            world.setBlockToAir(pos);
            world.spawnEntity(entitytntcrate);
        }
    }

    /**
     * Called right before the block is destroyed by a player. Args: world, x,
     * y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
        if (!world.isRemote) {
            EntityTNTCrate entitytntcrate = new EntityTNTCrate(world,
                    (double) ((float) pos.getX() + 0.5F),
                    (double) ((float) pos.getY() + 0.5F),
                    (double) ((float) pos.getZ() + 0.5F));
            world.setBlockToAir(pos);
            world.spawnEntity(entitytntcrate);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
            this.onBlockDestroyedByPlayer(worldIn, pos, state);
            return true;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the
     * block). Args: world, x, y, z, entity
     */
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        if (!worldIn.isRemote) {
            this.onBlockDestroyedByPlayer(worldIn, pos, state);
        }
    }

    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
    	this.onBlockDestroyedByPlayer(worldIn, pos, this.getDefaultState());
    }

    /**
     * Return whether this block can drop from an explosion.
     */
    public boolean canDropFromExplosion(Explosion par1Explosion) {
        return false;
    }
}