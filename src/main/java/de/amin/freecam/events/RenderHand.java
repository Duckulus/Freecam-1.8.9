package de.amin.freecam.events;

import de.amin.freecam.FreecamMod;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHand {

    @SubscribeEvent
    public void onHandRender(RenderHandEvent event) {
        if(FreecamMod.isEnabled) {
            event.setCanceled(true);
        }
    }

}
