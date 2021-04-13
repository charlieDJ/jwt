package com.example.boot.config.security;

/**
 * @author dengjia
 * @date 2019/8/15 17:50
 */

import com.example.boot.common.util.JwtTokenUtil;
import com.example.boot.common.util.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT登录授权过滤器，可继承 BasicAuthenticationFilter
 * @author dj
 */
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        ResponseHelper.responseAllowHeader(response);
        try {
            String authHeader = request.getHeader(this.tokenHeader);
            if (authHeader == null || !authHeader.startsWith(this.tokenHead)) {
                // 有些请求需要放行，在这里不做验证。对于不放行的url，如果没有token，authentication那里是过不了的
                log.debug("auth请求头为空或请求头参数不正确，authHeader:{}", authHeader);
                return;
            }
            String authToken = authHeader.substring(this.tokenHead.length());
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            log.info("checking username:{}", username);
            if (username == null) {
                throw new RuntimeException("解析token中的用户名，解析失败");
            }
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (!jwtTokenUtil.validateToken(authToken, userDetails)) {
                throw new RuntimeException("验证失败");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            log.info("authenticated user:{}", username);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            chain.doFilter(request, response);
        }
    }
}
