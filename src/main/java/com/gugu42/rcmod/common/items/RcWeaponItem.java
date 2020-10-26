package com.gugu42.rcmod.common.items;

import net.minecraft.item.Item;

public class RcWeaponItem extends Item {
    protected int maxAmmo;
    protected int buyPrice;
    protected int ammunitionPrice;

    public RcWeaponItem(Properties properties) {
        super(properties);
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getAmmunitionPrice() {
        return ammunitionPrice;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }
}
