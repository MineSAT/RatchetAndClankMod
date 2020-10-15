package com.gugu42.rcmod.client.utils.glutils.mc;

import com.gugu42.rcmod.client.utils.glutils.ObjEvent;
import net.minecraftforge.eventbus.api.Event;

public class ObjEventWrapper extends Event
{

    public ObjEvent objEvent;

    public ObjEventWrapper(ObjEvent e)
    {
        this.objEvent = e;
    }

    public boolean isCancelable()
    {
        return objEvent.canBeCancelled();
    }
}
