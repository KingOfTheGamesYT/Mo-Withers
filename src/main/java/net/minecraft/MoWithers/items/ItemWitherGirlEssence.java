package net.minecraft.MoWithers.items;

import java.util.List;

import net.minecraft.MoWithers.MoWithers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.wither.EntityWitherGirl;
import net.minecraft.entity.witherskulls.EntityItemMoWithers;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWitherGirlEssence extends Item
{
  public ItemWitherGirlEssence(String unlocalizedName)
  {
	this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(MoWithers.mowithers);
  }
  
  public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target)
  {
      if (target instanceof EntityLivingBase)
      {
    	  playerIn.swingItem();

    	  if (target instanceof EntityWitherGirl)
    	  {
    		  ((EntityWitherGirl)target).setInLove(true);
    	  }
    	  
    	  if (target instanceof EntityHorse)
    	  {
    		  ((EntityHorse)target).setHorseTamed(true);
    	  }
    	  
    	  if (target instanceof EntityTameable)
    	  {
    		  EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(target.worldObj.rand.nextInt(15));
    		  
    		  if (target instanceof EntityWolf)
    			  ((EntityWolf)target).setCollarColor(enumdyecolor);
    		  if (target instanceof EntityOcelot)
    			  ((EntityOcelot)target).setTameSkin(1 + ((EntityOcelot)target).worldObj.rand.nextInt(3));
    		  ((EntityTameable)target).setTamed(true);
    		  ((EntityTameable)target).getNavigator().clearPathEntity();
    		  ((EntityTameable)target).setAttackTarget((EntityLivingBase)null);
    		  ((EntityTameable)target).getAISit().setSitting(true);
    		  ((EntityTameable)target).setOwnerId(playerIn.getUniqueID().toString());
    	        for (int i = 0; i < 7; ++i)
    	        {
    	            double d0 = ((EntityTameable)target).getRNG().nextGaussian() * 0.02D;
    	            double d1 = ((EntityTameable)target).getRNG().nextGaussian() * 0.02D;
    	            double d2 = ((EntityTameable)target).getRNG().nextGaussian() * 0.02D;
    	            ((EntityTameable)target).worldObj.spawnParticle(EnumParticleTypes.HEART, ((EntityTameable)target).posX + (double)(((EntityTameable)target).getRNG().nextFloat() * ((EntityTameable)target).width * 2.0F) - (double)((EntityTameable)target).width, ((EntityTameable)target).posY + 0.5D + (double)(((EntityTameable)target).getRNG().nextFloat() * ((EntityTameable)target).height), ((EntityTameable)target).posZ + (double)(((EntityTameable)target).getRNG().nextFloat() * ((EntityTameable)target).width * 2.0F) - (double)((EntityTameable)target).width, d0, d1, d2, new int[0]);
    	        }
    		  ((EntityTameable)target).worldObj.setEntityState((EntityTameable)target, (byte)7);
    	  }


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
    	  if (target instanceof EntityWitherGirl)
    	  {
    		  ((EntityWitherGirl)target).setInLove(true);
    	  }
    	  
    	  if (target instanceof EntityHorse)
    	  {
    		  ((EntityHorse)target).setHorseTamed(true);
    	  }
    	  
    	  if (target instanceof EntityTameable)
    	  {
    		  EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(target.worldObj.rand.nextInt(15));
    		  
    		  if (target instanceof EntityWolf)
    			  ((EntityWolf)target).setCollarColor(enumdyecolor);
    		  if (target instanceof EntityOcelot)
    			  ((EntityOcelot)target).setTameSkin(1 + ((EntityOcelot)target).worldObj.rand.nextInt(3));
    		  ((EntityTameable)target).setTamed(true);
    		  ((EntityTameable)target).getNavigator().clearPathEntity();
    		  ((EntityTameable)target).setAttackTarget((EntityLivingBase)null);
    		  ((EntityTameable)target).getAISit().setSitting(true);
    		  ((EntityTameable)target).setOwnerId(((EntityPlayer)attacker).getUniqueID().toString());
    	        for (int i = 0; i < 7; ++i)
    	        {
    	            double d0 = ((EntityTameable)target).getRNG().nextGaussian() * 0.02D;
    	            double d1 = ((EntityTameable)target).getRNG().nextGaussian() * 0.02D;
    	            double d2 = ((EntityTameable)target).getRNG().nextGaussian() * 0.02D;
    	            ((EntityTameable)target).worldObj.spawnParticle(EnumParticleTypes.HEART, ((EntityTameable)target).posX + (double)(((EntityTameable)target).getRNG().nextFloat() * ((EntityTameable)target).width * 2.0F) - (double)((EntityTameable)target).width, ((EntityTameable)target).posY + 0.5D + (double)(((EntityTameable)target).getRNG().nextFloat() * ((EntityTameable)target).height), ((EntityTameable)target).posZ + (double)(((EntityTameable)target).getRNG().nextFloat() * ((EntityTameable)target).width * 2.0F) - (double)((EntityTameable)target).width, d0, d1, d2, new int[0]);
    	        }
    		  ((EntityTameable)target).worldObj.setEntityState((EntityTameable)target, (byte)7);
    	  }

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
      tooltip.add("\u00A76Some how, you have a sense of not being alone.");
      tooltip.add("\u00A76You feel comforted by holding this item.");
  }
}
