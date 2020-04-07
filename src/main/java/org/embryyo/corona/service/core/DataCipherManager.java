package org.embryyo.corona.service.core;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class DataCipherManager {

    @Value( "${app.security.key}" )
    private String key;

    @Value( "${app.security.algorithm}" )
    private String algorithm;

    @Value( "${app.security.transformation}" )
    private String transformation;

    public String encrypt(String value) {
        try {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(key.getBytes());
            byte[] digiest = messageDigest.digest();
            SecretKeySpec skeySpec = new SecretKeySpec(digiest, algorithm);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivspec);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e.getCause());
        } catch (NoSuchPaddingException e) {
            throw new SecurityException(e.getCause());
        } catch (InvalidKeyException e) {
            throw new SecurityException(e.getCause());
        } catch (IllegalBlockSizeException e) {
            throw new SecurityException(e.getCause());
        } catch (BadPaddingException e) {
            throw new SecurityException(e.getCause());
        } catch (InvalidAlgorithmParameterException e) {
            throw new SecurityException(e.getCause());
        }
    }

    public String decrypt(String encrypted) {
        try {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(key.getBytes());
            byte[] digiest = messageDigest.digest();
            SecretKeySpec skeySpec = new SecretKeySpec(digiest, algorithm);
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivspec);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original);
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException(e.getCause());
        } catch (BadPaddingException e) {
            throw new SecurityException(e.getCause());
        } catch (InvalidKeyException e) {
            throw new SecurityException(e.getCause());
        } catch (NoSuchPaddingException e) {
            throw new SecurityException(e.getCause());
        } catch (IllegalBlockSizeException e) {
            throw new SecurityException(e.getCause());
        } catch (InvalidAlgorithmParameterException e) {
            throw new SecurityException(e.getCause());
        }
    }
}
