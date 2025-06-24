// The "Optimization #n" comments refer to paragraphs in the 
// discussion for this leetcode problem #1044 created by 
// username "dudeandcat", for the code below.

class Solution {
    static final int CHAR_BASE = 'a';   // Character with the lowest  
                                        // allowed value in the passed 
                                        // String.
    static final int CHAR_COUNT = 'z' - 'a' + 1;
                                        // Number of unique characters 
                                        // allowed in the passed String.
                                        // 'a'...'z' ==> 26 chars.
    
    public String longestDupSubstring(String S) {
        char[] sc = S.toCharArray();
        int scLen = sc.length;
        
        // Check if there aren't any duplicate substrings.  There can 
        // only be no duplicates if the string does not have more than 
        // one occurrence of any character in the string.  Since the 
        // string only contains lowercase characters, the string 
        // length must be less than 26 characters, otherwise at least 
        // one character must be duplicated.
        // (Optimization #1)
        int longestSubstringIdx = 0;
        int longestSubstringLen = 0;
        int[] found = new int[CHAR_COUNT];
        for (int i = scLen - 1; i >= 0; i--) {
            if (found[sc[i] - CHAR_BASE]++ > 0) {
                longestSubstringIdx = i;
                longestSubstringLen = 1;
                break;
            }
        }
        if (longestSubstringLen == 0)  return "";
        
        // Check for the same character over a large contiguous area.  
        // If we find a long repeat of the same character, then we can 
        // use this to set a minimum length for the longest duplicate 
        // substring, and therefore we don't have to check any shorter 
        // substrings.
        // (Optimization #2)
        for (int i = scLen - 1; i > 0; i--) {
            if (sc[i] == sc[i - 1]) {
                final char c = sc[i];
                final int startI = i;
                int reptCount = 2;
                for (i = i - 2; i >= 0 && sc[i] == c; i--) { }
                i++;
                if (startI - i > longestSubstringLen) {
                    longestSubstringLen = startI - i;
                    longestSubstringIdx = i + 1;
                }
            }
        }
        if (longestSubstringLen == scLen - 1)  return S.substring(0, longestSubstringLen);

        // Build a table of two-charactar combined values for the 
        // passed String.  These combined values are formed for any 
        // index into the String, by the character at the current 
        // index reduced to the range [0..25] times 26, plus the 
        // next character in the string reduced to the range [0..25].  
        // This combined value is used to index into the array 
        // twoCharHead[], which contains the index into the string of 
        // the first character pair with this combined value, which is 
        // also used to index into the array twoCharList[].  The 
        // twoCharList[] array is a "linked list" of String indexes 
        // that have the same combined values for a character pair.
        //
        // To look up all character pairs with the same combined 
        // value N, start at twoCharHead[N].  This will give the 
        // String index X of the first character pair with that 
        // combined value.  To find successive String indexes, lookup 
        // in twoCharList[X] to get the new String index X.  Then 
        // repeatedly lookup new X values in twoCharList[X], until 
        // X equals zero, which indicates the end of the character 
        // pairs with the same combined value.
        short[] twoCharHead = new short[CHAR_COUNT * CHAR_COUNT];
        short[] twoCharList = new short[scLen];
        for (int i = scLen - longestSubstringLen - 1; i > 0; i--) {
            int twoCharNum = (sc[i] - CHAR_BASE) * CHAR_COUNT + 
                        sc[i + 1] - CHAR_BASE;
            twoCharList[i] = twoCharHead[twoCharNum];
            twoCharHead[twoCharNum] = (short)i;
        }
        
        // Search the String for matching substrings that are longer 
        // than the current longest substring found.  Start at the 
        // beginning of the string, and successively get a character 
        // pair's combined value.  Use that character pair's combined 
        // value to find all other character pair's with the same 
        // combined value.  In the process, remove any character pairs 
        // that occur in the String before the current character pair.  
        // For two character pairs that appear that they may be a 
        // possible matching substring longer than the currently 
        // longest found match, then test to see if the substrings 
        // match.
        int curIdxLimit = scLen - longestSubstringLen - 1;  // (Optimization #3)
        for (int i = 0; i <= curIdxLimit; i++) {            // (Optimization #3)
            int twoCharNum = (sc[i] - CHAR_BASE) * CHAR_COUNT + 
                        sc[i + 1] - CHAR_BASE;
            while (twoCharHead[twoCharNum] <= i && twoCharHead[twoCharNum] != 0)
                twoCharHead[twoCharNum] = twoCharList[twoCharHead[twoCharNum]];
                                                            // (Optimization #4)
            int listIdx = twoCharHead[twoCharNum];
            while (listIdx != 0 && listIdx <= curIdxLimit /* (Optimization #5) */) {
                if (sc[i + longestSubstringLen] == sc[listIdx + longestSubstringLen] && 
                        sc[i + longestSubstringLen/2] == sc[listIdx + longestSubstringLen/2]) {
                                                            // (Optimization #6)
                    int lowIdx = i + 2;                     // (Optimization #7)
                    int highIdx = listIdx + 2;              // (Optimization #7)
                    while (highIdx < scLen && sc[lowIdx] == sc[highIdx]) {
                        lowIdx++;
                        highIdx++;
                    }
                    if (lowIdx - i > longestSubstringLen) {
                        longestSubstringLen = lowIdx - i;
                        longestSubstringIdx = i;
                        curIdxLimit = scLen - longestSubstringLen - 1;  // (Optimization #3)
                    }
                }
                listIdx = twoCharList[listIdx];
            }
        }
        
        return S.substring(longestSubstringIdx, longestSubstringIdx + longestSubstringLen);
    }
}