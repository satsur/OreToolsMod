package me.satsur.oretools.enchantments;

import me.satsur.oretools.item.ModItems;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

import javax.swing.text.html.HTML;
import java.util.UUID;

public class CoalStrikerEnchantment extends Enchantment {

    protected CoalStrikerEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        Level level = pAttacker.level;
        Player player = (Player)pAttacker;
        if (!level.isClientSide()) {
            SwordItem sword = (SwordItem)player.getMainHandItem().getItem();

            if (sword == ModItems.COAL_SWORD.get()) {
                Mob mob = (Mob) pTarget;

                switch (pLevel) {
                    case 1:
                        mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
                        break;
                    case 2:
                        mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 3));
                        break;
                }
            }
        }
    }
}
