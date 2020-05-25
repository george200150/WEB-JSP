package main;

public class TicTacToe { // the true game engine
    private String[][] table = new String[3][3];

    public TicTacToe() { // creates a 3 by 3 board with empty tiles
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = "";
            }
        }
    }

    public boolean isFree(int i, int j) {
        return table[i][j].equals("");
    } // means that the tile has no text value

    public String getValue(int i, int j) {
        return table[i][j];
    }

    public void setValue(int i, int j, String value) {
        table[i][j] = value;
    } // used when inserting 'X' or '0'

    public String getValues() { // return the game board in a (.csv) format
        StringBuilder values = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                values.append(table[i][j]).append(",");
            }
        }
        values = new StringBuilder(values.substring(0, values.length() - 1));
        return values.toString();
    }

    public String linie() { // check if there is any line completed
        for (int i = 0; i < 3; i++) {
            if (!table[i][0].equals("") && table[i][0].equals(table[i][1]) && table[i][0].equals(table[i][2])) {
                return table[i][0];
            }
        }
        return "";
    }

    public String coloana() { // check if there is any column completed
        for (int i = 0; i < 3; i++) {
            if (!table[0][i].equals("") && table[0][i].equals(table[1][i]) && table[0][i].equals(table[2][i])) {
                return table[0][i];
            }
        }
        return "";
    }

    public String diagonalaPrincipala() { // check if the first diagonal is completed
        if (!table[0][0].equals("") && table[0][0].equals(table[1][1]) && table[0][0].equals(table[2][2])) {
            return table[0][0];
        }
        return "";
    }

    public String diagonalaSecundara() { // check if the second diagonal is completed
        if (!table[0][2].equals("") && table[0][2].equals(table[1][1]) && table[0][2].equals(table[2][0])) {
            return table[0][2];
        }
        return "";
    }

    public String verifyWinner() { // the verifier functions return a string telling the user who the winner is
        String verify = linie() + coloana() + diagonalaPrincipala() + diagonalaSecundara();
        // concatenate all the strings together; only one function (at most) will return a non-empty string
        if (!verify.equals("")) {
            return verify;
        }
        return "";
    }

    public boolean verifyDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equals("")) { // if all the tiles are completed and there is no winner (verified before), it means the game ended as a draw
                    return false;
                }
            }
        }
        return true;
    }
}
