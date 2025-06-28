class Solution {
    public boolean isTransformable(String s, String t) {
        Map<Integer, List<Integer>> mapToStoreStringSDigitIndex = new HashMap<Integer, List<Integer>>();

        for (int index = 0; index < 10; index++) {
            mapToStoreStringSDigitIndex.put(index, new ArrayList<Integer>());
        }

        for (int index = 0; index < s.length(); index++) {
            int currDigit = s.charAt(index) - '0';
            mapToStoreStringSDigitIndex.get(currDigit).add(index);
        }

        int[] digitCountInStringTEncounteredSoFar = new int[10];

        for (int index = 0; index < t.length(); index++) {
            int currDigit = t.charAt(index) - '0';
            // checking for anagram here. S and T should have equal number of x digit.
            // S = 2341, T = 2231 => Invalid anagram
            // S = 2431, T = 4123 => Valid anagram
            if (digitCountInStringTEncounteredSoFar[currDigit] >= mapToStoreStringSDigitIndex.get(currDigit).size()) {
                return false;
            }

            // Access the curr digit position from string S, we might have x digit with multiple occurance. To store this we use 
            // "digitCountInStringTEncounteredSoFar" array.
            int currDigitPositionInStringS = mapToStoreStringSDigitIndex.get(currDigit).get(digitCountInStringTEncounteredSoFar[currDigit]);

            // Here we check whether we have a digit smaller than "currDigit" before the "currDigit".
            // S = 84523, T = 34852
            // We can't move digit 3 in String S to 0th index, because we have 2 before 3. If we sort we get 23 in the sequence.
            for (int digit = 0; digit < currDigit; digit++) {
                if (digitCountInStringTEncounteredSoFar[digit] < mapToStoreStringSDigitIndex.get(digit).size() &&
                    mapToStoreStringSDigitIndex.get(digit).get(digitCountInStringTEncounteredSoFar[digit]) < currDigitPositionInStringS) {
                    return false;
                }
            }

            digitCountInStringTEncounteredSoFar[currDigit]++;
        }

        return true;
    }
}


















































