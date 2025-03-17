
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int firstOccurence = stringb.indexOf(stringa);
        if (firstOccurence == -1) {
            return false;
        } else {
            int secondOccurence = stringb.indexOf(stringa,firstOccurence + stringa.length());
            if (secondOccurence == -1) {
                return false;
            } else {
                return true;
            }
        }
    }
    
    public void testingtwoOccurences() {
        String stringa = "occ";
        String stringb = "cul";
        String stringc = "occultommculopiocca";
        System.out.println(stringa + " in " + stringc + " is " + twoOccurrences(stringa, stringc));
        System.out.println(stringb + " in " + stringc + " is " + twoOccurrences(stringb, stringc));
    }
    
    public String lastPart(String stringa, String stringb) {
        int firstOccurence = stringb.indexOf(stringa);
        if (firstOccurence == -1) {
            return stringb;
        } else {
            return stringb.substring(firstOccurence + stringa.length());
        }
    }
    
    public void testinglastPart() {
        String stringa = "occ";
        String stringb = "culq";
        String stringc = "occultommculopiocca";
        System.out.println(stringa + ", " + stringc + ", " + lastPart(stringa, stringc));
        System.out.println(stringb + ", " + stringc + ", " + lastPart(stringb, stringc));
    }
    
    public static void main(String[] args) {
        Part3 two = new Part3();
        two.testingtwoOccurences();
        two.testinglastPart();
    }
}
