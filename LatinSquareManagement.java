import java.util.Scanner;

/**
 * LatinSquareManagement is a class that manages all the functionalities of the latin square program.
 *
 * @author Antonio De Angelis
 * @version 1.0
 */
public class LatinSquareManagement {

    /**
     * The name of the LatinSquare object we are treating with.
     */
    LatinSquare latinSquare;

    /**
     * Constructor of the LatinSquareManagement class that makes the user choose an option from the menu.
     */
    public LatinSquareManagement() {
        int option;
        do {
            option = menu();
            executeOption(option);
        } while (option != 0);
    }

    /**
     * A method that shows the user the different options of the menu.
     *
     * @return An int which represents the option chosen from the menu
     */
    public int menu() {

        String menuText;

        menuText = """
                
                Tip: 
                 - You can use the Wolfram latin square generator at https://www.wolframcloud.com/objects/demonstrations/LatinSquaresPuzzleGenerator-source.nb.
                 - Click "numbers" and select "dimension". Then, click "new problem" and, if you wish, click "show solution".
                 - If latin square is not valid, it won't output anything.
                
                1. Create latin square.
                2. Insert latin square numbers.
                3. Solve latin square.
                4. Show initial latin square.
                0. Terminate the program.

                Enter a number between 0 and 4:\s""";

        int option = readNumber(0,4, menuText);

        return option;
    }

    /**
     * A method that forces a user to choose a number between n1 and n2, both included, displaying a text.
     * If the user chooses a number that is not in this range, the method will ask for a number again and re-display the text. This will occur until a valid number is chosen.
     *
     * @param n1 The lower limit
     * @param n2 The upper limit
     * @param text The text shown to the user
     * @return An int which represents the number chosen by the user
     */
    public int readNumber(int n1, int n2, String text) {
        Scanner scanner = new Scanner(System.in);
        int num;
        do {
            System.out.print(text);
            num = scanner.nextInt();
            if (num < n1)
                System.out.println("The number " + num + " is not greater than " + n1);
            else if (num > n2)
                System.out.println("The number " + num + " is not less than " + n2);
        } while (num < n1 || num > n2);
        return num;
    }

    /**
     * @param option
     */
    public void executeOption(int option) {

        switch(option){

            case 1:

            {

                String dimensionText = "\nPlease enter the dimension of your latin square (2-5): ";
                int dimension = readNumber(2,5, dimensionText);

                latinSquare = new LatinSquare(dimension);

                System.out.println("\nThe latin square with dimension " + dimension + " has been created.");

                break;
            }

            case 2: {

                if(latinSquare != null) {
                    int number;
                    int matrixDimension = latinSquare.getDimension();

                    do {

                        String numberText = "\nPlease enter the number you would like to insert in your latin square (0 - " + matrixDimension + ") or -1 to stop: ";
                        number = readNumber(-1, matrixDimension, numberText);

                        if(number > -1) {

                            String rowText = "\nPlease enter the row (1 - " + matrixDimension + "): ";
                            int row = readNumber(1, matrixDimension, rowText);

                            String columnText = "\nPlease enter the column (1 - " + matrixDimension + "): ";
                            int column = readNumber(1, matrixDimension, columnText);

                            latinSquare.latinSquareInserter(row, column, number);

                            latinSquare.latinSquareShower();

                        }

                    } while(number != -1);

                } else System.out.println("\nError: Latin square not created.");

                break;

            }

            case 3: {

                if(latinSquare != null) {
                    latinSquare.latinSquareSolver();
                } else System.out.println("\nError: Latin square not created.");

                break;
            }

            case 4: {

                if(latinSquare != null) {
                    latinSquare.latinSquareShower();
                } else System.out.println("\nError: Latin square not created.");
                break;
            }

            case 0: {
                System.out.print("\nProgram terminated.\n");
                break;
            }
        }

    }

    public static void main(String[] args) {
        LatinSquareManagement latinSquareManagement = new LatinSquareManagement();
    }

}
