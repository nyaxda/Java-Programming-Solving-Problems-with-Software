import edu.duke.*;
import java.io.*;
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        if (dna.matches("[A-Z]+")) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) {
            return "";
        }
        if ((stopIndex - startIndex) % 3 == 0) {
            String gene = dna.substring(startIndex, stopIndex + 3);
            return gene;
        } else {
            return "";
        }
    }
    
    public void testSimpleGene() {
    String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
    String dna2 = dna1.toUpperCase();
    String startCodon = "atg";
    String stopCodon = "taa";
    System.out.println("gene for dna1 is: " + findSimpleGene(dna1, startCodon, stopCodon));
    System.out.println("gene for dna2 is: " + findSimpleGene(dna2, startCodon, stopCodon));
    }
    
    public static void main (String[] args) {
        Part2 genes = new Part2();
        genes.testSimpleGene();
    }
}
