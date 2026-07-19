package com.dtteam.dtaddon_lib.compat.waila;

import com.dtteam.dtaddon_lib.blocks.maplespile.MapleSpileCommon;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class WailaCompat implements IWailaPlugin {

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(WailaSpileHandler.INSTANCE, MapleSpileCommon.class);
        registration.registerBlockIcon(WailaSpileHandler.INSTANCE, MapleSpileCommon.class);
    }
}
