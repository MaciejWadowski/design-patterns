package agh.dp.models;

import javax.persistence.*;

@Entity
public class User {
    @Column(name ="USERNAME")
    private String userName;
    @Column(name = "ROLEID")
    private Long roleId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public User() {}

    public User(String userName, long roleId){
        this.userName = userName;
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
    }
}
