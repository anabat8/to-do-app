package nl.tudelft.abatrineanu.ToDoList.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Verifies the JWT token in the request for validity.
 */

@Service
public class JwtService {

    private final String jwtSecret = "orJuR4s42yFwk2CwDU8kUnQi8TlrisAvpummyJbn8ww7YByDvseHjrmeng8AqSaR";

    /**
     * Validate the JWT token for expiration.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Extracts a username from a token.
     * @param token
     * @return The user's username if the token can be parsed correctly
     */
    public String extractUsername(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Verifies if the token is expired.
     * @param token
     * @return true if the token is expired, false otherwise
     */
    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new java.util.Date());
    }

    public java.util.Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
