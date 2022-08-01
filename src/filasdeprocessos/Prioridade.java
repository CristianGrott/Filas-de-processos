package filasdeprocessos;

public class Prioridade {

    static void prioridade(int quantProces, int processo[], int cheg[], int prioridade[], int exe[], int resto[], int tempoTotal){
        
        boolean verificador = true;
        int tempoAtual=0;
        int processoAtual = 0;
        double tempoMedioEspera = 0;
        
        for(int i=0; i<1000; i++){
            
            
            if(processoAcabou(processoAtual, resto[processoAtual])){
                    tempoMedioEspera += tempoAtual - ( cheg[processoAtual] + exe[processoAtual]);
                if(processoPrioritario(processoAtual, prioridade, cheg, resto) > 0){
                    
                    
                    
                    processoAtual = processoPrioritario(processoAtual, prioridade, cheg, resto);
                }else{
                    break;
                }
                
            }
            
            
            
            System.out.printf("Processo:   %d      Tempo restante:   %d       Tempo Atual:     %d\n", processo[processoAtual], resto[processoAtual], tempoAtual);
            tempoAtual++;
            resto[processoAtual]--;
        }
        
        //estatisticas
        
        System.out.println("TEMPO MÉDIO DE ESPERA: "+tempoMedioEspera/exe.length);
        
        
        
    }
    
  
    public static boolean processoAcabou(int pos,int resto){
        if(resto > 0){
            return false;
        }
        return true;
        
    }
    
    public static int processoPrioritario(int pos, int[] prioridade, int[] chega, int[] resto){
       
        for (int pri = 7; pri > 0; pri--){ 
            for (int i = 0; i < prioridade.length; i++) {  
                if(prioridade[i] == pri && processoAcabou(pos,resto[i]) == false){
                    return i;
                }
            }
        }
        
        return checarProximoProx(resto); 
        
    }
    
    public static int checarProximoProx(int[] resto){
        for (int i = 0; i < resto.length; i++) {
            if(resto[i] > 0){
                return i;
            }
        }
        return -1;
    }
    
    
    
}
