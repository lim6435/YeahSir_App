package kr.co.william.yeahsir;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import kr.co.william.yeahsir.utils.CommonUtil;

/**
 * RSA암/복호화를 수행한다
 * 네이버 페이의 RSA 사용은 항상 공개키로 암호화 하고 개인키로 복호화 한다
 *
 * @author koomac
 *
 */
public class RSACrypto
{
    private String pubKey, priKey;

    public RSACrypto()
    {
    }

    public void setPubKey(String pubKey)
    {
        this.pubKey = pubKey;
    }

    public void setPriKey(String priKey)
    {
        this.priKey = priKey;
    }

    /**
     * 공개키로 암호화 한다. 설정된 공개키가 없다면 입력받은 원본 문자열을 반환한다
     *
     * @param plainStr 원본 문자열
     * @param formatMode 암호화형식
     * @param encodeType HEX:암호화된 문자열을 hexa형태로 생성, 기타:암호화된 문자열을 Base64형태로 생성
     * @return 암호화된 문자열
     * @throws Exception
     */
    public String encrypt(String plainStr, String formatMode, String encodeType) throws Exception
    {
        if (plainStr == null) return null;
        if (CommonUtil.isEmpty(this.pubKey)) return plainStr;

        PublicKey publicKey = generateRsaPublicKeyFromStringKey(this.pubKey);

        Cipher cipher = Cipher.getInstance(formatMode);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainStr.getBytes());

