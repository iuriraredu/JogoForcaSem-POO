package br.com.bootcamp.jogo;

import java.util.Random;
import java.util.Scanner;

public class Forca {
    private static String[] dica;
    public static void main (String[] args){
        String palavraChave = null, novaDica = "";
        int qtdLetra, vida = 0;
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
        String listaEmString = "";
        montaDica(palavraChave.length());
        for(String s : dica){
            listaEmString += s;
        }
        System.out.println(listaEmString);
        String[] listaLetrasCorretas = palavraChave.split("");
        qtdLetra = palavraChave.length();

        do {
            boolean acertou = false;
            int i = 0;
            novaDica = "";
            System.out.println("Qual letra você chuta? ");
            String letraDigitada = in.next().toLowerCase();
            while (i < qtdLetra){
                if (listaLetrasCorretas[i].equals(letraDigitada)){
                    dica[i] = letraDigitada;
                    acertou = true;
                }
                i++;
            }
            vida += acertou ? 0:1; // if (acertou  == true) {vida=vida+0;} else {vida=vida+1;}
            for (String s : dica){
                novaDica += s;
            }
            System.out.println(novaDica);
        } while (!novaDica.equals(palavraChave) && vida<10);
        System.out.println(novaDica.equals(palavraChave) ? "Parabéns!\nVocê acertou, a palavra era "+novaDica+".":"Poxa, que pena!\nVocê não acertou a palavra!");
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