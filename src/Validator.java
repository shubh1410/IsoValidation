import java.util.Base64;


public class Validator {

    static int index;
    public static void main(String[] args){
        XmlParser parser=new XmlParser();
        XmlParser.Record record=parser.Parser();
        index=0;
        Base64.Decoder decoder = Base64.getDecoder();
        String dStr = new String(decoder.decode(record.biometric_record));
        String str=bytesToHex(dStr.getBytes());
        if(isValidFormat(str) && isValidVersion(str))
            System.out.println("VALID");
        else
            System.out.print("INVALID");
    }

    private static boolean isValidVersion(String str) {
        if(str.charAt(index++)=='3' && str.charAt(index++)=='0' && str.charAt(index++)=='3' && str.charAt(index++)=='2' && str.charAt(index++)=='3'
                && str.charAt(index++)=='0' && str.charAt(index++)=='0' && str.charAt(index++)=='0')
            return true;
        return false;
    }

    private static boolean isValidFormat(String str) {
        if(str.charAt(index++)=='4' && str.charAt(index++)=='9' && str.charAt(index++)=='4' && str.charAt(index++)=='9' && str.charAt(index++)=='5'
        && str.charAt(index++)=='2' && str.charAt(index++)=='0' && str.charAt(index++)=='0')
            return true;
        return false;
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}
