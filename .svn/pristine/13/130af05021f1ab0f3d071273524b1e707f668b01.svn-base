package simulador;


import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.tools.rma.rma;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;



import simulador.suport.PropertiesLoaderImpl;

/**
 * Classe que realiza o startup do software SimShip.
 * <p>
 * Os parametros que gerem a inicialização sao obtidos do arquivo SimShip.properties 
 * 
 * @author taranti
 * 
 */
public class SimuladorStarterASP {

	private static SimuladorStarterASP singleton = null;

	private Runtime rt;

	//private static rma remoteAgentManager;

	
	
	private static SimuladorStarterASP getInstance() {
		if (singleton == null) {
			singleton = new SimuladorStarterASP();
		}
		return singleton;
	}

	private SimuladorStarterASP() {

		rt = Runtime.instance();
		try {
			createSeaTeather(rt);
		} catch (ControllerException e) {
			e.printStackTrace();
		}
	}

	
	private static void createSeaTeather(Runtime rt) throws ControllerException {

		// instanciando MainConteiner (JADE)
		Profile m = new ProfileImpl(true);
		m.setParameter("detect-main", "false"); // to avoid a bug in jade 3.5
		m.setParameter("local-port", PropertiesLoaderImpl.findJadePort());
		m.setParameter("port", PropertiesLoaderImpl.findJadePort());
		ContainerController myMain = rt.createMainContainer(m);

		// instanciando SeaAgents
		AgentController seaAgent;

		// O código abaixo habilita o RMA -Remot Monitoring Agent - interface
		// grafica - não usar na DMZ
		if(PropertiesLoaderImpl.findDebug()){
				 AgentController myRMA = myMain.createNewAgent("RMA",
		 "jade.tools.rma.rma", new Object[0]);
		 myRMA.start();
		 
		 assert (myRMA != null) : "agente RMA nao instanciado";
		 
		}
	
		assert (PropertiesLoaderImpl.findTimeInterval() > 0 ): "valor TimeInterval é menor ou igual a zero";
			
		int intervalo = PropertiesLoaderImpl.findTimeInterval();
		int contTotalNavios = 1;

		// Agente responsavel por reponder requisição
		seaAgent = myMain.createNewAgent("gatewayAgent",
				"simulador.agents.GatewayAgent", new Object[0]);
		seaAgent.start();
		
		assert (seaAgent != null) : "agente transducer simulador.agents.GatewayAgent nao instanciado";

		
		assert (PropertiesLoaderImpl.findNrSimShip() >= 0 ): "PropertiesLoaderImpl.findNrSimShip() < 0";
		
		// criação de navios
		while (contTotalNavios <= PropertiesLoaderImpl.findNrSimShip()) {

			String shipImonumber = String.valueOf(contTotalNavios);
			while (shipImonumber.length() < 6)
				shipImonumber = "0" + shipImonumber;

			seaAgent =null; //para validar assertiva abaixo
			
			
			assert ((Integer.parseInt(PropertiesLoaderImpl.findCgCode()) > -1 )&& (Integer.parseInt(PropertiesLoaderImpl.findCgCode()) < 10)): "CG Code fora dos valores (-1<CG<10) valor: "+ PropertiesLoaderImpl.findCgCode() ;
			
			seaAgent = myMain.createNewAgent("Ship"
					+ PropertiesLoaderImpl.findCgCode() + shipImonumber,
					"simulador.agents.ASPShipAgent", new Object[0]);
			seaAgent.start();
			
			assert (seaAgent != null) : "agente simulador.agents.ASPShipAgent "+ shipImonumber +" nao instanciado";
			
			

			contTotalNavios++;// conta o Total Navios

			try {
				Thread.sleep(intervalo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		
		assert (contTotalNavios-1 == PropertiesLoaderImpl.findNrSimShip()): "instanciado o numero errado de navios simulador.agents.ASPShipAgent";

		contTotalNavios = 999998;

		assert (PropertiesLoaderImpl.findNrDelayedSimShip() >= 0 ): "PropertiesLoaderImpl.findNrDelayedSimShip() < 0";
		
		while (contTotalNavios > (999998 - PropertiesLoaderImpl
				.findNrDelayedSimShip())) {

			String shipImonumber = String.valueOf(contTotalNavios);
			while (shipImonumber.length() < 6)
				shipImonumber = "0" + shipImonumber;

			seaAgent =null; //preparacao para assertica abaixo
			seaAgent = myMain.createNewAgent("Ship"
					+ PropertiesLoaderImpl.findCgCode() + shipImonumber,
					"simulador.agents.ASPShipDelayAgent", new Object[0]);
			seaAgent.start();
			assert (seaAgent != null) : "agente simulador.agents.ASPShipDelayAgent "+ shipImonumber +" nao instanciado";
			
			
			contTotalNavios = contTotalNavios - 1;// conta o Total Navios

			try {
				Thread.sleep(intervalo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		assert (contTotalNavios == 999998 - PropertiesLoaderImpl
				.findNrDelayedSimShip()): "instanciado o numero errado de navios simulador.agents.ASPShipDelayAgent";
	

		if (PropertiesLoaderImpl.findPortTest()) {

			String shipImonumber = "999999";

			seaAgent = null;
			seaAgent = myMain.createNewAgent("Ship"
					+ PropertiesLoaderImpl.findCgCode() + shipImonumber,
					"simulador.agents.ASPShipAgentPortTest", new Object[0]);
			seaAgent.start();
			assert (seaAgent != null) : "agente simulador.agents.ASPShipAgentPortTest "+ shipImonumber +" nao instanciado";
			

		}

	}

	/**
	 * Metodo que inicializa a simulacao.
	 * Realiza a instanciação de todos agentes do sistema
	 *   
	 * @param args nao sao previstos parametros para o startup do sistema
	 */
	public static void main(String[] args) {

		if (PropertiesLoaderImpl.findUseSSL()) {

			System.out.println("Será usado SSL");
			
			System.setProperty("javax.net.ssl.keyStore", PropertiesLoaderImpl
					.findKeyStoreLocation()
					+ PropertiesLoaderImpl.findKeyStoreName());
			System.setProperty("javax.net.ssl.keyStorePassword",
					PropertiesLoaderImpl.findKeyStorePassword());
			System.setProperty("javax.net.ssl.trustStore", PropertiesLoaderImpl
					.findKeyStoreLocation()
					+ PropertiesLoaderImpl.findKeyStoreName());
			System.setProperty("javax.net.ssl.trustStorePassword",
					PropertiesLoaderImpl.findKeyStorePassword());
			if(PropertiesLoaderImpl.findDebug()) System.setProperty("javax.net.debug", "all");

		}

		// Obs:. Antes de executar o jar estabeleca um limite de arquivos
		// abertos ao mesmo tempo: ulimit -n 32000
		// E para executar o jar: nohup /opt/java/1.6.0_10/bin/java
		// -XX:+AggressiveOpts -XX:ThreadStackSize=1024 -Xmx6144m -jar
		// /srv/jade/simula20mil1min_Cluster_1.02

		SimuladorStarterASP.getInstance();

	}

}
