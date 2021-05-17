package com.senla.model;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private  UUID uuid;

    public AbstractEntity(){

    }

    public UUID getUuid(){
        return this.uuid;
    }

    public void setUuid(UUID id){
        this.uuid = id;
    }
}
