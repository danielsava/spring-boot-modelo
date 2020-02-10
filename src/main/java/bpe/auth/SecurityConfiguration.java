package bpe.auth;

//@EnableWebSecurity
public class SecurityConfiguration {



    /* Exemplo retirado do tutorial: https://developer.okta.com/blog/2020/01/06/crud-angular-9-spring-boot-2

    public void configure(HttpSecurity http) throws Exception {

        // Jwt
        http
            .authorizeRequests().anyRequest().authenticated()
            .and()
            //.oauth2Client()   // The oauth2Login() configuration is not necessary for this example to work. It’s only needed if you want to require authentication from a browser.
            //.and()
            .oauth2ResourceServer().jwt();


        // HTTPS : Force HTTPS in production
        http
            .requiresChannel()
            .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null).requiresSecure();


        // CSRF Protection : Configure the CSRF Cookie so it can be read by JavaScript
        http
            .csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        // XSS Attacks Protection : Use a Content Security Policy (CSP) to Prevent XSS Attacks. Configure a CSP that only allows local scripts
        http
            .headers()
            .contentSecurityPolicy("script-src 'self'; report-to /csp-report-endpoint/");

    }
    */




}
