class Pair{
    String s;
    int level;
    public Pair(String s,int level)
    {
        this.s = s;
        this.level = level;
    }
}
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for(String s:wordList)
        set.add(s);
        if(!set.contains(endWord)) return 0;

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(beginWord,1));
        if(set.contains(beginWord)) set.remove(beginWord);

        while(!q.isEmpty())
        {
            Pair curr = q.poll();
            String s = curr.s;
            int level = curr.level+1;

            for(int i=0;i<s.length();i++)
            {
                char[] chArr = s.toCharArray();
                for(char c='a'; c<='z';c++)
                {
                    chArr[i] = c;
                    String chStr = new String(chArr);
                    if(chStr.equals(endWord)) return level;
                    if(set.contains(chStr))
                    {
                        set.remove(chStr);
                        q.offer(new Pair(chStr,level));
                    }
                }
            }
        }
        return 0;
    }
}