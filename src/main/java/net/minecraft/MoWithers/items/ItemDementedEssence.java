package net.minecraft.MoWithers.items;

import java.util.List;

import net.minecraft.MoWithers.MoWithers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.witherskulls.EntityItemMoWithers;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemDementedEssence extends Item
{
  public ItemDementedEssence(String unlocalizedName)
  {
	this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(MoWithers.mowithers);
  }
  
  public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target)
  {
      if (target instanceof EntityLivingBase)
      {
    	  playerIn.swingItem();
    	  target.addPotionEffect(new PotionEffect(1 + target.getRNG().nextInt(22), 1000, 1));
    	  playerIn.worldObj.playSound(target.posX + 0.5D, target.posY + 0.5D, target.posZ + 0.5D, "mob.zombie.remedy", 1F, 1F, false);

          return true;
      }
      else
      {
    	  return super.itemInteractionForEntity(stack, playerIn, target);
      }
  }
  
  public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
  {
      if (target instanceof EntityLivingBase && attacker instanceof EntityPlayer)
      {
    	  target.addPotionEffect(new PotionEffect(1 + target.getRNG().nextInt(22), 1000, 1));
    	  attacker.worldObj.playSound(target.posX + 0.5D, target.posY + 0.5D, target.posZ + 0.5D, "mob.zombie.remedy", 1F, 1F, false);

          return true;
      }
      else
      {
    	  return super.hitEntity(stack, target, attacker);
      }
  }
  
  public EnumRarity getRarity(ItemStack stack)
  {
    return EnumRarity.EPIC;
  }
  
  @Override
  public boolean hasCustomEntity(ItemStack stack)
  {
      return true;
  }

  @Override
  public Entity createEntity(World world, Entity location, ItemStack itemstack)
  {
	  EntityItemMoWithers newItem = new EntityItemMoWithers(world, true, true, true, true);
	  newItem.copyLocationAndAnglesFrom(location);
	  newItem.motionX = location.motionX;
	  newItem.motionY = location.motionY;
	  newItem.motionZ = location.motionZ;
	  newItem.setEntityItemStack(itemstack);
      return newItem;
  }
  
  @Override
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) 
  {
      tooltip.add("You sense it's power.");
      tooltip.add("All the good and evil this one item could do.");
  }
}
