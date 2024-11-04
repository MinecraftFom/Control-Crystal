package com.fom477.api.logic;

import com.fom477.api.init.ItemInit;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = "control_crystal")
public class CCEvents {
    CCFunctions ccFuncs = new CCFunctions();
    private int randint(int _min, int _max)
    {
        Random rand = new Random();
        int randomNum = rand.nextInt(_max - _min + 1) + _min;
        if (randomNum < ccFuncs.activePlayers.size())
        {
            return randomNum;
        }
        else
        {
            return randomNum --;
        }
    }
    @SubscribeEvent
    public void onPlayerRightClickedEvent(PlayerInteractEvent.RightClickItem event) {
        ItemStack eitem = event.getItemStack();
        MinecraftServer server = event.getEntity().getServer();

        // 确保有有效的服务器和玩家
        if (server == null || event.getEntity() == null) {
            return;
        }

        try {

            Player player = ccFuncs.activePlayers.get(randint(0, ccFuncs.activePlayers.size()) - 1);

            if (ItemInit.COAL_CRYS.get() == eitem.getItem() ||
                    ItemInit.COPPER_CRYS.get() == eitem.getItem() ||
                    ItemInit.IRON_CRYS.get() == eitem.getItem() ||
                    ItemInit.QUARTZ_CRYS.get() == eitem.getItem()) {
                if (ccFuncs.activePlayers.size() > 1){
                    ccFuncs.appendQuest(player, event.getEntity(), server);
                    if (eitem.getCount() > 0)
                    {
                        eitem.shrink(1);
                    }
                }
                else
                {
                    new Exception("NO PLAYERS AVILIABLE");
                    event.getEntity().sendSystemMessage(Component.literal(Component.translatable("cc.error.no_players_available").toString()));
                }
            }

            else if (ItemInit.GOLD_CRYS.get() == eitem.getItem() ||
                    ItemInit.EMERALD_CRYS.get() == eitem.getItem() ||
                    ItemInit.LAPIS_CRYS.get() == eitem.getItem() ||
                    ItemInit.REDSTONE_CRYS.get() == eitem.getItem()) {
                if (ccFuncs.activePlayers.size() > 2) {
                    Player player2 = ccFuncs.activePlayers.get(randint(0, ccFuncs.activePlayers.size()) - 1);
                    ccFuncs.appendQuestMedi(player, player2, event.getEntity(), server);
                    if (eitem.getCount() > 0)
                    {
                        eitem.shrink(1);
                    }
                }
                else
                {
                    new Exception("NO PLAYERS AVILIABLE");
                    event.getEntity().sendSystemMessage(Component.literal(Component.translatable("cc.error.no_players_available").toString()));
                }
            }
            else if (ItemInit.DIAMOND_CRYS.get() == eitem.getItem() || ItemInit.NETHERITE_CRYS.get() == eitem.getItem() && ccFuncs.activePlayers.size() > 2){
                if (ccFuncs.activePlayers.size() > 3) {
                    Player player2 = ccFuncs.activePlayers.get(randint(0, ccFuncs.activePlayers.size()) - 1);
                    Player player3 = ccFuncs.activePlayers.get(randint(0, ccFuncs.activePlayers.size()) - 1);
                    ccFuncs.appendQuestUlta(player, player2, player3, event.getEntity(), server);
                    if (eitem.getCount() > 0)
                    {
                        eitem.shrink(1);
                    }
                }
                else
                {
                    new Exception("NO PLAYERS AVILIABLE");
                    event.getEntity().sendSystemMessage(Component.literal(Component.translatable("cc.error.no_players_available").toString()));
                }
            }

        } catch (Exception e) {
            // 将错误信息发送到玩家聊天框中
            event.getEntity().sendSystemMessage(Component.literal("An error occurred in RightClickItem event: " + e.getMessage()));
            e.printStackTrace();
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public void onPlayerJoinedEvent(PlayerEvent.PlayerLoggedInEvent event)
    {
        Player player = event.getEntity();
        ccFuncs.initalize(player);
    }
}
