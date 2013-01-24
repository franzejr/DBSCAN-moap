
package util;

import method.DBScan;
import arida.ufc.br.moap.core.imp.Parameters;
import arida.ufc.br.moap.datamodelapi.imp.ListModelImpl;
import arida.ufc.br.moap.datamodelapi.instances.imp.Instance;
import arida.ufc.br.moap.datamodelapi.instances.imp.InstancesBasedModelImpl;
import arida.ufc.br.moap.db.postgres.imp.PostgresqlProvider;
import functions.DistanceString;

/**
 *
 * @author franzejr
 * @author victorfarias
 * 
 */
public class Main {

    public static void main(String[] args) {
        
        /* 
         * Instanciação do algoritmo
         */
        DBScan clustering = new DBScan();
        
        /**
         * Indica qual a função de distância. Para o algoritmo neste exemplo, nós não utilizamos nenhuma função
         * 
         **/
        clustering.setDistanceFunction(new DistanceString());
        
        InstancesBasedModelImpl data = new InstancesBasedModelImpl();
        
        data = Importer.doImport();
        
        
        /*
         * Execução do algoritmo.
         */
        Parameters parameters = new Parameters();
        parameters.addParam("minPoints", 2);
        parameters.addParam("eps", 1.0);
        ListModelImpl<LocalCluster> res = (ListModelImpl<LocalCluster>) clustering.execute(data, parameters);
        
        /*
         * Ver o resultado. Iterar nos clusters encontrados
         */
        for(LocalCluster cluster : res.getInstances()){
            System.out.println("Cluster "+cluster.getId());
            System.out.println("Objetos:\n"+cluster.getObjects());
        }
        
        /*
         * Exemplo de uma função de distância
         */
        DistanceString funcao = new DistanceString();
        System.out.println("Distancia "+funcao.evaluate("maca", "banana"));
    }
}
