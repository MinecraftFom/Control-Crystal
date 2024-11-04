package com.fom477.api.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ItemControlCrystalFragment extends Item {
    public ItemControlCrystalFragment() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(16).tab(CreativeModeTab.TAB_MISC));
    }
}
