package com.senla.model;

import javax.persistence.Enumerated;
import java.io.Serializable;


public enum BookStatus implements Serializable {   // FIRST ADD VALUES THAT ARE MORE IMPORTANT FOR SORTING

    RESERVED,
    ABSENT


}
