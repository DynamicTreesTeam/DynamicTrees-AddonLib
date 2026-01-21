package com.dtteam.dtaddon_lib.compat.waila;

import com.dtteam.dtaddon_lib.blocks.MapleSpileCommon;
import snownee.jade.api.*;

@WailaPlugin
public class WailaCompat implements IWailaPlugin {

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(WailaSpileHandler.INSTANCE, MapleSpileCommon.class);
        registration.registerBlockIcon(WailaSpileHandler.INSTANCE, MapleSpileCommon.class);
    }

    @SuppressWarnings("removal")
    @Override
    public void register(IWailaCommonRegistration registrar) {
        //registrar.registerStackProvider(new WailaSpileHandler(), MapleSpileCommon.class);
    }

}
