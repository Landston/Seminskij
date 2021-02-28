package com.Models.Models;

import java.util.UUID;

public abstract class AEntityID {

    private  UUID uuid;

    public AEntityID(){
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid(){
        return this.uuid;
    }
}
