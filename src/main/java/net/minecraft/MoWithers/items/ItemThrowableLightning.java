package net.minecraft.MoWithers.items;

import net.minecraft.MoWithers.MoWithers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.witherskulls.EntityItemMoWithers;
import net.minecraft.entity.witherskulls.EntityThrowableLightning;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemThrowableLightning extends Item
{
  public ItemThrowableLightning(String unlocalizedName)
  {
	this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(MoWithers.mowithers);
  }
  
  public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
  {
	  playerIn.swingItem();
      double d1 = 1.5D;
      double d22 = 64D;
      Vec3d vec3 = playerIn.getLook(1.0F);
      double d2 = (playerIn.posX + vec3.xCoord * d22) - (playerIn.posX + vec3.xCoord * d1);
      double d3 = ((playerIn.posY + 1.5D) + vec3.yCoord * d22) - ((playerIn.posY + 1.5D) + vec3.yCoord * d1);
      double d4 = (playerIn.posZ + vec3.zCoord * d22) - (playerIn.posZ + vec3.zCoord * d1);
      EntityThrowableLightning entitylargefireball = new EntityThrowableLightning(worldIn, playerIn, d2, d3, d4);
      entitylargefireball.posX = playerIn.posX + vec3.xCoord;
      entitylargefireball.posY = playerIn.posY + vec3.yCoord + 1.5D;
      entitylargefireball.posZ = playerIn.posZ + vec3.zCoord;
      worldIn.playSoundAtEntity(playerIn, "ambient.weather.thunder", 3.0F, 2.0F);
      for (int i = 0; i < 500; ++i)
      worldIn.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, playerIn.posX + vec3.xCoord * d1, (playerIn.posY + 1.75D) + vec3.yCoord * d1, playerIn.posZ + vec3.zCoord * d1, d2 * (0.0003D * i), d3 * (0.0003D * i), d4 * (0.0003D * i), new int[0]);
      
      if (!worldIn.isRemote)
      {
          worldIn.spawnEntityInWorld(entitylargefireball);
          if (!playerIn.capabilities.isCreativeMode)
          {
              --itemStackIn.stackSize;
          }
      }

      playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return itemStackIn;
  }
  
  public EnumRarity getRarity(ItemStack stack)
  {
    return EnumRarity.UNCOMMON;
  }
  
  @Override
  public boolean hasCustomEntity(ItemStack stack)
  {
      return true;
  }

  @Override
  public Entity createEntity(World world, Entity location, ItemStack itemstack)
  {
	  EntityItemMoWithers newItem = new EntityItemMoWithers(world, true, true, false, false);
	  newItem.copyLocationAndAnglesFrom(location);
	  newItem.motionX = location.motionX;
	  newItem.motionY = location.motionY;
	  newItem.motionZ = location.motionZ;
	  newItem.setEntityItemStack(itemstack);
      return newItem;
  }
}
