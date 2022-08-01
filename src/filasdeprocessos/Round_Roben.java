package filasdeprocessos;

public class Round_Roben {

   public static void roundRoben(int processo[], int rest[], int cheg[], int exe[], int timeSlice, int tempTot){

        int posAtual = 0;
        String troca = "não";
        int slice = timeSlice;
        double tempoMedioEspera = 0;

        for (int tempAtual = 0; tempAtual < tempTot; tempAtual++) {
            
            if(slice == 0){
                troca = "SIM";
                if(proximoProc(rest, posAtual) == posAtual){
                    
                    break;
                    
                }else{
                    posAtual = proximoProc(rest, posAtual);
                }
               
               slice = timeSlice;
            }
            
            if (rest[posAtual] <= 0) {
                tempoMedioEspera += tempAtual - ( cheg[posAtual] + exe[posAtual]);
                slice = timeSlice;
                
                if(proximoProc(rest, posAtual) == posAtual){
                    
                    break;
                    
                }else{
                    posAtual = proximoProc(rest, posAtual);
                }
                
            }
            
            System.out.printf("Processo:   %d      Tempo restante:   %d       Tempo Atual:     %d        Trocado?     %s \n", processo[posAtual], rest[posAtual], tempAtual, troca);
            troca = "NÃO";
            rest[posAtual]--;
            slice--;
       }
        
        
        //Estatisticas
        
        System.out.println("TEMPO MÉDIO DE ESPERA: " +tempoMedioEspera / rest.length);
        
    }
   
   public static int proximoProc(int[] resto, int posAtual){
       int atual = posAtual;
       
       for (int i = posAtual; i < (resto.length * 2); i++) {
           posAtual++;
           if(posAtual == resto.length){
               posAtual = 0;
           }
           if(posAtual == atual){
               return posAtual;
           }
           if(resto[posAtual] > 0){
               return posAtual;
           }
       }
       
       return posAtual;
   }

}
