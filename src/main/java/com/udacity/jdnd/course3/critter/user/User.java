package com.udacity.jdnd.course3.critter.user;

import org.hibernate.annotations.Nationalized;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Nationalized
    protected String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
