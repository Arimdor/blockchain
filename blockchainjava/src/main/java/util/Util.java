package util;

public class Util {
    private static StringBuffer sb;
    private static StringBuilder text;

    public static String toBytesHexString(byte[] bytes) {
        sb = new StringBuffer(64);
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
        text = new StringBuilder();
        for (int item : array) {
            text.append(item);
        }
        return text.toString();
    }
}
