package com.broadcom.apiml.pkcs11;

import java.security.KeyStore;
import java.security.Provider;
import java.security.Security;

/**
 * A class to access a PKCS#11 token
 */
public class SunAccessToken {

    private SunAccessToken(String algorithm) {
        String config = "--name = MY_APP_TOKEN\nlibrary=/usr/lpp/pkcs11/lib/csnpca64.so\ndescription=Zowe Config";
        try {
            Provider p11Provider = Security.getProvider("SunPKCS11");

            p11Provider.configure(config);
            KeyStore ks = KeyStore.getInstance(algorithm, p11Provider);
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

        new SunAccessToken(args[0]);

    }

}
