package net.minecraft.entity.witherskulls;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLightningSkull
  extends EntityWitherSkull
{
  public EntityLightningSkull(World worldIn)
  {
    super(worldIn);
    setSize(0.5F, 0.5F);
  }
  
  public EntityLightningSkull(World worldIn, EntityLivingBase p_i1794_2_, double p_i1794_3_, double p_i1794_5_, double p_i1794_7_)
  {
    super(worldIn, p_i1794_2_, p_i1794_3_, p_i1794_5_, p_i1794_7_);
    setSize(0.5F, 0.5F);
  }
  
  protected float getMotionFactor()
  {
    return isInvulnerable() ? 0.8F : 1.0F;
  }
  
  @SideOnly(Side.CLIENT)
  public EntityLightningSkull(World worldIn, double p_i1795_2_, double p_i1795_4_, double p_i1795_6_, double p_i1795_8_, double p_i1795_10_, double p_i1795_12_)
  {
    super(worldIn, p_i1795_2_, p_i1795_4_, p_i1795_6_, p_i1795_8_, p_i1795_10_, p_i1795_12_);
    setSize(0.5F, 0.5F);
  }
  
  protected void onImpact(RayTraceResult movingObject)
  {
    if (!this.worldObj.isRemote)
    {
      if (movingObject.entityHit != null)
      {
        if (this.shootingEntity != null)
        {
          if (movingObject.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 10.0F))
          {
            movingObject.entityHit.setFire(25);
            
            this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, movingObject.entityHit.posX, movingObject.entityHit.posY, movingObject.entityHit.posZ, false));
            
            movingObject.entityHit.attackEntityFrom(DamageSource.lightningBolt, 11.0F + (rand.nextFloat() * 19F));
            if (!movingObject.entityHit.isEntityAlive()) {
              this.shootingEntity.heal(5.0F);
            } else {
              applyEnchantments(this.shootingEntity, movingObject.entityHit);
            }
          }
        }
        else {
          movingObject.entityHit.attackEntityFrom(DamageSource.lightningBolt, 15.0F);
        }
        if ((movingObject.entityHit instanceof EntityLivingBase))
        {
          byte b0 = 5;
          if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL)
          {
            b0 = 15;
            for (int l1 = 0; l1 <= 2; l1++)
            {
              this.playSound(SoundEvents.entity_generic_explode, 2.0F, 0.7F + this.rand.nextFloat() * 0.2F);
              movingObject.entityHit.hurtResistantTime = 1;
              this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, movingObject.entityHit.posX, movingObject.entityHit.posY, movingObject.entityHit.posZ, false));
            }
          }
          else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD)
          {
            b0 = 40;
            for (int l1 = 0; l1 <= 4; l1++)
            {
              this.playSound(SoundEvents.entity_generic_explode, 2.0F, 0.7F + this.rand.nextFloat() * 0.2F);
              movingObject.entityHit.hurtResistantTime = 1;
              this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, movingObject.entityHit.posX, movingObject.entityHit.posY, movingObject.entityHit.posZ, false));
            }
          }
          if (b0 > 0)
          {
            ((EntityLivingBase)movingObject.entityHit).motionY = (0.75F * this.worldObj.getDifficulty().getDifficultyId());
            
            ((EntityLivingBase)movingObject.entityHit).addPotionEffect(new PotionEffect(MobEffects.digSlowdown, 20 * b0, 0));
            ((EntityLivingBase)movingObject.entityHit).addPotionEffect(new PotionEffect(MobEffects.confusion, 20 * b0, 0));
            ((EntityLivingBase)movingObject.entityHit).addPotionEffect(new PotionEffect(MobEffects.wither, 20 * b0, 1));
            if (this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
              ((EntityLivingBase)movingObject.entityHit).addPotionEffect(new PotionEffect(MobEffects.moveSlowdown, 20 * b0, 0));
            }
          }
        }
      }
      this.playSound(SoundEvents.entity_generic_explode, 2.0F, 0.7F + this.rand.nextFloat() * 0.2F);
      if (this.worldObj.getClosestPlayerToEntity(this, 6D) != null)
      {
    	  EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this, 6D);
    	  player.attackEntityFrom(DamageSource.outOfWorld, 1F);
          this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, player.posX, player.posY, player.posZ, false));
      }
      else
      {
          this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.posX, this.posY, this.posZ, false));
      }
      this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 2.0F, true, this.worldObj.getGameRules().getBoolean("mobGriefing"));
      setDead();
    }
  }
}
