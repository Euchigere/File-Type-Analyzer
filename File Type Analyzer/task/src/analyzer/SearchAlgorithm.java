package analyzer;

interface SearchAlgorithm{
    boolean findPattern(String pattern, byte[] unknownFileByteArray);
}