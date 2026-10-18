//Andres Succar
/**Main.java
 * App's control menu. 
 * It asks for data, does the calculations and shows results.
 * 
 */

import java.io.*;
import java.util.Scanner;

public class Main
{
    static final String FOLDER_NAME = "BandPayoutsAFSFinalProject";
    static final File DATA_FILE = findDataFile();

    static File findDataFile()
    {
        File folder = new File(FOLDER_NAME);
        if (folder.isDirectory())
        {
            return new File(folder, "band.csv");
        }
        return new File("band.csv");
    }

    public static void main(String[] args)
    {
        Band band = new Band();
        Scanner input = new Scanner(System.in);
        String option;

        do
        {
            System.out.println("\n1 Add member | 2 Add gig | 3 Payouts table | 4 Save | 5 Load | 0 Exit");
            System.out.print("Choose >> ");
            option = input.nextLine().trim();
            try
            {
                switch (option)
                {
                    case "1": addMember(band, input); break;
                    case "2": addGig(band, input); break;
                    case "3": showPayouts(band); break;
                    case "4": save(band); break;
                    case "5": band = load(band); break;
                    case "0": System.out.println("Goodbye"); break;
                    default: System.out.println("Invalid option");
                }
            }
            catch (BandException e)
            {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!option.equals("0"));

        input.close();
    }

    // Reads a line and converts it to double; if it is not a number, throws BandException
    static double number(Scanner input, String prompt) throws BandException
    {
        System.out.print(prompt);
        try
        {
            return Double.parseDouble(input.nextLine().trim());
        }
        catch (NumberFormatException e)
        {
            throw new BandException("That is not a valid number");
        }
    }

    static void addMember(Band band, Scanner input) throws BandException
    {
        System.out.print("Name >> ");
        String name = input.nextLine();
        System.out.print("Type (1 = Core, 2 = Lead, 3 = Session) >> ");
        String type = input.nextLine().trim();

        Member m;
        if (type.equals("1"))
        {
            m = new Member(name, number(input, "Percent >> "));
        }
        else if (type.equals("2"))
        {
            m = new LeadMember(name, number(input, "Percent >> "),
                    number(input, "Bonus per gig >> "));
        }
        else if (type.equals("3"))
        {
            m = new SessionMember(name, number(input, "Fee per gig >> "));
        }
        else
        {
            throw new BandException("Invalid member type");
        }
        band.addMember(m);
        System.out.println(m.getName() + " added");
    }

    static void addGig(Band band, Scanner input) throws BandException
    {
        System.out.print("Description >> ");
        String description = input.nextLine();
        band.addGig(new Gig(description, number(input, "Income >> "),
                number(input, "Expenses >> ")));
        System.out.println("Gig added");
    }

    static void showPayouts(Band band) throws BandException
    {
        double[][] matrix = band.buildPayoutMatrix();
        String[] names = band.getMemberNames();
        double[] totals = new double[names.length];

        System.out.printf("%-20s", "Gig");
        for (String n : names)
        {
            System.out.printf("%10s", n);
        }
        System.out.println();

        // Outer loop: one row per gig. Inner loop: one cell per member.
        for (int row = 0; row < matrix.length; row++)
        {
            System.out.printf("%-20s", band.getGigs().get(row).getDescription());
            for (int col = 0; col < matrix[row].length; col++)
            {
                System.out.printf("%10.2f", matrix[row][col]);
                totals[col] += matrix[row][col];
            }
            System.out.println();
        }

        System.out.printf("%-20s", "TOTAL");
        for (double t : totals)
        {
            System.out.printf("%10.2f", t);
        }
        System.out.println();
    }

    // One line per member and one per gig. getFixedPay() is the "extra" value:
    // bonus (LEAD), fee (SESSION) or 0 (CORE).
    static void save(Band band)
    {
        try (PrintWriter out = new PrintWriter(new FileWriter(DATA_FILE)))
        {
            for (Member m : band.getMembers())
            {
                out.println("MEMBER," + m.getType() + "," + m.getName() + ","
                        + m.getPercent() + "," + m.getFixedPay());
            }
            for (Gig g : band.getGigs())
            {
                out.println("GIG," + g.getDescription() + ","
                        + g.getIncome() + "," + g.getExpenses());
            }
            System.out.println("Saved to " + DATA_FILE.getPath());
        }
        catch (IOException e)
        {
            System.out.println("Could not save: " + e.getMessage());
        }
    }

    // Returns a new Band from the file, or the current one if loading fails
    static Band load(Band current)
    {
        Band newBand = new Band();
        try (Scanner file = new Scanner(DATA_FILE))
        {
            while (file.hasNextLine())
            {
                String[] p = file.nextLine().split(",");
                if (p[0].equals("MEMBER"))
                {
                    double percent = Double.parseDouble(p[3]);
                    double extra = Double.parseDouble(p[4]);
                    if (p[1].equals("LEAD"))
                    {
                        newBand.addMember(new LeadMember(p[2], percent, extra));
                    }
                    else if (p[1].equals("SESSION"))
                    {
                        newBand.addMember(new SessionMember(p[2], extra));
                    }
                    else
                    {
                        newBand.addMember(new Member(p[2], percent));
                    }
                }
                else if (p[0].equals("GIG"))
                {
                    newBand.addGig(new Gig(p[1], Double.parseDouble(p[2]),
                            Double.parseDouble(p[3])));
                }
            }
            System.out.println("Loaded from " + DATA_FILE.getPath());
            return newBand;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + DATA_FILE.getPath());
        }
        catch (Exception e)
        {
            System.out.println("The file is damaged: " + e.getMessage());
        }
        return current;
    }
}