package jp.co.rakus.stockmanagement.web;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.service.MemberService;

/**
 * メンバー関連処理を行うコントローラー.
 * @author kanekoshuhei
 *
 */
@Controller
@RequestMapping("/member")
@Transactional
public class MemberController {

	
	@Autowired
	private MemberService memberService;
	
	/**
	 * メンバーフォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public MemberForm setUpMemberForm() {
		return new MemberForm();
	}
	
	/**
	 * メンバー情報登録画面を表示します.
	 * @return メンバー情報登録画面
	 */
	@RequestMapping(value = "form")
	public String form() {
		return "/member/form";
	}
	
	/**
	 * メンバー情報を登録します.
	 * @param form フォーム
	 * @param result リザルト
	 * @param model モデル
	 * @return ログイン画面
	 */
	@RequestMapping(value = "create")
	public String create(@Validated MemberForm form,BindingResult result, Model model) {
		
		if (result.hasErrors()){
			return "/member/form";
		}
		
		if (!(form.getCheckPassword().equals(form.getPassword()))){	
			result.rejectValue("password", null , "パスワードが確認用と異なります");
			return "/member/form";
		}

		try {
			
			Member member = new Member();
			form.setPassword(memberService.encode(form.getPassword()));
			BeanUtils.copyProperties(form, member);
			memberService.save(member);
			return "redirect:/";
			
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			result.rejectValue("mailAddress", null , "既に登録されているメールアドレスです");
			return "/member/form";
		}
	}
	
}
