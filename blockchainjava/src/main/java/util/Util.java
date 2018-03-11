package util;

public class Util {
    public static String toBytesHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (byte aByte : bytes) {
            sb.append(toHex(aByte >> 4));
            sb.append(toHex(aByte));
        }
        return sb.toString();
    }

    private static char toHex(int nibble) {
        final char[] hexDigit =
                {
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
                };
        return hexDigit[nibble & 0xF];
    }

    public static String arrayToString(int[] array) {
        StringBuilder text = new StringBuilder();
        for (int item : array) {
            text.append(item);
        }
        return text.toString();
    }
}
