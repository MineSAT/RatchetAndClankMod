package com.gugu42.rcmod.network.packets;

import com.gugu42.rcmod.capabilities.bolt.BoltProvider;
import com.gugu42.rcmod.capabilities.bolt.IBolt;
import com.gugu42.rcmod.items.EnumRcWeapons;
import com.gugu42.rcmod.items.ItemRcWeap;
import com.gugu42.rcmod.utils.ffmtutils.AbstractPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class PacketRefill extends AbstractPacket {

	private int id;

	public PacketRefill() {

	}

	public PacketRefill(int id) {
		this.id = id;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(id);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.id = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		IBolt props = player.getCapability(BoltProvider.BOLT_CAP, null);
		props.setPlayer((EntityPlayerMP)player);
		
		ItemStack item = this.getItemInInventory(player.inventory, EnumRcWeapons.getItemFromID(id).getWeapon());
		if(item != ItemStack.EMPTY){
			ItemRcWeap itemWeap = (ItemRcWeap)item.getItem();
			if(props.consumeBolts(item.getItemDamage() * itemWeap.getPrice()) && item.getItemDamage() > 0){
				item.setItemDamage(0);
				player.sendMessage(new TextComponentString(I18n.format("gui.vendor.refill.success")));
				player.world.playSound(null, new BlockPos(player.posX, player.posY, player.posZ), new SoundEvent(new ResourceLocation("rcmod:vendor.buy")), SoundCategory.MASTER, 1.0f, 1.0f);
			} else {
				player.sendMessage(new TextComponentString(I18n.format("gui.vendor.refill.bolt")));
				player.world.playSound(null, new BlockPos(player.posX, player.posY, player.posZ), new SoundEvent(new ResourceLocation("rcmod:vendor.maxAmmo")), SoundCategory.MASTER, 1.0f, 1.0f);
			}
		} else {
			player.sendMessage(new TextComponentString(I18n.format("gui.vendor.refill.error")));
		}
	}
	
	private int getSlotContainingItem(InventoryPlayer inventory, Item item){
		for (int i = 0; i < inventory.mainInventory.size(); ++i)
        {
            if (inventory.mainInventory.get(i) != ItemStack.EMPTY && inventory.mainInventory.get(i).getItem() == item)
            {
                return i;
            }
        }

        return -1;
	}
	
	public ItemStack getItemInInventory(InventoryPlayer inventory, Item p_146026_1_)
    {
        int i = this.getSlotContainingItem(inventory, p_146026_1_);

        if (i < 0)
        {
            return null;
        }
        else
        {
            return inventory.mainInventory.get(i);
        }
    }

}
