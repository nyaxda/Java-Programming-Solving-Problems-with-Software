
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1 || (stopIndex - startIndex) % 3 != 0) {
            return dna.length();
        }
            return stopIndex;
    }
    
    public String findGene(String dna, int startIndex) {
        if (startIndex == -1) {
            return "";
        }
        int stopIndextaa = findStopCodon(dna, startIndex, "TAA");
        int stopIndextag = findStopCodon(dna, startIndex, "TAG");
        int stopIndextga = findStopCodon(dna, startIndex, "TGA");
        
        int midIndex = 0;
        if (stopIndextaa == -1 || (stopIndextag != -1 && stopIndextag < stopIndextaa)) {
            midIndex = stopIndextag;
        } else {
            midIndex = stopIndextaa;
        }
        if (midIndex == -1 || (stopIndextga != -1 && stopIndextga < midIndex)) {
            midIndex = stopIndextga;
        }
        if (midIndex == -1 || midIndex + 3 > dna.length()) {
            return "";
        }
        return dna.substring(startIndex, midIndex + 3);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, dna.indexOf("ATG", startIndex));
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = startIndex + currentGene.length();
            if (startIndex > dna.length()) {
                break;
            }
        }
    }
    
    public void testCountGenes() {
        // Test cases
        String dna1 = "ATGAAATGAAAAATGTAG";  // Multiple genes
        String dna2 = "ATGAAATAA";            // Single valid gene
        String dna3 = "ATGAAATGA";            // No valid gene (missing valid stop codon)
        String dna4 = "ATGCCCATGTTTTAAGGGTGA"; // Multiple genes with valid stop codons
        String dna5 = "CCCATGAAAGGGTTT";       // Single ATG, no stop codon (not a valid gene)
        String dna6 = "CCCATAAGGGTTT";         // No ATG at all (should return nothing)
        String dna7 = "";                      // Empty string (should return nothing)
        String dna8 = "ATGTAAGATGCCCTAGT";     // Two valid genes
        String dna9 = "ATGAAATTAAATGTGATGA";   // Two genes but second has no valid stop codon
        String dna10 = "ATGAAAATGCCCTAATGA";   // Overlapping genes but should extract valid ones
    
        System.out.println("Testing dna1:");
        printAllGenes(dna1);
        System.out.println("\nTesting dna2:");
        printAllGenes(dna2);
        System.out.println("\nTesting dna3:");
        printAllGenes(dna3);
        System.out.println("\nTesting dna4:");
        printAllGenes(dna4);
        System.out.println("\nTesting dna5:");
        printAllGenes(dna5);
        System.out.println("\nTesting dna6:");
        printAllGenes(dna6);
        System.out.println("\nTesting dna7:");
        printAllGenes(dna7);
        System.out.println("\nTesting dna8:");
        printAllGenes(dna8);
        System.out.println("\nTesting dna9:");
        printAllGenes(dna9);
        System.out.println("\nTesting dna10:");
        printAllGenes(dna10);
    }
}
