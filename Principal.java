import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Declara as variaveis da primeria linha de entrada
        int m, n, k;

        m = 0;
        n = 0;

        int validadeDimensao = 0;
        while (validadeDimensao == 0) {
            System.out.println("Insira a dimensao do tabuleiro (minimo 2x2):");
            m = teclado.nextInt();
            n = m;

            if (2 <= m && 2 <= n) {
                validadeDimensao = 1;
            } else {
                System.out.println("Dimensao invalida, tente novamente.");
            }
        }

        System.out.println("Insira a quantidade de bombas:");
        k = teclado.nextInt();

        // Cria as matrizes
        // Cria primeiro uma matriz que recebe apenas a informacao da localizacao das
        // bombas
        // Apos isso, cria uma nova matriz que sera preenchida somente com a informacao
        // das vizinhancas das bombas
        int[][] bombas;
        int[][] tabuleiro;

        bombas = GeraTabuleiro.geraBombas(k, m, n);
        tabuleiro = new int[m][n];

        // Recebe o tabuleiro com as vizinhancas preenchidas, sem a posicao das bombas,
        // usando o metodo preencheProximidades, explicado adiante
        int[][] proximidades = GeraTabuleiro.preencheProximidades(tabuleiro, bombas, m, n);

        // Por fim, recebe o tabuleiro preenchido completamente, com as bombas como "*"
        // e as informacoes das vizinhancas das bombas
        // Usa o metodo completaTabuleiro
        String[][] tabuleiroCompleto = GeraTabuleiro.completaTabuleiro(bombas, proximidades, m, n);

        String[][] tabuleiroNovo = GeraTabuleiro.geraNovo(m, n);

        System.out.println();
        ImprimeTabuleiro.imprimeParcial(tabuleiroNovo, m, n);
        System.out.println();

        int flag = 0;

        int a, b, cI, cJ;

        a = 0;
        b = 0;
        cI = 0;
        cJ = 0;

        int jogadasPossiveis = n * m - k;
        int jogadasFeitas = 0;

        while (flag == 0) {

            int validadeJogada = 0;
            while (validadeJogada == 0) {
                System.out.println("Insira sua proxima jogada: ");
                System.out.print("Linha: ");
                a = teclado.nextInt();
                
                System.out.print("Coluna: ");
                b = teclado.nextInt();
                System.out.println();
                
                if (0 <= a && a <= m && 0 <= b && b <= n) {
                    validadeJogada = 1;
                    cI = a - 1;
                    cJ = b - 1;
                } else {
                    System.out.println("Jogada invalida, jogue novamente.");
                }
            }

            if (tabuleiroCompleto[cI][cJ] == "*") {
                System.out.println("Voce perdeu :(");
                System.out.println();
                ImprimeTabuleiro.imprimeCompleto(tabuleiroCompleto, m, n);
                System.out.println();
                flag = 1;
            } else {

                if (tabuleiroNovo[cI][cJ] == tabuleiroCompleto[cI][cJ]) {
                    System.out.println("Voce ja inseriu essa casa, jogue novamente.");
                    System.out.println();
                    ImprimeTabuleiro.imprimeParcial(tabuleiroNovo, m, n);
                    System.out.println();
                } else {
                    tabuleiroNovo = GeraTabuleiro.geraParcial(tabuleiroCompleto, tabuleiroNovo, m, n, cI, cJ);
                    ImprimeTabuleiro.imprimeParcial(tabuleiroNovo, m, n);
                    System.out.println();

                    jogadasFeitas = jogadasFeitas + 1;
                }
            }
            if (jogadasFeitas == jogadasPossiveis) {
                System.out.println("Voce ganhou XD XD");
                System.out.println();
                ImprimeTabuleiro.imprimeCompleto(tabuleiroCompleto, m, n);
                flag = 1;
            }
        }

        teclado.close();
    }
}
