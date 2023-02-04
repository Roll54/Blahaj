package hibi.blahaj.block;

import net.minecraft.block.*;
import net.minecraft.client.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.world.*;

import java.util.*;

public class CuddlyItem extends BlockItem {

	public static final String OWNER_KEY = "Owner";

	private final Text subtitle;

	public CuddlyItem(Block block, Settings settings, String subtitle) {
		super(block, settings);
		this.subtitle = subtitle == null ? null : new TranslatableText(subtitle).formatted(Formatting.GRAY);
	}

	@Override
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		if (this.subtitle != null) {
			tooltip.add(this.subtitle);
		}
		NbtCompound nbt = stack.getNbt();
		if (nbt != null) {
			String owner = nbt.getString(OWNER_KEY);
			if (owner.isEmpty()) {
				return;
			}
			if (stack.hasCustomName()) {
				tooltip.add(new TranslatableText("tooltip.blahaj.owner.rename", this.getName(), new LiteralText(owner)).formatted(Formatting.GRAY));
			} else {
				tooltip.add(new TranslatableText("tooltip.blahaj.owner.craft", new LiteralText(owner)).formatted(Formatting.GRAY));
			}
		}
	}

	@Override
	public void onCraft(ItemStack stack, World world, PlayerEntity player) {
		if (player != null) { // compensate for auto-crafter mods
			stack.setSubNbt(OWNER_KEY, NbtString.of(player.getName().getString()));
		}
		super.onCraft(stack, world, player);
	}

	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		return 0.25f;
	}
}
