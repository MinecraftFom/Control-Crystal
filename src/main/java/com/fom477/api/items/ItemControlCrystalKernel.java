package com.fom477.api.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ItemControlCrystalKernel extends Item {
    public ItemControlCrystalKernel() {
        super(new Item.Properties().rarity(Rarity.COMMON).stacksTo(16).tab(CreativeModeTab.TAB_MISC));
    }
}
