package functions;

import util.LocalCluster;
import arida.ufc.br.moap.distance.spi.IDistanceFunction;

public class LocalDistance implements IDistanceFunction<LocalCluster> {

	@Override
	public String getName() {
		return "LocalDistance";
	}

	@Override
	public Double evaluate(LocalCluster arg0, LocalCluster arg1) {
		return null;
	}

}
