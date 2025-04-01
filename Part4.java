import edu.duke.*;
import java.io.*;

/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void youtubeUrlPrinter() {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        String content = ur.asString();
        System.out.println(content);
        String target = "youtube.com";
        int index = 0;
        while (true) {
            int targetIndex = content.toLowerCase().indexOf(target, index);
            if (targetIndex == -1 ) {
                break;
            }
            
            int startQuote = content.lastIndexOf("\"", targetIndex);
            int endQuote = content.indexOf("\"", targetIndex);
            
            if (startQuote != -1 && endQuote != -1) {
                String url = content.substring(startQuote + 1, endQuote);
                System.out.println(url);
            } else {
                System.out.println("Invalid URL format near index: " + targetIndex);
            }
            index = endQuote + 1;
        }
        /*for (String s : ur.words()) {
            System.out.println("URL : " + s);
            String sLower = s.toLowerCase();
            int isThere = sLower.indexOf(target);
            if (isThere != -1) {
                int startingIndex = s.lastIndexOf("\"", isThere);
                int endingIndex = s.indexOf("\"", isThere);
                if (startingIndex != -1 && endingIndex != -1) {
                    String url = s.substring(startingIndex + 1, endingIndex);
                    System.out.println(url);
                }
            } 
        }*/
    }
    
    public static void main(String[] args) {
        Part4 four = new Part4();
        four.youtubeUrlPrinter();
    }
}
