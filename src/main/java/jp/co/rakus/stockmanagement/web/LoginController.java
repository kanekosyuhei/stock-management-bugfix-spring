package jp.co.rakus.stockmanagement.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.rakus.stockmanagement.domain.Member;
import jp.co.rakus.stockmanagement.service.MemberService;

/**
 * ログイン関連処理を行うコントローラー.
 * @author kanekoshuhei
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {
			
//	@Autowired
//	private HttpSession session;
//	
//	@Autowired
//	private MemberService memberService;
		
	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public LoginForm setUpForm() {
		return new LoginForm();
	}

	/**
	 * ログイン画面を表示します.
	 * @return ログイン画面
	 */
	@RequestMapping
	public String index(LoginForm form,BindingResult result,String error,Model model) {
		
		if(error != null){
			System.err.println("member: login failed");
			result.addError(new ObjectError("error","メールアドレスまたはパスワードが違います。"));
		}
		
		return "loginForm";
	}
	
	
	
//	/**
//	 * ログイン処理を行います.
//	 * @param form　フォーム
//	 * @param result　リザルト
//	 * @param model　モデル
//	 * @return　ログイン成功時：書籍リスト画面
//	 */
//	@RequestMapping(value = "/login")
//	public String login(@Validated LoginForm form,
//			BindingResult result) {
//		if (result.hasErrors()){
//			return index(true,null);
//		}
//		String mailAddress = form.getMailAddress();
//		String password = form.getPassword();
//		
//		Member member = memberService.findOneByMailAddressAndPassword(mailAddress,password);
//		
//		if (member == null) {
//			ObjectError error = new ObjectError("loginerror", "メールアドレスまたはパスワードが違います。");
//            result.addError(error);
//			return index(true,null);
//		}
//		session.setAttribute("member", member);
//		return "redirect:/book/list";
//	}
}
