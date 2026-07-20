package com.github.RecoverDev.j2docx.styles;

public abstract class Style<T extends Style<T>> {

    private String styleId;
    private String name;
    private T baseOn;
    private Boolean qFormat;
    private Integer uiPriority;

    // StyleId
    public T assignStyleId(String styleId) {
        if (this.styleId == null) {
            this.styleId = styleId;
        }
        return self();
    }

    public boolean hasStyleId() {
        return this.styleId != null;
    }

    public String getStyleId() {
        return this.styleId;
    }

    // Name
    public T name(String name) {
        this.name = name;
        return self();
    }

    public boolean hasName() {
        return this.name != null;
    }

    public String getName() {
        return this.name;
    }

    public T cleanName() {
        this.name = null;
        return self();
    }

    // BaseOn
    public T basedOn(T baseOn) {
        this.baseOn = baseOn;
        return self();
    }

    public boolean hasBasedOn() {
        return this.baseOn != null;
    }

    public T getBasedOn() {
        return this.baseOn;
    }

    public T clearBasedOn() {
        this.baseOn = null;
        return self();
    }

    // qFormat
    public T quickStyle() {
        this.qFormat = true;
        return self();
    }

    public T quickStyle(boolean value) {
        this.qFormat = value;
        return self();
    }

    public boolean hasQuickStyle() {
        return this.qFormat != null;
    }

    public boolean getQuickStyle() {
        return Boolean.TRUE.equals(this.qFormat);
    }

    public T clearQuickStyle() {
        this.qFormat = null;
        return self();
    }

    // uiPriority
    public T uiPriority(Integer value) {
        this.uiPriority = value;
        return self();
    }

    public boolean hasUiPriority() {
        return this.uiPriority != null;
    }

    public Integer getUiPriority() {
        return this.uiPriority;
    }

    protected abstract T self();

}
