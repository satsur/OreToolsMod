package me.satsur.oretools.enchantments;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = "oretools")
public class AutoSmeltEnchantment extends Enchantment {

    //Modified block drops
    private static Map<Block, Item> AUTO_SMELTABLE_BlOCKS =
            new ImmutableMap.Builder<Block, Item>()
                    .put(Blocks.IRON_ORE, Items.IRON_INGOT)
                    .put(Blocks.GOLD_ORE, Items.GOLD_INGOT)
                    .put(Blocks.COPPER_ORE, Items.COPPER_INGOT)
                    .put(Blocks.DEEPSLATE_IRON_ORE, Items.IRON_INGOT)
                    .put(Blocks.DEEPSLATE_GOLD_ORE, Items.GOLD_INGOT)
                    .put(Blocks.DEEPSLATE_COPPER_ORE, Items.COPPER_INGOT)
                    .put(Blocks.ANCIENT_DEBRIS, Items.NETHERITE_SCRAP)
                    .build();

    protected AutoSmeltEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @SubscribeEvent
    public static void onBlockMined(BlockEvent.BreakEvent event) {
        Level level = event.getPlayer().level;
        if (!level.isClientSide()) {
            Player player = event.getPlayer();
            BlockPos blockPos = event.getPos();
            Block block = event.getState().getBlock();
            ItemStack tool = player.getMainHandItem();
            if (EnchantmentHelper.getEnchantments(tool).get(ModEnchantments.AUTO_SMELT.get()) != null && AUTO_SMELTABLE_BlOCKS.containsKey(block)) {
                ItemEntity smelted_item = new ItemEntity(level,
                        blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        new ItemStack(AUTO_SMELTABLE_BlOCKS.get(block), 1)
                        );
                //Destroy block and don't drop normal item and prevent normal drops
                level.destroyBlock(blockPos, false);
                //Drop new ItemStack of the smelted ingot/item
                level.addFreshEntity(smelted_item);
            }

        }
    }

    protected boolean checkCompatibility(UntouchingEnchantment e) {
        return super.checkCompatibility(e);
    }

    private static boolean blockIsValidToBeAutoSmelted(Block block) {
        if (block.getTags().contains(ItemTags.GOLD_ORES) ||
                block.getTags().contains(ItemTags.IRON_ORES) ||
                block.getTags().contains(ItemTags.COPPER_ORES)) {
            return true;
        }
        return false;

    }


}