        return "HEX".equals(encodeType) ? new String(Hex.encode(encryptedBytes)) : new String(Base64.encode(encryptedBytes));
    }

    /**
     * 개인키로 복호화 한다. 설정된 개인키가 없다면 입력받은 암호화 문자열을 반환한다
     *
     * @param encStr 암호화된 문자열
     * @param formatMode 암호화형식
     * @param charsetName 복호화된 원본 문자열의 chaerset(없으면 euc-kr)
     * @return
     * @throws Exception
     */
    public String decrypt(String encStr, String formatMode, String charsetName) throws Exception
    {
        if (encStr == null) return null;
        if (CommonUtil.isEmpty(this.priKey)) return encStr;

        if (CommonUtil.isEmpty(charsetName)) charsetName = "euc-kr";

        PrivateKey privateKey = generateRsaPrivateKeyFromStringKey(this.priKey);

        Cipher cipher = Cipher.getInstance(formatMode);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Hex.decode(encStr));

        return new String(decryptedBytes, charsetName);
    }

    /**
     * key 스트링으로 RsaPublicKey 생성
     *
     * @param key
     * @return
     */
    private PublicKey generateRsaPublicKeyFromStringKey(String key)
    {
        PublicKey publicKey = null;
        try
        {
            byte[] byteArray = Base64.decode(key.getBytes());
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(byteArray));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * key 스트링으로 RsaPrivateKey 생성
     *
     * @param key
     * @return
     */
    private PrivateKey generateRsaPrivateKeyFromStringKey(String key)
    {
        PrivateKey privateKey = null;
        try
        {
            // byte[] byteArray = Base64.decodeBase64(key.getBytes());
            byte[] byteArray = Base64.decode(key.getBytes());
            privateKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(byteArray));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return privateKey;
    }

//    public static void main(String agrs[]) {
//
//        String plainStr = "브이피네이버페이테스트文字123456*&^%$#@+\\ ";
//        System.out.println("plainStr : "+plainStr);
//
//        try {
//            RSACrypto rsaEncObj = new RSACrypto();
//            rsaEncObj.setPriKey("MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCAJb1yL0s5Z+fqTqqIXZ1vPbypJUwXg92nXhYVILnuYG0oA0IJuftOCXnPP12IPDZIQwJXnuuiauc8MsBj/5SQAPxquLI3KAR9q8WFFGXXa9zPrwLKKxR4cf0pRexY1xCjArpXrkd/6D23PklcmjdLz8DmxuR7MfQLCq95dhZe5sqmA/EqjwSCt0V/UV/q2nDXt6Od++ri+ez/42i+rJcSZAFeOnWi9rYWpTFNza1gHNRaOIzPdV1SG4tB9XhlWs26XRZQT8dYPeHK27UCRFbdL1lufs3fd8q1sF1ZbxN4VDaMU30KfR7y58z7QbwerYvvEDUWLR0Kbzm/A6NTC/1ZAgMBAAECggEAFRAe92m2n5TolaageRGKEwK+w76RpzvRbvUUBB0A8qF+z5jZfefH2qfMEbiEkyPyeCYreGNyr5uKrmZZIRadQJ4o3xHEmTRGqLFXIeycYLAl9yz49GnoT3PM/L+1fVS8M077efX3Ypq1qizeug3PEPLw7f27CFTJnBcBiKFUQ/zGeviNb3rRptdzJYYZ/jY9r77H3J5WkQfHOAOrI5xRjqdxMc9Y1ADpYA1lW8emLKenkzVX0r6gz0C8bduE3aJz2fMWiVwY8MLBrj2WPtChiQgvpBA7HuuccwntL9CpFH4SnvK1w49nluWdTjjRBtmtPiwx7AXgp6j4ZauOlm2qEQKBgQDej82WrNxdMl2yEMHSXoFMW8Iuaw1lgGWfLW08A7H+iEPXoOvnk79WXNJ4tWO2wF/nnKSRhKAIY1fNqabwZaGwjYl9l1+Vr3mrEkISb38nHFFWb49d9mBSn+invy89bxqrfPyOjCUqjGd/9y6Nx3SJLOCRoyl/BR6jw6OTS5mdHwKBgQCTZo/kO+jPxT/EXYPfMsuA7n8dqWu8cMhkQvIPR01d0OwrCmOAMihSTKB+xSnw0WlcRkEcd3Qwkxa8lSvE5+EBH1phxDAdlLc6NvQ3hlgMQYoOfgkHLFSDbfDFPwqs8THE7Y+qkNpNzGDUrBfDBkLlGl5oB+KFFAK6tTibJs+ehwKBgQC+OouuzfFMd1A+crQ36y745TUEF9xLdYNmmp1TPpVUyYOzOohoV5CjcJM1OVURTzyp6ul+t61Rfhf+YZOexj99eHycLFY2ytVjsydmOoakF0AucQxJc9zAwo1JU9A84koZaas06/SYeYm9hrjz1hd1aDZKRPui5e1abE3UCDo0YQKBgQCQoch7GNXeWRuy4FevQLYyctLEFiOiqb/XsWC24S3m6kNyECWF+rFZ39pzyZ7Xr4OEiWMGPagCqbFbpbtm4GgFo/AZklnsDKqlMpr7iJUIoZ6Hjd/p6/OZat0fjjY5cAl3w+p+YNf0EqDIzXwbXx9wkikkRuhb0EDFV2CNzM1YLQKBgQCybqW9aegcT4NCVx4NPuljEXYCYwvVtejOMpesbkQ7bMnzIGf5plxgcHz12HnZNkjofp3Iw458RG++Q8uY0qn5dvyDix6fYq/eEtAktQreZoNTtN1UBa/+MhTGCIHGwFM6ZUEF5+OIh/53dpfc4Xo2QqikjnNXPcUQbrhdIWswLA==");
//            rsaEncObj.setPubKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgCW9ci9LOWfn6k6qiF2dbz28qSVMF4Pdp14WFSC57mBtKANCCbn7Tgl5zz9diDw2SEMCV57romrnPDLAY/+UkAD8ariyNygEfavFhRRl12vcz68CyisUeHH9KUXsWNcQowK6V65Hf+g9tz5JXJo3S8/A5sbkezH0CwqveXYWXubKpgPxKo8EgrdFf1Ff6tpw17ejnfvq4vns/+NovqyXEmQBXjp1ova2FqUxTc2tYBzUWjiMz3VdUhuLQfV4ZVrNul0WUE/HWD3hytu1AkRW3S9Zbn7N33fKtbBdWW8TeFQ2jFN9Cn0e8ufM+0G8Hq2L7xA1Fi0dCm85vwOjUwv9WQIDAQAB");
//
//            String encStr = rsaEncObj.encrypt(plainStr, "RSA/ECB/PKCS1Padding", "HEX");
//            System.out.println("encStr[" + encStr + "]");
//            String d = rsaEncObj.decrypt(encStr, "RSA/ECB/PKCS1Padding", "euc-kr");
//            System.out.println("decStr[" + d + "]");
//
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//    }

}