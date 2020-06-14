package net.minecraft.MoWithers;

import net.minecraft.MoWithers.items.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
public class MoWitherItems 
{
	public static void MOWI()
	{
		init();
		register();
	}
	
	public static ArmorMaterial WitherArmor = EnumHelper.addArmorMaterial("WitherArmor", "mowithers:wither", 660, new int[] {5, 9, 7, 3}, 25);
	public static ToolMaterial WitherTools = EnumHelper.addToolMaterial("WitherTools", 4, 26470, 32.0F, 76.0F, 25).setRepairItem(new ItemStack(Items.skull, 1, 1));
	public static ArmorMaterial EmeraldArmor = EnumHelper.addArmorMaterial("EmeraldArmor", "mowithers:emerald", 66, new int[] {3, 8, 6, 3}, 14);
	public static ToolMaterial EmeraldTools = EnumHelper.addToolMaterial("EmeraldTools", 3, 2647, 12.0F, 4.0F, 14).setRepairItem(new ItemStack(Items.emerald));
	
	public static Item sculpture;
	public static Item fireball;
	public static Item lightningbolt;
	public static Item skull;
	public static Item meteorpower;
	public static Item skullpower;
	public static Item lightningstormpower;
	public static Item enderCharge;
	public static Item wither_essence;
	public static Item wither_girl_essence;
	public static Item pink_wither_girl_essence;
	public static Item void_wither_girl_essence;
	public static Item fire_essence;
	public static Item water_essence;
	public static Item air_essence;
	public static Item earth_essence;
	public static Item ice_essence;
	public static Item lava_essence;
	public static Item lightning_essence;
	public static Item hurricane_essence;
	public static Item blaze_essence;
	public static Item creeper_essence;
	public static Item ender_essence;
	public static Item ghast_essence;
	public static Item zombie_essence;
	public static Item valentine_essence;
	public static Item halloween_essence;
	public static Item saint_patrick_essence;
	public static Item christmas_essence;
	public static Item friendly_essence;
	public static Item growth_essence;
	public static Item demented_essence;
	public static Item negative_essence;
	public static Item wither_dragon_essence;
	public static Item sand_essence;
	public static Item gravel_essence;
	public static Item glass_essence;
	public static Item obsidian_essence;
	public static Item bedrock_essence;
	public static Item coal_essence;
	public static Item redstone_essence;
	public static Item lapis_essence;
	public static Item iron_essence;
	public static Item gold_essence;
	public static Item diamond_essence;
	public static Item emerald_essence;
	public static Item emeraldPickaxe;
	public static Item emeraldAxe;
	public static Item emeraldSpade;
	public static Item emeraldHoe;
	public static Item emeraldSword;
	public static Item emeraldHelmet;
	public static Item emeraldChestplate;
	public static Item emeraldLeggings;
	public static Item emeraldBoots;
	public static Item recordCalm4;
	public static Item recordWither;
	public static Item recordEnderDragon;
	public static Item recordSamantha;
	public static Item recordRheia;
	public static Item recordTheia;
	public static Item witherPickaxe;
	public static Item witherAxe;
	public static Item witherSpade;
	public static Item witherHoe;
	public static Item witherSword;
	public static Item witherHelmet;
	public static Item witherChestplate;
	public static Item witherLeggings;
	public static Item witherBoots;
	public static Item netherStarShard;
	public static Item blackHole;
	public static Item witherlove;
	public static Item witherfamily;
	public static Item threesome;
	public static Item allstarfather;
	
