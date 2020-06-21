package net.minecraft.MoWithers.items;

import net.minecraft.MoWithers.MoWithers;
import net.minecraft.item.ItemHoe;

public class ItemEmeraldHoe extends ItemHoe 
{

	public ItemEmeraldHoe(String unlocalizedName, ToolMaterial material) 
	{
		super(material);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(MoWithers.mowithers);
	}
}