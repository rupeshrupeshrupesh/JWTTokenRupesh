//package com.eazybyte.springsecuritybasic.filter;
//
//import jakarta.servlet.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.io.IOException;
//import java.util.logging.Logger;
//
//public class AuthoritiesLoggoingAfterFilet implements Filter {
//
//
//    private final Logger LOG=Logger.getLogger(AuthoritiesLoggoingAfterFilet.class.getName());
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        if(null!=authentication)
//        {
//            LOG.info("User : "+authentication.getName()+" is successfully authicated and "+"has the authrities "+authentication.getAuthorities().toString());
//        }
//        chain.doFilter(request,response);
//
//    }
//}
