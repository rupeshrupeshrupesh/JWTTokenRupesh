//package com.eazybyte.springsecuritybasic.config;
//
//import com.eazybyte.springsecuritybasic.Model.Customer;
//import com.eazybyte.springsecuritybasic.Repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class EazybankDetails implements UserDetailsService {
//
//
//    @Autowired
//    CustomerRepository cusetomerRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        String usename, password = null;
//        List<GrantedAuthority> authorities = null;
//        List<Customer> customers = cusetomerRepository.findByEmail(username);
//        if (customers.size() == 0) {
//            throw new UsernameNotFoundException("users detials not found foe the user " + username);
//
//        } else {
//            username = customers.get(0).getEmail();
//            password = customers.get(0).getPwd();
//            authorities = new ArrayList<>();
//            authorities.add(new SimpleGrantedAuthority(customers.get(0).getRole()));
//
//
//        }
//        return new User(username, password, authorities);
//    }
//}
