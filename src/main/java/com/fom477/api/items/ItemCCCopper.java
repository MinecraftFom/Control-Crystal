package com.fom477.api.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ItemCCCopper extends Item {
    public ItemCCCopper() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(1).tab(CreativeModeTab.TAB_TOOLS));
    }
}
