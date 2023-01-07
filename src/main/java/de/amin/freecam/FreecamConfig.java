package de.amin.freecam;

public class FreecamConfig {

    private boolean noClip;
    private boolean showHand;
    private float flySpeed;

    public FreecamConfig() {
        this.noClip = true;
        this.showHand = false;
        this.flySpeed = 0.05f;
    }

    public boolean isNoClip() {
        return noClip;
    }

    public void setNoClip(boolean noClip) {
        this.noClip = noClip;
    }

    public boolean isShowHand() {
        return showHand;
    }

    public void setShowHand(boolean showHand) {
        this.showHand = showHand;
    }

    public float getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(float flySpeed) {
        this.flySpeed = flySpeed;
    }
}
