package com.gugu42.rcmod.client.gui;

import com.gugu42.rcmod.common.RcItems;
import com.gugu42.rcmod.common.inventory.VendorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class VendorScreen extends ContainerScreen<VendorContainer> {
    private static final ResourceLocation RECIPE_BUTTON_TEXTURE = new ResourceLocation("rcmod:textures/gui/vendor.png");

    public VendorScreen(VendorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 256;
        this.ySize = 200;
    }

    @Override
    public void tick() {
        super.tick();
        this.putItemInSlots();
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(RECIPE_BUTTON_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }

    private void putItemInSlots() {
        this.container.putStackInSlot(0, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(1, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(2, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(3, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(4, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(5, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(6, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(7, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(8, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(9, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(10, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(11, new ItemStack(RcItems.BLASTER.get()));
        this.container.putStackInSlot(12, new ItemStack(RcItems.BLASTER.get()));
    }
}
