package me.satsur.oretools.item;

import me.satsur.oretools.ModArmorMaterials;
import me.satsur.oretools.OreTools;
import me.satsur.oretools.item.custom.BlowtorchItem;
import me.satsur.oretools.item.custom.HeatedDiamondItem;
import me.satsur.oretools.item.custom.ModArmorItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.rmi.registry.Registry;

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

    public static final RegistryObject<Item> COAL_HELMET = ITEMS.register("coal_helmet", () ->
            new ModArmorItem(ModArmorMaterials.COAL, EquipmentSlot.HEAD,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> COAL_CHESTPLATE = ITEMS.register("coal_chestplate", () ->
            new ArmorItem(ModArmorMaterials.COAL, EquipmentSlot.CHEST,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> COAL_LEGGINGS = ITEMS.register("coal_leggings", () ->
            new ArmorItem(ModArmorMaterials.COAL, EquipmentSlot.LEGS,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));
    public static final RegistryObject<Item> COAL_BOOTS = ITEMS.register("coal_boots", () ->
            new ArmorItem(ModArmorMaterials.COAL, EquipmentSlot.FEET,
                    new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    //MISC
    public static final RegistryObject<Item> HEATED_DIAMOND = ITEMS.register("heated_diamond", () ->
            new HeatedDiamondItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                    .fireResistant()
                    .durability(50)
                    .defaultDurability(50)
                    .setNoRepair()));

    public static final RegistryObject<Item> BLOWTORCH = ITEMS.register("blowtorch", () ->
        new BlowtorchItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)
                .stacksTo(1)
        ));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
