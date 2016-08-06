package com.dk.dento.care.service;


import com.dk.dento.care.model.UserCredentials;
import com.dk.dento.care.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class PatientRepositoryUserDetailsService implements UserDetailsService {
    private final UserCredentialsService userCredentialsService;

    public static final String ROLE_DENTOCARE_ADMIN = "DENTOCARE_ADMIN";
    public static final String ROLE_CLINIC_ADMIN = "CLINIC_ADMIN";
    public static final String ROLE_CLINIC_USER = "CLINIC_USER";
    public static final String ROLE_PATIENT = "PATIENT";

    @Autowired
    public PatientRepositoryUserDetailsService(UserCredentialsService userCredentialsService) {
        this.userCredentialsService = userCredentialsService;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserCredentials userCredentials = userCredentialsService.getPrincipal(username);
        if(userCredentials == null) {
            throw new UsernameNotFoundException("Could not find user " + username);
        }
        return new CustomDoctorDetails(userCredentials);
    }

    private final static class CustomDoctorDetails extends UserCredentials implements UserDetails {

        private Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        private CustomDoctorDetails(UserCredentials userCredentials) {
            super(userCredentials);
            this.authorities.add(new SimpleGrantedAuthority(role(userCredentials.getRole())));
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.authorities;
        }

        public String getUsername() {
            return getLoginId();
        }

        public boolean isAccountNonExpired() {
            return true;
        }

        public boolean isAccountNonLocked() {
            return true;
        }

        public boolean isCredentialsNonExpired() {
            return true;
        }

        public boolean isEnabled() {
            return true;
        }

        private String role(String i) {
            return "ROLE_" + i;
        }

        private static final long serialVersionUID = 5639683223516504866L;
    }
}
