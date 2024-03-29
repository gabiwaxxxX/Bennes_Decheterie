package com.jacla.api.models;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@RedisHash
@Entity
public class Dumpster implements Serializable {

    private static final long serialVersionUID = -1546689101108179607L;
    private String name;
    private int filling;
    private boolean connected;
    @Id
    private String ip;
    private String id;
    private boolean emptyInitialised;
    private boolean fullInitialised;

    public Dumpster(){ }

    public Dumpster(String id, String name, int filling, boolean connected, String ip, boolean emptyInitialised, boolean fullInitialised) {
        this.id = id;
        this.filling = filling;
        this.connected = connected;
        this.ip = ip;
        this.name = name;
        this.emptyInitialised = emptyInitialised;
        this.fullInitialised = fullInitialised;
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

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
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

    public boolean isEmptyInitialised() {
        return emptyInitialised;
    }

    public void setEmptyInitialised(boolean emptyInitialised) {
        this.emptyInitialised = emptyInitialised;
    }

    public boolean isFullInitialised() {
        return fullInitialised;
    }

    public void setFullInitialised(boolean fullInitialised) {
        this.fullInitialised = fullInitialised;
    }

    @Override
    public String toString() {
        return "Dumpster{" +
                "name='" + name + '\'' +
                ", filling=" + filling +
                ", connected=" + connected +
                ", ip='" + ip + '\'' +
                ", id='" + id + '\'' +
                ", emptyInitialised=" + emptyInitialised +
                ", fullInitialised=" + fullInitialised +
                '}';
    }
}
