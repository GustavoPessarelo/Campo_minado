import java.util.Random;

public class GeraTabuleiro {
    // Preenche a matriz do tabuleiro somente com a informacao das vizinhancas, sem
    // as bombas nos locais as quais elas pertencem
    public static int[][] preencheProximidades(int[][] tabuleiro, int[][] bombas, int m, int n) {
        int tabuleiro2[][] = tabuleiro;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (bombas[i][j] == -1) {

                    // Casos de bomba nas quinas
                    if (i == 0 && j == 0) {
                        tabuleiro2[i + 1][j] += 1;
                        tabuleiro2[i][j + 1] += 1;
                        tabuleiro2[i + 1][j + 1] += 1;
                    } else if (i == 0 && j == n - 1) {
                        tabuleiro2[i + 1][j] += 1;
                        tabuleiro2[i][j - 1] += 1;
                        tabuleiro2[i + 1][j - 1] += 1;
                    } else if (i == m - 1 && j == 0) {
                        tabuleiro2[i - 1][j] += 1;
                        tabuleiro2[i][j + 1] += 1;
                        tabuleiro2[i - 1][j + 1] += 1;
                    } else if (i == m - 1 && j == n - 1) {
                        tabuleiro2[i - 1][j] += 1;
                        tabuleiro2[i][j - 1] += 1;
                        tabuleiro2[i - 1][j - 1] += 1;
                    }
                    // Linhas extremas, colunas do meio
                    else if (i == 0 && j != 0 && j != n - 1) {
                        tabuleiro2[i + 1][j] += 1;
                        tabuleiro2[i][j + 1] += 1;
                        tabuleiro2[i][j - 1] += 1;
                        tabuleiro2[i + 1][j - 1] += 1;
                        tabuleiro2[i + 1][j + 1] += 1;
                    } else if (i == m - 1 && j != 0 && j != n - 1) {
                        tabuleiro2[i - 1][j] += 1;
                        tabuleiro2[i][j + 1] += 1;
                        tabuleiro2[i][j - 1] += 1;
                        tabuleiro2[i - 1][j - 1] += 1;
                        tabuleiro2[i - 1][j + 1] += 1;
                    }
                    // Linhas do meio, colunas extremas
                    else if (j == 0 && i != 0 && i != n - 1) {
                        tabuleiro2[i + 1][j] += 1;
                        tabuleiro2[i - 1][j] += 1;
                        tabuleiro2[i][j + 1] += 1;
                        tabuleiro2[i + 1][j + 1] += 1;
                        tabuleiro2[i - 1][j + 1] += 1;
                    } else if (j == n - 1 && i != 0 && i != n - 1) {
                        tabuleiro2[i - 1][j] += 1;
                        tabuleiro2[i + 1][j] += 1;
                        tabuleiro2[i][j - 1] += 1;
                        tabuleiro2[i + 1][j - 1] += 1;
                        tabuleiro2[i - 1][j - 1] += 1;
                    }
                    // Bombas fora das bordas
                    else if (j != n - 1 && j != 0 && i != 0 && j != n - 1) {
                        tabuleiro2[i - 1][j] += 1;
                        tabuleiro2[i + 1][j] += 1;
                        tabuleiro2[i][j - 1] += 1;
                        tabuleiro2[i][j + 1] += 1;

                        tabuleiro2[i + 1][j + 1] += 1;
                        tabuleiro2[i + 1][j - 1] += 1;
                        tabuleiro2[i - 1][j + 1] += 1;
                        tabuleiro2[i - 1][j - 1] += 1;
                    }

                }
            }
        }

        return tabuleiro2;
    }

    public static int[][] geraBombas(int k, int m, int n){
        int [][] bombas = new int[m][n];

        Random rd = new Random();
        
        int linhaBomba, colunaBomba;

        // Preenche um tabuleiro somente com as bombas
        for (int x = 0; x < k; x++) {

            linhaBomba = rd.nextInt(m);
            colunaBomba = rd.nextInt(m);

            if(bombas[linhaBomba][colunaBomba] == -1){
                x = x-1;
            } else {
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (bombas[i][j] == 0) {
                            if (linhaBomba == i && colunaBomba == j) {
                                // Posição da bomba
                                bombas[i][j] = -1;
                            } else {
                                bombas[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }

        return bombas;
    }

    // Cria o tabuleiro completo
    public static String[][] completaTabuleiro(int[][] bombas, int tabuleiro2[][], int m, int n) {

        String[][] matrizCompleta = new String[m][n];

        // Junta a informacao da posicao das bombas com a informacao das vizinhacas das
        // bombas na matriz matrizCompleta, passando para string
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Se ha bomba na posicao, preenche com "*"
                if (bombas[i][j] == -1) {
                    matrizCompleta[i][j] = "*";
                }
                // Se nao ha bomba na posicao, Preenche com a informacao da vizinhanca das
                // bombas, sendo um inteiro maior ou igual a 0
                else if (bombas[i][j] != -1) {
                    matrizCompleta[i][j] = Integer.toString(tabuleiro2[i][j]);
                }
            }
        }

        return matrizCompleta;

    }

    public static String[][] geraNovo(int m, int n) {
        String[][] tabuleiroNovo = new String[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tabuleiroNovo[i][j] = "+";
            }
        }

        return tabuleiroNovo;
    }
    public static String[][] geraParcial(String[][] tabuleiro, String[][] novo, int m, int n, int cI, int cJ) {
        String[][] tabuleiroParcial = novo;

        if(tabuleiro[cI][cJ] != "*"){
            tabuleiroParcial[cI][cJ] = tabuleiro[cI][cJ];
        }

        return tabuleiroParcial;
    }
}
