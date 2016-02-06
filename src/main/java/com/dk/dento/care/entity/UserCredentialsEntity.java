package com.dk.dento.care.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_credentials")
public class UserCredentialsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    /** The main email address for the user */
    @Embedded
    private EmailAddress emailAddress;

    @Column(name = "login_enabled")
    private boolean loginEnable;

    private String password;

    @OneToOne
    @JoinColumn(name="role_id")
    private RoleEntity roleEntity;


    @OneToOne(fetch = FetchType.LAZY,cascade= CascadeType.ALL)
    @JoinColumn(name="user_id")
    private UserDetailEntity userDetailEntity;

    public UserDetailEntity getUserDetailEntity() {
        return userDetailEntity;
    }

    public void setUserDetailEntity(UserDetailEntity userDetailEntity) {
        this.userDetailEntity = userDetailEntity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginEnable() {
        return loginEnable;
    }

    public void setLoginEnable(boolean loginEnable) {
        this.loginEnable = loginEnable;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }
}
