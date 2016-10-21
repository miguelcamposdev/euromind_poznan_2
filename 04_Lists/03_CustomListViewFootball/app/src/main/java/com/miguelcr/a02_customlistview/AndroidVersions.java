package com.miguelcr.a02_customlistview;

/**
 * Created by miguelcampos on 21/10/16.
 */
public class AndroidVersions {
    private String name;
    private int icon;

    public AndroidVersions(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
