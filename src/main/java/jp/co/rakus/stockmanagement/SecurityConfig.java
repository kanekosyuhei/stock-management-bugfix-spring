package jp.co.rakus.stockmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security設定クラス.
 */
@Configuration
@EnableWebMvcSecurity   // Spring Securityの基本設定
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService LoginUserDetailsService;
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        // セキュリティ設定を無視するリクエスト設定
        // 静的リソース(images、css、javascript)に対するアクセスはセキュリティ設定を無視する
        web.ignoring().antMatchers(
                            "/images/**",
                            "/css/**",
                            "/javascript/**",
                            "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 認可の設定
        http.authorizeRequests()
            .antMatchers("/", "/member/form","/member/create").permitAll() // indexは全ユーザーアクセス許可
            .anyRequest().authenticated();  // それ以外は全て認証無しの場合アクセス不許可

        // ログイン設定
        http.formLogin()
            .loginProcessingUrl("/login")   // 認証処理のパス
            .loginPage("/")            // ログインフォームのパス
            .failureUrl("/?error=true")
            .defaultSuccessUrl("/book/list",false)    // 認証成功時の遷移先
            .usernameParameter("mailAddress").passwordParameter("password")  // メールアドレス、パスワードのパラメータ名
            .and();

        // ログアウト設定
        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))       // ログアウト処理のパス
            .logoutSuccessUrl("/")// ログアウト完了時のパス
        	.deleteCookies("JSESSIONID")
        	.invalidateHttpSession(true);
    }
    
	/**
	 * 「認証」に関する設定.<br>
	 * 認証ユーザを取得する「UserDetailsService」の設定や<br>
	 * パスワード照合時に使う「PasswordEncoder」の設定
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(LoginUserDetailsService)
			.passwordEncoder(new StandardPasswordEncoder());
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
    		return new StandardPasswordEncoder();
    }
}