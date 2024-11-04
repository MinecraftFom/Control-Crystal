package net.fom477.io;

import com.fom477.api.init.BlockInit;
import com.fom477.api.init.FeatureInit;
import com.fom477.api.init.ItemInit;
import com.fom477.api.logic.CCEvents;
import com.fom477.api.logic.CCFunctions;
import com.fom477.api.logic.Quests;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;
@Mod("control_crystal")
public class cc {
    CCFunctions ccFunc = new CCFunctions();
    Quests quests = new Quests();

    public static final String MODID = "control_crystal";
    private static final Logger LOGGER = LogUtils.getLogger();
    public cc() {
        IEventBus modEventBus= FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        BlockInit.BREGS.register(modEventBus);
        ItemInit.REGS.register(modEventBus);
        FeatureInit.REGISTRY.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new CCEvents());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("CONTROL CRYSTAL GAME STARTING...");
        quests.initalize();
    }
    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        ccFunc.updatePlayerQuest();
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("INIT Control Crystal");
            LOGGER.info("Fork's Github: https://github.com/MinecraftFOM");
            LOGGER.info("Original's Github: https://github.com/imfanhua");
        }
    }
}
