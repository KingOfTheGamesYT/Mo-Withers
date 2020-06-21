package net.minecraft.MoWithers.items;

import java.util.List;

import net.minecraft.MoWithers.MoWithers;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.witherskulls.EntityIceHolder;
import net.minecraft.entity.witherskulls.EntityItemMoWithers;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemIceEssence extends Item
{
  public ItemIceEssence(String unlocalizedName)
  {
	this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(MoWithers.mowithers);
  }
  
  public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target)
  {
      if (target instanceof EntityLivingBase)
      {
    	  playerIn.swingItem();
    	  target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400, 255));
    	  
          int i = MathHelper.floor_double(target.posY);
          int i1 = MathHelper.floor_double(target.posX);
          int j1 = MathHelper.floor_double(target.posZ);
          for (int l1 = -(int)(target.width / 2); l1 <= (int)(target.width / 2); l1++) {
            for (int i2 = -(int)(target.width / 2); i2 <= (int)(target.width / 2); i2++) {
              for (int j = 0; j <= (int)(target.height); j++)
              {
                int j2 = i1 + l1;
                int k = i + j;
                int l = j1 + i2;
                BlockPos blockpos = new BlockPos(j2, k, l);
                Block block = playerIn.worldObj.getBlockState(new BlockPos(blockpos)).getBlock();
                target.renderYawOffset = target.rotationYaw;
                if (l1 == 0 && i2 == 0 && j == 0)
                {
                	target.extinguish();
                	target.setPosition(j2 + 0.5D, k, l + 0.5D);
                	EntityIceHolder entitylargefireball = new EntityIceHolder(playerIn.worldObj, j2 + 0.5D, k, l + 0.5D, target, target.rotationYaw, target.rotationYawHead);
                	if (!playerIn.worldObj.isRemote)
                    {
                		playerIn.worldObj.spawnEntityInWorld(entitylargefireball);
                    }
                }
                if (block.getBlockHardness(playerIn.worldObj, blockpos) != -1F && !block.isOpaqueCube() && block != Blocks.ice) {
                	playerIn.worldObj.setBlockState(blockpos, Blocks.ice.getDefaultState());
                	playerIn.worldObj.playAuxSFX(2001, blockpos, Block.getStateId(Blocks.ice.getDefaultState()));
                }
              }
            }
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
    	  target.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 400, 255));

          int i = MathHelper.floor_double(target.posY);
          int i1 = MathHelper.floor_double(target.posX);
          int j1 = MathHelper.floor_double(target.posZ);
          for (int l1 = -(int)(target.width / 2); l1 <= (int)(target.width / 2); l1++) {
            for (int i2 = -(int)(target.width / 2); i2 <= (int)(target.width / 2); i2++) {
              for (int j = 0; j <= (int)(target.height); j++)
              {
                int j2 = i1 + l1;
                int k = i + j;
                int l = j1 + i2;
                BlockPos blockpos = new BlockPos(j2, k, l);
                Block block = attacker.worldObj.getBlockState(new BlockPos(blockpos)).getBlock();
                target.renderYawOffset = target.rotationYaw;
                if (l1 == 0 && i2 == 0 && j == 0)
                {
                	target.extinguish();
                	target.setPosition(j2 + 0.5D, k, l + 0.5D);
                	EntityIceHolder entitylargefireball = new EntityIceHolder(attacker.worldObj, j2 + 0.5D, k, l + 0.5D, target, target.rotationYaw, target.rotationYawHead);
                	if (!attacker.worldObj.isRemote)
                    {
                		attacker.worldObj.spawnEntityInWorld(entitylargefireball);
                    }
                }
                if (block.getBlockHardness(attacker.worldObj, blockpos) != -1F && !block.isOpaqueCube() && block != Blocks.ice) {
                	attacker.worldObj.setBlockState(blockpos, Blocks.ice.getDefaultState());
                	attacker.worldObj.playAuxSFX(2001, blockpos, Block.getStateId(Blocks.ice.getDefaultState()));
                }
              }
            }
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
      tooltip.add("You sense it's power.");
      tooltip.add("All the good and evil this one item could do.");
  }
}
