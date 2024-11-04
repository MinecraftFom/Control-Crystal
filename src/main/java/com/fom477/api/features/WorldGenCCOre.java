package com.fom477.api.features;

import com.fom477.api.init.BlockInit;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;

import java.util.List;
import java.util.Set;

public class WorldGenCCOre extends OreFeature {
    public static WorldGenCCOre FEATURE = null;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> CONFIGURED_FEATURE = null;
    public static Holder<PlacedFeature> PLACED_FEATURE = null;

    // Remove dimension restriction for testing purposes
    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

    public WorldGenCCOre() {
        super(OreConfiguration.CODEC);
    }

    public WorldGenCCOre(Codec<OreConfiguration> p_66531_) {
        super(p_66531_);
    }

    public boolean place(FeaturePlaceContext<OreConfiguration> context) {
        WorldGenLevel world = context.level();
        return super.place(context);
    }

    public static Feature<?> feature() {
        FEATURE = new WorldGenCCOre();
        CONFIGURED_FEATURE = FeatureUtils.register("control_crystal:control_crystal_ore", FEATURE,
                new OreConfiguration(List.of(OreConfiguration.target(new BlockStateMatchTest(Blocks.STONE.defaultBlockState()), BlockInit.CCORE.get().defaultBlockState())), 10));  // Reduce vein size for testing
        PLACED_FEATURE = PlacementUtils.register("control_crystal:control_crystal_ore", CONFIGURED_FEATURE,
                List.of(CountPlacement.of(20),
                        InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(40))));  // Adjust height range
        return FEATURE;
    }
}
