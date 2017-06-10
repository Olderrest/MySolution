package test;

import java.util.*;

/**
 * Created by PILOT on 23.05.2017.
 */
public class BracketSequence {

    private static void calculateBracketSequence(int N) {
        // create two collection for storage brackets
        // use Set to avoid duplicates
        Set<String> resSet = new HashSet<>();
        Set<String> brackets = new HashSet<>();
        brackets.add(" ");
        StringBuilder s = new StringBuilder("()");


        // in the cycle up to number N create ArrayList currentBrackets, in which copy all items form Set 'brackets'
        // this need, because in cycle we will be changed Set 'brackets'
        for (int i = 0; i < N; i++) {
            List<String> currentBrackets = new ArrayList<>(brackets);
            for (String p : currentBrackets) {
                for (int j = 0; j < p.length(); j++) {
                    StringBuilder forInsert = new StringBuilder(p);
                    forInsert.insert(j, s);
                    String res = String.valueOf(forInsert);
                    resSet.add(res);
                    brackets.add(res);
                    brackets.remove(p);
                }
            }
            System.out.println(i + 1 + ": " + resSet.size());
            resSet.clear();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number of brackets: ");
        int N = sc.nextInt();
        calculateBracketSequence(N);
    }

}
