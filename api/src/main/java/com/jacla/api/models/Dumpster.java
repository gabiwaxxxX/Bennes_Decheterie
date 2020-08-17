package com.jacla.api.models;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dumpster {

    private @Id @GeneratedValue long id;
    private String name;
    private int filling;
    private boolean connection;
    private String ip;

    public Dumpster( int iFilling, boolean iConnection, String iIp) {
        this.filling = iFilling;
        this.connection = iConnection;
        this.ip = iIp;
        this.name="Benne \u2116 "+this.id;
    }

    public int getFilling() { return this.filling; }

    public String getName() { return this.name; }

    public boolean isConnection() { return this.connection; }

    public String getIp(){ return this.ip; }

    public void setName(String name){ 

        if (name != null){
        this.name = name;
        }
        else{
            this.name="Benne \u2116 "+this.id;
        }
     }

    public void setIp(String ip) { this.ip = ip; }

    public void setConnection(boolean connection) { this.connection = connection; }

    public void setFilling(int filling) { this.filling = filling; }

    public long getId() {
		return this.id;
    }
    public void setId(long id) {
        this.id = id;
        setName(null);
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

    

}
