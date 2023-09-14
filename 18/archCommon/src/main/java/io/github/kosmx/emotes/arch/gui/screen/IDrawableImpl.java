package io.github.kosmx.emotes.arch.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.kosmx.emotes.arch.executor.types.IdentifierImpl;
import io.github.kosmx.emotes.arch.executor.types.TextImpl;
import io.github.kosmx.emotes.executor.dataTypes.IIdentifier;
import io.github.kosmx.emotes.executor.dataTypes.Text;
import io.github.kosmx.emotes.main.screen.IRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;

public interface IDrawableImpl extends IRenderHelper<PoseStack> {
    @Override
    default void renderSystemBlendColor(float r, float g, float b, float a){
        RenderSystem.setShaderColor(r, g, b, a);
    }

    @Override
    default void drawableHelperFill(PoseStack matrices, int x1, int y1, int x2, int y2, int color){
        GuiComponent.fill(matrices, x1, y1, x2, y2, color);
    }

    @Override
    default void textDrawWithShadow(PoseStack matrices, Text text, float x, float y, int color){
        Minecraft.getInstance().font.drawShadow(matrices, ((TextImpl)text).get(), x, y, color);
    }

    @Override
    default void textDraw(PoseStack matrices, Text text, float x, float y, int color){
        Minecraft.getInstance().font.draw(matrices, ((TextImpl)text).get(), x, y, color);
    }

    @Override
    default int textRendererGetWidth(Text text){
        return Minecraft.getInstance().font.width(((TextImpl)text).get());
    }

    @Override
    default void renderBindTexture(IIdentifier texture){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, ((IdentifierImpl)texture).get());
    }

    @Override
    default void renderEnableBend(){
        RenderSystem.enableBlend();
    }

    @Override
    default void renderDisableBend(){
        RenderSystem.disableBlend();
    }

    @Override
    default void renderDefaultBendFunction(){
        RenderSystem.defaultBlendFunc();
    }

    @Override
    default void renderEnableDepthText(){
        RenderSystem.enableDepthTest();
    }

    @Override
    default void drawableDrawTexture(PoseStack matrices, IIdentifier texture, int x, int y, int width, int height, float u, float v, int regionWidth, int regionHeight, int textureWidth, int textureHeight){
        renderBindTexture(texture);
        GuiComponent.blit(matrices, x, y, width, height, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
    }

    @Override
    default void drawCenteredText(PoseStack matrices, Text text, int centerX, int y, int color){
        GuiComponent.drawCenteredString(matrices, Minecraft.getInstance().font, ((TextImpl)text).get(), centerX, y, color);
    }
}
