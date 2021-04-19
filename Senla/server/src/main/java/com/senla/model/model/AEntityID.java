package com.senla.model.model;


import java.io.Serializable;
import java.util.UUID;


public abstract class AEntityID implements Serializable {

    private  UUID uuid;

    public AEntityID(){
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid(){
        return this.uuid;
    }

    public void setUuid(UUID id){
        this.uuid = id;
    }
}
