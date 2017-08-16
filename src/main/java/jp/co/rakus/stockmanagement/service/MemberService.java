package jp.co.rakus.stockmanagement.service;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * メンバー関連サービスクラス.
 * @author kanekoshuhei
 *
 */
@Service
public class MemberService {

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberRepository memberRepository;
	
//	public List<Member> findAll(){
//		return memberRepository.findAll();
//	}
//	
//	public Member findOne(Integer id) {
//		return memberRepository.findOne(id);
//	}
	
	public Member findOneByMailAddressAndPassword(String mailAddress, String password){
		return memberRepository.findByMailAddressAndPassword(mailAddress, password);
	}
	
	public Member save(Member member){
		return memberRepository.save(member);
	}
	
//	public Member update(Member member){
//		return memberRepository.save(member);
//	}
//	
//	public void delete(Integer id){
//		memberRepository.delete(id);
//	}
	
	public String encode(String password){
		String encodePassword = passwordEncoder.encode(password);
		return encodePassword;
	}	
	
}
