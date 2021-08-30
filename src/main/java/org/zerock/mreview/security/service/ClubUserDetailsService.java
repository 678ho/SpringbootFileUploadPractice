package org.zerock.mreview.security.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.mreview.entity.ClubMember;
import org.zerock.mreview.repository.ClubMemberRepository;
import org.zerock.mreview.security.dto.ClubAuthMemberDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService  implements UserDetailsService {

    private final ClubMemberRepository clubMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("ClubUserDetailsService loadUserByUsername " + username);


        Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);

        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check User Email or from Social ");
        }

        ClubMember clubMember = result.get();

        log.info("-----------------------------");
        log.info(clubMember);

        ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                clubMember.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toSet())
        );

        clubAuthMember.setName(clubMember.getName());


        return clubAuthMember;
    }
}
/*
변경사항

ClubMemberRepository를 받을 수 있는 구조로 변경하고 @RequiredArgsConstructor 처리.

username이 실제로 email을 의미하므로 이를 사용해서 findByEmail을 호출

사용자가 존재하지 않으면 예외처리

ClubMember를 UserDetail 타입으로 처리하기 위해서 ClubAuthMemberDTO타입으로 변환

ClubMemberRole은 SimpleGrantedAuthority 변환
 */