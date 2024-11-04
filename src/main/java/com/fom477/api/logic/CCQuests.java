package com.fom477.api.logic;

import net.minecraft.network.chat.Component;

public enum CCQuests {
    QUEST_1(1),
    QUEST_2(2),
    QUEST_3(3),
    QUEST_4(4),
    QUEST_5(5);
    public int code;

    public String titles;

    CCQuests(int titles)
    {
        this.titles = Component.translatable("cc.quests." + titles + ".title").toString();
        this.code = titles;
    }
}

