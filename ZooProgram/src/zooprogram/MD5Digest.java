package zooprogram;


import java.security.MessageDigest;

public class MD5Digest {

    String original = "";

    public MD5Digest() {
    }

    public String convertToMD5(String original) throws Exception{
        this.original = original;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(original.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }
}
