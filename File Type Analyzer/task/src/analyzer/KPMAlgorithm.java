package analyzer;

class KPMAlgorithm implements SearchAlgorithm {
    @Override
    public boolean findPattern(String pattern, byte[] unknownFileByteArray) {
        /* 1 */
        int[] prefixFunc = prefixFunction(pattern);
        int j = 0;
        /* 2 */
        int i;
        for (i = 0; i < unknownFileByteArray.length; i++) {
            /* 3 */
            while (j > 0 && unknownFileByteArray[i] != pattern.charAt(j)) {
                j = prefixFunc[j - 1];
            }
            /* 4 */
            if (unknownFileByteArray[i] == pattern.charAt(j)) {
                j += 1;
            }
            /* 5 */
            if (j == pattern.length()) {
                return true;
            }
        }
        /* 6 */
        return false;
    }

    private int[] prefixFunction(String pattern) {
        /* 1 */
        int[] prefixFunc = new int[pattern.length()];

        /* 2 */
        for (int i = 1; i < pattern.length(); i++) {
            /* 3 */
            int j = prefixFunc[i - 1];

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = prefixFunc[j - 1];
            }

            /* 4 */
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j += 1;
            }

            /* 5 */
            prefixFunc[i] = j;
        }

        /* 6 */
        return prefixFunc;
    }
}