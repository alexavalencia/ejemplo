package com.alexandracastrillonvalencia.ejemplo;

/**
 * Created by Lenovo on 07/01/2016.
 */
public class list_menu {
    private String name;
    private int imageid;

    public list_menu(String name, int imageid) {
        this.name = name;
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public int getImageid() {
        return imageid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

}
