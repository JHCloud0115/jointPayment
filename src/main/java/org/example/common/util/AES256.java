package org.example.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES256 {
    public static String alg = "AES/CBC/PKCS5Padding";
    private final String key = "12345678910111213";
    private final String iv = key.substring(0, 16); // 16byte

    public String encrypt(String text) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes("UTF-8"), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String text) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(text);
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes("UTF-8"), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }

}
