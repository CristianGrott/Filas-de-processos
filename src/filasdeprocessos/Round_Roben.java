package filasdeprocessos;

public class Round_Roben {

   public static void roundRoben(int processo[], int rest[], int cheg[], int timeSlice, int tempTot){

        int posAtual = 0;
        String troca = "não";
        int slice = timeSlice;
        System.out.println("PROCESSO || TEMPO || TEMPO TOTAL || TEMPO DE ESPERA DE CADA PROCESSO || TEMPO DE CHEGADA DOS PROCESSOS");
        System.out.println("-------------------------------------------------------------------------------------------------------");

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
                slice = timeSlice;
                
                if(proximoProc(rest, posAtual) == posAtual){
                    
                    break;
                    
                }else{
                    posAtual = proximoProc(rest, posAtual);
                }
                
            }
            
            System.out.printf("Processo:   %d      Tempo restante:   %d       Tempo Atual:     %d        Trocado?     %s \n", processo[posAtual], rest[posAtual], tempAtual, troca);
            troca = "não";
            rest[posAtual]--;
            slice--;
            
       }
        
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
