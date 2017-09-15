package uk.co.jamesrossiter.spring.boot.saml.builder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class JwtBuilder {

    private Map<String, Object> claims = new HashMap<>();
    private String subject;

    public String build() {
        // end of day is used for signature and expiration
        long endOfDay = ZonedDateTime.now().with(LocalTime.MAX).toInstant().toEpochMilli();
        String endOfDayString = Long.toString(endOfDay);

        // add subject as "sub" to claims to match JWT standard
        claims.put("sub", subject);

        // build string
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, endOfDayString)
                .setHeaderParam("typ", "JWT")
                .setExpiration(new Date(endOfDay))
                .compact();
    }

    public JwtBuilder withClaims(final Map<String, Object> claims) {
        for (String key : claims.keySet()) {
            this.claims.put(key, claims.get(key));
        }
        return this;
    }

    public JwtBuilder withSubject(final String subject) {
        this.subject = subject;
        return this;
    }
}
