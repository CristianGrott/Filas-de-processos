package filasdeprocessos;

public class SJF_NPreemp {

    static void sjf(boolean preemp, int processo[], int cheg[], int exe[], int rest[], int numProces, int tempTot){

        int menor = 1000;
        int procesExecucao = -1;
        int espera[] = new int[numProces];
        int mediaTempEspera=0;

        //Em melhoria

        for(int temp=0; temp<=tempTot; temp++){
            if((procesExecucao==-1 && !preemp) || (preemp)){
                for(int i=0; i<numProces; i++){
                    if(rest[i]==exe[i]){
                        if(cheg[i]<=temp && rest[i]!=0){
                            if(rest[i]<=menor){
                                menor = rest[i];
                                procesExecucao = i;
                            }
                        }
                    }
                }
            }
            
            if(procesExecucao == -1){
                System.out.println(temp+ " - Nenhum processo pronto");
            }else{
                System.out.println(temp+ " - Processo: " +processo[procesExecucao]);
                
                if(exe[procesExecucao]==rest[procesExecucao]){
                    espera[procesExecucao] = temp - cheg[procesExecucao];
                }
                
                rest[procesExecucao]--;
                
                if(rest[procesExecucao]==0){
                    
                    procesExecucao = -1;
                    menor = 1000;
                }
            }
            
        }
        
        for(int i=0; i<numProces; i++){
                mediaTempEspera += espera[i];
        }
        
        mediaTempEspera /=numProces;
        
        System.out.println("Media tempo de espera: "+mediaTempEspera);

    }

}