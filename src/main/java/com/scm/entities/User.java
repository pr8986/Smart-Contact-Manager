package com.scm.entities;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user")  //This annotation create table of this class in database
@Table(name="users")  //This annotation is used to specify the name of the table in the database
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    //Field of the class change into column of the table in database

    @Id
    private String userId; 
    @Column(name ="user_name", nullable = false)
    
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    
    @Column(length = 1000 )
    private String about;
    
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;
    //information
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    @Enumerated(value = EnumType.STRING)
    // SELF, GOOGLE, FACEBOOK, GITHUB, TWITTER, LINKEDIN
    private Providers provider=Providers.SELF;
    private String providerUserId;

    //add more fields if needed
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)  //This annotation is used to specify the relationship between two entities. It is used to specify the field in the child entity that is used to map the relationship.
    private List<Contact> contacts=new ArrayList<>();

    
}
