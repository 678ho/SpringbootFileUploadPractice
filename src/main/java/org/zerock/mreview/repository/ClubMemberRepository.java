package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mreview.entity.ClubMember;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {
}
