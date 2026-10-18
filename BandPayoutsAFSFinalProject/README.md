# Band Payouts
by Andres Succar.

Band Payouts splits the money of each concert among the members of a band.
For every gig it subtracts the expenses, pays the fixed amounts first
(the lead's bonus and the session musician's fee), and divides what is left
by percentages that must add up to 100.

It can be used in two ways:

- `BandWindow`: a Swing window with three tabs (Members, Gigs, Payouts).
- `Main`: a console menu.

Both read and write the same data file, `band.csv`.

# Files


`BandException.java`  Custom exception for invalid data and broken rules 
`Member.java` Parent class: regular member (name and percent) 
`LeadMember.java` Leader: percent plus a fixed bonus per gig 
`SessionMember.java` Hired musician: 0 percent and a fixed fee per gig 
`Gig.java` One concert: description, income, expenses, net 
`Band.java` Keeps members and gigs, calculates the split, removes members and gigs 
`Main.java` Console menu, and the save and load methods 
`BandWindow.java` GUI in one Window
`band.csv` Saved data (sample test data included) 
`scrsht/` Test screenshots and word documents

All eight `.java` files must be compiled together.

## How to run

You need a JDK 17 or newer (the project was tested with Temurin JDK 25).
Open a terminal in the project folder and run:

```
javac -d bin *.java
java -cp bin BandWindow
```

For the console menu, use `java -cp bin Main` instead.

**VS Code:** open the project folder (File, Open Folder), open
`BandWindow.java` and press Run. For the console version, run `Main.java`.
If nothing appears, use the Terminal tab (not Debug Console) and the
commands above.

**jGRASP:** open all the `.java` files, compile them, then run `BandWindow`
(or `Main`).

## Where the data is stored

The data is saved in a text file called `band.csv`. The program looks for a
folder named `BandPayoutsAFSFinalProject` in the place where it is started:

- If that folder exists, `band.csv` is saved and loaded inside it.
- If it does not exist, `band.csv` is saved and loaded in the current folder.

The path is printed in the terminal after saving or loading, for example
`Saved to BandPayoutsAFSFinalProject/band.csv`. Nothing is saved
automatically; press Save first.

E
