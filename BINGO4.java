import java.util.Random;
import java.util.Scanner;

public class BINGO4 {
    public static String[] apresentacao() {
        Scanner scan = new Scanner(System.in);
        System.out.println("------------------BEM-VINDOS---------------------");
        System.out.println("----------------------AO-------------------------");
        System.out.println("------------------BINGO DA ADA-------------------");
        System.out.println("-------------------------------------------------");
        System.out.println("INFORME O NOME DOS JOGADORES SEPARADO POR HIFEN -");

        String jogadores = scan.nextLine();

        String[] players = jogadores.split("-");

        return players;
    }

    public static int[] gerarCartela() {
        int[] num = new int[5];
        Random random = new Random();

        int i = 0;
        while (i < num.length) {
            num[i] = random.nextInt(60);
            boolean colide = false;

            for (int j = 0; j < i; j++) {
                if (num[i] == num[j]) {
                    colide = true;
                    break;
                }
            }
            if (!colide) {
                i++;
            }
        }
        return num;
    }

    public static int[][] cartelasPlayers(String[] players) {
        Scanner scan = new Scanner(System.in);
        int[] cartela = new int[5];
        int[][] cartelaJogador = new int[players.length][5];


        System.out.println("Digite 1- Cartelas Manueais | 2- Cartelas automáticas ");
        int escolha = scan.nextInt();
        scan.nextLine();

        switch (escolha) {
            case 1:

                for (int i = 0; i < players.length; i++) {
                    System.out.println(players[i] + "Digite 5 números de 1 até 60, separando os jogos por \",\"");
                    String jogos = scan.nextLine();
                    String[] cartelasScan = jogos.split(",");
                    for (int j = 0; j < 5; j++) {
                        cartela[j] = Integer.parseInt(cartelasScan[j]);
                        cartelaJogador[i][j] = cartela[j];
                    }
                }

            case 2:
                for (int i = 0; i < players.length; i++) {
                    int[] aux = gerarCartela();
                    for (int j = 0; j < aux.length; j++) {
                        cartelaJogador[i][j] = aux[j];
                    }
                }
        }
        return cartelaJogador;

    }

    public static void inicio(String[] players, int[][] cartelasPlayers) {
        System.out.println("------------------------------------------------------------");
        System.out.println("-----------Cada Jogador terá sua respectiva cartela---------");
        System.out.println("------------------------------------------------------------");

        for (int i = 0; i < players.length; i++) {
            System.out.print((i + 1) + "-" + players[i]);
            for (int j = 0; j < 5; j++) {
                System.out.print("Recebe cartela " + cartelasPlayers[i][j]);
            }
            System.out.println(" ");

        }

    }

    public static int[] gerarSorteio() {
        int[] numSorteio = new int[5];
        Random random = new Random();

        int i = 0;
        while (i < numSorteio.length) {
            numSorteio[i] = random.nextInt(60);
            boolean colide = false;

            for (int j = 0; j < i; j++) {
                if (numSorteio[i] == numSorteio[j]) {
                    colide = true;
                    break;
                }
            }
            if (!colide) {
                i++;
            }
        }
        return numSorteio;
    }


    public static void main(String[] args) {
        String[] players = apresentacao();
        int[][] cartelaPlayers = cartelasPlayers(players);
        inicio(players, cartelaPlayers);


        Scanner scan = new Scanner(System.in);

        System.out.println("___________________________-");
        System.out.println("----BEM VINDO AO SORTEIO----");
        System.out.println("___________________________-");
        System.out.println("--1 para sorteio manual-----");
        System.out.println("--2 para sorteio automatico");
        int escolhe_sorteio = scan.nextInt();

        switch (escolhe_sorteio) {
            case 1:
                int[] numerosSorteados = new int[5];
                int[][] acertos = new int[players.length][5];
                int[] numAcertos = new int[players.length];
                int ganhador = 0;
                int rodada = 0;

                boolean sair = false;

                while (sair == false) {
                    System.out.println("Por favor, escolha 5 numeros de 0 a 60, não pode escolher numeros repetidos. Separas por \", \" ");
                    String numeros = scan.next();
                    scan.nextLine();
                    String[] numAux = numeros.split(",");
                    for (int i = 0; i < numAux.length; i++) {
                        numerosSorteados[i] = Integer.parseInt(numAux[i]);
                    }
                    for (int j = 0; j < players.length; j++) {
                        for (int k = 0; k < 5; k++) {
                            for (int l = 0; l < 5; l++) {
                                if (cartelaPlayers[j][k] == numerosSorteados[l]) {
                                    acertos[j][k] = -1;
                                    numAcertos[j] += 1;
                                }
                            }

                        }
                        if (numAcertos[j] == 5) {
                            sair = true;
                            ganhador = j;

                        }


                    }
                    System.out.println("Para continuar aperte qualquer tecla? OU X para sair e obter o resultado");
                    String resp = scan.next();
                    if (resp.equalsIgnoreCase("x")) {
                        sair = true;
                    }
                    rodada += 1;

                }
                System.out.println("O ganhador foi " + players[ganhador]);
                System.out.println("O número de rodadas foi " + rodada);


            case 2:
                int[] sorteioRandom = gerarSorteio();
                int[][] acertosRandom = new int[players.length][5];
                int[] numAcertosRandom = new int[players.length];
                int ganhadorRandom = 0;
                int rodadaRandom = 0;
                boolean sairRandom = false;


                for (int j = 0; j < players.length; j++) {
                    for (int k = 0; k < 5; k++) {
                        for (int l = 0; l < 5; l++) {
                            if (cartelaPlayers[j][k] == sorteioRandom[l]) {
                                acertosRandom[j][k] = -1;
                                numAcertosRandom[j] += 1;
                            }
                        }


                    }

                    if (numAcertosRandom[j] == 5) {
                        sairRandom = true;
                        ganhadorRandom = j;

                    }

                    rodadaRandom += 1;


                }
                System.out.println("O ganhador foi  " + players[ganhadorRandom]);
                System.out.println("O número de rodadas foi  " + rodadaRandom);


        }


    }
}





