package net.minecraft.MoWithers.items;

import java.util.Iterator;

import net.minecraft.MoWithers.MoWithers;
import net.minecraft.MoWithers.MoWithersAchievments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.witherskulls.EntityItemMoWithers;
import net.minecraft.entity.witherskulls.EntitySkullShower;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemInvokeSkullPower extends Item
{
  public ItemInvokeSkullPower(String unlocalizedName)
  {
	this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(MoWithers.mowithers);
  }
  
  public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
  {
	  playerIn.swingItem();
      if (!worldIn.isRemote)
      {
    	  playerIn.triggerAchievement(MoWithersAchievments.achievementInvokeGodPower);
    	  worldIn.playBroadcastSound(1013, new BlockPos(playerIn), 0);
          EntitySkullShower entitylargefireball = new EntitySkullShower(worldIn, playerIn.posX, playerIn.posY + 8D, playerIn.posZ);
          Iterator iterator = worldIn.playerEntities.iterator();
          EntityPlayer entityplayer;
          if (iterator.hasNext())
          {
              entityplayer = (EntityPlayer)iterator.next();
              entityplayer.addChatMessage(new TextComponentString(playerIn.getName() + " has invoked the Skull Rain Power!"));
          }
          worldIn.spawnEntityInWorld(entitylargefireball);
          if (!playerIn.capabilities.isCreativeMode)
          {
              --itemStackIn.stackSize;
          }
      }

      playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return itemStackIn;
  }
  
  @SideOnly(Side.CLIENT)
  public boolean hasEffect(ItemStack stack)
  {
      return true;
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
}
