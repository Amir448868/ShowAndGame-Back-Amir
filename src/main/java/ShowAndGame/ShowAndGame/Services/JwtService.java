package ShowAndGame.ShowAndGame.Services;

import ShowAndGame.ShowAndGame.Persistence.Entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;
    private SecretKey signInKey;

    @PostConstruct
    public void initSignInKey() {
        this.signInKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public String GenerateToken(User user, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (EXPIRATION_MINUTES*60*1000));

        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(signInKey, Jwts.SIG.HS256)
                .compact();
    }

    public String ExtractUsername(String jwt) {
        return ExtractAllClaims(jwt).getSubject();
    }

    private Claims ExtractAllClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(signInKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    //Getting String claims (this one is used to get the User role)
    public String ExtractClaim(String token, String claimName) {
        return ExtractAllClaims(token).get(claimName, String.class);
    }

    //Getting Integer claims (this one is used to get the User id)
    public Integer ExtractIdClaim(String token, String claimName) {
        return ExtractAllClaims(token).get(claimName, Integer.class);
    }

    public boolean isTokenValid(String token, User user) {
        String usernameFromToken = ExtractUsername(token);

        return (usernameFromToken.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = ExtractAllClaims(token).getExpiration();

        return expiration.before(new Date());
    }
}
