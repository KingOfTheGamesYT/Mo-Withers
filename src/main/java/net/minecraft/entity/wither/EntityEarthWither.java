package net.minecraft.entity.wither;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.MoWithers.MoWithersAchievments;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.witherskulls.EntityEarthSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameRules;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityEarthWither
  extends EntityMob
  implements IBossDisplayData, IRangedAttackMob
{
  private float[] field_82220_d = new float[2];
  private float[] field_82221_e = new float[2];
  private float[] field_82217_f = new float[2];
  private float[] field_82218_g = new float[2];
  private int[] field_82223_h = new int[2];
  private int[] field_82224_i = new int[2];
  private int blockBreakCounter;
  private static final Predicate attackEntitySelector = new Predicate()
  {
    public boolean func_180027_a(Entity p_180027_1_)
    {
      return ((p_180027_1_ instanceof EntityLivingBase)) && (((EntityLivingBase)p_180027_1_).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD) && (!(p_180027_1_ instanceof EntityAnimal)) && (!(p_180027_1_ instanceof EntityAgeable));
    }
    
    public boolean apply(Object p_apply_1_)
    {
      return func_180027_a((Entity)p_apply_1_);
    }
  };
  
  public EntityEarthWither(World worldIn)
  {
    super(worldIn);
    setHealth(getMaxHealth());
    setSize(1.5F, 5.25F);
    this.isImmuneToFire = true;
    ((PathNavigateGround)getNavigator()).func_179693_d(true);
    this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 40, 20.0F));
    this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
    this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
    this.tasks.addTask(7, new EntityAILookIdle(this));
    this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
    this.targetTasks.addTask(2, new EntityAINearestWitherAttackTarget(this, EntityLivingBase.class, 0, false, false, attackEntitySelector));
    this.experienceValue = 500;
  }
  
  protected void entityInit()
  {
    super.entityInit();
    this.dataWatcher.addObject(16, Byte.valueOf((byte)0));
    this.dataWatcher.addObject(17, new Integer(0));
    this.dataWatcher.addObject(18, new Integer(0));
    this.dataWatcher.addObject(19, new Integer(0));
    this.dataWatcher.addObject(20, new Integer(0));
  }
  
  public void writeEntityToNBT(NBTTagCompound tagCompound)
  {
    super.writeEntityToNBT(tagCompound);
    tagCompound.setInteger("Invul", getInvulTime());
    if (canConjoin()) {
      tagCompound.setBoolean("Unite", canConjoin());
    }
  }
  
  public void readEntityFromNBT(NBTTagCompound tagCompund)
  {
    super.readEntityFromNBT(tagCompund);
    setInvulTime(tagCompund.getInteger("Invul"));
    shouldConjoin(tagCompund.getBoolean("Unite"));
  }
  
  protected String getLivingSound()
  {
    return "mowithers:EarthWitherIdle";
  }
  
  protected String getHurtSound()
  {
    return "mowithers:EarthWitherHurt";
  }
  
  protected String getDeathSound()
  {
    return "mowithers:EarthWitherDeath";
  }
  
  public void shouldConjoin(boolean p_94061_1_)
  {
    this.dataWatcher.updateObject(16, Byte.valueOf((byte)(p_94061_1_ ? 1 : 0)));
  }
  
  public boolean canConjoin()
  {
    return this.dataWatcher.getWatchableObjectByte(16) != 0;
  }
  
  public boolean interact(EntityPlayer player)
  {
    ItemStack itemstack = player.getCurrentEquippedItem();
    if ((itemstack != null) && (itemstack.getItem() == Items.diamond_sword))
    {
      this.worldObj.playBroadcastSound(1013, new BlockPos(this), 0);
      
      shouldConjoin(true);
      
      return true;
    }
    return false;
  }
  
  public void onLivingUpdate()
  {
    if (!isWet() && this.worldObj.canSeeSky(new BlockPos(this))) {
      this.motionY *= 0.75D;
    }
    if ((!this.worldObj.isRemote) && (getWatchedTargetId(0) > 0))
    {
      Entity entity = this.worldObj.getEntityByID(getWatchedTargetId(0));
      if (entity != null)
      {
        if ((this.posY < entity.posY) || ((!isArmored()) && (!canConjoin()) && (this.posY < entity.posY + 3.0D) && (!isWet())))
        {
          if (this.motionY < 0.0D) {
            this.motionY = 0.0D;
          }
          if (!this.worldObj.canSeeSky(new BlockPos(this))) {
            this.motionY += (0.99D - this.motionY);
          } else {
            this.motionY += (0.33D - this.motionY);
          }
        }
        double d0 = entity.posX - this.posX;
        double d1 = entity.posZ - this.posZ;
        double d3 = d0 * d0 + d1 * d1;
        if (d3 > 4.0D)
        {
          double d5 = MathHelper.sqrt_double(d3);
          if (!this.worldObj.canSeeSky(new BlockPos(this)))
          {
            this.motionX += (d0 / d5 * 0.99D - this.motionX);
            this.motionZ += (d1 / d5 * 0.99D - this.motionZ);
          }
          else
          {
            this.motionX += (d0 / d5 * 0.33D - this.motionX);
            this.motionZ += (d1 / d5 * 0.33D - this.motionZ);
          }
        }
      }
    }
    if (this.motionX * this.motionX + this.motionZ * this.motionZ != 0.0D) {
      this.renderYawOffset = (this.rotationYaw = -(float)Math.atan2(this.motionX, this.motionZ) * 180.0F / 3.1415927F);
    }
    super.onLivingUpdate();
    for (int i = 0; i < 2; i++)
    {
      this.field_82218_g[i] = this.field_82221_e[i];
      this.field_82217_f[i] = this.field_82220_d[i];
    }
    for (int i = 0; i < 2; i++)
    {
      int j = getWatchedTargetId(i + 1);
      Entity entity1 = null;
      if (j > 0) {
        entity1 = this.worldObj.getEntityByID(j);
      }
      if (entity1 != null)
      {
        double d1 = func_82214_u(i + 1);
        double d3 = func_82208_v(i + 1);
        double d5 = func_82213_w(i + 1);
        double d6 = entity1.posX - d1;
        double d7 = entity1.posY + entity1.getEyeHeight() - d3;
        double d8 = entity1.posZ - d5;
        double d9 = MathHelper.sqrt_double(d6 * d6 + d8 * d8);
        float f = (float)(Math.atan2(d8, d6) * 180.0D / 3.141592653589793D) - 90.0F;
        float f1 = (float)-(Math.atan2(d7, d9) * 180.0D / 3.141592653589793D);
        this.field_82220_d[i] = func_82204_b(this.field_82220_d[i], f1, 40.0F);
        this.field_82221_e[i] = func_82204_b(this.field_82221_e[i], f, 10.0F);
      }
      else
      {
        this.field_82221_e[i] = func_82204_b(this.field_82221_e[i], this.renderYawOffset, 10.0F);
      }
    }
    boolean flag = isArmored();
    for (int j = 0; j < 3; j++)
    {
      double d10 = func_82214_u(j);
      double d2 = func_82208_v(j);
      double d4 = func_82213_w(j);
      this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, d10 + this.rand.nextGaussian() * 0.30000001192092896D, d2 + this.rand.nextGaussian() * 0.30000001192092896D, d4 + this.rand.nextGaussian() * 0.3D, 0.0D, 0.0D, 0.0D, new int[] { 3 });
      
      this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[] { 3 });
      if ((flag) || (this.worldObj.rand.nextInt(20) == 0))
      {
        this.worldObj.spawnParticle(EnumParticleTypes.DRIP_LAVA, d10 + this.rand.nextGaussian() * 0.30000001192092896D, d2 + this.rand.nextGaussian() * 0.30000001192092896D, d4 + this.rand.nextGaussian() * 0.6D, 0.0D, 0.0D, 0.0D, new int[0]);
        this.worldObj.spawnParticle(EnumParticleTypes.DRIP_LAVA, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
      }
      
      if (this.isInWater())
      {
          for (int j1 = 0; j1 < 30; j1++)
          {
              this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, d10 + this.rand.nextGaussian() * 0.30000001192092896D, d2 + this.rand.nextGaussian() * 0.30000001192092896D, d4 + this.rand.nextGaussian() * 0.3D, 0.0D, 0.0D, 0.0D, new int[] { 3 });
              
              this.worldObj.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[] { 3 });
              if ((flag) || (this.worldObj.rand.nextInt(20) == 0))
              {
                this.worldObj.spawnParticle(EnumParticleTypes.DRIP_LAVA, d10 + this.rand.nextGaussian() * 0.30000001192092896D, d2 + this.rand.nextGaussian() * 0.30000001192092896D, d4 + this.rand.nextGaussian() * 0.6D, 0.0D, 0.0D, 0.0D, new int[0]);
                this.worldObj.spawnParticle(EnumParticleTypes.DRIP_LAVA, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, 0.0D, 0.0D, 0.0D, new int[0]);
              }
          }
      }
    }
    if (getInvulTime() > 0) {
      for (int j = 0; j < 15; j++) {
        this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE, this.posX + this.rand.nextGaussian() * 1.0D, this.posY + this.rand.nextFloat() * 3.3F, this.posZ + this.rand.nextGaussian() * 0.6D, 0.0D, 0.0D, 0.0D, new int[0]);
      }
    }
    if (canConjoin()) {
      for (int j = 0; j < 5; j++) {
        this.worldObj.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, this.posX + this.rand.nextGaussian() * 1.0D, this.posY + this.rand.nextFloat() * 3.3F, this.posZ + this.rand.nextGaussian() * 0.6D, 0.0D, 0.0D, 0.0D, new int[0]);
      }
    }
  }
  
  protected void updateAITasks()
  {
    if (getInvulTime() > 0)
    {
      int i = getInvulTime() - 1;
      if (i <= 0) {
        this.worldObj.playBroadcastSound(1013, new BlockPos(this), 0);
      }
      setInvulTime(i);
      if (this.ticksExisted % 10 == 0) {
        heal(50.0F);
      }
    }
    else
    {
      super.updateAITasks();
      for (int i = 1; i < 3; i++) {
        if (this.ticksExisted >= this.field_82223_h[(i - 1)])
        {
          this.field_82223_h[(i - 1)] = (this.ticksExisted + 10 + this.rand.nextInt(10));
          if ((this.worldObj.getDifficulty() == EnumDifficulty.NORMAL) || (this.worldObj.getDifficulty() == EnumDifficulty.HARD))
          {
            int k2 = i - 1;
            int l2 = this.field_82224_i[(i - 1)];
            this.field_82224_i[k2] = (this.field_82224_i[(i - 1)] + 1);
            if (l2 > 15)
            {
              float f = 10.0F;
              float f1 = 5.0F;
              double d0 = MathHelper.getRandomDoubleInRange(this.rand, this.posX - f, this.posX + f);
              double d1 = MathHelper.getRandomDoubleInRange(this.rand, this.posY - f1, this.posY + f1);
              double d2 = MathHelper.getRandomDoubleInRange(this.rand, this.posZ - f, this.posZ + f);
              launchWitherSkullToCoords(i + 1, d0, d1, d2, true);
              this.field_82224_i[(i - 1)] = 0;
            }
          }
          int i1 = getWatchedTargetId(i);
          if (i1 > 0)
          {
            Entity entity = this.worldObj.getEntityByID(i1);
            if ((entity != null) && (entity.isEntityAlive()) && (getDistanceSqToEntity(entity) <= 3000.0D))
            {
              launchWitherSkullToEntity(i + 1, (EntityLivingBase)entity);
              this.field_82223_h[(i - 1)] = (this.ticksExisted + 40 + this.rand.nextInt(20));
              this.field_82224_i[(i - 1)] = 0;
            }
            else
            {
              func_82211_c(i, 0);
            }
          }
          else
          {
            List list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().expand(32.0D, 8.0D, 32.0D), Predicates.and(attackEntitySelector, IEntitySelector.NOT_SPECTATING));
            for (int k1 = 0; (k1 < 10) && (!list.isEmpty()); k1++)
            {
              EntityLivingBase entitylivingbase = (EntityLivingBase)list.get(this.rand.nextInt(list.size()));
              if ((entitylivingbase != this) && (entitylivingbase.isEntityAlive()))
              {
                if ((entitylivingbase instanceof EntityPlayer))
                {
                  if (((EntityPlayer)entitylivingbase).capabilities.disableDamage) {
                    break;
                  }
                  func_82211_c(i, entitylivingbase.getEntityId()); break;
                }
                func_82211_c(i, entitylivingbase.getEntityId());
                
                break;
              }
              list.remove(entitylivingbase);
            }
          }
        }
      }
      if (getAttackTarget() != null) {
        func_82211_c(0, getAttackTarget().getEntityId());
      } else {
        func_82211_c(0, 0);
      }
      if (this.blockBreakCounter > 0)
      {
        this.blockBreakCounter -= 1;
        if ((this.blockBreakCounter == 0))
        {
        	int i = MathHelper.floor_double(this.posY);
          int i1 = MathHelper.floor_double(this.posX);
          int j1 = MathHelper.floor_double(this.posZ);
          boolean flag = false;
          for (int l1 = -1; l1 <= 1; l1++) {
            for (int i2 = -1; i2 <= 1; i2++) {
              for (int j = -1; j <= 6; j++)
              {
                int j2 = i1 + l1;
                int k = i + j;
                int l = j1 + i2;
                Block block = this.worldObj.getBlockState(new BlockPos(j2, k, l)).getBlock();
                if ((!block.isAir(this.worldObj, new BlockPos(j2, k, l))) && (canEntityDestroy(block, this.worldObj, new BlockPos(j2, k, l), this)))
                {
                  boolean should = true;
                  if ((block.getMaterial() == Material.ground) || (block.getMaterial() == Material.water) || (block.getMaterial() == Material.lava) || (block.getMaterial() == Material.grass) || (block == Blocks.gravel) || (block == Blocks.netherrack)) {
                    should = false;
                  }
                  if ((block == Blocks.stone) || (block == Blocks.cobblestone) || (block == Blocks.leaves)) {
                    should = this.rand.nextInt(30) == 0;
                  }
                  this.worldObj.destroyBlock(new BlockPos(j2, k, l), should);
                }
              }
            }
          }
        }
      }
      if ((this.blockBreakCounter <= 0) && (getAttackTarget() != null)) {
        this.blockBreakCounter = 1;
      }
      if (this.ticksExisted % 10 == 0) {
        if (this.onGround) {
          heal(25.0F);
        } else {
          heal(1.0F);
        }
      }
      if (this.getHealth() <= this.getMaxHealth() / 5 && this.isInWater())
      {
    	  EntityIceWither entity = new EntityIceWither(this.worldObj);
          entity.copyLocationAndAnglesFrom(this);
          for (int j1 = 0; j1 < 3; j1++)
          {
            double d10 = func_82214_u(j1);
            double d2 = func_82208_v(j1);
            double d4 = func_82213_w(j1);
            
            this.worldObj.playAuxSFX(2001, new BlockPos(this.posX, this.posY, this.posZ), Block.getStateId(Blocks.ice.getDefaultState()));
            this.worldObj.playAuxSFX(2001, new BlockPos(this.posX, this.posY + 1D, this.posZ), Block.getStateId(Blocks.ice.getDefaultState()));
            this.worldObj.playAuxSFX(2001, new BlockPos(this.posX, this.posY + 2D, this.posZ), Block.getStateId(Blocks.ice.getDefaultState()));
            this.worldObj.playAuxSFX(2001, new BlockPos(this.posX, this.posY + 3D, this.posZ), Block.getStateId(Blocks.ice.getDefaultState()));
            this.worldObj.playAuxSFX(2001, new BlockPos(d10, d2, d4), Block.getStateId(Blocks.ice.getDefaultState()));
          }
          if (!this.worldObj.isRemote) {
            this.worldObj.spawnEntityInWorld(entity);
            this.setDead();
          }
      }
    }
  }
  
  public boolean canEntityDestroy(Block block, IBlockAccess world, BlockPos pos, Entity entity)
  {
    return (block != Blocks.water) && (block != Blocks.lava) && (block != Blocks.barrier) && (block != Blocks.bedrock) && (block != Blocks.end_portal) && (block != Blocks.end_portal_frame) && (block != Blocks.command_block);
  }
  
  public void func_82206_m()
  {
    setInvulTime(220);
    setHealth(getMaxHealth() / 3.0F);
  }
  
  public void setInWeb() {}
  
  public int getTotalArmorValue()
  {
    return 15;
  }
  
  private double func_82214_u(int p_82214_1_)
  {
    if (p_82214_1_ <= 0) {
      return this.posX;
    }
    float f = (this.renderYawOffset + 180 * (p_82214_1_ - 1)) / 180.0F * 3.1415927F;
    float f1 = MathHelper.cos(f);
    return this.posX + f1 * 1.95D;
  }
  
  private double func_82208_v(int p_82208_1_)
  {
    return p_82208_1_ <= 0 ? this.posY + 4.5D : this.posY + 3.3D;
  }
  
  private double func_82213_w(int p_82213_1_)
  {
    if (p_82213_1_ <= 0) {
      return this.posZ;
    }
    float f = (this.renderYawOffset + 180 * (p_82213_1_ - 1)) / 180.0F * 3.1415927F;
    float f1 = MathHelper.sin(f);
    return this.posZ + f1 * 1.95D;
  }
  
  private float func_82204_b(float p_82204_1_, float p_82204_2_, float p_82204_3_)
  {
    float f3 = MathHelper.wrapAngleTo180_float(p_82204_2_ - p_82204_1_);
    if (f3 > p_82204_3_) {
      f3 = p_82204_3_;
    }
    if (f3 < -p_82204_3_) {
      f3 = -p_82204_3_;
    }
    return p_82204_1_ + f3;
  }
  
  private void launchWitherSkullToEntity(int p_82216_1_, EntityLivingBase p_82216_2_)
  {
    launchWitherSkullToCoords(p_82216_1_, p_82216_2_.posX, p_82216_2_.posY + p_82216_2_.getEyeHeight() * 0.5D, p_82216_2_.posZ, (p_82216_1_ == 0) && (this.rand.nextFloat() < 0.001F));
  }
  
  private void launchWitherSkullToCoords(int p_82209_1_, double p_82209_2_, double p_82209_4_, double p_82209_6_, boolean p_82209_8_)
  {
    this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1008, new BlockPos(this), 0);
    double d3 = func_82214_u(p_82209_1_);
    double d4 = func_82208_v(p_82209_1_);
    double d5 = func_82213_w(p_82209_1_);
    double d6 = p_82209_2_ - d3;
    double d7 = p_82209_4_ - d4;
    double d8 = p_82209_6_ - d5;
    EntityEarthSkull entitywitherskull = new EntityEarthSkull(this.worldObj, this, d6, d7, d8);
    if (p_82209_8_) {
      entitywitherskull.setInvulnerable(true);
    }
    entitywitherskull.posY = d4;
    entitywitherskull.posX = d3;
    entitywitherskull.posZ = d5;
    this.worldObj.spawnEntityInWorld(entitywitherskull);
  }
  
  public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
  {
    if (!canConjoin()) {
      launchWitherSkullToEntity(0, p_82196_1_);
    }
  }
  
  public boolean attackEntityFrom(DamageSource source, float amount)
  {
    if (isEntityInvulnerable(source)) {
      return false;
    }
    if (source == DamageSource.inWall)
    {
      if (this.blockBreakCounter <= 0) {
        this.blockBreakCounter = 1;
      }
      return false;
    }
    if ((source != DamageSource.fall) && (source != DamageSource.anvil) && (source != DamageSource.fallingBlock) && (source != DamageSource.drown) && (!(source.getEntity() instanceof EntityEarthWither)))
    {
      if ((getInvulTime() > 0) && (source != DamageSource.outOfWorld)) {
        return false;
      }
      if (isArmored())
      {
        Entity entity = source.getSourceOfDamage();
        if ((entity instanceof EntityArrow)) {
          return false;
        }
      }
      Entity entity = source.getEntity();
      if ((entity != null) && (!(entity instanceof EntityPlayer)) && ((entity instanceof EntityLivingBase)) && (((EntityLivingBase)entity).getCreatureAttribute() == getCreatureAttribute())) {
        return false;
      }
      if (this.blockBreakCounter <= 0) {
        this.blockBreakCounter = 20;
      }
      for (int i = 0; i < this.field_82224_i.length; i++) {
        this.field_82224_i[i] += 3;
      }
      return super.attackEntityFrom(source, amount);
    }
    return false;
  }
  
  protected void dropFewItems(boolean p_70628_1_, int p_70628_2_)
  {
      int j = 16 + this.rand.nextInt(17 + p_70628_2_);

      for (int k = 0; k < j + 1 + p_70628_2_; ++k)
      {
          this.entityDropItem(new ItemStack(Blocks.dirt, 1), 3F);
      }
      for (int k = 0; k < j + 4 + p_70628_2_; ++k)
      {
          this.dropItem(Items.potato, 1);
      }
    EntityItem entityitem = dropItem(Items.nether_star, 1);
    if (entityitem != null) {
      entityitem.setNoDespawn();
    }
    if (!this.worldObj.isRemote)
    {
        Iterator iterator = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().expand(50.0D, 100.0D, 50.0D)).iterator();

        while (iterator.hasNext())
        {
            EntityPlayer entityplayer = (EntityPlayer)iterator.next();
            entityplayer.triggerAchievement(AchievementList.killWither);
            entityplayer.triggerAchievement(MoWithersAchievments.achievementKillEarthWither);
        }
    }
  }
  
  protected void despawnEntity()
  {
    this.entityAge = 0;
  }
  
  @SideOnly(Side.CLIENT)
  public int getBrightnessForRender(float p_70070_1_)
  {
    if (isWet()) {
      return 5000000;
    }
    return 15728880;
  }
  
  public void fall(float distance, float damageMultiplier) {}
  
  public void addPotionEffect(PotionEffect p_70690_1_) {}
  
  protected void applyEntityAttributes()
  {
    super.applyEntityAttributes();
    getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1500.0D);
    getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(64.0D);
  }
  
  @SideOnly(Side.CLIENT)
  public float func_82207_a(int p_82207_1_)
  {
    return this.field_82221_e[p_82207_1_];
  }
  
  @SideOnly(Side.CLIENT)
  public float func_82210_r(int p_82210_1_)
  {
    return this.field_82220_d[p_82210_1_];
  }
  
  public int getInvulTime()
  {
    return this.dataWatcher.getWatchableObjectInt(20);
  }
  
  public void setInvulTime(int p_82215_1_)
  {
    this.dataWatcher.updateObject(20, Integer.valueOf(p_82215_1_));
  }
  
  public int getWatchedTargetId(int p_82203_1_)
  {
    return this.dataWatcher.getWatchableObjectInt(17 + p_82203_1_);
  }
  
  public void func_82211_c(int p_82211_1_, int p_82211_2_)
  {
    this.dataWatcher.updateObject(17 + p_82211_1_, Integer.valueOf(p_82211_2_));
  }
  
  public boolean isArmored()
  {
    return getHealth() <= getMaxHealth() / 2.0F;
  }
  
  public EnumCreatureAttribute getCreatureAttribute()
  {
    return EnumCreatureAttribute.UNDEAD;
  }
  
  public void mountEntity(Entity entityIn)
  {
    this.ridingEntity = null;
  }
  
  protected float getSoundVolume()
  {
    return 3.0F;
  }
}
