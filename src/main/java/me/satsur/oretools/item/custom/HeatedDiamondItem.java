package me.satsur.oretools.item.custom;

import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.UUID;

public class HeatedDiamondItem extends Item {
    public HeatedDiamondItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level world = context.getLevel();

        if (!world.isClientSide()) {
            Player player = context.getPlayer();
            BlockState blockState = world.getBlockState(context.getClickedPos());
            player.sendMessage(new TextComponent("You clicked a block with the hardened diamond!"), UUID.randomUUID());
            //Does damage/breaks the item
            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(context.getHand()));
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

    private static void lightBlockOnFire(UseOnContext context, BlockState blockState) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos blockpos = context.getClickedPos().relative(context.getClickedFace());

        level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
        level.setBlock(blockpos, blockState.setValue(BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
        level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
        if (player != null) {
            context.getItemInHand().hurtAndBreak(1, player, (p_41303_) -> {
                p_41303_.broadcastBreakEvent(context.getHand());
            });
        }
    }
}
