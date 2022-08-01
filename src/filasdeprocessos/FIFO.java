package filasdeprocessos;

public class FIFO {

    static void fifo(int cheg[], int exe[], int numProces){

        int tempomedioespera=0, tempTot=0;

        System.out.println("PROCESSO || TEMPO || TEMPO TOTAL || TEMPO DE ESPERA DE CADA PROCESSO || TEMPO DE CHEGADA DOS PROCESSOS");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        for(int i=0; i<numProces; i++){
            int aux = tempTot; // SALVA O TEMPO DE ESPERA DO PROCESSO
            tempomedioespera += (aux-cheg[i]); // CALCULANDO O TEMPO MÉDIO DE ESPERA

            for(int j=exe[i];j>0;j--){
                System.out.print("    " +(i+1)+ "    ||   " +j+ "   ||      " +tempTot+ "      ||                ");

                if(aux == tempTot){ //IF PARA IMPRIMIR APENAS UMA VEZ O TEMPO DE ESPERA
                    System.out.print((aux-cheg[i])+"                 ||"); 
                }else{
                    System.out.print("                  ||");
                }

                for(int x=0; x<numProces; x++){
                    if(cheg[x]==tempTot){
                        System.out.print(" **PROCESSO "+(x+1)+ " CHEGOU** ");
                    }
                }

                System.out.println("");

                tempTot+=1;
            }

            System.out.println("---------------------------------------------------------------------------------------------------------");

        }

        System.out.println("TEMPO MÉDIO DE ESPERA: " +tempomedioespera/numProces);

    }

}