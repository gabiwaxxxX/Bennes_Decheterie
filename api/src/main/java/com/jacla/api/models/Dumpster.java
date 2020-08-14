package com.jacla.api.models;

public class Dumpster {
    private String name;
    private int filling;
    private boolean connection;
    private String ip;

    public Dumpster(String iName, int iFilling, boolean iConnection, String iIp) {
        this.name = iName;
        this.filling = iFilling;
        this.connection = iConnection;
        this.ip = iIp;
    }

    public int getFilling() { return this.filling; }

    public String getName() { return this.name; }

    public boolean isConnection() { return this.connection; }

    public String getIp(){ return this.ip; }

    public void setName(String name){ this.name = name; }

    public void setIp(String ip) { this.ip = ip; }

    public void setConnection(boolean connection) { this.connection = connection; }

    public void setFilling(int filling) { this.filling = filling; }

    @Override
    public String toString() {
        return "Dumpster{" +
                "name='" + name + '\'' +
                ", filling=" + filling +
                ", connection=" + connection +
                ", ip='" + ip + '\'' +
                '}';
    }
}
