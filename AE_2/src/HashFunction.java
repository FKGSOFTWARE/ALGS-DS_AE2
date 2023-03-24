public class HashFunction {

    public static String[] generateStrings(int n) {
        String[] result = new String[n];
        int FIXED_VALUES = 4;
        int digitsOfN = Integer.toString(n - 1).length();
        int skip = (int) Math.max(2, Math.ceil((digitsOfN + 2) / 3.0));
        int stringLength = 3 * skip - 2 + FIXED_VALUES;

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            String iString = Integer.toString(i);
            int l = 0;

            for (int j = 0; j < stringLength; j++) {
                if (j % skip == 0) {
                    sb.append("a");
                } else {
                    if (l < iString.length()) {
                        sb.append(iString.charAt(l));
                        l++;
                    } else {
                        sb.append("0");
                    }
                }
            }
            result[i] = sb.toString();
        }
        return result;
    }

    public static int hashCode(String s) {
        int hash = 0;
        int skip = Math.max(1, s.length() / 3);
        for (int i = 0; i < s.length(); i += skip) {
            hash = (hash * 37) + s.charAt(i);
        }
        return hash;
    }

    public static boolean checkLength(String[] values) {
        if (values.length == 0) {
            return true;
        }
        int length = values[0].length();
        for (int i = 1; i < values.length; i++) {
            if (length != values[i].length()) {
                System.out.println("values(" + i + ") has length: " + values[i].length());
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        int n = 10001;
        String[] strings = generateStrings(n);

        if (!checkLength(strings)) {
            throw new Exception("Error: Strings have different lengths!");
        }

        int expectedHash = hashCode(strings[0]);
        System.out.println("Expected hash value: " + expectedHash);

        for (int i = 0; i < n; i++) {
            String currentString = strings[i];
            int currentHash = hashCode(currentString);
            System.out.println(currentString + " - hash: " + currentHash);

            if (currentHash != expectedHash) {
                throw new Exception("Error: Hash code values do not match!");
            }
        }
    }
}
