package com.jacla.api.models;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Dumpster implements Serializable {

    private static final long serialVersionUID = -1546689101108179607L;
    private String name;
    private int filling;
    private boolean connection;
    private String ip;
    private String id;
    private boolean emptyInitialisation;
    private boolean fullInitialisation;

    public Dumpster(String id, String name, int filling, boolean connection, String ip, boolean emptyInitialisation, boolean fullInitialisation) {
        this.id = id;
        this.filling = filling;
        this.connection = connection;
        this.ip = ip;
        this.name = name;
        this.emptyInitialisation = emptyInitialisation;
        this.fullInitialisation = fullInitialisation;
    }

    public String getName() {
        return name;
    }

    public int getFilling() {
        return filling;
    }

    public String getIp() {
        return ip;
    }

    public String getId() {
        return id;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    public void setFilling(int filling) {
        this.filling = filling;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dumpster{" +
                "name='" + name + '\'' +
                ", filling=" + filling +
                ", connection=" + connection +
                ", ip='" + ip + '\'' +
                '}';
    }

    public boolean isEmptyInitialisation() {
        return emptyInitialisation;
    }

    public void setEmptyInitialisation(boolean emptyInitialisation) {
        this.emptyInitialisation = emptyInitialisation;
    }

    public boolean isFullInitialisation() {
        return fullInitialisation;
    }

    public void setFullInitialisation(boolean fullInitialisation) {
        this.fullInitialisation = fullInitialisation;
    }
}
