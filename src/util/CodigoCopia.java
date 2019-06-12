package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/*
 * @author Hugo Henrique
 */

public class CodigoCopia {
    public static String gerar() {
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);

        return String.valueOf(n);
    }
}

