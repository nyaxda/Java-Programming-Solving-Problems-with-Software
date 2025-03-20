import edu.duke.*;
import java.io.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

/**
 * Write a description of Part1 here.
 * 
 * @author (NyaxDa) 
 * @version (02.12.2025)
 */

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        System.out.println("\n🔍 Searching for " + stopCodon + " after ATG at " + startIndex);
        while (stopIndex != -1) {
            System.out.println("  Found " + stopCodon + " at " + stopIndex);
            if ((stopIndex - startIndex) % 3 == 0) {
                System.out.println("  ✅ Valid stop codon (in frame).");
                return stopIndex;
            } else {
                System.out.println("  ❌ Invalid frame. Continuing search...");
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }
        System.out.println("  ⚠️ No valid " + stopCodon + " found. Returning DNA length: " + dna.length());
        return dna.length();
    }
    
    public void testFindStopCodon() {
        // Test case 1: Valid stop codon at a correct multiple of 3
        String dna1 = "ATGAAATAG";
        int result1 = findStopCodon(dna1, 0, "TAG");
        System.out.println("Test 1 (Expect 6): " + result1);
    
        // Test case 2: Stop codon not at a valid multiple of 3
        String dna2 = "ATGAATAAG";
        int result2 = findStopCodon(dna2, 0, "TAA");
        System.out.println("Test 2 (Expect " + dna2.length() + "): " + result2);
    
        // Test case 3: No stop codon found
        String dna3 = "ATGCCCCCCCCC";
        int result3 = findStopCodon(dna3, 0, "TAA");
        System.out.println("Test 3 (Expect " + dna3.length() + "): " + result3);
    
        // Test case 4: Multiple stop codons, should return the first valid one
        String dna4 = "ATGAAATAATAAG";
        int result4 = findStopCodon(dna4, 0, "TAA");
        System.out.println("Test 4 (Expect 6): " + result4);
    
        // Test case 5: Stop codon found but not after ATG
        String dna5 = "GGTATGTAG";
        int result5 = findStopCodon(dna5, 3, "TAG");  // ATG at index 3
        System.out.println("Test 5 (Expect 6): " + result5);
    }
    
    public String findGene(String dna) {
        System.out.println("\n🧬 Searching for gene in: " + dna);
        int startIndex = dna.indexOf("ATG");
        if (startIndex  == -1) {
            System.out.println("  ⚠️ No ATG found.");
            return "";
        }
        System.out.println("  🏁 ATG found at " + startIndex);
        int stopIndextaa = findStopCodon(dna, startIndex, "TAA");
        int stopIndextag = findStopCodon(dna, startIndex,"TAG");
        int stopIndextga = findStopCodon(dna, startIndex, "TGA");
        System.out.println("ATG at " + startIndex + " → TAA: " + stopIndextaa + ", TAG: " + stopIndextag + ", TGA: " + stopIndextga);
        int midIndex = Math.min(stopIndextaa, (Math.min(stopIndextag, stopIndextga)));
        if (midIndex == dna.length()) {
            System.out.println("  ⚠️ No valid stop codon found.");
            return "";
        }
        String gene = dna.substring(startIndex, midIndex + 3);
        System.out.println("  🧪 Gene extracted: " + gene);
        return gene;
    }
    
    public void testfindGene() {
        // Test case 1: No "ATG" in the DNA string
        String dna1 = "CCCTAAACGGTAG";
        int startIndex = dna1.indexOf("ATG");
        String expected1 = ""; // No ATG, so no gene
        String result1 = findGene(dna1);
        System.out.println("DNA: " + dna1);
        System.out.println("Expected: " + expected1);
        System.out.println("Output: " + result1);
        System.out.println(result1.equals(expected1) ? "✅ Test Passed\n" : "❌ Test Failed\n");
    
        // Test case 2: "ATG" with one valid stop codon
        String dna2 = "ATGAAATAG";
        startIndex = dna2.indexOf("ATG");
        String expected2 = "ATGAAATAG"; // ATG to TAG is a valid gene
        String result2 = findGene(dna2);
        System.out.println("DNA: " + dna2);
        System.out.println("Expected: " + expected2);
        System.out.println("Output: " + result2);
        System.out.println(result2.equals(expected2) ? "✅ Test Passed\n" : "❌ Test Failed\n");
    
        // Test case 3: "ATG" with multiple valid stop codons, should return the shortest valid gene
        String dna3 = "ATGAAATAAATAG";
        startIndex = dna3.indexOf("ATG");
        String expected3 = "ATGAAATAA"; // The first valid stop codon is TAA
        String result3 = findGene(dna3);
        System.out.println("DNA: " + dna3);
        System.out.println("Expected: " + expected3);
        System.out.println("Output: " + result3);
        System.out.println(result3.equals(expected3) ? "✅ Test Passed\n" : "❌ Test Failed\n");
    
        // Test case 4: "ATG" with no valid stop codons
        String dna4 = "ATGCCCGGGAAA";
        startIndex = dna4.indexOf("ATG");
        String expected4 = ""; // No valid stop codon, so no gene
        String result4 = findGene(dna4);
        System.out.println("DNA: " + dna4);
        System.out.println("Expected: " + expected4);
        System.out.println("Output: " + result4);
        System.out.println(result4.equals(expected4) ? "✅ Test Passed\n" : "❌ Test Failed\n");
    
        // Test case 5: "ATG" with a stop codon at an invalid multiple of 3
        String dna5 = "ATGAAAATAAAGATAG";
        startIndex = dna5.indexOf("ATG");
        String expected5 = ""; // TAA is not a valid stop codon (not a multiple of 3)
        String result5 = findGene(dna5);
        System.out.println("DNA: " + dna5);
        System.out.println("Expected: " + expected5);
        System.out.println("Output: " + result5);
        System.out.println(result5.equals(expected5) ? "✅ Test Passed\n" : "❌ Test Failed\n");
    }
    
    public void printAllGenes(String dna) {
        int start = 0;
        while (true) {
            String substring = dna.substring(start);
            String currentGene = findGene(substring);
            if (currentGene.isEmpty()) {
                int nextATG = dna.indexOf("ATG", start + 1);
                if (nextATG == -1) {
                    break;
                } else {
                    start = nextATG;
                }
            } else {
                int geneStartInOriginal = start + substring.indexOf("ATG");
                int geneEndInOriginal = geneStartInOriginal + currentGene.length();
                System.out.println(currentGene);
                start = geneEndInOriginal;
            }
        }

    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource dnaData = new StorageResource();
        int start = 0;
        System.out.println("\n🔬 Starting gene extraction for DNA: " + dna);
        while (true) {
            String substring = dna.substring(start);
            String currentGene = findGene(substring);
            if (currentGene.isEmpty()) {
                System.out.println("  ⚠️ No gene found. Searching for next ATG...");
                int nextATG = dna.indexOf("ATG", start + 1);
                if (nextATG == -1) {
                    System.out.println("  🛑 No more ATG codons found.");
                    break;
                }
                start = nextATG;
                System.out.println("  🔄 Next ATG found at " + start);
            }else {
                int geneStartInSubstring = substring.indexOf(currentGene);
                int geneStartInOriginal = start + geneStartInSubstring;
                int geneEndInOriginal = geneStartInOriginal + currentGene.length();
                System.out.println("  ➕ Adding gene: " + currentGene + " (spans " + geneStartInOriginal + "-" + (geneEndInOriginal - 1) + ")");
                dnaData.add(currentGene);
                start = geneEndInOriginal;
                System.out.println("  🔄 Updated start to " + start);
            }
        }
        System.out.println("🎉 Total genes found: " + dnaData.size());
        return dnaData;
    }
    
    public void testGetAllGenes() {
        System.out.println("Running testGetAllGenes...\n");

        // Test Case 1: No ATG present
        String dna1 = "CCCTAAACGGTAG";
        StorageResource result1 = getAllGenes(dna1);
        System.out.println("DNA: " + dna1);
        System.out.println("Expected: []");
        printGenes(result1);

        // Test Case 2: One valid gene with a stop codon
        String dna2 = "ATGAAATAG";
        StorageResource result2 = getAllGenes(dna2);
        System.out.println("DNA: " + dna2);
        System.out.println("Expected: [ATGAAATAG]");
        printGenes(result2);

        // Test Case 3: Multiple valid genes
        String dna3 = "ATGAAATAGATGCCCTAAATGTTTTGA";
        StorageResource result3 = getAllGenes(dna3);
        System.out.println("DNA: " + dna3);
        System.out.println("Expected: [ATGAAATAG, ATGCCCTAA, ATGTTTTGA]");
        printGenes(result3);

        // Test Case 4: ATG inside a gene is ignored until after a valid stop codon
        String dna4 = "ATGAAAATGCCCTGAATGAATAG";
        StorageResource result4 = getAllGenes(dna4);
        System.out.println("DNA: " + dna4);
        System.out.println("Expected: [ATGAAAATGCCCTGA]");
        printGenes(result4);

        // Test Case 5: ATG but no valid stop codon
        String dna5 = "ATGCCCCCCCCC";
        StorageResource result5 = getAllGenes(dna5);
        System.out.println("DNA: " + dna5);
        System.out.println("Expected: []");
        printGenes(result5);
    }

    // ✅ **New Helper Method to Print Genes in `StorageResource`**
    public void printGenes(StorageResource sr) {
        System.out.print("Output: [");
        for (String gene : sr.data()) { // `sr.data()` gives an iterable list of stored genes
            System.out.print(gene + " ");
        }
        System.out.println("]");
    }
    
    public double cgRatio(String dna) {
        int cgCount = 0;
        int startIndex = 0;
        for (char c : dna.toCharArray()) {
            if (c == 'C' || c == 'G') {
                cgCount++;
            }
        }
        return ((double)cgCount)/dna.length();
        
    }
    
    public void testCgRatio() {
        System.out.println("Running testCgRatio...\n");
    
        // Test Case 1: All Cs and Gs
        String dna1 = "CCGGCCGG";
        System.out.println("DNA: " + dna1 + " | Expected: 1.0 | Output: " + cgRatio(dna1));
    
        // Test Case 2: No Cs or Gs
        String dna2 = "ATATAAAT";
        System.out.println("DNA: " + dna2 + " | Expected: 0.0 | Output: " + cgRatio(dna2));
    
        // Test Case 3: Half C and G
        String dna3 = "ATGCCGTA";
        System.out.println("DNA: " + dna3 + " | Expected: 0.5 | Output: " + cgRatio(dna3));
    
        // Test Case 4: Mixed Case
        String dna4 = "CGATCGGTTTAACG";
        System.out.println("DNA: " + dna4 + " | Expected: 0.5 | Output: " + cgRatio(dna4));
    }
    
    public int countCTG(String dna) {
        int count = 0;
        int startIndex = 0;
        while ((startIndex=dna.indexOf("CTG", startIndex)) != -1) {
            count++;
            startIndex += 3;
        }
        return count;
    }

    public void testCountCTG() {
        System.out.println("Running testCountCTG...\n");
    
        // Test Case 1: No "CTG" present
        String dna1 = "ATGAAATAA";
        int result1 = countCTG(dna1);
        System.out.println("DNA: " + dna1 + " | Expected: 0 | Output: " + result1);
        System.out.println(result1 == 0 ? "✅ Test Passed\n" : "❌ Test Failed\n");
    
        // Test Case 2: One "CTG" occurrence
        String dna2 = "ATGCTGAA";
        int result2 = countCTG(dna2);
        System.out.println("DNA: " + dna2 + " | Expected: 1 | Output: " + result2);
        System.out.println(result2 == 1 ? "✅ Test Passed\n" : "❌ Test Failed\n");
    
        // Test Case 3: Multiple "CTG" occurrences
        String dna3 = "CTGAAACTGCTGTAA";
        int result3 = countCTG(dna3);
        System.out.println("DNA: " + dna3 + " | Expected: 3 | Output: " + result3);
        System.out.println(result3 == 3 ? "✅ Test Passed\n" : "❌ Test Failed\n");
    
        // Test Case 4: Overlapping "CTG" occurrences should NOT be counted
        String dna4 = "CTGCTGCTG";
        int result4 = countCTG(dna4);
        System.out.println("DNA: " + dna4 + " | Expected: 3 | Output: " + result4);
        System.out.println(result4 == 3 ? "✅ Test Passed\n" : "❌ Test Failed\n");
    
        // Test Case 5: "CTG" scattered with random letters
        String dna5 = "AACTGTTCTGCTGGA";
        int result5 = countCTG(dna5);
        System.out.println("DNA: " + dna5 + " | Expected: 3 | Output: " + result5);
        System.out.println(result5 == 3 ? "✅ Test Passed\n" : "❌ Test Failed\n");
    }
    
    public void processGenes(StorageResource sr) {
        int count60Char = 0;
        int count9Char = 0;
        int countCGRatio = 0;
        int max = 0;
        int totalGenes = 0;
        int totalCTGcount = 0;
        String longestGene = "";
        System.out.println("Total number of genes: " + sr.size());
        for (String s : sr.data()) {
            System.out.println("On gene: " + s);
            totalGenes++;
            totalCTGcount+=countCTG(s);
            if (s.length() > 9) {
                //System.out.println("String longer than 9 chars: " + s);
                count9Char++;
            }
            if(s.length() > 60) {
                //System.out.println("String longer than 60 chars: " + s);
                count60Char++;
            }
            if (cgRatio(s) > 0.35) {
                //System.out.println("String with CG Ratio > 0.35: " + s);
                countCGRatio++;
            }
            if(s.length() > max) {
                longestGene = s;
                max = s.length();
            }
        }
        // Print counts
        System.out.println("\nSummary:");
        System.out.println("Total genes extracted: " + totalGenes);
        System.out.println("Number of strings longer than 9 characters: " + count9Char);
        System.out.println("Numbers of strings longer than 60 Characters: " + count60Char);
        System.out.println("Numbers of strings with higher than 0.35 Cg Ratio: " + countCGRatio);
        System.out.println("Longest Gene: " + longestGene + "and its length is: " + max);
        System.out.println("Total times codon CTG appeared: " + totalCTGcount);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println("DNA is: " + dna + " and the length is: " + dna.length());
        StorageResource sr = getAllGenes(dna);
        System.out.println("Size of sr is : " + sr.size());
        System.out.println("Testing StorageResource:");
        processGenes(sr);
        }

    public static void main(String[] args) {
        Part1 gp = new Part1();
        //gp.testFindStopCodon();
        //gp.testfindGene();
        //gp.testGetAllGenes();
        //gp.testCgRatio();
        //gp.testCountCTG();
        gp.testProcessGenes();
        
    }
}

