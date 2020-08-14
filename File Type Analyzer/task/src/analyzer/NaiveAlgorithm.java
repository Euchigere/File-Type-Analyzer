package analyzer;

class NaiveAlgorithm implements SearchAlgorithm {
    @Override
    public boolean findPattern(String pattern, byte[] unknownFileByteArray) {
        int i = 0;
        int j = 0;

        while (i < unknownFileByteArray.length && j < pattern.length()) {
            if (unknownFileByteArray[i] == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length() - 1) {
                    return true;
                }
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        return false;
    }
}
