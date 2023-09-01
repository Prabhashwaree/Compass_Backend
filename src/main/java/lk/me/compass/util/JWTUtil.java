package lk.me.compass.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
    @Value("${security.jwt.expire}")
	private long jwtExpire;
	@Value("${security.jwt.secret}")
	private String SecretKey;

    public String generateJwtToken(String username) {
		
		return Jwts.builder()
		.setSubject(username)
		.setIssuedAt(new Date())
		.setExpiration(new Date((new Date()).getTime() + jwtExpire))
		.signWith(SignatureAlgorithm.HS512, SecretKey)
		.compact();
		
	}
}
