package ryerson.ca.frontend;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map.Entry;

public class Authenticate {

    private final Key key;
    private final SignatureAlgorithm signatureAlgorithm;

    public Authenticate() {
        // Ensure that the key is at least 256 bits
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        signatureAlgorithm = SignatureAlgorithm.HS256;
    }

    public String createJWT(String issuer, String subject, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(key, signatureAlgorithm);

        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        return builder.compact();
    }

    public Entry<Boolean, String> verify(String jwt) throws UnsupportedEncodingException {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);

            String username = jws.getBody().getSubject();
            return new AbstractMap.SimpleEntry<>(true, username);
        } catch (JwtException ex) {
            return new AbstractMap.SimpleEntry<>(false, "");
        }
    }
}
