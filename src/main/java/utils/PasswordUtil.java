package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    private static final int HASH_ROUNDS = 12;

    public static String hash(String plainText) {
        return BCrypt.hashpw(plainText, BCrypt.gensalt(HASH_ROUNDS));
    }

    public static boolean verify(String plainText, String hashed) {
        try {
            return BCrypt.checkpw(plainText, hashed);
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid hash format: " + e.getMessage());
            return false;
        }
    }
}
