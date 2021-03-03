package com.Models.Models;


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
}
