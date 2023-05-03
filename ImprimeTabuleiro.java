public class ImprimeTabuleiro {

    public static void imprimeCompleto(String[][] tabuleiroCompleto, int m, int n) {
        // Imprime o tabuleiro completo
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tabuleiroCompleto[i][j]);
                if (j < n - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    public static void imprimeParcial(String[][] tabuleiroParcial, int m, int n) {
        // Imprime o tabuleiro Parcial
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tabuleiroParcial[i][j]);
                if (j < n - 1) {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

}
