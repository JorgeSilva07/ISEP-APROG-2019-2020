package trabalhopratico;

import java.io.IOException;
import java.text.Normalizer;


/**
 * Esta classe, Utils, contém métodos que, na nossa opinião, se enquandram melhor a serem 
 * criados aqui do que na classe Main.
 * 
 * @author Jorge Silva e Diogo Lima
 */
public class Utils {
    
    //Foi criado este método, porque as equipas que contêm acentos não eram detetadas
    public static String removerAcentos(String str) {
         return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
    
    public static boolean compararEquipa(String[][] equipas_string, String equipa, int equipasCarregadas) {
        int cont = 0;
        for (int i = 0; i < equipasCarregadas; i++) {
            if (equipas_string[i][1].equalsIgnoreCase(equipa)) {
                return true;
            }
        }    
        return false;
    }
    
    public static boolean compararGrupo(String[][] equipas_string, String grupo, int equipas_carregadas) {
        int cont = 0;
        for (int i = 0; i < equipas_carregadas; i++) {
            if (grupo.equals(equipas_string[i][0])) {
                cont++;
            }
            if (cont == 4) {
                System.out.println("O grupo encontra-se cheio (MAX 4 equipas)");
                return true;
            }
        }
        return false;
    }
    
    public static void calcularDG(int[][] equipas_int, int equipas_carregadas){
        for(int i = 0; i<equipas_carregadas;i++){
            equipas_int[i][8] = equipas_int[i][6] - equipas_int[i][7];
        }
    }
    
    //Alinea 3
    public static void calcularArmazenarPontos(int[][] equipas_int, int equipas_carregadas) {
        int vitorias, empates;
        //int[] arr_pontos = new int[equipas_carregadas];
        for (int i = 0; i < equipas_carregadas; i++) {
            vitorias = equipas_int[i][3];
            empates = equipas_int[i][4];
            equipas_int[i][1] = vitorias * 3 + empates * 1;
        }
        
        System.out.println("\nPontos calculados!");
    }
    
    //ordena o array tendo por base a alinea 4
    public static void ordenar(int[][] equipas_int, String[][] equipas_string, int equipas_carregadas) {
        for (int idx1 = 0; idx1 < equipas_carregadas - 1; idx1++) {
            for(int idx2 = idx1 + 1; idx2 < equipas_carregadas; idx2++) {
                if (equipas_string[idx1][0].compareTo(equipas_string[idx2][0]) > 0 ||
                        equipas_string[idx1][0].equals(equipas_string[idx2][0]) && equipas_int[idx1][1] < equipas_int[idx2][1] || 
                        equipas_string[idx1][0].equals(equipas_string[idx2][0]) && equipas_int[idx1][1] == equipas_int[idx2][1] && equipas_int[idx1][6] < equipas_int[idx2][6] ||
                        equipas_string[idx1][0].equals(equipas_string[idx2][0]) && equipas_int[idx1][1] == equipas_int[idx2][1] && equipas_int[idx1][6] == equipas_int[idx2][6] && equipas_int[idx1][8] < equipas_int[idx2][8] ||
                        equipas_string[idx1][0].equals(equipas_string[idx2][0]) && equipas_int[idx1][1] == equipas_int[idx2][1] && equipas_int[idx1][6] == equipas_int[idx2][6] && equipas_int[idx1][7] == equipas_int[idx2][7] && equipas_string[idx1][1].compareTo(equipas_string[idx2][1]) > 0) {
                  
                    
                        //Trocar nomes
                        String[] auxNomes = equipas_string[idx1];
                        equipas_string[idx1] = equipas_string[idx2];
                        equipas_string[idx2] = auxNomes;
                    
                        //Trocar pontos
                        int[] auxPontos = equipas_int[idx1];
                        equipas_int[idx1] = equipas_int[idx2];
                        equipas_int[idx2] = auxPontos;
                        
                }
                //inserePos(equipas_int, equipas_string, equipas_carregadas);                
            }
        }
    }
    
    public static void ordenarAlfabeticamente(int[][] equipas_int, String[][] equipas_string, int equipas_carregadas) {
        for (int i = 0; i < equipas_carregadas - 1; i++) {
            for (int j = i + 1; j < equipas_carregadas; j++) {
                if (equipas_string[i][1].compareTo(equipas_string[j][1]) > 0) {
                    //Trocar nomes
                        String[] auxNomes = equipas_string[i];
                        equipas_string[i] = equipas_string[j];
                        equipas_string[j] = auxNomes;
                    
                        //Trocar pontos
                        int[] auxPontos = equipas_int[i];
                        equipas_int[i] = equipas_int[j];
                        equipas_int[j] = auxPontos;
                }
            }
        }
    }
    
    public static void listagem(String[][] equipas_string, int[][] equipas_int, int equipas_carregadas) throws IOException {
        System.out.println();
        System.out.println("| Grp | Pos | Equipa          | Pts| J  | V  | E  | D  | GM | GS | GD |");
        System.out.println("|=====|=====|=================|====|====|====|====|====|====|====|====|");
        for (int i = 0; i < equipas_carregadas; i++) {
            System.out.printf("|%-5s|", equipas_string[i][0]);
            System.out.printf("%5s|", equipas_int[i][0]);
            System.out.printf("%-17s|", equipas_string[i][1]);
            for (int j = 1; j < equipas_int[i].length; j++) {
                System.out.printf("%4d|", equipas_int[i][j]);
                
            }
                
            System.out.println();
        }
        
        //Se fizer este comentário em baixo, altera o array completamente 
        //EX: menu listagens - 2, menu listagens - 1, vai mostrar as equipas cujos golos marcados é igaul ao nº max de golos marcados
        /*String n;
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPrima uma tecla para voltar ao menu anterior. 0 para sair.");
        n = sc.nextLine();
        if (n.equals("0")) 
            System.exit(0);
        else 
            Menu.listagens(equipas_string, equipas_int, equipas_carregadas);*/
        
    }

}
