package net.minecraft.MoWithers.render.shields;

import net.minecraft.MoWithers.models.ModelHurricaneWither;
import net.minecraft.MoWithers.render.RenderHurricaneWither;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.wither.EntityHurricaneWither;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerHurricaneWitherAura
  implements LayerRenderer
{
  private static final ResourceLocation field_177217_a = new ResourceLocation("mowithers", "textures/entities/hurricane_wave.png");
  private final RenderHurricaneWither field_177215_b;
  private final ModelHurricaneWither field_177216_c = new ModelHurricaneWither(0.0F);
  
  public LayerHurricaneWitherAura(RenderHurricaneWither p_i46105_1_)
  {
    this.field_177215_b = p_i46105_1_;
  }
  
  public void func_177214_a(EntityHurricaneWither p_177214_1_, float p_177214_2_, float p_177214_3_, float p_177214_4_, float p_177214_5_, float p_177214_6_, float p_177214_7_, float p_177214_8_)
  {
      GlStateManager.depthMask(!p_177214_1_.isInvisible());
      this.field_177215_b.bindTexture(field_177217_a);
      GlStateManager.matrixMode(5890);
      GlStateManager.loadIdentity();
      float f7 = (float)p_177214_1_.ticksExisted + p_177214_4_;
      GlStateManager.translate(f7 * 0.05F, f7 * 0.025F, 0.0F);
      GlStateManager.matrixMode(5888);
      GlStateManager.enableBlend();
      GlStateManager.color(1F, 1F, 1F, 1.0F);
      GlStateManager.disableLighting();
      GlStateManager.blendFunc(1, 1);
      this.field_177216_c.setLivingAnimations(p_177214_1_, p_177214_2_, p_177214_3_, p_177214_4_);
      this.field_177216_c.setModelAttributes(this.field_177215_b.getMainModel());
      this.field_177216_c.render(p_177214_1_, p_177214_2_, p_177214_3_, p_177214_5_, p_177214_6_, p_177214_7_, p_177214_8_);
      GlStateManager.matrixMode(5890);
      GlStateManager.loadIdentity();
      GlStateManager.matrixMode(5888);
      GlStateManager.enableLighting();
      GlStateManager.disableBlend();
  }
  
  public boolean shouldCombineTextures()
  {
    return true;
  }
  
  public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_)
  {
    func_177214_a((EntityHurricaneWither)p_177141_1_, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
  }
}