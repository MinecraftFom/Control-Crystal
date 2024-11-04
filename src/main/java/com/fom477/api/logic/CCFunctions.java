package com.fom477.api.logic;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

import com.fom477.api.logic.CCQuests;

import java.util.*;

public class CCFunctions {
    public  static List<Player>   activePlayers   = null;
    public  static List<Player>   questingPlayers = null;
    private Map<Player, CCQuests> playerQuests    = null;
    private Map<Player, Integer>  playerQuestTime = null;
    private Map<Player, Integer>  playerScores    = null;
    private Map<Player, Player>   playerSender    = null;
    private Map<Player, Integer>  playerVotes     = null;
    private int randint(int _min, int _max)
    {
        Random rand = new Random();
        return rand.nextInt(_max - _min + 1) + _min;
    }
    public boolean isPlayerActivated(Player _player)
    {
        return questingPlayers.contains(_player);
    }
    public boolean isPlayerPlaying(Player _player)
    {
        return activePlayers.contains(_player);
    }
    public int getPlayerQuestTimeLeft(Player _player)
    {
        return playerQuestTime.get(_player);
    }
    public void activePlayerQuest(Player _player, Player _sender, CCQuests _quest, Integer _time)
    {
        activePlayers.remove(_player);
        playerQuests.put(_player, _quest);
        playerQuestTime.put(_player, _time);
        playerSender.put(_player, _sender);
        playerVotes.put(_player, 0);
        questingPlayers.add(_player);
    }
    public void appendQuest(Player _player, Player _sender, MinecraftServer _server)
    {
        Quests _questlist = new Quests();
        _questlist.initalize();
        int getrand = randint(0, _questlist.quests.size()-1);
        activePlayerQuest(_player, _sender, _questlist.quests.get(getrand), 114514*20);

        Component titlecomp = Component.literal(_player.toString());
        Component subtitlecomp = Component.literal(Component.translatable(_questlist.quests.get(getrand).titles).toString());
        for (ServerPlayer player1 : _server.getPlayerList().getPlayers())
        {
            player1.connection.send(new ClientboundSetTitleTextPacket(titlecomp));
            player1.connection.send(new ClientboundSetTitleTextPacket(subtitlecomp));
        }
    }
    public void appendQuestMedi(Player _player, Player _player2,Player _sender, MinecraftServer _server)
    {
        Quests _questlist = new Quests();
        _questlist.initalize();
        int getrand = randint(0, _questlist.quests.size()-1);
        activePlayerQuest(_player, _sender, _questlist.quests.get(getrand), 114514*20);
        int getrand2 = randint(0, _questlist.quests.size()-1);
        activePlayerQuest(_player2, _sender, _questlist.quests.get(getrand2), 114514*20);
        Component titlecomp = Component.literal(_player.toString() + "," + _player2.toString());
        Component subtitlecomp = Component.literal(Component.translatable(_questlist.quests.get(getrand).titles) + "," + Component.translatable(_questlist.quests.get(getrand2).titles));
        for (ServerPlayer player1 : _server.getPlayerList().getPlayers())
        {
            player1.connection.send(new ClientboundSetTitleTextPacket(titlecomp));
            player1.connection.send(new ClientboundSetTitleTextPacket(subtitlecomp));
        }
    }
    public void appendQuestUlta(Player _player, Player _player2, Player _player3, Player _sender, MinecraftServer _server)
    {
        Quests _questlist = new Quests();
        _questlist.initalize();
        int getrand = randint(0, _questlist.quests.size()-1);
        activePlayerQuest(_player, _sender, _questlist.quests.get(getrand), 114514*20);
        int getrand2 = randint(0, _questlist.quests.size()-1);
        activePlayerQuest(_player2, _sender, _questlist.quests.get(getrand2), 114514*20);
        int getrand3 = randint(0, _questlist.quests.size()-1);
        activePlayerQuest(_player3, _sender, _questlist.quests.get(getrand3), 114514*20);
        Component titlecomp = Component.literal(_player.toString() + "," + _player2.toString() + "," + _player3.toString());
        Component subtitlecomp = Component.literal(Component.translatable(_questlist.quests.get(getrand).titles) + "," + Component.translatable(_questlist.quests.get(getrand2).titles) + "," + Component.translatable(_questlist.quests.get(getrand3).titles));
        for (ServerPlayer player1 : _server.getPlayerList().getPlayers())
        {
            player1.connection.send(new ClientboundSetTitleTextPacket(titlecomp));
            player1.connection.send(new ClientboundSetTitleTextPacket(subtitlecomp));
        }
    }
    public void deactivatePlayerQuest(Player _player)
    {
        playerQuests.remove(_player);
        playerQuestTime.remove(_player);
        playerVotes.remove(_player);
    }
    public void initalize(Player _player)
    {
        activePlayers.add(_player);
        playerScores.put(_player, 0);
    }
    public void updatePlayerQuest()
    {
        try {
            for (Player player : questingPlayers) {
                Integer scoretmp = playerScores.get(player);
                Integer questtimetmp = playerQuestTime.get(player);
                if (getPlayerQuestTimeLeft(player) == 0) {

                    if (playerVotes.get(player) >= (activePlayers.size() + questingPlayers.size()) / 2) {
                        player.sendSystemMessage(Component.literal(player.toString() + Component.translatable("quest.win")));
                        playerScores.put(player, scoretmp++);
                        deactivatePlayerQuest(player);
                    } else {
                        player.sendSystemMessage(Component.literal(player.toString() + Component.translatable("quest.lose")));
                        playerScores.put(playerSender.get(player), scoretmp++);
                        playerScores.put(player, scoretmp--);
                        deactivatePlayerQuest(player);
                    }
                } else {
                    playerVotes.put(player, questtimetmp--);
                }
            }
        } catch (Exception e) {
            return;
        }
    }
    public void votePlayer(Player _player)
    {
        Integer votetmp = playerVotes.get(_player);
        playerVotes.put(_player, votetmp++);
    }
    public CCFunctions()
    {
        activePlayers = new ArrayList<>();
        questingPlayers = new ArrayList<>();
        playerQuests = new HashMap<>();
        playerQuestTime = new HashMap<>();
        playerScores = new HashMap<>();
        playerSender = new HashMap<>();
        playerVotes = new HashMap<>();
    }
}
