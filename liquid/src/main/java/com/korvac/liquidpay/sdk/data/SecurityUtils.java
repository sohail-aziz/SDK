package com.korvac.liquidpay.sdk.data;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Utility class to create sha512 signature API request paramaters
 * <p>
 * Created by sohail on 6/6/2017.
 */

@Singleton
class SecurityUtils {


//    private static String secret = "c3b268862bb6af82";
    // private static String nonce = "5897884565425";

    @Inject
    public SecurityUtils() {

    }

    /**
     * Create Sha512 signature using none and secret
     *
     * @param treeMap    treeMap containing request keys and values
     * @param nonceValue current time stamp
     * @param secret     unique secret (provided by liquidpay along with apiKey)
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public String getSignature(TreeMap<String, Object> treeMap, String nonceValue, String secret) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        //TODO
        //1- sort alphabetically
        //2- remove empty string values
        //3- convert boolean to 1 and 0
        //4- add nounce and secret
        //5- url encode everything
        //6- compute sha512
        //7- convert to upper case


        //remove empty strings

        Set<String> emptyKeys = new HashSet<>();
        Set<String> bolKeys = new HashSet<>();

        for (Map.Entry entry : treeMap.entrySet()) {

            if (entry.getValue().equals("")) {
                emptyKeys.add((String) entry.getKey());
            }

            if (entry.getValue() instanceof Boolean) {
                bolKeys.add((String) entry.getKey());
            }

            Log.d("SecurityUtils", "k,v=" + entry.getKey() + "," + entry.getValue());
        }

        //remove empty keys
        for (String s : emptyKeys) {
            treeMap.remove(s);
        }
        //convert boolean keys to 1 and 0
        for (String s : bolKeys) {
            int val = ((boolean) treeMap.get(s)) ? 1 : 0;
            treeMap.put(s, val);

        }


        //amount=25.1&force=1&merchant_ref_no=12345679&payee=payee_i%26ent%7Cfi%24r&service_type=SP&nonce=5897884565425&secret=c3b268862bb6af82
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry e : treeMap.entrySet()) {

            stringBuilder.append(e.getKey().toString().toUpperCase()).append("=");
            String encodeValue = URLEncoder.encode(String.valueOf(e.getValue()).toUpperCase(), "UTF-8");
            stringBuilder.append(encodeValue).append("&");
        }

        //append nonce
        stringBuilder.append("NONCE=");
        String encodeNonce = URLEncoder.encode(nonceValue.toUpperCase(), "UTF-8");
        stringBuilder.append(encodeNonce).append("&");

        //append secret
        stringBuilder.append("SECRET=");
        //String encodedSecret = URLEncoder.encode(secret.toUpperCase(), "UTF-8");
        String encodedSecret = URLEncoder.encode(secret, "UTF-8"); //no upper case
        stringBuilder.append(encodedSecret);

        Log.d("SecurityUtils", "encodedURLString=" + stringBuilder.toString());

        //compute SHA1


        final String encodedString = stringBuilder.toString();
        return getSha512(encodedString).toUpperCase();

    }

    private String getSha512(String inputString) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(inputString.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("getSha512: Hex format : " + sb.toString());

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();

    }
}
