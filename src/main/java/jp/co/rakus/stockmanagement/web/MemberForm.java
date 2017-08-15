package jp.co.rakus.stockmanagement.web;

import org.hibernate.validator.constraints.NotBlank;

/**
 * メンバー関連のリクエストパラメータが入るフォーム.
 * @author igamasayuki
 *
 */
public class MemberForm {
	/** 名前 */
	@NotBlank(message = "値を入力してください")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "値を入力してください")
	private String mailAddress;
	/** 重複チェックアドレス*/
	private String duplicate_address;
	/** パスワード */
	@NotBlank(message = "値を入力してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "値を入力してください")
	private String check_password;
	
	public String getName() {
		return name;
	}
	public String getCheck_password() {
		return check_password;
	}
	public void setCheck_password(String check_password) {
		this.check_password = check_password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDuplicate_address() {
		return duplicate_address;
	}
	public void setDuplicate_address(String duplicate_address) {
		this.duplicate_address = duplicate_address;
	}
}
