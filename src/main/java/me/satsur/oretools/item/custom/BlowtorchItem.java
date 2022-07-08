package me.satsur.oretools.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.apache.http.annotation.Immutable;

import java.util.Map;

public class BlowtorchItem extends Item {
    Map<Block, Item> BLOWTORCH_DROPS = new ImmutableMap.Builder()
            .put(Blocks.SAND, Items.GLASS)
            .build();

    public BlowtorchItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        BlockState blockstate = level.getBlockState(pContext.getClickedPos());
        Block block = blockstate.getBlock();
        BlockPos pos = pContext.getClickedPos();

        if (!level.isClientSide()) {
            if (BLOWTORCH_DROPS.containsKey(block)) {
                //Destroy block and don't drop an item
                level.destroyBlock(pos, false);
                //Spawn in the "blowtorched" drop
                ItemEntity itemDrop = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
                        new ItemStack(BLOWTORCH_DROPS.get(block), 1));
                level.addFreshEntity(itemDrop);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
