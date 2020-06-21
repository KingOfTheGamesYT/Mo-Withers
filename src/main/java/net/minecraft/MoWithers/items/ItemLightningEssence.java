package net.minecraft.MoWithers.items;

import java.util.List;

import net.minecraft.MoWithers.MoWithers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.witherskulls.EntityItemMoWithers;
import net.minecraft.entity.witherskulls.EntityLightningShot;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLightningEssence extends Item
{
  public ItemLightningEssence(String unlocalizedName)
  {
	this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(MoWithers.mowithers);
  }
  
  public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
  {
	  playerIn.extinguish();
	  playerIn.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 60));
	  playerIn.addPotionEffect(new PotionEffect(Potion.regeneration.id, 30, 5));
	  playerIn.swingItem();
      double d1 = 1.5D;
      double d22 = 64D;
      Vec3d vec3 = playerIn.getLook(1.0F);
      double d2 = (playerIn.posX + vec3.xCoord * d22) - (playerIn.posX + vec3.xCoord * d1);
      double d3 = ((playerIn.posY + 1.5D) + vec3.yCoord * d22) - ((playerIn.posY + 1.5D) + vec3.xCoord * d1);
      double d4 = (playerIn.posZ + vec3.zCoord * d22) - (playerIn.posZ + vec3.zCoord * d1);
      worldIn.playAuxSFXAtEntity(playerIn, 1009, new BlockPos(playerIn), 0);
      EntityLightningShot entitylargefireball = new EntityLightningShot(worldIn, playerIn, d2, d3, d4);
      entitylargefireball.posX = playerIn.posX + vec3.xCoord;
      entitylargefireball.posY = playerIn.posY + vec3.yCoord + 1.5D;
      entitylargefireball.posZ = playerIn.posZ + vec3.zCoord;

      if (!worldIn.isRemote)
      {
          worldIn.spawnEntityInWorld(entitylargefireball);
      }

      playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return itemStackIn;
  }
  
  public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
  {
      pos = pos.offset(side);

      if (!playerIn.canPlayerEdit(pos, side, stack))
      {
          return false;
      }
      else
      {
          if (worldIn.isAirBlock(pos))
          {
        	  playerIn.extinguish();
        	  playerIn.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 60));
        	  playerIn.addPotionEffect(new PotionEffect(Potion.regeneration.id, 30, 5));
        	  worldIn.newExplosion(playerIn, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 1F, false, false);
              worldIn.playSoundEffect((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.2F);
              worldIn.setBlockState(pos, Blocks.fire.getDefaultState());
              worldIn.addWeatherEffect(new EntityLightningBolt(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ()));
          }

          return true;
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
