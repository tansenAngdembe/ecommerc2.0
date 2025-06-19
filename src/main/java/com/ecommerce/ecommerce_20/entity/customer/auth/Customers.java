package com.ecommerce.ecommerce_20.entity.customer.auth;

import com.ecommerce.ecommerce_20.entity.customer.Address;
import com.ecommerce.ecommerce_20.entity.customer.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name ="customers")
public class Customers implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name",length = 20,nullable = false)
    private String lastName;

    @Column(name="email", nullable = false, length = 50,unique=true)
    private String email;

    @Column(name = "mobile_number",nullable = false,length = 20,unique = true)
    private String mobileNumber;

    @Column(name="is_active",nullable = false)
    private Boolean isActive;

    @Column(name= "password", nullable = false, length = 100)
    private String password;

    @Column(name="unique_id",nullable = false,unique=true )
    private String uniqueId;

    @OneToMany(mappedBy ="customer",cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Address> addresses = new ArrayList<>();


    @OneToMany(mappedBy = "customers" , cascade = CascadeType.ALL, orphanRemoval = true )
    private List<Cart> carts = new ArrayList<>();

    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="updated_at")
    private Instant updatedAt;

    @Column(name="password_change_at")
    private Instant passwordChangeAt;

    @Column(name = "last_logged_in_at")
    private Instant lastLoggedInAt;

    @Column(name ="wrong_password_attempt_count")
    private Integer wrongPasswordAttemptCount;

    @Column(name="is_account_locked")
    private Boolean isAccountLocked;

    @Column(name="locked_Time")
    private Instant lockedTime;

    @Column(name ="profile_picture_url",length = 255)
    private String profilePictureUrl;



    @PrePersist
    protected void generateUniqueId() {
        this.uniqueId = UUID.randomUUID().toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

}
