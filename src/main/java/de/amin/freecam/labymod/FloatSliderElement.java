package de.amin.freecam.labymod;

import net.labymod.main.LabyMod;
import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.DrawUtils;
import net.labymod.utils.ModColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class FloatSliderElement extends ControlElement {

    public static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
    private Float currentValue;
    private final Consumer<Float> changeListener;
    private Consumer<Float> callback;
    private float minValue;
    private float maxValue;
    private boolean dragging;
    private boolean hover;
    private float dragValue;

    public FloatSliderElement(String displayName, ControlElement.IconData iconData, float currentValue) {
        super(displayName, null, iconData);
        this.minValue = 0;
        this.maxValue = 10;
        this.currentValue = currentValue;
        this.changeListener = accepted -> {
            if (callback != null) {
                callback.accept(accepted);
            }

        };
    }

    public FloatSliderElement setMinValue(float minValue) {
        this.minValue = minValue;
        if (this.currentValue < this.minValue) {
            this.currentValue = this.minValue;
        }

        return this;
    }

    public FloatSliderElement setMaxValue(float maxValue) {
        this.maxValue = maxValue;
        if (this.currentValue > this.maxValue) {
            this.currentValue = this.maxValue;
        }
        return this;
    }

    public FloatSliderElement setRange(float min, float max) {
        this.setMinValue(min);
        this.setMaxValue(max);
        return this;
    }

    public void draw(int x, int y, int maxX, int maxY, int mouseX, int mouseY) {
        super.draw(x, y, maxX, maxY, mouseX, mouseY);
        DrawUtils draw = LabyMod.getInstance().getDrawUtils();
        int width = this.getObjectWidth();
        if (this.displayName != null) {
            draw.drawRectangle(x - 1, y, x, maxY, ModColor.toRGB(120, 120, 120, 120));
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(buttonTextures);
        GlStateManager.color(1.0F, 1.0F, 1.0F);
        double sliderWidth = width - 8;
        double minSliderPos = (double) maxX - (double) width;
        double totalValueDiff = this.maxValue - this.minValue;
        double currentValue = (double) this.currentValue;
        double pos = minSliderPos + sliderWidth / totalValueDiff * (currentValue - (double) this.minValue);
        draw.drawTexturedModalRect(minSliderPos, y + 1, 0.0, 46.0, (double) width / 2.0, 20.0);
        draw.drawTexturedModalRect(minSliderPos + (double) width / 2.0, y + 1, 200.0 - (double) width / 2.0, 46.0, (double) width / 2.0, 20.0);
        this.hover = mouseX > x && mouseX < maxX && mouseY > y + 1 && mouseY < maxY;
        draw.drawTexturedModalRect(pos, y + 1, 0.0, 66.0, 4.0, 20.0);
        draw.drawTexturedModalRect(pos + 4.0, y + 1, 196.0, 66.0, 4.0, 20.0);
        if (!this.isMouseOver()) {
            this.mouseRelease(mouseX, mouseY, 0);
        } else {
            double mouseToMinSlider = (double) mouseX - minSliderPos;
            float finalValue = (float) (this.minValue + totalValueDiff / sliderWidth * (mouseToMinSlider - 1.0));
            if (this.dragging) {
                this.dragValue = finalValue;
                this.mouseClickMove(mouseX, mouseY, 0);
            }
        }

        draw.drawCenteredString("" + this.currentValue, minSliderPos + (double) width / 2.0, y + 7);
    }

    public void unfocus(int mouseX, int mouseY, int mouseButton) {
        super.unfocus(mouseX, mouseY, mouseButton);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.hover) {
            this.dragging = true;
        }

    }

    public void mouseRelease(int mouseX, int mouseY, int mouseButton) {
        super.mouseRelease(mouseX, mouseY, mouseButton);
        if (this.dragging) {
            this.dragging = false;
            this.currentValue =  (float) ((double) Math.round(dragValue * 100) / 100.0);
            if (this.currentValue > this.maxValue) {
                this.currentValue = this.maxValue;
            }

            if (this.currentValue < this.minValue) {
                this.currentValue = this.minValue;
            }

            this.changeListener.accept(this.currentValue);
        }

    }

    public void mouseClickMove(int mouseX, int mouseY, int mouseButton) {
        super.mouseClickMove(mouseX, mouseY, mouseButton);
        if (this.dragging) {
            this.currentValue =  (float) ((double) Math.round(dragValue * 100) / 100.0);
            if (this.currentValue > this.maxValue) {
                this.currentValue = this.maxValue;
            }

            if (this.currentValue < this.minValue) {
                this.currentValue = this.minValue;
            }

            this.changeListener.accept(this.currentValue);
        }

    }

    public FloatSliderElement addCallback(Consumer<Float> callback) {
        this.callback = callback;
        return this;
    }

    public int getObjectWidth() {
        return 50;
    }

    public void setCurrentValue(Float currentValue) {
        this.currentValue = currentValue;
    }

}
