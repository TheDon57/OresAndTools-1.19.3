package de.thedon.oresandtools.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class XpOreBlock extends RedStoneOreBlock {
//    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final Vector3f XP_PARTICLE_COLOR = new Vector3f(0.5F, 1.0F, 0.0F);
    public static final DustParticleOptions XP = new DustParticleOptions(XP_PARTICLE_COLOR, 1.0F);
    private final IntProvider xpRange;

    public XpOreBlock(BlockBehaviour.Properties pProperties, IntProvider pXpRange) {
        super(pProperties);
        this.xpRange = pXpRange;
    }

    @Override
    public void attack(@NotNull BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer) {
        interact(pState, pLevel, pPos);
//        super.attack(pState, pLevel, pPos, pPlayer);
    }

    @Override
    public void stepOn(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, Entity pEntity) {
        if (!pEntity.isSteppingCarefully()) {
            interact(pState, pLevel, pPos);
        }
//        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState pState, Level pLevel, @NotNull BlockPos pPos, @NotNull Player pPlayer, @NotNull InteractionHand pHand, @NotNull BlockHitResult pHit) {
        if (pLevel.isClientSide) {
            spawnParticles(pLevel, pPos);
        } else {
            interact(pState, pLevel, pPos);
        }

        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        return itemstack.getItem() instanceof BlockItem && (new BlockPlaceContext(pPlayer, pHand, itemstack, pHit)).canPlace() ? InteractionResult.PASS : InteractionResult.SUCCESS;
    }

    private static void interact(BlockState pState, Level pLevel, BlockPos pPos) {
        spawnParticles(pLevel, pPos);
        if (!pState.getValue(LIT)) {
            pLevel.setBlock(pPos, pState.setValue(LIT, true), 3);
        }

    }

    @Override
    public int getExpDrop(@NotNull BlockState state, net.minecraft.world.level.@NotNull LevelReader world, @NotNull RandomSource randomSource, @NotNull BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? xpRange.sample(randomSource) : 0;
    }

    @Override
    public void animateTick(BlockState pState, @NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull RandomSource pRandom) {
        if (pState.getValue(LIT)) {
            spawnParticles(pLevel, pPos);
        }

    }

    private static void spawnParticles(Level pLevel, BlockPos pPos) {
        RandomSource randomsource = pLevel.random;

        for(Direction direction : Direction.values()) {
            BlockPos blockpos = pPos.relative(direction);
            if (!pLevel.getBlockState(blockpos).isSolidRender(pLevel, blockpos)) {
                Direction.Axis direction$axis = direction.getAxis();
                double d1 = direction$axis == Direction.Axis.X ? 0.5D + 0.5625D * (double)direction.getStepX() : (double)randomsource.nextFloat();
                double d2 = direction$axis == Direction.Axis.Y ? 0.5D + 0.5625D * (double)direction.getStepY() : (double)randomsource.nextFloat();
                double d3 = direction$axis == Direction.Axis.Z ? 0.5D + 0.5625D * (double)direction.getStepZ() : (double)randomsource.nextFloat();
                pLevel.addParticle(XP, (double)pPos.getX() + d1, (double)pPos.getY() + d2, (double)pPos.getZ() + d3, 0.0D, 0.0D, 0.0D);
            }
        }

    }
}
