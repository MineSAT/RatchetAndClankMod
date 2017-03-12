package com.gugu42.rcmod.blocks;

import com.gugu42.rcmod.RcMod;
import com.gugu42.rcmod.client.ClientProxy;
import com.gugu42.rcmod.tileentity.TileEntityVendor;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockVendor extends Block implements ITileEntityProvider {

	public BlockVendor(Material par2Material) {
		super(par2Material);
		this.setCreativeTab(RcMod.rcTab);
	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity == null || player.isSneaking() || hand != EnumHand.MAIN_HAND) {
			return false;
		}
		
		player.openGui(RcMod.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
		world.playSound(null, pos, new SoundEvent(new ResourceLocation("rcmod:MenuOpen")), SoundCategory.BLOCKS, 1.0f, 1.0f);
		//par1World.playSoundAtEntity(par5EntityPlayer, "rcmod:MenuOpen", 1.0F, 1.0F);
		return true;
	}

    /*@SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }*/

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityVendor();
	}
}
