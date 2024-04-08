package ca.mcmaster.se2aa4.mazerunner;

public class FactorizedConverter {
    public static String canonicalToFactorized(String path) {

        String fact_path = "";
        char before = path.charAt(0);
        int num = 1;

        for (int i = 1; i < path.length(); i++) {
            if (path.charAt(i) == before && i < path.length()) {
                num = num + 1;
                if (i == path.length() - 1) {
                    fact_path = fact_path + String.valueOf(num) + before;
                }
            } else {
                if (num > 1) {
                    fact_path = fact_path + String.valueOf(num);
                }
                fact_path = fact_path + before + ' ';
                num = 1;
                before = path.charAt(i);
                if (i == path.length() - 1) {
                    fact_path = fact_path + before;
                }
            }
        }

        return fact_path;

    }
}
