package com.fom477.api.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ItemCCEmerald extends Item {
    public ItemCCEmerald() {
        super(new Item.Properties().rarity(Rarity.RARE).stacksTo(1).tab(CreativeModeTab.TAB_TOOLS));
    }

    @Override
    public boolean isFireResistant() {
        return true;
    }
}
