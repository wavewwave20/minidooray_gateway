package com.nhnacademy.minidooray_gateway.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final HttpSession httpSession;

    private final AccountService accountService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        accountService.login()
    }

//    private final MemberRepository memberRepository;
//
//    public CustomUserDetailsService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findById(username)
//                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
//
//        return new User(member.getId(), member.getPwd(),
//                Collections.singletonList(new SimpleGrantedAuthority(member.getAuthority().getAuthority())));
//    }
}