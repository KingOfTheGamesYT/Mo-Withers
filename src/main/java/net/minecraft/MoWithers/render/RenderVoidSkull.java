package net.minecraft.MoWithers.render;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.entity.witherskulls.EntityVoidSkull;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVoidSkull
  extends Render
{
  private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation("mowithers", "textures/entities/void_wither_invul.png");
  private static final ResourceLocation witherTextures = new ResourceLocation("mowithers", "textures/entities/void_wither.png");
  private final ModelSkeletonHead skeletonHeadModel = new ModelSkeletonHead();
  
  public RenderVoidSkull(RenderManager p_i46129_1_)
  {
    super(p_i46129_1_);
  }
  
  private float func_82400_a(float p_82400_1_, float p_82400_2_, float p_82400_3_)
  {
      float f3;

      for (f3 = p_82400_2_ - p_82400_1_; f3 < -180.0F; f3 += 360.0F)
      {
          ;
      }

      while (f3 >= 180.0F)
      {
          f3 -= 360.0F;
      }

      return p_82400_1_ + p_82400_3_ * f3;
  }

  /**
   * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
   * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
   * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
   * double d2, float f, float f1). But JAD is pre 1.5 so doe
   */
  public void doRender(EntityVoidSkull entity, double x, double y, double z, float p_76986_8_, float partialTicks)
  {
      GlStateManager.pushMatrix();
      GlStateManager.disableCull();
      float f2 = this.func_82400_a(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
      float f3 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
      GlStateManager.translate((float)x, (float)y, (float)z);
      float f4 = 0.0625F;
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(1.0F, -1.0F, 1.0F);
      GlStateManager.scale(2.0F, 2.0F, 2.0F);
      GlStateManager.rotate(entity.rotationPitch * 90F, 1F, 0F, 0F);
      GlStateManager.enableAlpha();
      this.bindEntityTexture(entity);
      this.skeletonHeadModel.render(entity, 0.0F, 0.0F, 0.0F, 0F, 0F, 0.0625F);
      GlStateManager.popMatrix();
      super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
  }

  protected ResourceLocation func_180564_a(EntityVoidSkull p_180564_1_)
  {
      return p_180564_1_.isInvulnerable() ? invulnerableWitherTextures : witherTextures;
  }

  /**
   * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
   */
  protected ResourceLocation getEntityTexture(Entity entity)
  {
      return this.func_180564_a((EntityVoidSkull)entity);
  }

  /**
   * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
   * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
   * (Render<T extends Entity>) and this method has signature public void func_76986_a(T entity, double d, double d1,
   * double d2, float f, float f1). But JAD is pre 1.5 so doe
   */
  public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks)
  {
      this.doRender((EntityVoidSkull)entity, x, y, z, p_76986_8_, partialTicks);
  }
}
