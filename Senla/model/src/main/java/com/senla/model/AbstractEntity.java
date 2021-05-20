package com.senla.model;


import jdk.jfr.Name;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.swing.*;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public interface AbstractEntity  {

    public  UUID getId();


}
