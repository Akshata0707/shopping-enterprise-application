package com.apiGateway.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {

	    // Secret key (should be at least 256 bits for HS256)
	    private static final String SECRET = "mysecretkeymysecretkeymysecretkey123";

	    // Token validity (30 minutes)
	    private static final long EXPIRATION_TIME = 1000 * 60 * 30;

	    private Key getSigningKey() {
	        return Keys.hmacShaKeyFor(SECRET.getBytes());
	    }

	    /* ==============================
	       GENERATE JWT TOKEN
	       ============================== 
	    public String generateToken(String username, Map<String, Object> claims) {

	        return Jwts.builder()
	                .setClaims(claims) // Custom claims (roles, etc.)
	                .setSubject(username) // Username
	                .setIssuedAt(new Date()) // Token creation time
	                .setExpiration(new Date(
	                        System.currentTimeMillis() + EXPIRATION_TIME))
	                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
	                .compact();
	    }

	    ==============================
	       VALIDATE JWT TOKEN
	       ============================== */
	    public boolean validateToken(String token) {
	        try {
	            Jwts.parserBuilder()
	                    .setSigningKey(getSigningKey())
	                    .build()
	                    .parseClaimsJws(token);

	            return true; // Token is valid

	        } catch (ExpiredJwtException e) {
	            System.out.println("JWT expired");
	        } catch (UnsupportedJwtException e) {
	            System.out.println("JWT unsupported");
	        } catch (MalformedJwtException e) {
	            System.out.println("JWT malformed");
	        } catch (SignatureException e) {
	            System.out.println("Invalid signature");
	        } catch (IllegalArgumentException e) {
	            System.out.println("JWT claims string is empty");
	        }
	        return false;
	    }

	    /* ==============================
	       EXTRACT USERNAME
	       ============================== */
	    public String extractUsername(String token) {
	        return extractAllClaims(token).getSubject();
	    }

	    /* ==============================
	       EXTRACT CLAIMS
	       ============================== */
	    public Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getSigningKey())
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    /* ==============================
	       CHECK TOKEN EXPIRATION
	       ============================== */
	    public boolean isTokenExpired(String token) {
	        return extractAllClaims(token)
	                .getExpiration()
	                .before(new Date());
	    }
	
	
}
