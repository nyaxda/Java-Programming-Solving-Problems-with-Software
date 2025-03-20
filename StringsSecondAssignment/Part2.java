
/**
 * Write a description of Part2 here.
 * 
 * @author (Daniel Nyakundi) 
 * @version (02.12.2025)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int count = 0;
        int currIndex = 0;
        while (currIndex != -1) {
            currIndex = stringb.indexOf(stringa, currIndex);
            if (currIndex == -1) {
                break;
            } else if ((currIndex + stringa.length() > stringb.length())){
                break;
            } else {
                currIndex = currIndex + stringa.length();
            }
            count++;
        }
        return count;
    }
    
    public void testHowMany() {
        String stringb = "REDSAGSDVAADFHEASDGASDFD";
        String stringa = "A";
        int count = howMany(stringa, stringb);
        System.out.println(count);
    }
}
