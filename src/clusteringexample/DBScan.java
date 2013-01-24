/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clusteringexample;

import arida.ufc.br.moap.core.imp.Parameters;
import arida.ufc.br.moap.datamodelapi.imp.ListModelImpl;
import clustering.sample.FuncaoDistanciaExemplo;
import clustering.sample.LocalCluster;

/**
 *
 * @author igobrilhante
 * 
 * Exemplo de execução de um simples algoritmo de clusterização
 */
public class DBScan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* 
         * Instanciação do algoritmo
         */
        clustering.sample.DBScan clustering = new clustering.sample.DBScan();
        
        /**
         * Indica qual a função de distância. Para o algoritmo neste exemplo, nós não utilizamos nenhuma função
         * 
         **/
        clustering.setDistanceFunction(new FuncaoDistanciaExemplo());
        
        /*
         * Criando um modelo simples de dados utilizando ListModelImpl que é igual a uma lista de elementos, no caso,
         * strings
         */
        ListModelImpl<String> data = new ListModelImpl<String>();
        data.add("maça");
        data.add("banan");
        data.add("125456789");
        data.add("1230567890");
        data.add("126454789");
        data.add("12445567890"); //11
        data.add("1");
        data.add("12345678901234567890");
        
        /*
         * Execução do algoritmo.
         * Tem como entrada os dados (data) e parametros(null). Para esse algoritmo, nenhuma parâmetro foi necessário
         * 
         * O resultado é armazenado em ListModelImpl<StringCluster>
         */
        Parameters parameters = new Parameters();
        parameters.addParam("minPoints", 2);
        parameters.addParam("eps", 1.0);
        ListModelImpl<LocalCluster> res = clustering.execute(data, parameters);
        
        /*
         * Ver o resultado. Iterar nos clusters encontrados
         */
//        for(LocalCluster cluster : res.getInstances()){
//            System.out.println("Cluster "+cluster.getId());
//            System.out.println("Objetos:\n"+cluster.getObjects());
//        }
        
        /*
         * Exemplo de uma função de distância
         */
        FuncaoDistanciaExemplo funcao = new FuncaoDistanciaExemplo();
        System.out.println("Distancia "+funcao.evaluate("maca", "banana"));
    }
}
