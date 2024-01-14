package com.workintech.ecommerce.entity.User;

import com.workintech.ecommerce.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user",schema = "ecommerce")
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "name must be included in user information")
    @Column(name = "full_name")
    private String fullName;

    @Email
    @NotNull(message = "email must be included in user information")
    @Column(name = "email",unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;


    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(name="user_role",schema = "ecommerce",
          joinColumns =@JoinColumn(name = "user_id"),
           inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authority= new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            return authority;
        }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
