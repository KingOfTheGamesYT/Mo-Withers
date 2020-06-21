package net.minecraft.entity.witherskulls;

import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.entity.wither.EntityCreeperWither;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityWitherSkullCreeper
  extends EntityWitherSkull
{
  public EntityWitherSkullCreeper(World worldIn)
  {
    super(worldIn);
    setSize(0.5F, 0.5F);
  }
  
  public EntityWitherSkullCreeper(World worldIn, EntityLivingBase p_i1794_2_, double p_i1794_3_, double p_i1794_5_, double p_i1794_7_)
  {
    super(worldIn, p_i1794_2_, p_i1794_3_, p_i1794_5_, p_i1794_7_);
    setSize(0.5F, 0.5F);
  }
  
  @SideOnly(Side.CLIENT)
  public EntityWitherSkullCreeper(World worldIn, double p_i1795_2_, double p_i1795_4_, double p_i1795_6_, double p_i1795_8_, double p_i1795_10_, double p_i1795_12_)
  {
    super(worldIn, p_i1795_2_, p_i1795_4_, p_i1795_6_, p_i1795_8_, p_i1795_10_, p_i1795_12_);
    setSize(0.5F, 0.5F);
  }
  
  protected void onImpact(RayTraceResult movingObject)
  {
    if (!this.worldObj.isRemote)
    {
    	if (movingObject.entityHit != null && movingObject.entityHit instanceof EntityCreeper && !((EntityCreeper)movingObject.entityHit).getPowered() && this.shootingEntity != null && this.shootingEntity instanceof EntityCreeperWither && ((EntityCreeperWither)this.shootingEntity).getPowered())
        {
   		 this.worldObj.addWeatherEffect(new EntityLightningBolt(this.worldObj, this.posX, this.posY, this.posZ));
        }
      if (movingObject.entityHit != null && !(movingObject.entityHit instanceof EntityCreeper) && !(movingObject.entityHit instanceof EntityCreeperWither))
      {
        if (this.shootingEntity != null)
        {
          if (movingObject.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 12.0F))
          {
            if (!movingObject.entityHit.isEntityAlive()) {
              this.shootingEntity.heal(5.0F);
            } else {
              func_174815_a(this.shootingEntity, movingObject.entityHit);
            }
          }
        }
        else {
          movingObject.entityHit.attackEntityFrom(DamageSource.setExplosionSource(null), 12.0F);
        }
        if ((movingObject.entityHit instanceof EntityLivingBase))
        {
          byte b0 = 0;
          if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL) {
            b0 = 10;
          } else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
            b0 = 40;
          }
          if (b0 > 0) {
            ((EntityLivingBase)movingObject.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 20 * b0, 1));
          }
        }
      }
      if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL) {
        this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 2.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
      } else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
        this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 4.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
      } else {
        this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"));
      }
      setDead();
    }
  }
}
