package com.broadcom.apiml.pkcs11;

import java.security.KeyStore;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.spec.SecretKeySpec;

import com.ibm.crypto.pkcs11impl.provider.IBMPKCS11Impl;

/**
 * A class to access a PKCS#11 token
 */
public class AccessToken {

    private AccessToken(String directory) {
        String config = directory + "MyTokenConfig.txt";
        byte[] rawKeyBytes = new byte[32];
        new SecureRandom().nextBytes(rawKeyBytes);
        SecretKeySpec genericKeySpec = new SecretKeySpec(rawKeyBytes, "Generic Secret");
        try {
            Provider p11Provider = new IBMPKCS11Impl(config);

             Security.addProvider(p11Provider);

             KeyStore ks = KeyStore.getInstance("PKCS11IMPLKS", p11Provider);
             ks.load(null, "ignored".toCharArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Main methods
     *
     * @param args not passed
     */
    public static void main(String[] args) {

        new AccessToken(args[0]);

    }

}
