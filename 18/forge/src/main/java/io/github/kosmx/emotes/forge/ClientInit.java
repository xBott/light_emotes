package io.github.kosmx.emotes.forge;

import io.github.kosmx.emotes.arch.gui.EmoteMenuImpl;
import io.github.kosmx.emotes.forge.executor.ForgeClientMethods;
import io.github.kosmx.emotes.forge.network.ClientNetworkInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ClientInit {

    static void initClient(){

        ClientNetworkInstance.networkInstance.init(); //init network

        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((minecraft, screen) -> new EmoteMenuImpl(screen)));
    }

    @SubscribeEvent
    public static void endClientTick(TickEvent.ClientTickEvent event){
        ForgeClientMethods.tick++;
    }

}
