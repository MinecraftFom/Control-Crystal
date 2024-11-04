package com.fom477.api.init;

import com.fom477.api.blocks.BlockControlCrystalOre;
import net.fom477.io.cc;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
    public static final DeferredRegister<Block> BREGS = DeferredRegister.create(ForgeRegistries.BLOCKS, cc.MODID);
    public static final RegistryObject<Block> CCORE = BREGS.register("control_crystal_ore", BlockControlCrystalOre::new);
}
