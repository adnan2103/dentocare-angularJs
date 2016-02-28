package com.dk.dento.care.security;


import com.dk.dento.care.model.UserCredentials;
import com.dk.dento.care.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PatientRepositoryUserDetailsService implements UserDetailsService {
    private final UserCredentialsService userCredentialsService;

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

        private CustomDoctorDetails(UserCredentials userCredentials) {
            super(userCredentials);
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return AuthorityUtils.createAuthorityList("ROLE_USER");
        }

        public String getUsername() {
            return getEmailId();
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

        public String getPassword() {
            return getPassword();
        }

        private static final long serialVersionUID = 5639683223516504866L;
    }
}
