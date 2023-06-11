package hibi.blahaj;

import hibi.blahaj.block.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.loot.v2.*;
import net.fabricmc.fabric.api.object.builder.v1.trade.*;
import net.minecraft.item.*;
import net.minecraft.loot.*;
import net.minecraft.loot.entry.*;
import net.minecraft.village.*;

public class Blahaj implements ModInitializer {

	public static final String MOD_ID = "blahaj";

	public void onInitialize() {
		BlahajBlocks.register();
		registerLootTables();
		registerTrades();
	}

	private static void registerLootTables() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (id.equals(LootTables.STRONGHOLD_CROSSING_CHEST)
					|| id.equals(LootTables.STRONGHOLD_CORRIDOR_CHEST)) {
				LootPool.Builder pb = LootPool.builder()
						.with(ItemEntry.builder(BlahajBlocks.GRAY_SHARK_ITEM)
								.weight(5))
						.with(ItemEntry.builder(Items.AIR)
								.weight(100));
				tableBuilder.pool(pb);
			} else if (id.equals(LootTables.VILLAGE_PLAINS_CHEST)) {
				LootPool.Builder pb = LootPool.builder()
						.with(ItemEntry.builder(BlahajBlocks.GRAY_SHARK_ITEM))
						.with(ItemEntry.builder(Items.AIR)
								.weight(43));
				tableBuilder.pool(pb);
			} else if (id.equals(LootTables.VILLAGE_TAIGA_HOUSE_CHEST)
					|| id.equals(LootTables.VILLAGE_SNOWY_HOUSE_CHEST)) {
				LootPool.Builder pb = LootPool.builder()
						.with(ItemEntry.builder(BlahajBlocks.GRAY_SHARK_ITEM)
								.weight(5))
						.with(ItemEntry.builder(Items.AIR)
								.weight(54));
				tableBuilder.pool(pb);
			}
		});
	}

	private static void registerTrades() {
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.SHEPHERD, 5, factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new ItemStack(Items.EMERALD, 15), new ItemStack(BlahajBlocks.GRAY_SHARK_ITEM),
					2, 30, 0.1f));
		});
	}

}