	public static void init()
	{
		blackHole = new ItemUnusableItem("blackHole");
		witherlove = new ItemUnusableItem("witherlove");
		witherfamily = new ItemUnusableItem("witherfamily");
		threesome = new ItemUnusableItem("threesome");
		allstarfather = new ItemUnusableItem("allstarfather");
		recordCalm4 = new ItemRecordMoWithers("record_calm4", "Calm4");
		recordWither = new ItemRecordMoWithers("record_wither", "Wither");
		recordEnderDragon = new ItemRecordMoWithers("record_dragon", "EnderDragon");
		recordSamantha = new ItemRecordMoWithers("record_samantha", "SamanthasTheme");
		recordRheia = new ItemRecordMoWithers("record_rheia", "RheiasTheme");
		recordTheia = new ItemRecordMoWithers("record_theia", "TheiasTheme");
		sculpture = new ItemSculpture("sculpture");
		fireball = new ItemThrowableFireball("fireball");
		lightningbolt = new ItemThrowableLightning("lightningbolt");
		skullpower = new ItemInvokeSkullPower("skullpower");
		lightningstormpower = new ItemInvokeLightningStormPower("lightningstormpower");
		meteorpower = new ItemInvokeMeteorPower("meteorpower");
		skull = new ItemThrowableWitherSkull("throwable_skull");
		enderCharge = new ItemEnderCharge("enderCharge");
		netherStarShard = new ItemNetherStarShard("nether_star_shard");
		emeraldPickaxe = new ItemEmeraldPickaxe("emerald_pickaxe", EmeraldTools);
		emeraldAxe = new ItemEmeraldAxe("emerald_axe", EmeraldTools);
		emeraldSpade = new ItemEmeraldShovel("emerald_shovel", EmeraldTools);
		emeraldHoe = new ItemEmeraldHoe("emerald_hoe", EmeraldTools);
		emeraldSword = new ItemEmeraldSword("emerald_sword", EmeraldTools);
		emeraldHelmet = new ItemEmeraldArmor("emerald_helmet", EmeraldArmor, 1, 0);
		emeraldChestplate = new ItemEmeraldArmor("emerald_chestplate", EmeraldArmor, 1, 1);
		emeraldLeggings = new ItemEmeraldArmor("emerald_leggings", EmeraldArmor, 2, 2);
		emeraldBoots = new ItemEmeraldArmor("emerald_boots", EmeraldArmor, 1, 3);
		witherPickaxe = new ItemWitherPickaxe("wither_pickaxe", WitherTools);
		witherAxe = new ItemWitherAxe("wither_axe", WitherTools);
		witherSpade = new ItemWitherShovel("wither_shovel", WitherTools);
		witherHoe = new ItemWitherHoe("wither_hoe", WitherTools);
		witherSword = new ItemWitherSword("wither_sword", WitherTools);
		witherHelmet = new ItemWitherArmor("wither_helmet", WitherArmor, 1, 0);
		witherChestplate = new ItemWitherArmor("wither_chestplate", WitherArmor, 1, 1);
		witherLeggings = new ItemWitherArmor("wither_leggings", WitherArmor, 2, 2);
		witherBoots = new ItemWitherArmor("wither_boots", WitherArmor, 1, 3);
		wither_essence = new ItemWitherEssence("wither_essence");
		air_essence = new ItemAirEssence("air_essence");
		fire_essence = new ItemFireEssence("fire_essence");
		water_essence = new ItemWaterEssence("water_essence");
		earth_essence = new ItemEarthEssence("earth_essence");
		ice_essence = new ItemIceEssence("ice_essence");
		lava_essence = new ItemLavaEssence("lava_essence");
		lightning_essence = new ItemLightningEssence("lightning_essence");
		hurricane_essence = new ItemHurricaneEssence("hurricane_essence");
		sand_essence = new ItemBlockEssence("sand_essence", Blocks.sand);
		gravel_essence = new ItemBlockEssence("gravel_essence", Blocks.gravel);
		glass_essence = new ItemBlockEssence("glass_essence", Blocks.glass);
		obsidian_essence = new ItemBlockEssence("obsidian_essence", Blocks.obsidian);
		bedrock_essence = new ItemBlockEssence("bedrock_essence", Blocks.bedrock);
		coal_essence = new ItemBlockEssence("coal_essence", Blocks.coal_block);
		redstone_essence = new ItemBlockEssence("redstone_essence", Blocks.redstone_block);
		lapis_essence = new ItemBlockEssence("lapis_essence", Blocks.lapis_block);
		iron_essence = new ItemBlockEssence("iron_essence", Blocks.iron_block);
		gold_essence = new ItemBlockEssence("gold_essence", Blocks.gold_block);
		diamond_essence = new ItemBlockEssence("diamond_essence", Blocks.diamond_block);
		emerald_essence = new ItemBlockEssence("emerald_essence", Blocks.emerald_block);
		christmas_essence = new ItemHolidayEssence("christmas_essence", 1);
		saint_patrick_essence = new ItemHolidayEssence("saint_patrick_essence", 2);
		valentine_essence = new ItemHolidayEssence("valentine_essence", 3);
		halloween_essence = new ItemHolidayEssence("halloween_essence", 4);
		friendly_essence = new ItemFriendlyEssence("friendly_essence");
		growth_essence = new ItemGrowthEssence("growth_essence");
		demented_essence = new ItemDementedEssence("demented_essence");
		negative_essence = new ItemNegativeEssence("negative_essence");
		wither_dragon_essence = new ItemWitherDragonEssence("wither_dragon_essence");
		wither_girl_essence = new ItemWitherGirlEssence("girl_essence");
		pink_wither_girl_essence = new ItemPinkWitherGirlEssence("pink_girl_essence");
		void_wither_girl_essence = new ItemVoidWitherGirlEssence("negative_girl_essence");
	}
	
