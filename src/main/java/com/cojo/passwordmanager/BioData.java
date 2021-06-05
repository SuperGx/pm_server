package com.cojo.passwordmanager;

import javax.persistence.*;

@Entity
public class BioData {

    @OneToOne(targetEntity = UserData.class, cascade= CascadeType.ALL)
    @JoinColumn(name = "email")
    private UserData userData;

    @Id
    @GeneratedValue
    private Long id;
    private String biomail;
    private String bioKey;

    public BioData() {
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBiomail() {
        return biomail;
    }

    public void setBiomail(String biomail) {
        this.biomail = biomail;
    }

    public String getBioKey() {
        return bioKey;
    }

    public void setBioKey(String bioKey) {
        this.bioKey = bioKey;
    }
}
