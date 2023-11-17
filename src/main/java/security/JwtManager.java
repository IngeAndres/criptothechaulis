package security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class JwtManager {

    // Autogenera una clave para firmar y verificar tokens
    private static final Key SECRET_KEY = generateSecretKey();

    // Tiempo de expiración del token en milisegundos (en este caso, 15 minutos)
    private static final int EXPIRATION_TIME = 900000;

    /**
     * Genera una clave secreta utilizando un par de claves asimétricas.
     *
     * @return Clave secreta generada.
     */
    private static Key generateSecretKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            return keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar la clave secreta", e);
        }
    }

    /**
     * Genera un token JWT con el nombre de usuario como sujeto.
     *
     * @param username Nombre de usuario para el cual se genera el token.
     * @return Token JWT generado.
     */
    public static String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.RS256, SECRET_KEY)
                .compact();
    }

    /**
     * Verifica un token JWT y devuelve el nombre de usuario si es válido.
     *
     * @param token Token JWT a verificar.
     * @return Nombre de usuario extraído del token, o null si el token no es válido.
     */
    public static String verifyToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
