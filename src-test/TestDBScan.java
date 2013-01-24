

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import method.DBScan;

import org.junit.Before;
import org.junit.Test;

import arida.ufc.br.moap.core.imp.Parameters;
import arida.ufc.br.moap.datamodelapi.imp.ListModelImpl;
import functions.DistanceString;

import util.LocalCluster;

public class TestDBScan {

	@Test
	public void test() {
		  /* 
         * Instanciação do algoritmo
         */
        DBScan clustering = new DBScan();
        
        /**
         * Indica qual a função de distância. Para o algoritmo neste exemplo, nós não utilizamos nenhuma função
         * 
         **/
        clustering.setDistanceFunction(new DistanceString());
        
        /*
         * Criando um modelo simples de dados utilizando ListModelImpl que é igual a uma lista de elementos, no caso,
         * strings
         */
        ListModelImpl<String> data = new ListModelImpl<String>();
        data.add("mava");
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
