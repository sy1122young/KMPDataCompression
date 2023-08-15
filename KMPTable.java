//Jade Thomas 1558251
//Steven Young 1549512
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.*;

class KMPTable {
    public static void main(String[] args) {
        // string where searching for
        String subString = args[0];
        subString = subString.toLowerCase();
        // text file

        List<Character> charList = new ArrayList<>();

        for (int i = 0; i < subString.length(); i++) {
            if (!(charList.contains(subString.charAt(i)))) {
                charList.add(subString.charAt(i));
            }
        }
        // add other characters
        charList.add("*".charAt(0));

        String[][] inputTable = new String[charList.size()][subString.length()];

        int charNum = 0;

        // for each character in string
        for (char compChar : charList) {

            int stringNum = 0;
            // String out = String.valueOf(compChar) + ":";
            String out = "";

            // string for pattern
            String pattern = "";
            // string for copy of pattern to compoare against
            String compareString = "";

            for (char subChar : subString.toCharArray()) {

                int count = 0;
                // add seeked character to prefix
                String prefix = pattern + compChar;
                // System.out.println(prefix);

                compareString += subChar;
                // alwatys 0 when equal
                if (subChar == compChar) {
                    count = 0;

                } else { // NOTE: need a better way of comparing strings to adhere to pattern
                    while (!(compareString.contains(prefix))) {
                        // increment count (no. skips)
                        count += 1;
                        // drop off first character and repeat
                        prefix = prefix.substring(1);

                    }
                    if (compareString.contains(prefix)) {
                        for (int x = 0; x < prefix.length(); x++) {
                            if (subString.charAt(x) != prefix.charAt(x)) {
                                count += 1;
                                prefix = prefix.substring(1);
                                x = -1;
                            }
                        }
                    }
                }
                inputTable[charNum][stringNum] = Integer.toString(count);
                // System.out.println(inputTable[charNum][stringNum]);

                // add to array 'row'
                out += " " + String.valueOf(count);
                // add current char to pattern
                pattern += subChar;
                // add current pattern to comparestring

                // pattern and comparestring the same at this stage
                stringNum++;
            }
            System.out.println(out);
            charNum++;
        }
        // System.out.println(Arrays.deepToString(inputTable));
        OutputSkip(inputTable, subString);

    }

    public static void OutputSkip(String[][] skip, String substring) {
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < skip.length; i++)// for each row
            {

                for (int j = 0; j < skip[i].length; j++)// for each column
                {

                    builder.append(skip[i][j] + "");// append to the output string
                    if (j < skip[i].length - 1)// if this is not the last row element
                        builder.append(",");// then add comma (if you don't like commas you can use spaces)

                }
                if (i != skip.length - 1) {
                    builder.append("\n");// append new line at the end of the row
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter("xyxyz.txt"));
            writer.write(substring + "\n");
            writer.write(builder.toString());// save the string representation of the board
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
