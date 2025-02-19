class Solution {
    List<String> list = new ArrayList<>();

    public String getHappyString(int n, int k) {
        char[] chr = {'a', 'b', 'c'};

        solve(chr, n, new StringBuilder());

        Collections.sort(list);

        return list.size() < k ? "" : list.get(k - 1);   
    }

    void solve(char[] chr, int n, StringBuilder sb) {
        if (n == sb.length()) {
            list.add(sb.toString());
            return;
        }

        for (int i = 0; i < chr.length; i++) {
            if (sb.length() > 0 && chr[i] == sb.charAt(sb.length() - 1)) continue;
            sb.append(chr[i]);
            solve(chr, n, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}