package com.fom477.api.init;

import com.fom477.api.items.ItemCCCoal;
import com.fom477.api.items.ItemCCCopper;
import com.fom477.api.items.ItemCCDiamond;
import com.fom477.api.items.ItemCCEmerald;
import com.fom477.api.items.ItemCCGold;
import com.fom477.api.items.ItemCCIron;
import com.fom477.api.items.ItemCCLapis;
import com.fom477.api.items.ItemCCNetherite;
import com.fom477.api.items.ItemCCQuartz;
import com.fom477.api.items.ItemCCRedstone;
import com.fom477.api.items.ItemControlCrystalFragment;
import com.fom477.api.items.ItemControlCrystalKernel;

import net.fom477.io.cc;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> REGS = DeferredRegister.create(ForgeRegistries.ITEMS, cc.MODID);
    public static final RegistryObject<Item> COAL_CRYS = REGS.register("coal_crystal", ItemCCCoal::new);
    public static final RegistryObject<Item> COPPER_CRYS = REGS.register("copper_crystal", ItemCCCopper::new);
    public static final RegistryObject<Item> DIAMOND_CRYS = REGS.register("diamond_crystal", ItemCCDiamond::new);
    public static final RegistryObject<Item> EMERALD_CRYS = REGS.register("emerald_crystal", ItemCCEmerald::new);
    public static final RegistryObject<Item> GOLD_CRYS = REGS.register("gold_crystal", ItemCCGold::new);
    public static final RegistryObject<Item> IRON_CRYS = REGS.register("iron_crystal", ItemCCIron::new);
    public static final RegistryObject<Item> LAPIS_CRYS = REGS.register("lapis_crystal", ItemCCLapis::new);
    public static final RegistryObject<Item> NETHERITE_CRYS = REGS.register("netherite_crystal", ItemCCNetherite::new);
    public static final RegistryObject<Item> QUARTZ_CRYS = REGS.register("quartz_crystal", ItemCCQuartz::new);
    public static final RegistryObject<Item> REDSTONE_CRYS = REGS.register("redstone_crystal", ItemCCRedstone::new);
    public static final RegistryObject<Item> CONTROL_CRYSTAL_FRAGMENT = REGS.register("control_crystal_fragment", ItemControlCrystalFragment::new);
    public static final RegistryObject<Item> CONTROL_CRYSTAL_KERNEL = REGS.register("control_crystal_kernel", ItemControlCrystalKernel::new);

    public static RegistryObject<Item> BlockReg(RegistryObject<Block> block, CreativeModeTab tab)
    {
        return REGS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
}
