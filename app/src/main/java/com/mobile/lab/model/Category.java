package com.mobile.lab.model;

import java.io.Serializable;

/**
 * Category Model for grouping tasks in Android UI.
 */
public class Category implements Serializable {
    private String name;
    private String colorHex;
    private int iconResId;

    public Category(String name, String colorHex, int iconResId) {
        this.name = name;
        this.colorHex = colorHex;
        this.iconResId = iconResId;
    }

    public String getName() { return name; }
    public String getColorHex() { return colorHex; }
    public int getIconResId() { return iconResId; }
}
