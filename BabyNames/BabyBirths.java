
/**
 * Write a description of BabyBirths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalGirlsNames = 0;
        int totalBoysNames = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                totalBoysNames++;
            } else {
                totalGirls += numBorn;
                totalGirlsNames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total births = " + totalGirls);
        System.out.println("total girls names = " + totalGirlsNames);
        System.out.println("total boys names = " + totalBoysNames);
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 1;
        int isThere = 0;
        int targetBirth = 0;
        String filename = "yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                isThere = 1;
                targetBirth = Integer.parseInt(rec.get(2));
                break;
            }
        }
        if (isThere == 1){
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (Integer.parseInt(rec.get(2)) >= targetBirth && rec.get(1).equals(gender)) {
                    if (rec.get(0).equals(name)){
                        break;
                    } else {
                        rank++;
                    }
                }
            }
        } else {
            return -1;
        }
        return rank;
    }
    
    public void testGetRank() {
        String name = "Frank";
        String gender = "M";
        int year = 1971;
        System.out.println("Rank of " + name + " in " + year + " is " + getRank(year, name, gender));
    }
    
    public String getName(int year, int rank, String gender) {
        String filename = "yob" + year + ".csv";
        String name = "NO NAME";
        FileResource fr = new FileResource(filename);
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                int currentRank = getRank(year, rec.get(0), gender);
                if(currentRank == rank) {
                    name = rec.get(0);
                    System.out.println("Name found is " + name + " and rank is " + currentRank);
                    break;
                }
            }
        }
        return name;
    }
    
    public void testGetName() {
        System.out.println(getName(1982, 450, "M"));
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank1 = getRank(year, name, gender);
        String newName = null;

        if(rank1 != -1) {
            System.out.println(rank1);
            newName = getName(newYear, rank1, gender);
            System.out.println("The new name is " + newName);
            if (newName.isEmpty()) {
                System.out.println("There is no match");
            } else {
                System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear + ".");
            }
        } else {
            System.out.println("There was no rank found");
        }  
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    
    public int yearOfHighestRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestYear = -1;
        int rank = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            System.out.println(f.getName());
            int year = Integer.parseInt(f.getName().substring(3,7));
            int currentRank = getRank(year, name, gender);
            if (currentRank != -1){
                if (rank == 0) {
                rank = currentRank;
                highestYear = year;
                } else {
                    if (currentRank < rank) {
                        rank = currentRank;
                        highestYear = year;
                    }
                } 
            }
        }
        return highestYear;
    }
    
    public void testyearOfHighestRank() {
        System.out.println(yearOfHighestRank("Mich", "M"));
    }
    
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        int totalCount = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            int year = Integer.parseInt(f.getName().substring(3, 7));
            int currentRank = getRank(year, name, gender);
            totalRank += currentRank;
            totalCount++;
        }
        if(totalRank < 0) {
            return -1;
        } else {
            return (double) totalRank / totalCount;
        }
    }
    
    public void testGetAverageRank() {
        String name = "Robert";
        String gender = "M";
        System.out.println("Average Rank for " + name + " is " + getAverageRank(name, gender));
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        String filename = "yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        int totalBirths = 0;
        int targetBirth = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                targetBirth = Integer.parseInt(rec.get(2));
                break;
            }
        }

        for (CSVRecord rec : fr.getCSVParser(false)) {
            int currentBirth = Integer.parseInt(rec.get(2));
            if (rec.get(1).equals(gender) && currentBirth >= targetBirth && !rec.get(0).equals(name)) {
                totalBirths += currentBirth;
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        String name = "Drew";
        String gender = "M";
        int year = 1990;
        System.out.println("Total births ranked higher than " + name + " of gender " + gender + " is " + getTotalBirthsRankedHigher(year, name, gender));
    }
}
