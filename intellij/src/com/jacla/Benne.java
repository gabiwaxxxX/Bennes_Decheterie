package com.jacla;

public class Benne {
    private final String name;
    private final int filling;
    private final boolean connection;

    public Benne(String iname, int ifilling, boolean iconnection) {
        this.name = iname;
        this.filling = ifilling;
        this.connection = iconnection;
    }

    public int getFilling() {
        return filling;
    }

    public String getName() {
        return name;
    }

    public boolean getConnection(){
        return connection;
    }
}
