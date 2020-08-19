package com.jacla.api.models;

import com.jacla.api.repositories.DumpsterRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dumpster {

   // private @Id @GeneratedValue long id;
    private String name;
    private int filling;
    private boolean connection;
    private String ip;

    public Dumpster(String iName, int iFilling, boolean iConnection, String iIp) {

        this.filling = iFilling;
        this.connection = iConnection;
        this.ip = iIp;
        if(iName != "") this.name = iName;
        else {
            DumpsterRepository dr = new DumpsterRepository();
            this.name="Benne \u2116 "+ dr.getDumpsters().size();
        }
    }

    public int getFilling() { return this.filling; }

    public String getName() { return this.name; }

    public boolean getConnection() { return this.connection; }

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
