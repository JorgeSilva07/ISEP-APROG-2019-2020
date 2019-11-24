package trabalhopratico;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Esta classe, tal como o nome indica, serve de interface para uma melhor
 * orientação pelo programa e para permitir escolha do utilizador
 * 
 * @author Jorge Silva e Diogo Lima
 */
public class Menu {
    static final String FICHEIRO_LER = "PracticalWork.csv";
    static final String FICHEIRO_ESTATISTICAS = "Statistics.txt";
    static final String FICHEIRO_FINAL_STAGE = "FinalStage.csv";
    static final String FICHEIRO_FINAL_STAGE_GAMES = "FinalStageGames.txt";
    static Boolean apagar = false;
    static Scanner sc = new Scanner(System.in);
    
    public static void menuPrincipal(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws FileNotFoundException, IOException {
        System.out.println();
        System.out.println("|-----------------------------------|");
        System.out.println("| Campeonato Mundial de Futebol     | ");
        System.out.println("|-----------------------------------|");
        System.out.println("| 1 - Adicionar equipa              |");
        System.out.println("| 2 - Calcular pontuações           |");
        System.out.println("| 3 - Calcular Classificações       |");
        System.out.println("| 4 - Listagens                     |");
        System.out.println("| 5 - Ficheiros                     |");
        System.out.println("| 6 - Remover 3º e 4º classificados |");
        System.out.println("|-----------------------------------|");
        System.out.println("| 0 - Sair                          |");
        System.out.println("|-----------------------------------|");
        System.out.println();
        
        int num;
        System.out.println("Insira um nrº:");
        num = sc.nextInt();
        while (num < 0 || num > 6) {
            System.out.println("Número inválido insira outro nrº: ");
            num = sc.nextInt();
        }
        
        switch(num) {
            case 1: equipas_carregadas = Main.adicionaEquipa(equipas_string, equipas_int, equipas_carregadas);
                    menuPrincipal(equipas_string, equipas_int, equipas_carregadas); break;
                    
            case 2: Utils.calcularDG(equipas_int, equipas_carregadas);
                    Utils.calcularArmazenarPontos(equipas_int, equipas_carregadas);
                    menuPrincipal(equipas_string, equipas_int, equipas_carregadas); break;
                    
            case 3: Main.inserePos(equipas_int, equipas_string, equipas_carregadas); 
                    System.out.println("Classificações inseridas!");
                    menuPrincipal(equipas_string, equipas_int, equipas_carregadas); break;
                    
            case 4: listagens(equipas_string, equipas_int, equipas_carregadas); break;
            
            case 5: ficheiros(equipas_string, equipas_int, equipas_carregadas); break;
            
            case 6: if (equipas_int[0][0] == 0) {
                        System.out.println("É preciso calcular as classificações antes de remover o 3º e 4º classificado.");
                        menuPrincipal(equipas_string, equipas_int, equipas_carregadas); break;
                    }
                    else {
                        System.out.println("Ao remover os 3º e 4º classificados, não vai ser possível aceder a essas equipas.");
                        System.out.println("Se pretende remover digite 1, caso contrário 0");
                        int n = sc.nextInt();
                        while (n != 1 && n != 0) {
                            System.out.println("Número inválido. Digite 1 ou 0.");
                            n = sc.nextInt();
                        }
                        if (n == 1) {
                            System.out.println(equipas_carregadas);
                        //Main.removeLinha(equipas_string, equipas_int, equipas_carregadas, num);
                            equipas_carregadas = Main.removerEquipas(equipas_string, equipas_int, equipas_carregadas);
                            System.out.println(equipas_carregadas);
                            apagar = true;
                            System.out.println("Equipas removidas!");
                        } else {
                            System.out.println("Equipas não removidas!"); 
                        }
                        menuPrincipal(equipas_string, equipas_int, equipas_carregadas); break;
                    }
            case 0: System.exit(0); break;
        }
        
    }
    
    public static void listagens(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws FileNotFoundException, IOException {
        System.out.println();
        System.out.println("|-----------------------------------------------------------------------------------|");
        System.out.println("|                               Menu Listagens                                      | ");
        System.out.println("|-----------------------------------------------------------------------------------|");
        System.out.println("| 1 - Listar Classificações por grupo                                               |");
        System.out.println("| 2 - Listar equipas cujos golos marcados é igual ao máximo de golos marcados       |");
        System.out.println("| 3 - Listar equipas com determinado de golos sofridos                              |");
        System.out.println("| 4 - Listar equipas que têm mais golos sofridos do que marcados (alfabeticamente)  |");
        System.out.println("| 5 - Listar primeiro classificado de cada grupo                                    |");
        System.out.println("| 6 - Listar uma equipa                                                             |");
        System.out.println("|-----------------------------------------------------------------------------------|");
        System.out.println("| 0 - Voltar ao menu anterior                                                       |");
        System.out.println("|-----------------------------------------------------------------------------------|");
        System.out.println();
        
        int num;
        System.out.println("Insira um nrº: ");
        num = sc.nextInt();
        while (num < 0 || num > 6) {
            System.out.println("Número inválido insira outro nrº: ");
            num = sc.nextInt();
        }
        
        switch(num) {
            case 1: Utils.ordenar(equipas_int, equipas_string, equipas_carregadas);
                    Utils.listagem(equipas_string, equipas_int, equipas_carregadas);
                    listagens(equipas_string, equipas_int, equipas_carregadas); break;
            case 2: Main.gmIgualMg(equipas_string, equipas_int, equipas_carregadas); 
                    listagens(equipas_string, equipas_int, equipas_carregadas); break;
            case 3: Main.nGolosSofridos(equipas_string, equipas_int, equipas_carregadas);
                    listagens(equipas_string, equipas_int, equipas_carregadas); break;
            case 4: Main.mGs(equipas_string, equipas_int, equipas_carregadas); 
                    listagens(equipas_string, equipas_int, equipas_carregadas); break;
            case 5: Main.pClassificado(equipas_string, equipas_int, equipas_carregadas);
                    listagens(equipas_string, equipas_int, equipas_carregadas); break;
            case 6: Main.listarEquipa(equipas_string, equipas_int, equipas_carregadas); 
                    listagens(equipas_string, equipas_int, equipas_carregadas); break;
            case 0: menuPrincipal(equipas_string, equipas_int, equipas_carregadas); break;
        }
    }
    
    public static void ficheiros(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws FileNotFoundException, IOException {
        System.out.println();
        System.out.println("|---------------------------------------------|");
        System.out.println("|                 Menu Ficheiros              |");
        System.out.println("|---------------------------------------------|");
        System.out.println("| 1 - Criar ficheiro Statistics.txt           |");
        System.out.println("| 2 - Criar ficheiro FinalStage.csv           |");
        System.out.println("| 3 - Criar ficheiro FinalStageGames.txt      |");
        System.out.println("|---------------------------------------------|");
        System.out.println("| 4 - Visualizar ficheiro Statistics.txt      |");
        System.out.println("| 5 - Visualizar ficheiro FinalStageGames.txt |");
        System.out.println("|---------------------------------------------|");
        System.out.println("| 0 - Voltar ao menu anterior                 |");
        System.out.println("|---------------------------------------------|");
        System.out.println();
        
        int num;
        System.out.println("Insira um nrº: ");
        num = sc.nextInt();
        while (num < 0 || num > 6) {
            System.out.println("Número inválido insira outro nrº: ");
            num = sc.nextInt();
        }
        
        switch(num) {
            case 1: Main.estatisticas(equipas_string, equipas_int, equipas_carregadas, FICHEIRO_ESTATISTICAS);
                    ficheiros(equipas_string, equipas_int, equipas_carregadas); break;
            case 2: if (apagar == false) {
                        System.out.println("\nÉ preciso remover os 3º e 4º classificados primeiro!");
                        menuPrincipal(equipas_string, equipas_int, equipas_carregadas); break;
                    } else {
                        Main.escreverCSV(equipas_string, equipas_int, equipas_carregadas, FICHEIRO_FINAL_STAGE);
                        ficheiros(equipas_string, equipas_int, equipas_carregadas); break;
                    }
            case 3: if (apagar == false) {
                        System.out.println("\nÉ preciso remover os 3º e 4º classificados primeiro!");
                        menuPrincipal(equipas_string, equipas_int, equipas_carregadas); break;
                    } else {
                        Main.escreverTXT(equipas_string, equipas_int, equipas_carregadas, FICHEIRO_FINAL_STAGE_GAMES);
                        ficheiros(equipas_string, equipas_int, equipas_carregadas); break;
                    }
            case 4: Main.lerStatistics();
                    ficheiros(equipas_string, equipas_int, equipas_carregadas); break;
                    
            case 5: Main.lerFinalStageGames();
                    ficheiros(equipas_string, equipas_int, equipas_carregadas); break;
            
            case 0: menuPrincipal(equipas_string, equipas_int, equipas_carregadas); break;
        }
    }
    
}
