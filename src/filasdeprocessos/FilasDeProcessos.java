package filasdeprocessos;

import java.util.Random;
import java.util.Scanner;

public class FilasDeProcessos {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Random aleat = new Random();
//Variáveis
        int n_processos, tempTot, aleator, escalona;

        System.out.print("Digite o número de processos que vão chegar: "); //Definindo a quantidade de processos
            n_processos = input.nextInt();

        int processo_ID [] = new int[n_processos];
        int tempo_execucao [] = new int[n_processos]; // Tempo de execução de cada processo
        int tempo_restante [] = new int [n_processos];
        int tempo_chegada [] = new int [n_processos]; // Tempo de chegada de cada processo
        int prioridade [] = new int [n_processos];
        int timeSlice;
        
        for(int i=0; i<processo_ID.length; i++){ // Identificando os processos
            processo_ID[i] = i+1;
        }

//Definindo se o tempo de execução de cada processo vai ser inserido manualmente ou aleatoriamente
        System.out.print("Definir o tempo de execução de cada processo aleatoriamente [1=S/2=N]: ");

        do{
                aleator = input.nextInt(); // Aleatorio ou manual

            System.out.println("----------------------------------------------------------------------");

            if(aleator!=2 && aleator!=1){
                System.err.println("ERRO!!! DIGITE UMA OPÇÃO VÁLIDA [1=S/2=N]: "); // Mensagem de erro, caso digite uma opção inválida
            }
        }while(aleator!=2 && aleator!=1); // Laço que retorna para a entrada do valor, caso ele seja inválido

        for(int i=0; i<n_processos; i++){
            if(aleator==1){
                tempo_execucao[i] = aleat.nextInt(15)+1; // Inserindo os tempos de execução aleatoriamente
                tempo_restante[i] = tempo_execucao[i];

                System.out.println("Processo " +processo_ID[i]+": " +tempo_execucao[i]);
            }else{
                System.out.print("Insira o tempo de execução do processo " +processo_ID[i]+ ": "); // Inserindo os tempos de execução manualmente

                do{
                        tempo_execucao[i] = input.nextInt(); // Tempo de execução
                        tempo_restante[i] = tempo_execucao[i];

                    if(tempo_execucao[i]<=0){
                        System.err.println("ERRO!!! DIGITE UM VALOR VÁLIDO"); // Mensagem de erro caso digite um valor inválido
                    }
                }while(tempo_execucao[i]<=0); // Laço que retorna para a entrada do valor, caso ele seja inválido
            }
        }

        aleator = 0; // Zerando a variável de escolha
        tempTot = tempo_execucao[0]; // Definindo o valor máximo que o próximo processo pode chegar na fila de execução
        tempo_chegada[0] = 0; // Definindo que o primeiro processo chega no momento zero

        System.out.println("======================================================================");

//Definindo se o momento de chegada de cada processo vai ser inserido manualmente ou aleatoriamente
        System.out.print("Definir o momento de chegada de cada proesso aleatoriamente [1=S/2=N]: ");

        do{
                aleator = input.nextInt(); // Aleatório ou manual

            System.out.println("----------------------------------------------------------------------");

            if(aleator!=2 && aleator!=1){
                System.err.println("ERRO!!! DIGITE UMA OPÇÃO VÁLIDA [1=S/2=N]: "); // Mensagem de erro, caso digite uma opção inválida
            }
        }while(aleator!=2 && aleator!=1); // Laço que retorna para a entrada do valor, caso ele seja inválido

        System.out.println("Momento de chegada do processo 1: " +tempo_chegada[0]);
        System.out.println("----------------------------------------------------------------------");

        for(int i=1; i<n_processos; i++){
            if(aleator==1){
                tempo_chegada[i] = aleat.nextInt((tempTot - tempo_chegada[i-1])+1)+tempo_chegada[i-1]; // Momento de chegada aleatoriamente

                System.out.println("Momento de chegada do processo " +processo_ID[i]+ ": " +tempo_chegada[i]);
            }else{
                System.out.println("Processo " +processo_ID[i]);
                System.out.print("Digite o momento de chegada (entre " +tempo_chegada[i-1]+ " e " +tempTot+ "): "); // Inserindo o momento de chegada manualmente

                do{
                        tempo_chegada[i] = input.nextInt(); // Momento de chegada manualmente

                    if(tempo_chegada[i]<tempo_chegada[i-1] || tempo_chegada[i]>tempTot){
                        System.err.println("ERRO!!! DIGITE UM VALOR VÁLIDO"); // Erro, caso digite um valor inválido
                    }
                }while(tempo_chegada[i]<tempo_chegada[i-1] || tempo_chegada[i]>tempTot); // Laço que retorna para a entrada do valor, caso ele seja inválido

                System.out.println("----------------------------------------------------------------------");
            }

            if((tempo_chegada[i]+tempo_execucao[i]) > tempTot){
                tempTot = tempo_chegada[i]+tempo_execucao[i];
            }
        }

