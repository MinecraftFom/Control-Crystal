package com.fom477.api.logic;

import com.fom477.api.logic.CCQuests;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Quests {
    public List<CCQuests> quests = null;
    public Quests()
    {
        quests = new ArrayList<>();
    }
    public void initalize()
    {
        for (CCQuests quest : CCQuests.values())
        {
            quests.add(quest);
        }
    }
}