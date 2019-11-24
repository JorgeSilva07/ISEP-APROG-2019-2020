package trabalhopratico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Nesta classe foram criados os principais métodos a serem usados na execução 
 * do programa
 * 
 * @author Jorge Silva e Diogo Lima
 */
public class Main {
    static Scanner sc;
    static final String FICHEIRO_LER = "PracticalWork.csv";
    static final int ARR_STR = 2; //parâmetros do array de strings
    static final int ARR_INT = 9; //parâmetros do array de ints
    static int EQUIPAS_MAX = 32; //tamanho do array que vai ser criado inicialmente
    
    
    //Alinea 1
    public static int lerFicheiro(String[][] equipas_string, int[][] equipas_int, String file) throws FileNotFoundException {
        sc = new Scanner(new File(file));
        int linhas = 0;
        sc.nextLine();
        while (sc.hasNextLine() && linhas < EQUIPAS_MAX) {
            String[] itens = sc.nextLine().split(",");
            equipas_string[linhas][0] = itens[0];
            equipas_string[linhas][1] = Utils.removerAcentos(itens[1]);
            for (int item = 2; item < itens.length; item++) {
                equipas_int[linhas][item] = Integer.parseInt(itens[item]);
            }
            linhas++;
        }
        sc.close();
        System.out.println("O ficheiro " + FICHEIRO_LER + " foi carregado em memória!");
        return linhas;
    }
  
    
    //Alinea 2
    public static int adicionaEquipa(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws IOException {
        //é preciso verificar se o nome da equipa já existe
        sc = new Scanner(System.in);
        String nome_equipa;
        String grupo;
        if (equipas_carregadas >= EQUIPAS_MAX) {
            System.out.println("Número de equipas permitidas excedido!");
            Menu.menuPrincipal(equipas_string, equipas_int, equipas_carregadas);
        }
        else {
            System.out.println("Insira o grupo: ");
            grupo = sc.nextLine();
            while(Utils.compararGrupo(equipas_string, grupo, equipas_carregadas)) {
                System.out.println("Insira outro grupo: ");
                grupo = sc.nextLine();
            }
            equipas_string[equipas_carregadas][0] = grupo; 
            
            System.out.println("Insira o nome da equipa: ");
            nome_equipa = sc.nextLine();
            while(Utils.compararEquipa(equipas_string, nome_equipa, equipas_carregadas)) {
                System.out.println("Insira outro nome: ");
                nome_equipa = sc.nextLine();
            }
            equipas_string[equipas_carregadas][1] = nome_equipa;
            
            System.out.println("Insira os jogos efetuados: ");
            equipas_int[equipas_carregadas][2] = sc.nextInt();
            System.out.println("Nº de vitórias: ");
            equipas_int[equipas_carregadas][3] = sc.nextInt();
            System.out.println("Nº de empates: ");
            equipas_int[equipas_carregadas][4] = sc.nextInt();
            System.out.println("Nº de derrotas: ");
            equipas_int[equipas_carregadas][5] = sc.nextInt();
            if (equipas_int[equipas_carregadas][3] + equipas_int[equipas_carregadas][4] + 
                    equipas_int[equipas_carregadas][5] != equipas_int[equipas_carregadas][2]) {
                System.out.println("O número de jogos tem de ser igual ao nº de vitórias + "
                        + "nº de empates + nº de derrotas\nA equipas não foi guardada!");
                Menu.menuPrincipal(equipas_string, equipas_int, equipas_carregadas);
            } else {
                System.out.println("Quantidade de golos marcados: ");
                equipas_int[equipas_carregadas][6] = sc.nextInt();
                System.out.println("Quantidade de golos sofridos: ");
                equipas_int[equipas_carregadas][7] = sc.nextInt();
                System.out.println("A equipa " + equipas_string[equipas_carregadas][1] + "foi guardada.");
                equipas_carregadas++;
            }
        }
        
        return equipas_carregadas;
    }
    
    
    
    
    
    
    //alinea 4 e 5
    
    //Este método insere as posições das equipas no array
    public static void inserePos(int[][] equipas_int, String[][] equipas_string, int equipas_carregadas) {
        int cont = 0;
        String grp = equipas_string[0][0]; // grp fica inicialmente com 'A'
        for (int i = 0; i < equipas_carregadas; i++) {
            if (grp.equals(equipas_string[i][0])) {
                cont++;
                equipas_int[i][0] = cont;
                
            }
            else {
                cont = 1;
                grp = equipas_string[i][0]; // grupo passa a ser o próximo caracter do array equipas_string
                equipas_int[i][0] = cont;
            }
        }
    }
    
    
    //Alinea 6
    public static void gmIgualMg(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws IOException{
        String[][] temp_string = new String[EQUIPAS_MAX][ARR_STR];
        int[][] temp_int = new int[EQUIPAS_MAX][ARR_INT];
        int maiorgolos = 0, cont = 0;
        for(int i=0;i<equipas_carregadas;i++){
            if(equipas_int[i][6]>maiorgolos)
                maiorgolos = equipas_int[i][6];
        }
        
        for(int i = 0; i < equipas_carregadas; i++) {
            if (equipas_int[i][6] == maiorgolos) {
               temp_string[cont] = equipas_string[i];
               temp_int[cont] = equipas_int[i];
               cont++;
            }
            
        }
        
        Utils.listagem(temp_string, temp_int, cont);
    }
    
    //Alinea 7
    public static void nGolosSofridos(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws IOException {
        String[][] temp_string = new String[EQUIPAS_MAX][ARR_STR];
        int[][] temp_int = new int[EQUIPAS_MAX][ARR_INT];
        sc = new Scanner(System.in);
        int cont = 0;
        
        System.out.println("Insira o número de golos sofridos: ");
        int n = Integer.parseInt(sc.nextLine()); // para não ficar informação do buffer
        
        for (int i = 0; i < equipas_carregadas; i++) {
            if (equipas_int[i][7] == n) {
                temp_string[cont] = equipas_string[i];
                temp_int[cont] = equipas_int[i];
                cont++;
            }
        }
        if (cont == 0) {
            System.out.println("Não há equipas que sofreram esse número de golos!");
        } else {
            Utils.listagem(temp_string, temp_int, cont);
        }
    }
    
    //Alinea 8
    public static void mGs(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws IOException {
        String[][] temp_string = new String[EQUIPAS_MAX][ARR_STR];
        int[][] temp_int = new int[EQUIPAS_MAX][ARR_INT];
        int cont = 0;
        
        for (int i = 0; i < equipas_carregadas; i++) {
            if (equipas_int[i][7] > equipas_int[i][6]) {
                temp_string[cont] = equipas_string[i];
                temp_int[cont] = equipas_int[i];
                cont++;
            }
        }
        
        if (cont == 0) {
            System.out.println("Não há equipas que têm mais golos sofridos que marcados!");
        } else {
            Utils.ordenarAlfabeticamente(temp_int, temp_string, cont);
            Utils.listagem(temp_string, temp_int, cont);
        }
    }
    
    //Alinea 9
    public static void pClassificado(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws IOException {
        String[][] temp_string = new String[EQUIPAS_MAX][ARR_STR];
        int[][] temp_int = new int[EQUIPAS_MAX][ARR_INT];
        int cont = 0;
        
        for (int i = 0; i < equipas_carregadas; i++) {
            if (equipas_int[i][0] == 1) {
                temp_string[cont] = equipas_string[i];
                temp_int[cont] = equipas_int[i];
                cont++;
            }
        }
        
        Utils.listagem(temp_string, temp_int, cont);
    }
    
    //Alinea 10
    public static void listarEquipa(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws IOException {
        String[][] temp_string = new String[1][ARR_STR];
        int[][] temp_int = new int[1][ARR_INT];
        sc = new Scanner(System.in);
        System.out.println("Insira o nome da equipa: ");
        String nome = sc.nextLine();
        
        if (Utils.compararEquipa(equipas_string, nome, equipas_carregadas)) {
            for (int i = 0; i < equipas_carregadas; i++) {
                if (nome.equals(equipas_string[i][1])) {
                    temp_string[0] = equipas_string[i];
                    temp_int[0] = equipas_int[i];
                }
            }
            
        Utils.listagem(temp_string, temp_int, 1);
        } else {
            System.out.println("\nNão existe nenhuma equipa com esse nome!");
            Menu.listagens(equipas_string, equipas_int, equipas_carregadas);
        }
       
    }
    //Alinea 11
    public static void estatisticas(String[][] equipas_string, int[][] equipas_int, int equipasCarregadas, String file) throws FileNotFoundException, IOException {
        int tjogos = 0, tvitorias = 0, tempates = 0, tderrotas = 0, tgm = 0, tgs = 0;
        double mgm, mgs; 
        
        for (int i = 0; i < equipasCarregadas; i++) {
            tjogos += equipas_int[i][2];
            tvitorias += equipas_int[i][3];
            tempates += equipas_int[i][4];
            tderrotas += equipas_int[i][5];
            tgm += equipas_int[i][6];
            tgs += equipas_int[i][7];
        }
        
        mgm = (double)tgm/tjogos;
        mgs = (double)tgm/tjogos;
        
        try (Formatter out = new Formatter(new File(file))) {
            out.format("Total de jogos= %d%n", tjogos);
            out.format("Total de vitorias= %d%n", tvitorias);
            out.format("Total de empates= %d%n", tempates);
            out.format("Total de golos marcados= %d%n", tgm);
            out.format("Total de golos sofridos= %d%n", tgs);
            out.format("Total de jogos= %d%n", tjogos);
            out.format("Média de golos marcados por jogo= %.1f%n", mgm);
            out.format("Total de golos sofridos por jogo= %.1f%n", mgs);
        }
        
        System.out.println("Estatisticas carregadas para ficheiro!");
    }
    
    //Alinea 12
    
    public static void removeLinha(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas, int pos) {
        for (int i = pos; i < equipas_carregadas; i++) {
            equipas_string[i] = equipas_string[i + 1];
            equipas_int[i] = equipas_int[i + 1];
        }
        
    }
    public static int removerEquipas(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) {
       for (int i = 0; i < equipas_carregadas; i++) {
           System.out.println(equipas_int[i][0]);
           if (equipas_int[i][0] == 3) { // verifica se a posição é igual a 3
               removeLinha(equipas_string, equipas_int, equipas_carregadas, i);
               equipas_carregadas--; // decrementa 1 equipa do array
                if (equipas_int[i][0] == 4) { /* O removeLinha remove a linha passada como parâmetro e guarda a posição posterior
                                                 na que foi eliminada. EX: Supondo que o i é 2 e equipas_int[2][0] = 3, o quarto classificado passa a ficar nessa
                                                 posição, ou seja, equipas_int[2][0] = 4. Por isso é necessário fazer esta verificação*/
                   removeLinha(equipas_string, equipas_int, equipas_carregadas, i);
                   equipas_carregadas--;
                }
           } 
       }
       return equipas_carregadas;
    }
    
    //Alinea 13
    public static void escreverCSV(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas, String File) throws IOException {
        try (FileWriter csvWriter = new FileWriter(File)) {
            csvWriter.append("Grupo");
            csvWriter.append(",");
            csvWriter.append("Classificacao no grupo");
            csvWriter.append(",");
            csvWriter.append("Equipa");
            csvWriter.append(",");
            csvWriter.append("Pontos");
            csvWriter.append("\n");
            for (int i = 0; i < equipas_carregadas; i++) {
                csvWriter.append(equipas_string[i][0]);
                csvWriter.append(",");
                csvWriter.append(Integer.toString(equipas_int[i][0]));
                csvWriter.append(",");
                csvWriter.append(equipas_string[i][1]);
                csvWriter.append(",");
                csvWriter.append(Integer.toString(equipas_int[i][1]));
                csvWriter.append("\n");
            }
            
            System.out.println("Ficheiro " + File + " criado!");
            //Não é preciso fechar o ficheiro porque o "try" já o assegura
        }
    }
    
    //Alinea 14
    public static void escreverTXT(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas, String File) throws FileNotFoundException {
        try (PrintWriter txtFile = new PrintWriter(File)) {
            txtFile.println("----JOGOS DA FASE FINAL----");
            for(int i = 0; i < equipas_carregadas - 2; i++) { //equipas_carregadas - 2, porque o último grupo joga sempre com o grupo anterior
                if (equipas_int[i][0] == 1) {
                    txtFile.print(equipas_string[i][0] + "," + equipas_int[i][0] +
                            "," + equipas_int[i][1] + " - " + equipas_string[i+3][0] +
                            "," + equipas_int[i+3][0] + "," + equipas_int[i+3][1]);
                    txtFile.println();
                } else if (equipas_int[i][0] == 2) {
                    txtFile.print(equipas_string[i][0] + "," + equipas_int[i][0] +
                            "," + equipas_int[i][1] + " - " + equipas_string[i+1][0] +
                            "," + equipas_int[i+1][0] + "," + equipas_int[i+1][1]);
                    txtFile.println();
                }
            }    
        }
        System.out.println("Ficheiro " + File + " criado!");
        //Não é preciso fechar o ficheiro porque o "try" já o assegura
    }
    
    // Os 2 métodos seguintes são opcionais e serve para mostrar o conteúdo dos ficheiros Statistics.txt e FinalStageGames.txt
    public static void lerStatistics() {
        String conteudo;
        try {
            conteudo = new Scanner(new File("Statistics.txt")).useDelimiter("\\Z").next();
            System.out.println(conteudo);
        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado!");
        } 
    }
    
    
    public static void lerFinalStageGames() {
        String conteudo;
        try {
            conteudo = new Scanner(new File("FinalStageGames.txt")).useDelimiter("\\Z").next();
            System.out.println(conteudo);
        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro não encontrado!");
        } 
    }
    
    
    public static void main(String[] args) throws IOException {
        String[][] equipas_string = new String[EQUIPAS_MAX][ARR_STR]; //GRP, EQUIPA
        int[][] equipas_int = new int[EQUIPAS_MAX][ARR_INT]; //POS, PTS, J, V, E, D, GM, GS, GD
        int equipasCarregadas = lerFicheiro(equipas_string, equipas_int , FICHEIRO_LER);
        Menu.menuPrincipal(equipas_string, equipas_int, equipasCarregadas);
        //removerEquipas(equipas_string, equipas_int, equipasCarregadas);
        /*Utils.calcularArmazenarPontos(equipas_int, equipasCarregadas);
        Utils.calcularDG(equipas_int,equipasCarregadas);
        Utils.listagem(equipas_string, equipas_int, equipasCarregadas);
        Utils.ordenar(equipas_int, equipas_string, equipasCarregadas);
        Utils.listagem(equipas_string, equipas_int, equipasCarregadas);
        equipasCarregadas = removerEquipas(equipas_string, equipas_int, equipasCarregadas);
        Utils.listagem(equipas_string, equipas_int, equipasCarregadas);
        escreverCSV(equipas_string, equipas_int, equipasCarregadas, FICHEIRO_FINAL_STAGE);
        escreverTXT(equipas_string, equipas_int, equipasCarregadas, FICHEIRO_FINAL_STAGE_GAMES);
        estatisticas(equipas_int, equipasCarregadas, FICHEIRO_ESTATISTICAS);
        //Menu.menuPrincipal(equipas_string, equipas_int, equipasCarregadas);
        //adicionaEquipa(equipas);
        //System.out.println(equipasCarregadas + " " + EQUIPAS_MAX);
        //listagem(equipas);
        //Utils.printStringMatrix(equipas);*/
        
        
    }
    
}
