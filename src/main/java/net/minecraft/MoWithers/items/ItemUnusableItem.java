package net.minecraft.MoWithers.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemUnusableItem extends Item
{
  public ItemUnusableItem(String unlocalizedName)
  {
	this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(null);
  }
  
  public EnumRarity getRarity(ItemStack stack)
  {
    return EnumRarity.RARE;
  }
}
