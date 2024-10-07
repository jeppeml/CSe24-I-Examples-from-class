package dk.easv.threelayeredexample.bll;

public class Calculator {
    public int countChars(String input){
        // only count capitalized chars
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            String in = input.charAt(i)+"";
            if(in.toUpperCase().equals(in) &&
                !in.equals(" ")){
                count++;
            }
        }
        return count;
    }
}
