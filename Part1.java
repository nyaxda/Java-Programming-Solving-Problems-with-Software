import edu.duke.*;
import java.io.*;

public class Part1 {
    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("atg");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("taa", startIndex + 3);
        if (stopIndex == -1) {
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0) {
            String gene = dna.substring(startIndex, stopIndex);
            return gene;
        } else {
            return "";
        }
    }
    
    public void testSimpleGene() {
    String dna1 = "cccatggggttttaaataataataggagagagagagagagttt";
    System.out.println("gene for dna1 is: " + findSimpleGene(dna1));
    }
    
    public static void main (String[] args) {
        Part1 genes = new Part1();
        genes.testSimpleGene();
    }
}
