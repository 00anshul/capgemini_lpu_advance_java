package com.library.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.entity.Member;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // CREATE
    @Transactional
    public Member addMember(Member member) {
        // set defaults
        member.setMemberSince(LocalDate.now());
        member.setStatus("ACTIVE");
        return memberRepository.save(member);
    }

    // READ by ID
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Member not found with id: " + id));
    }

    // READ all
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // UPDATE
    @Transactional
    public Member updateMember(Long id, Member updatedMember) {
        Member existing = getMemberById(id);
        existing.setName(updatedMember.getName());
        existing.setEmail(updatedMember.getEmail());
        existing.setPhone(updatedMember.getPhone());
        return memberRepository.save(existing);
    }

    // BLOCK member — soft disable
    @Transactional
    public Member blockMember(Long id) {
        Member existing = getMemberById(id);
        existing.setStatus("BLOCKED");
        return memberRepository.save(existing);
    }

    // DELETE
    @Transactional
    public void deleteMember(Long id) {
        getMemberById(id);
        memberRepository.deleteById(id);
    }
}