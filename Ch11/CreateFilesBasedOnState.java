//Andres Succar
//p.471

import java.nio.file.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;
import java.text.*;

public class CreateFilesBasedOnState {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Path inStateFile = Paths.get("InStateCusts.txt");
        Path outOfStateFile = Paths.get("OutOfStateCusts.txt");
        final String id_format = "000";
        final String name_format = "        ";
        final int name_length = name_format.length();
        final String home_state = "WI";
        final String balance_format = "0000.00";
        String delimiter = ",";
        String s = id_format + delimiter + name_format +
        delimiter + home_state + delimiter + balance_format +
        System.getProperty("line.separator");
        final int recsize = s.length();
        FileChannel fcIn = null;
        FileChannel fcOut = null;
        String idString;
        int id;
        String name;
        String state;
        double balance;
        final String quit = "999";
        createEmptyFile(inStateFile, s);
        createEmptyFile(outOfStateFile, s);
        try
        {
            fcIn = (FileChannel)Files.newByteChannel(inStateFile, CREATE, WRITE);
            fcOut = (FileChannel)Files.newByteChannel(outOfStateFile, CREATE, WRITE);
            System.out.print("Enter customer ID number >> ");
            idString = input.nextLine();
            while(!(idString.equals(quit)))
            {
                id = Integer.parseInt(idString);
                System.out.print("Enter name for customer #" + id + " >> ");
                name = input.nextLine();
                StringBuilder sb = new StringBuilder(name);
                sb.setLength(name_length);
                System.out.print("Enter state >> ");
                state = input.nextLine();
                System.out.print("Enter balance >> ");
                balance = input.nextDouble();
                input.nextLine();
                DecimalFormat idFormat = new DecimalFormat(id_format);
                DecimalFormat balFormat = new DecimalFormat(balance_format);
                s = idFormat.format(id) + delimiter + sb.toString() +
                    delimiter + state + delimiter + balFormat.format(balance) +
                    System.getProperty("line.separator");
                byte[] data = s.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(data);
                if(state.equals(home_state))
                {
                    fcIn.position(id * recsize);
                    fcIn.write(buffer);
                }
                else
                {
                    fcOut.position(id * recsize);
                    fcOut.write(buffer);
                }
                System.out.print("Enter next customer ID number or " +
                    quit + " to quit >> ");
                idString = input.nextLine();
            }
            fcIn.close();
            fcOut.close();
        }
        catch(Exception e)
        {
            System.out.println("Error message: " + e);
        }
    }

    public static void createEmptyFile(Path file, String s) {
        final int numrecs = 1000;
        try
        {
            OutputStream outputStr =
                new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                new BufferedWriter(new OutputStreamWriter(outputStr));
            for(int count = 0; count < numrecs; ++count)
                writer.write(s);
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("Error message: " + e);
        }
    }
}
