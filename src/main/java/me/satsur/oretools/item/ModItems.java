package me.satsur.oretools.item;

import me.satsur.oretools.OreTools;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OreTools.MODID);

    public static final RegistryObject<Item> COAL_PICKAXE = ITEMS.register("coal_pickaxe", () ->
            new PickaxeItem(ModTiers.COAL, 2, -2F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COAL_AXE = ITEMS.register("coal_axe", () ->
            new AxeItem(ModTiers.COAL, 7, -3.2F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COAL_SWORD = ITEMS.register("coal_sword", () ->
            new SwordItem(ModTiers.COAL, 5, -2F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COAL_SHOVEL = ITEMS.register("coal_shovel", () ->
            new ShovelItem(ModTiers.COAL, 2, -2F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    public static final RegistryObject<Item> COAL_HOE = ITEMS.register("coal_hoe", () ->
            new HoeItem(ModTiers.COAL, 2, -2F, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
