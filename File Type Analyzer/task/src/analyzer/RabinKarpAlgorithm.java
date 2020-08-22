package analyzer;

class RabinKarpAlgorithm implements SearchAlgorithm {
    private long charToLong(char ch) {
        return 200 - ch + 1;
    }

    public boolean findPattern(String pattern, byte[] unknownFileByteArray) {
        if (pattern.length() > unknownFileByteArray.length) {
            return false;
        }

        /* 2 */
        int a = 53;
        long m = 1_000_000_000 + 9;

        /* 3 */
        long patternHash = 0;
        long currSubstrHash = 0;
        long pow = 1;

        for (int i = 0; i < pattern.length(); i++) {
            patternHash += charToLong(pattern.charAt(i)) * pow;
            patternHash %= m;

            currSubstrHash += charToLong((char) unknownFileByteArray[unknownFileByteArray.length - pattern.length() + i]) * pow;
            currSubstrHash %= m;

            if (i != pattern.length() - 1) {
                pow = pow * a % m;
            }
        }

        /* 4 */

        for (int i = unknownFileByteArray.length; i >= pattern.length(); i--) {
            if (patternHash == currSubstrHash) {
                boolean patternIsFound = true;

                for (int j = 0; j < pattern.length(); j++) {
                    if (unknownFileByteArray[i - pattern.length() + j] != pattern.charAt(j)) {
                        patternIsFound = false;
                        break;
                    }
                }

                if (patternIsFound) {
                    return patternIsFound;
                }
            }

            if (i > pattern.length()) {
                /* 5 */
                currSubstrHash = (currSubstrHash - charToLong((char) unknownFileByteArray[i - 1]) * pow % m + m) * a % m;
                currSubstrHash = (currSubstrHash + charToLong((char) unknownFileByteArray[i - pattern.length() - 1])) % m;
            }
        }
        return false;
    }
}
