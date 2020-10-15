package com.gugu42.rcmod.common.blocks;

import com.gugu42.rcmod.common.RcBlocks;
import com.gugu42.rcmod.common.entities.TntCrateEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class CrateBlock extends FallingBlock {
    private static final BooleanProperty HAD_TNT_UNDER_IT = BooleanProperty.create("had_tnt_under_it");

    public CrateBlock(Properties properties) {
        super(properties);
        this.setDefaultState(
                this.getStateContainer().getBaseState()
                    .with(HAD_TNT_UNDER_IT, false)
        );
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(HAD_TNT_UNDER_IT);
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if ((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && !state.get(HAD_TNT_UNDER_IT) && pos.getY() >= 0) {
            FallingBlockEntity fallingblockentity = new FallingBlockEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
            this.onStartFalling(fallingblockentity);
            worldIn.addEntity(fallingblockentity);
        }

        if (worldIn.getBlockState(pos.down()).getBlock() == RcBlocks.TNT_CRATE_BLOCK.get())
            worldIn.setBlockState(pos, state.with(HAD_TNT_UNDER_IT, true));
    }
}
