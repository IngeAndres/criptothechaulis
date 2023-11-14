package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ing. Andres Gomez
 */
public class SHA256 {

    public static void main(String[] args) {
        String cadena = "gomez";
        String hashSHA256 = calcularSHA256(cadena);
        System.out.println("Hash SHA-256: " + hashSHA256);
        System.out.println(hashSHA256.equals("931f22c9283e090b805bb80726169a730cf80971d087fdc22d35a962fa40aa45"));
    }

    public static String calcularSHA256(String cadena) {
        try {
            // Crea un objeto MessageDigest para SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convierte la cadena a bytes y actualiza el objeto MessageDigest
            md.update(cadena.getBytes());

            // Calcula el hash SHA-256
            byte[] hashBytes = md.digest();

            // Convierte los bytes del hash a una representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            // Retorna el hash SHA-256 en formato hexadecimal
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
