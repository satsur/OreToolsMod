package me.satsur.oretools.item.custom;

import com.google.common.collect.ImmutableMap;
import me.satsur.oretools.ModArmorMaterials;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

public class ModArmorItem extends ArmorItem {

    private static final Map<ArmorMaterial, MobEffect> MATERAL_TO_EFFECT = new ImmutableMap.Builder<ArmorMaterial, MobEffect>()
            .put(ModArmorMaterials.COAL, MobEffects.MOVEMENT_SLOWDOWN).build();

    public ModArmorItem(ArmorMaterial pMaterial, EquipmentSlot pSlot, Properties pProperties) {
        super(pMaterial, pSlot, pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);

        if (pLevel.isClientSide()) {
            if (pEntity instanceof Player) {
                Player player = (Player) pEntity;
                if (hasAllArmorPiecesEquipped(player)) {
                    evaluateEffects(player);
                }
            }

        }

    }

    private static void evaluateEffects(Player player) {
        for (Map.Entry<ArmorMaterial, MobEffect> m : MATERAL_TO_EFFECT.entrySet()) {
            ArmorMaterial material = m.getKey();
            MobEffect effect = m.getValue();

            if (hasCorrectArmorEquipped(player, material))
                applyStatusEffect(player, effect);

        }
    }

    private static void applyStatusEffect(Player player, MobEffect effect) {
        player.addEffect(new MobEffectInstance(effect, 20));
    }

    private static boolean hasCorrectArmorEquipped(Player player, ArmorMaterial material) {
        for (int i = 0; i < 4; i++) {
            if (!((ArmorItem)player.getInventory().getArmor(i).getItem()).getMaterial().equals(material))
                return false;
        }
        return true;
    }

    private static boolean hasAllArmorPiecesEquipped(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }
}
