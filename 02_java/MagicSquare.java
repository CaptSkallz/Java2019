import java.util.Arrays;

public class MagicSquare {

  public static void main(String[] args) {
    int n = 9;
    if (magic(n) != null) {
      vypis(magic(n));

      if (isMagic(magic(n))) {
        System.out.println("je magicky" + '\n');
      } else {
        System.out.println("nie je magicky" + '\n');
      }
    }
    else {
      System.out.println("Zadali ste parne cislo");
    }
  }

  public static int[][] magic(int size) {
    if (size == 2) {
      return null;
    }
    int[][] res = new int[size][size];
    if (size % 2 != 0) {
      res = magicEven(size);
    } else {
      return null;
    }
    // if (size % 4 == 0) {
    // res = magicOdd1(size);
    // }
    return res;
  }

  public static int[][] magicEven(int size) { // Siamese_method
                        // http://thospel.home.xs4all.nl/siamese.html
    int[][] res = new int[size][size];
    int posX = size / 2, posY = 0; // posX - stlpec, posY riadok
    res[posY][posX] = 1; // pociatocny prvok - prvy riadok v strede
    for (int i = 2; i <= size * size; i++) { // od 2 po velkost^2
      if (posY - 1 >= 0 && posX + 1 <= size - 1
          && res[posY - 1][posX + 1] == 0) { // ak je vpravo a hore v poly a je tam 0
        posX = posX + 1;
        posY = posY - 1;
      } else if (res[(posY - 1 + size) % size][(posX + 1 + size) % size] == 0) { // ak je mimo pola
        posX = (posX + 1 + size) % size;
        posY = (posY - 1 + size) % size;
      } else {
        posY = (posY + 1 + size) % size; // ak je vpravo hore plno chod
                          // o 1 dole
      }
      res[posY][posX] = i;
    }
    return res;
  }

  public static int[][] magicOdd1(int size) { // zistil som ze netreba :)
    int[][] res = new int[size][size];
    int napln = 1;
    for (int i = 0; i < res.length; i++) {
      for (int j = 0; j < res[i].length; j++) {
        res[i][j] = napln;
        napln++;
      }
    }

    return res;
  }

  // /////////////// FUNCKIE NA OVEROVANIE MAGIC //////////////////////////

  public static boolean isMagic(int[][] sq) {
    if (!isUniq(sq)) {
      return false;
    }
    int sum = 0, temp1 = 0, temp2 = 0, diag1 = 0, diag2 = 0;
    for (int i = 0; i < sq.length; i++) {
      sum += sq[0][i];
    }
    for (int i = 0; i < sq.length; i++) {
      for (int j = 0; j < sq[i].length; j++) {
        temp1 += sq[i][j]; // kontrola riadkov
        temp2 += sq[j][i]; // kontrola stlpcov
        if (i == j) { // diagonala1
          diag1 += sq[i][j];
        }
        if (i + j == sq.length - 1) { // diagonala2
          diag2 += sq[i][j];
        }
      }
      if (temp1 != sum || temp2 != sum) {
        return false;
      }
      temp1 = 0;
      temp2 = 0;
    }
    if (diag1 != sum || diag2 != sum) {
      return false;
    }
    return true;
  }

  public static boolean isUniq(int[][] sq) {
    int[] sq_copy = new int[sq.length * sq.length];
    int next = 0;
    for (int i = 0; i < sq.length; i++) {
      for (int j = 0; j < sq[i].length; j++) {
        sq_copy[next] = sq[i][j];
        next++;
      }
    }
    Arrays.sort(sq_copy);
    for (int i = 0; i < sq_copy.length; i++) {
      if (i != sq_copy.length - 1) {
        if (sq_copy[i] == sq_copy[i + 1]) {
          return false;
        }
      }
    }
    return true;
  }

  public static void vypis(int[][] sq) {
    for (int i = 0; i < sq.length; i++) {
      for (int j = 0; j < sq[i].length; j++) {
        System.out.print(sq[i][j] + " ");
      }
      System.out.println();
    }
  }

}
