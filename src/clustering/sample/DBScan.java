/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering.sample;

import arida.ufc.br.moap.clustering.api.ICluster;
import arida.ufc.br.moap.clustering.api.IClusteringAlgorithm;
import arida.ufc.br.moap.core.imp.Parameters;
import arida.ufc.br.moap.core.imp.Reporter;
import arida.ufc.br.moap.core.spi.IDataModel;
import arida.ufc.br.moap.datamodelapi.imp.ListModelImpl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.lang.String;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author franzejr
 * @author victorfarias
 *
 * Esse é um exemplo de como criar um algoritmo de clusterização seguindo o
 * modelo.
 *
 * A classe abstrata IClusteringAlgorithm<T> requer a definição de T que é o
 * tipo a ser clusterizado. Neste exemplo utilizamos String. Ex.
 * IClusteringAlgorithm<String>.
 *
 * Esse algoritmo é bem simples. Ele simplesmente junta Strings que possuem o
 * mesmo tamanho. Neste caso, nenhuma função de distância é necessária.
 *
 *
 */
public class DBScan extends IClusteringAlgorithm<String> {

    private Set<String> corePoints;
    private Set<String> borderPoints;
    private Set<String> noisePoints;
    private Double eps;
    private Integer minPoints;
    private FuncaoDistanciaExemplo funcao;

    /*
     * Construtor padrão
     */
    public DBScan() {
    }

    /**
     * Este é o método que executa o algoritmo. O algoritmo recebe dados de
     * entrada e retorna um modelo com clusters StringCluster
     *
     * @param data Dados de entrada cujo tipo é T (String)
     * @param prmtrs Paramêtros para o algoritmo (não utilizado nesse exemplo)
     * @return ListModelImpl<StringCluster> modelo de dados com os cluster
     */
    @Override
    public ListModelImpl<LocalCluster> execute(IDataModel<String> data, Parameters prmtrs) {
        //report = new Reporter(DBScan.class);
        
        corePoints = new HashSet<String>();
        borderPoints = new HashSet<String>();
        noisePoints = new HashSet<String>();


        //report.setReport("Pegando min-Points e eps");
        eps = (Double) prmtrs.getParamValue("eps");
        minPoints = (Integer) (prmtrs.getParamValue("minPoints"));

        List<String> vizinhos = new ArrayList<String>();
        funcao = (FuncaoDistanciaExemplo) this.distanceFunction;
        
        //report.setReport("Definir ");

        for (String local : data.getInstances()) {
            vizinhos = getNeighbors(data, funcao, local);
            
            if (vizinhos.size() >= minPoints) {
                corePoints.add(local);
                if(borderPoints.contains(local))
                    borderPoints.remove(local);
                for (String vizinho : vizinhos) {
                    if (!(corePoints.contains(vizinho))) {
                        borderPoints.add(vizinho);

                        if (noisePoints.contains(vizinho)) {
                            noisePoints.remove(vizinho);
                        }
                    }
                }

            } else {
                if (!borderPoints.contains(local)) {
                    noisePoints.add(local);
                }
            }
        }
        
        
        List<LocalCluster> clusters = new ArrayList<LocalCluster>();
        Set<LocalCluster> markedPoints = new HashSet<LocalCluster>();
        Queue<String> queue = new LinkedBlockingQueue<String>();
        
        
//        for (String local : data) {
//            if(queue.isEmpty()){
//                
//            }
//            else{
//                for (String localInt : getNeighbors(data, funcao, local)) {
//                    queue.add(localInt);
//                }
//            }
//            
//        }
        
        return null;
    }

    /**
     *
     * @return Nome do algoritmo
     */
    @Override
    public String getName() {
        return "Clustering Example";
    }

    private List<String> getNeighbors(IDataModel<String> data, FuncaoDistanciaExemplo funcao, String local) {

        List<String> vizinhos = new ArrayList<String>();
        for (String localIter : data.getInstances()) {
            if (local!=localIter && funcao.evaluate(local, localIter) <=eps) {
                vizinhos.add(localIter);
            }

        }
        return vizinhos;
    }
}
