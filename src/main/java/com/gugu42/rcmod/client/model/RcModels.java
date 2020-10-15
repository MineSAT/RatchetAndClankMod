package com.gugu42.rcmod.client.model;

import com.gugu42.rcmod.client.utils.glutils.mc.TessellatorModel;
import net.minecraft.util.ResourceLocation;

public class RcModels {


    public static TessellatorModel CLANK_BACKPACK;
    public static TessellatorModel HELIPACK_BODY;
    public static TessellatorModel HELIPACK_WINGS_SMALL;
    public static TessellatorModel HELIPACK_WING_BIG;

    public static void loadModels() {
        CLANK_BACKPACK = new TessellatorModel(new ResourceLocation("rcmod:models/item/clank/backpack.obj"));
        HELIPACK_BODY = new TessellatorModel(new ResourceLocation("rcmod:models/item/clank/bodyheli.obj"));
        HELIPACK_WING_BIG = new TessellatorModel(new ResourceLocation("rcmod:models/item/clank/heli.obj"));
        HELIPACK_WINGS_SMALL = new TessellatorModel(new ResourceLocation("rcmod:models/item/clank/heli2.obj"));
    }
}
