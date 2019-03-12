package per.qy.auth.util;

import java.security.*;
import java.util.Base64;

public class CreateRsaSecret {

    public static void main(String[] args) {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(1024);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            System.out.println("私钥------------------");
            System.out.println(privateKey.getFormat());
            System.out.println(privateKey.getAlgorithm());
            System.out.println(privateKeyStr);
            System.out.println(privateKeyStr.length());
            PublicKey publicKey = keyPair.getPublic();
            String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            System.out.println("公钥------------------");
            System.out.println(publicKeyStr);
            System.out.println(publicKeyStr.length());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
