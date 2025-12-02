package com.galatea.util;

public class ContainsArtifactClueUtil {

    private static final int TARGET = 4;

    public static boolean containsArtifactClue(String[] manuscript) {
        if (manuscript == null || manuscript.length == 0) return false;
        int rows = manuscript.length;
        int cols = manuscript[0].length();
        char[][] g = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (manuscript[i].length() != cols) return false;
            for (int j = 0; j < cols; j++)
                g[i][j] = Character.toUpperCase(manuscript[i].charAt(j));
        }

        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = g[r][c];
                if (!Character.isLetter(ch)) continue;

                for (int d = 0; d < 8; d++) {
                    int rr = r, cc = c, k;
                    for (k = 1; k < TARGET; k++) {
                        rr += dx[d];
                        cc += dy[d];
                        if (rr < 0 || cc < 0 || rr >= rows || cc >= cols) break;
                        if (g[rr][cc] != ch) break;
                    }
                    if (k == TARGET) return true;
                }
            }
        }
        return false;
    }
}