        tempTot = 0; // Zerando a variável de tempo máximo
// Definindo se a prioridade do processo vai ser inserida aleatoriamente
        System.out.print("Definir a prioridade de cada processo aleatoriamente [1=S/2=N]: ");

        do{
                aleator = input.nextInt(); // Aleatorio ou manual

            System.out.println("----------------------------------------------------------------------");

            if(aleator!=2 && aleator!=1){
                System.err.println("ERRO!!! DIGITE UMA OPÇÃO VÁLIDA [1=S/2=N]: "); // Mensagem de erro, caso digite uma opção inválida
            }
        }while(aleator!=2 && aleator!=1); // Laço que retorna para a entrada do valor, caso ele seja inválido

        for(int i=0; i<n_processos; i++){
            if(aleator==1){
                prioridade[i] = aleat.nextInt((7-0)+1)+0; // Inserindo as prioridades aleatoriamente
                

                System.out.println("Processo " +processo_ID[i]+": " +prioridade[i]);
            }else{
                System.out.print("Insira a prioridade do processo " +processo_ID[i]+ " (entre 0 e 7): "); // Inserindo as prioridades manualmente

                do{
                        prioridade[i] = input.nextInt(); // Prioridade

                    if(prioridade[i]<0 || prioridade[i]>7){
                        System.err.println("ERRO!!! DIGITE UM VALOR VÁLIDO"); // Mensagem de erro caso digite um valor inválido
                    }
                }while(prioridade[i]<0 || prioridade[i]>7); // Laço que retorna para a entrada do valor, caso ele seja inválido
            }
        }

        System.out.println("-------------------------------------------");
        System.out.print("Digite o Time-Slice: ");
            timeSlice = input.nextInt();

        for(int i=0; i<n_processos; i++){ // Armazenando o tempo total que o processador vai rodar
            for(int j=tempo_execucao[i]; j>0; j--){
                tempTot++;
            }
        }

        System.out.println("-------------------------------------------");

        for(int i=0; i<n_processos; i++){ // Lista de processos
            System.out.println("PROCESSO " +processo_ID[i]);
            System.out.println("Momento da chegada: " +tempo_chegada[i]);
            System.out.println("Tempo de processamento: " +tempo_execucao[i]);
            System.out.println("Prioridade de processamento: " +prioridade[i]);
            System.out.println("-------------------------------------------");
        }

        System.out.println("TIME-SLICE: "+timeSlice);
        System.out.println("-------------------------------------------");

        System.out.println("Selecione o tipo de escalonamento de processos para processar a fila:");
        System.out.printf("[1] First-in First-out (FIFO) \n[2] Shortest Job First não preemptivo (SJF não preemp)"
                        + "\n[3] Shortest Job First preemptivo (SJF preemp)\n[4] Prioridade \n[5] Round Robin\n");

        do{
                escalona = input.nextInt();

            switch (escalona){
                case 1:
                    FIFO.fifo(tempo_chegada, tempo_execucao, n_processos); // Método que os processos são organizados por ordem de chegada
                    break;
                case 2:
                    SJF.sjf(false, processo_ID, tempo_chegada, tempo_execucao, tempo_restante, n_processos, tempTot); // Método que organiza os processos conforme o menor tempo de execução
                    break;
                case 3:
                    SJF.sjf(true, processo_ID, tempo_chegada, tempo_execucao, tempo_restante, n_processos, tempTot);
                    break;
                case 4:
                    Prioridade.prioridade(processo_ID, tempo_chegada, prioridade);
                    break;
                case 5:
                    Round_Roben.roundRoben(processo_ID, tempo_restante, tempo_chegada, timeSlice, tempTot);
                    break;
                default:
                    System.err.println("ERRO!!! DIGITE UMA OPÇÃO VÁLIDA");
            }

        }while(escalona<1 && escalona>4);
        System.out.println(tempTot);
    }
}