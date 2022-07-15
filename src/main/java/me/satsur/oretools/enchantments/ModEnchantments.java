package me.satsur.oretools.enchantments;

import me.satsur.oretools.OreTools;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEnchantments {
    public static DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, OreTools.MODID);

    public static RegistryObject<Enchantment> AUTO_SMELT = ENCHANTMENTS.register("auto_smelt", () ->
            new AutoSmeltEnchantment(Enchantment.Rarity.COMMON, EnchantmentCategory.DIGGER, EquipmentSlot.MAINHAND));

    public static RegistryObject<Enchantment> COAL_STRIKER = ENCHANTMENTS.register("coal_striker", () ->
        new CoalStrikerEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));


    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }

}
