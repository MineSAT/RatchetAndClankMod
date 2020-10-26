package com.gugu42.rcmod.client.gui;

import com.gugu42.rcmod.common.RcItems;
import com.gugu42.rcmod.common.inventory.VendorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.sun.org.apache.xpath.internal.operations.String;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class VendorScreen extends ContainerScreen<VendorContainer> {
    private static final ResourceLocation RECIPE_BUTTON_TEXTURE = new ResourceLocation("rcmod:textures/gui/vendor.png");
    private ItemEntity itemEntity = null;
    private int rotation;

    public VendorScreen(VendorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 256;
        this.ySize = 200;
    }

    @Override
    protected void init() {
        super.init();

        this.addButton(new Button(guiLeft + 41, guiTop + 137,35,20, new StringTextComponent("Buy"), (button) -> {
            System.out.println("buy pp");
        }));

        this.addButton(new Button(guiLeft + 181, guiTop + 137,35,20, new StringTextComponent("Exit"), (button) -> {
            this.closeScreen();
        }));
    }

    @Override
    public void tick() {
        super.tick();
        this.putItemInSlots();
    }

    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        if (itemEntity != null) {
            renderItem(matrixStack);
            renderWeaponText(matrixStack);
        }

        rotation -= 1f;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(RECIPE_BUTTON_TEXTURE);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
    }

    private void renderItem(MatrixStack matrixStack) {
        matrixStack.push();

        matrixStack.translate(guiLeft + 52, guiTop + 115, 100);
        matrixStack.scale(-60, 60, 60);
        matrixStack.rotate(Vector3f.ZP.rotationDegrees(180));
        matrixStack.rotate(Vector3f.YP.rotationDegrees(rotation % 360));

        EntityRendererManager entityrenderermanager = Minecraft.getInstance().getRenderManager();

        entityrenderermanager.setRenderShadow(false);
        IRenderTypeBuffer.Impl irendertypebuffer = Minecraft.getInstance().getRenderTypeBuffers().getBufferSource();
        entityrenderermanager.renderEntityStatic(itemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, matrixStack, irendertypebuffer, 15728880);
        irendertypebuffer.finish();
        entityrenderermanager.setRenderShadow(true);

        matrixStack.pop();
    }

    private void renderWeaponText(MatrixStack stack) {
        Minecraft.getInstance().fontRenderer.drawString(stack, this.itemEntity.getItem().getItem().getName().getString(), guiLeft + 110, guiTop + 17, 0x00FF00);
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

    private Slot getSelectedSlot(double mouseX, double mouseY) {
        for(int i = 0; i < this.container.inventorySlots.size(); ++i) {
            Slot slot = this.container.inventorySlots.get(i);
            if (this.isSlotSelected(slot, mouseX, mouseY) && slot.isEnabled()) {
                return slot;
            }
        }

        return null;
    }

    private boolean isSlotSelected(Slot slotIn, double mouseX, double mouseY) {
        return this.isPointInRegion(slotIn.xPos, slotIn.yPos, 16, 16, mouseX, mouseY);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        Slot slot = getSelectedSlot(mouseX, mouseY);
        if (slot != null) {
            if (itemEntity == null)
                itemEntity = new ItemEntity(Minecraft.getInstance().world, 0, 0, 0, slot.getStack());
            itemEntity.setItem(slot.getStack());
            return true;
        } else {
            return super.mouseClicked(mouseX, mouseY, button);
        }
    }
}
