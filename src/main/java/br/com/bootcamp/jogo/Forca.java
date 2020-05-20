package br.com.bootcamp.jogo;

import java.util.Random;
import java.util.Scanner;

public class Forca {

    private static String[] dica, letrasChutadas;
    public static void main (String[] args){
        String palavraChave = null;
        int qtdLetra, vida = 0, qtdDigitadas = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Bem vindo ao jogo!\nQual dificuldade do jogo? F (Fácil); N (Normal); D (Difícil): ");
        String dificuldadeJogo = in.next().toUpperCase();

        switch (dificuldadeJogo){
            case "F":
                palavraChave = listaPalavraFacil();
                break;
            case "N":
                palavraChave = listaPalavraNormal();
                break;
            case "D":
                palavraChave = listaPalavraDificil();
                break;
            default:
                System.out.println("Nenhuma tecla válida.");
                break;
        }

        if (palavraChave != null) {
            String listaEmString = "", novaDica = null, auxiliar = null;
            montaDica(palavraChave.length());
            letrasChutadas = new String[(palavraChave.length() + 10)];
            // tamanho LetrasChutadas recebe tamanho da palavra mais 10 chances
            for (int i = 0; i < letrasChutadas.length;i++){
                letrasChutadas[i]=""; // preenche com espaço para depois comparar
            }
            for (String s : dica) {
                listaEmString += s;
            }
            System.out.println(listaEmString);
            String[] listaLetrasCorretas = palavraChave.split("");
            qtdLetra = palavraChave.length();

            do {
                System.out.println(auxiliar == null ? "Qual letra você chuta?":auxiliar+"\n"+novaDica+"\nQual letra você chuta?");
                String letraDigitada = in.next().toLowerCase();
                if (!verificaLetra(letraDigitada,qtdDigitadas)) {
                    boolean acertou = false;
                    novaDica = "";
                    qtdDigitadas++;
                    int i = 0;
                    while (i < qtdLetra) {
                        if (listaLetrasCorretas[i].equals(letraDigitada)) {
                            dica[i] = letraDigitada;
                            acertou = true;
                        }
                        i++;
                    }
                    vida += acertou ? 0 : 1; // if (acertou  == true) {vida=vida+0;} else {vida=vida+1;}
                    auxiliar = "Você ainda tem " + (10 - vida) + " vida (s) e as letras já digitadas foram: ";
                    for (String s : letrasChutadas) {
                        auxiliar += s+" ";
                    }
                    for (String s : dica) {
                        novaDica += s;
                    }
                } else {
                    System.out.print("Ops, você já digitou essa letra! ");
                }
            } while (!novaDica.equals(palavraChave) && vida < 10);
            System.out.println(novaDica.equals(palavraChave) ? //if (novaDica.equals(palavraChave))
                    "Parabéns!\nVocê acertou, a palavra era " + novaDica + "." : // true
                    "Poxa, que pena!\nVocê não acertou a palavra!"); // false
        }
    }

    public static String listaPalavraDificil(){
        String[] palavras = {"carambola","lichia","jatoba"};
        Random random = new Random();
        return palavras[random.nextInt(palavras.length)];
    }

    public static String listaPalavraNormal(){
        String[] palavras = {"abacaxi","melancia","melao"};
        Random random = new Random();
        return palavras[random.nextInt(palavras.length)];
    }

    public static String listaPalavraFacil(){
        String[] palavras = {"banana","uva","maca"};
        Random random = new Random();
        return palavras[random.nextInt(palavras.length)];
    }

    public static String[] montaDica(int qdtLetras){
        dica = new String[qdtLetras];
        for (int i = 0; i < dica.length; i++){
            dica[i] = " _ ";
        }
        return dica;
    }

    public static boolean verificaLetra(String letra, int qtdDigitada){
        boolean t = false;
        for(int i = 0;i < (letrasChutadas.length); i++){
            if (letrasChutadas[i].equals(letra)){
                t = true;
                break;
            }
        }
        letrasChutadas[qtdDigitada] = letra;
        return t;
    }
}