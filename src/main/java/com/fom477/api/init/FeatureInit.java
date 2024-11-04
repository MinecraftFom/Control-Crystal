package com.fom477.api.init;

import com.fom477.api.features.WorldGenCCOre;
import net.fom477.io.cc;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber
public class FeatureInit {
    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, cc.MODID);
    public static final RegistryObject<Feature<?>> COREGERN = REGISTRY.register("control_crystal_ore", WorldGenCCOre::feature);
}