	public static void register() 
	{
		GameRegistry.registerItem(blackHole, blackHole.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherlove, witherlove.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherfamily, witherfamily.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(threesome, threesome.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(allstarfather, allstarfather.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(recordCalm4, recordCalm4.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(recordWither, recordWither.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(recordEnderDragon, recordEnderDragon.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(recordSamantha, recordSamantha.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(recordRheia, recordRheia.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(recordTheia, recordTheia.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(sculpture, sculpture.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(fireball, fireball.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(lightningbolt, lightningbolt.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(skull, skull.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(skullpower, skullpower.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(meteorpower, meteorpower.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(lightningstormpower, lightningstormpower.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(enderCharge, enderCharge.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(netherStarShard, netherStarShard.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emeraldPickaxe, emeraldPickaxe.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emeraldAxe, emeraldAxe.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emeraldSpade, emeraldSpade.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emeraldHoe, emeraldHoe.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emeraldSword, emeraldSword.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emeraldHelmet, emeraldHelmet.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emeraldChestplate, emeraldChestplate.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emeraldLeggings, emeraldLeggings.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emeraldBoots, emeraldBoots.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherPickaxe, witherPickaxe.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherAxe, witherAxe.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherSpade, witherSpade.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherHoe, witherHoe.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherSword, witherSword.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherHelmet, witherHelmet.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherChestplate, witherChestplate.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherLeggings, witherLeggings.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(witherBoots, witherBoots.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(wither_essence, wither_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(air_essence, air_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(fire_essence, fire_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(water_essence, water_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(earth_essence, earth_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(ice_essence, ice_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(lava_essence, lava_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(lightning_essence, lightning_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(hurricane_essence, hurricane_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(sand_essence, sand_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(gravel_essence, gravel_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(glass_essence, glass_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(obsidian_essence, obsidian_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(bedrock_essence, bedrock_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(coal_essence, coal_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(redstone_essence, redstone_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(lapis_essence, lapis_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(iron_essence, iron_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(gold_essence, gold_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(diamond_essence, diamond_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(emerald_essence, emerald_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(christmas_essence, christmas_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(saint_patrick_essence, saint_patrick_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(valentine_essence, valentine_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(halloween_essence, halloween_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(friendly_essence, friendly_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(growth_essence, growth_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(demented_essence, demented_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(negative_essence, negative_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(wither_dragon_essence, wither_dragon_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(wither_girl_essence, wither_girl_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(pink_wither_girl_essence, pink_wither_girl_essence.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(void_wither_girl_essence, void_wither_girl_essence.getUnlocalizedName().substring(5));
	}

	public static void registerRenders() 
	{
		registerItem(MoWitherItems.blackHole);
		registerItem(MoWitherItems.witherlove);
		registerItem(MoWitherItems.witherfamily);
		registerItem(MoWitherItems.threesome);
		registerItem(MoWitherItems.allstarfather);
		registerItem(MoWitherItems.recordCalm4);
		registerItem(MoWitherItems.recordWither);
		registerItem(MoWitherItems.recordEnderDragon);
		registerItem(MoWitherItems.recordSamantha);
		registerItem(MoWitherItems.recordRheia);
		registerItem(MoWitherItems.recordTheia);
		registerItem(MoWitherItems.sculpture);
		registerItem(MoWitherItems.fireball);
		registerItem(MoWitherItems.lightningbolt);
		registerItem(MoWitherItems.skull);
		registerItem(MoWitherItems.skullpower);
		registerItem(MoWitherItems.meteorpower);
		registerItem(MoWitherItems.lightningstormpower);
		registerItem(MoWitherItems.enderCharge);
		registerItem(MoWitherItems.netherStarShard);
		registerItem(MoWitherItems.emeraldPickaxe);
		registerItem(MoWitherItems.emeraldAxe);
		registerItem(MoWitherItems.emeraldSpade);
		registerItem(MoWitherItems.emeraldHoe);
		registerItem(MoWitherItems.emeraldSword);
		registerItem(MoWitherItems.emeraldHelmet);
		registerItem(MoWitherItems.emeraldChestplate);
		registerItem(MoWitherItems.emeraldLeggings);
		registerItem(MoWitherItems.emeraldBoots);
		registerItem(MoWitherItems.witherPickaxe);
		registerItem(MoWitherItems.witherAxe);
		registerItem(MoWitherItems.witherSpade);
		registerItem(MoWitherItems.witherHoe);
		registerItem(MoWitherItems.witherSword);
		registerItem(MoWitherItems.witherHelmet);
		registerItem(MoWitherItems.witherChestplate);
		registerItem(MoWitherItems.witherLeggings);
		registerItem(MoWitherItems.witherBoots);
		registerItem(MoWitherItems.wither_essence);
		registerItem(MoWitherItems.air_essence);
		registerItem(MoWitherItems.fire_essence);
		registerItem(MoWitherItems.water_essence);
		registerItem(MoWitherItems.earth_essence);
		registerItem(MoWitherItems.ice_essence);
		registerItem(MoWitherItems.lava_essence);
		registerItem(MoWitherItems.lightning_essence);
		registerItem(MoWitherItems.hurricane_essence);
		registerItem(MoWitherItems.sand_essence);
		registerItem(MoWitherItems.gravel_essence);
		registerItem(MoWitherItems.glass_essence);
		registerItem(MoWitherItems.obsidian_essence);
		registerItem(MoWitherItems.bedrock_essence);
		registerItem(MoWitherItems.coal_essence);
		registerItem(MoWitherItems.redstone_essence);
		registerItem(MoWitherItems.lapis_essence);
		registerItem(MoWitherItems.iron_essence);
		registerItem(MoWitherItems.gold_essence);
		registerItem(MoWitherItems.diamond_essence);
		registerItem(MoWitherItems.emerald_essence);
		registerItem(MoWitherItems.christmas_essence);
		registerItem(MoWitherItems.saint_patrick_essence);
		registerItem(MoWitherItems.valentine_essence);
		registerItem(MoWitherItems.halloween_essence);
		registerItem(MoWitherItems.friendly_essence);
		registerItem(MoWitherItems.growth_essence);
		registerItem(MoWitherItems.demented_essence);
		registerItem(MoWitherItems.negative_essence);
		registerItem(MoWitherItems.wither_dragon_essence);
		registerItem(MoWitherItems.wither_girl_essence);
		registerItem(MoWitherItems.pink_wither_girl_essence);
		registerItem(MoWitherItems.void_wither_girl_essence);
	}
	
	public static void registerItem(Item item) 
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(MoWithers.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}