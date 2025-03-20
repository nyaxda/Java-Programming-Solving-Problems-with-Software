
/**
 * Write a description of Part1 here.
 * 
 * @author (NyaxDa) 
 * @version (02.12.2025)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1 || (stopIndex - startIndex) % 3 != 0) {
            return dna.length();
        }
            return stopIndex;
    }
    
    public void testFindStopCodon() {
        String dna1 = "CCADSATGJSKKALKELTAAKDLKSDKTGA";
        int startIndex = dna1.indexOf("ATG");
        int stopCodon = findStopCodon(dna1, startIndex, "TAA");
        System.out.println(stopCodon);
    }
    
    public String findGene(String dna) {
        if (startIndex == -1) {
            return "not found";
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
        if (midIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, midIndex + 3);
    }
    
    public void testfindGene() {
        String dna1 = "CCADSATGJSKKALKELTAAKDLTAGKSDTGAKTGAKDSJTAGTASDJTGAJDKSJDTAG";
        String gene = findGene(dna1,dna1.indexOf("ATG"));
        System.out.println(gene);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, dna.indexOf("ATG", startIndex));
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
}
