package com.eazybyte.springsecuritybasic.config;

import com.eazybyte.springsecuritybasic.Model.Authority;
import com.eazybyte.springsecuritybasic.Model.Customer;
import com.eazybyte.springsecuritybasic.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class EazyBankUsernamePwdAuthenticationProvide implements AuthenticationProvider {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

       String username= authentication.getName();
       String pwd=authentication.getCredentials().toString();
        List<Customer> listc=customerRepository.findByEmail(username);

        if(listc.size()>0)
        {
            if (passwordEncoder.matches(pwd,listc.get(0).getPwd()))
            {
                List<GrantedAuthority> authorities=new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(listc.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username,pwd,authorities);
            }
            else {
                throw new BadCredentialsException("Invalid password");
            }
        }
        else {
            throw new BadCredentialsException("no user details with the details");
        }

    }


    private List<GrantedAuthority> getGrantedAuthority(Set<Authority> authorities ){
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for (Authority aut:authorities
             ) {

            grantedAuthorities.add(new SimpleGrantedAuthority(aut.getName()));

        }

        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
