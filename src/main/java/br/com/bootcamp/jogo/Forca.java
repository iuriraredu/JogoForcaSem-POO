package br.com.bootcamp.jogo;

import java.util.Random;
import java.util.Scanner;

public class Forca {
    private static String[] dica;
    public static void main (String[] args){
        String palavraChave = null, novaDica = "";
        int qtdLetra, vida = 0;
        System.out.println("Bem vindo ao jogo!");
        Scanner in = new Scanner(System.in);
        System.out.print("Qual dificuldade do jogo? F (Fácil); N (Normal); D (Difícil): ");
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
        String listaEmString = "";
        montaDica(palavraChave.length());
        for(String s : dica){
            listaEmString += s;
        }
        System.out.println(listaEmString);

        do {
            novaDica = "";
            System.out.println("Qual letra você chuta? ");
            String letraDigitada = in.next().toLowerCase();
            String[] listaLetrasCorretas = palavraChave.split("");
            qtdLetra = palavraChave.length();
            int i = 0;
            while (i < qtdLetra){
                if (listaLetrasCorretas[i].equals(letraDigitada)){
                    dica[i]=letraDigitada;
                }
                i++;
            }
            for (String s : dica){
                novaDica += s;
            }
            System.out.println(novaDica);
            vida++;
        } while (!(novaDica.equals(palavraChave)) && vida<10);
        if ((novaDica.equals(palavraChave))){
            System.out.println("Parabéns!\nVocê acertou a palavra! A palavra era "+novaDica+".");
        } else {
            System.out.println("Poxa, que pena!\nVocê não acertou a palavra!");
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
}
