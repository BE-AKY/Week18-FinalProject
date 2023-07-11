package orchid.society.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import orchid.society.entity.Member;


public interface MemberDao extends JpaRepository<Member, Long> {


}
