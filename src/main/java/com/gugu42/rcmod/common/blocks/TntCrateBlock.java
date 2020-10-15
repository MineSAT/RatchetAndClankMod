package com.gugu42.rcmod.common.blocks;

import com.gugu42.rcmod.common.entities.TntCrateEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.TNTBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class TntCrateBlock extends Block {
    public TntCrateBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote() /*&& !player.isCreative()*/) {
            explode(worldIn, pos, player);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }

    public void explode(World world, BlockPos pos, Entity igniter) {
        TntCrateEntity crateEntity = new TntCrateEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D);
        world.addEntity(crateEntity);
    }
}
