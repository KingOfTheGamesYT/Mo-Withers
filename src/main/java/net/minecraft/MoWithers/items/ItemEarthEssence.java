package net.minecraft.MoWithers.items;

import java.util.List;

import net.minecraft.MoWithers.MoWithers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.witherskulls.EntityItemMoWithers;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEarthEssence extends Item
{
  public ItemEarthEssence(String unlocalizedName)
  {
	this.setUnlocalizedName(unlocalizedName);
    this.setCreativeTab(MoWithers.mowithers);
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
    	  int i = MathHelper.floor_double(playerIn.posY);
          int i1 = MathHelper.floor_double(playerIn.posX);
          int j1 = MathHelper.floor_double(playerIn.posZ);
          for (int l1 = -3; l1 <= 3; l1++) {
            for (int i2 = -3; i2 <= 3; i2++) {
              for (int j = (int)-playerIn.posY; j <= 3; j++)
              {
                int j2 = i1 + l1;
                int k = i + j;
                int l = j1 + i2;
                Block block = worldIn.getBlockState(new BlockPos(j2, k, l)).getBlock();
                Block newblock = worldIn.getBlockState(new BlockPos(j2, i, l)).getBlock();
                if (block instanceof BlockOre && (newblock instanceof BlockAir || newblock instanceof BlockStone || newblock.getMaterial() == Material.vine)) 
                {
                	worldIn.setBlockState(new BlockPos(j2, k, l), Blocks.air.getDefaultState());
                	worldIn.setBlockState(new BlockPos(j2, i, l), block.getDefaultState());
                	worldIn.playAuxSFX(2001, new BlockPos(j2, i, l), Block.getStateId(block.getDefaultState()));
                }
              }
            }
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
