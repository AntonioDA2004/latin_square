/**
 * LatinSquare is a class that manages all the functions related to the latin square.
 *
 * @author Antonio De Angelis
 * @version 1.0
 */
public class LatinSquare {

    /**
     * The matrix associated to the latin square.
     */
    private int[][] latinSquare;

    /**
     * The dimension of the latin square.
     */
    private int dimension;

    public LatinSquare(int dimension){
        latinSquare = new int[dimension][dimension];
        this.dimension = dimension;
    }

    public int getDimension() {
        return this.dimension;
    }

    public void latinSquareInserter(int row, int column, int number) {
        this.latinSquare[row-1][column-1] = number;
    }

    public void latinSquareSolver() {

        int[] possibleSolutions = new int[this.dimension];

        for(int i = 0; i < dimension; i++){
            possibleSolutions[i] = i + 1;
        }

        int[][][] solutionsMatrix = new int[this.dimension][this.dimension][this.dimension];

        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                solutionsMatrix[i][j] = possibleSolutions;
            }
        }

        System.out.println("\nYour incomplete latin square was:");
        this.latinSquareShower();

        System.out.println("\nThe solution of this latin square is:");

        for(int i = 0; i < this.dimension; i++){
            for(int j = 0; j < this.dimension; j++){
                int numberCell = this.latinSquare[i][j];
                if(numberCell != 0){
                    for(int row = 0; row < this.dimension; row++){
                        if(!(row == i) && !(solutionsMatrix[row][j].length == 1))
                            solutionsMatrix[row][j] = eliminateNumber(solutionsMatrix[row][j], numberCell);
                    }
                    for(int column = 0; column < this.dimension; column++){
                        if(!(column == j) && !(solutionsMatrix[i][column].length == 1))
                            solutionsMatrix[i][column] = eliminateNumber(solutionsMatrix[i][column], numberCell);
                    }
                }
            }
        }

        boolean continueChecking = true;

        while(continueChecking){
            continueChecking = false;
            for(int i = 0; i < this.dimension; i++){
                for(int j = 0; j < this.dimension; j++){
                    int cellLength = solutionsMatrix[i][j].length;
                    if(cellLength == 1){
                        int numberCell = solutionsMatrix[i][j][0];
                        for(int row = 0; row < this.dimension; row++){
                            if(!(row == i) && !(solutionsMatrix[row][j].length == 1)) solutionsMatrix[row][j] = eliminateNumber(solutionsMatrix[row][j], numberCell);
                        }
                        for(int column = 0; column < this.dimension; column++){
                            if(!(column == j) && !(solutionsMatrix[i][column].length == 1)) solutionsMatrix[i][column] = eliminateNumber(solutionsMatrix[i][column], numberCell);
                        }
                    } else continueChecking = true;
                }
            }
        }

        boolean validLatinSquare = true;
        LatinSquare latinSquareSolution = new LatinSquare(this.dimension);
        int row = 0;
        int column;

        while(row < this.dimension && validLatinSquare){
            column = 0;
            while(column < this.dimension && validLatinSquare){
                if(solutionsMatrix[row][column].length == 1){
                    int numberCell = solutionsMatrix[row][column][0];
                    latinSquareSolution.latinSquareInserter(row+1, column+1, numberCell);
                } else validLatinSquare = false;
                column++;
            }
            row++;
        }

        if(validLatinSquare) latinSquareSolution.latinSquareShower();
        else System.out.println("\nLatin square not valid.");
    }

    public void latinSquareShower(){
        System.out.println("\nLatin square:");
        for(int i = 0; i < this.getDimension(); i++){
            for(int j = 0; j < this.getDimension(); j++){
                System.out.print(this.latinSquare[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] eliminateNumber(int[] originalArray, int number){
        boolean found = false;
        int i = 0;

        while(i < originalArray.length && !found){
            if(originalArray[i] == number) found = true;
            else i++;
        }

        if(found) {
            int[] newArray = new int[originalArray.length - 1];
            int j = 0;
            for(int k = 0; k < originalArray.length; k++){
                if (originalArray[k] != number){
                    newArray[j] = originalArray[k];
                    j++;
                }
            }
            return newArray;
        } else return originalArray;
    }
}
