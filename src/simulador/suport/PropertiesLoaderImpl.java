package simulador.suport;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Oferece metodos estaticos para leitura das propriedades do arquivo de
 * propriedades acessado pela classe PropertiesLoader
 * 
 * @author taranti
 * 
 */
public class PropertiesLoaderImpl {

	private static PropertiesLoader loader = new PropertiesLoader();
	private static boolean overallClassTest = true;

	/**
	 * @return Numero de navios de comportamento correto a serem instanciados
	 *         serao identificados na simulacao com seis digitos em contagem
	 *         crescente a partir de X000001, sendo X o CG code
	 */
	public static int findNrSimShip() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		// teste de limites
		int nrSimShip = Integer.parseInt(PropertiesLoaderImpl
				.getValor("NR_SIM_SHIPS"));
		if (nrSimShip < 0 || nrSimShip > 999998) {
			System.out
					.println("Arquivo simShip.properties com erro: NR_SIM_SHIPS > 999998 ou menor que zero");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		if (nrSimShip
				+ Integer.parseInt(PropertiesLoaderImpl
						.getValor("NR_DELAYED_SIM_SHIPS")) > 999998) {
			System.out
					.println("Arquivo simShip.properties com erro: NR_SIM_SHIPS + NR_DELAYED_SIM_SHIPS > 999998");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}

		return nrSimShip;
	}

	/**
	 * @return Numero de navios de comportamento erroneo (emitem mensagens
	 *         atrasadas) a serem instanciadosserao identificados na simulacao
	 *         com seis digitos em contagem decrescente a partir de X999998
	 *         sendo X o CG code
	 */
	public static int findNrDelayedSimShip() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		// teste de limites
		int nrDelayedSimShip = Integer.parseInt(PropertiesLoaderImpl
				.getValor("NR_DELAYED_SIM_SHIPS"));
		if (nrDelayedSimShip < 0 || nrDelayedSimShip > 999998) {
			System.out
					.println("Arquivo simShip.properties com erro: NR_DELAYED_SIM_SHIPS > 999998 ou menor que zero");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		if (nrDelayedSimShip
				+ Integer.parseInt(PropertiesLoaderImpl
						.getValor("NR_SIM_SHIPS")) > 999998) {
			System.out
					.println("Arquivo simShip.properties com erro: NR_SIM_SHIPS + NR_DELAYED_SIM_SHIPS > 999998");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}

		return nrDelayedSimShip;
	}

	/**
	 * @return Informa true se será realizado teste de PORT (caso seja executado
	 *         será instanciado o navio com numero X999999 sendo X o CG code)
	 */
	public static boolean findPortTest() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		boolean portTest = Boolean.parseBoolean(PropertiesLoaderImpl
				.getValor("PORT_TEST"));

		if (portTest)
			System.out.println("O teste de port sera realizado");
		else
			System.out
					.println("o teste de port nao sera realizado");

		return portTest;

	}

	/**
	 * @return naciolalidade dos Navios (LRIT ID do flag state)
	 */
	public static String findLDU() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		String ldu = PropertiesLoaderImpl.getValor("LDU");
		if (!ldu.matches("1[0-9]{3}")) {
			System.out
					.println("Arquivo simShip.properties com erro: LDU fora do padrao (regexp 1[0-9]{3})");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}

		return ldu;
	}

	/**
	 * @return identificacao do ASP (LRIT ID do ASP)
	 */
	public static String findASP() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		String asp = PropertiesLoaderImpl.getValor("ASP");
		if (!asp.matches("4[0-9]{3}")) {
			System.out
					.println("Arquivo simShip.properties com erro: ASP fora do padrao (regexp 4[0-9]{3})");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}

		return asp;
	}

	/**
	 * @return porta logica a ser usada pela plataforma JADE
	 */
	public static String findJadePort() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		String port = PropertiesLoaderImpl.getValor("JADE_PORT");
		if (!port.matches("160[0-1][0-9]")) {
			System.out
					.println("Arquivo simShip.properties com erro: PORT fora do padrao (regexp 160[0-1][0-9])");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		return port;
	}

	/**
	 * @return codigo que identifica os navios deste buid de simulacao- antecede
	 *         o imo number
	 */
	public static String findCgCode() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		String cg = PropertiesLoaderImpl.getValor("CG_CODE");
		if (!cg.matches("[0-9]")) {
			System.out
					.println("Arquivo simShip.properties com erro: CG_CODE fora do padrao (regexp [0-9])");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		return cg;

	}

	/**
	 * @return Limite norte da area de simulacao em graus
	 */
	public static float findLatNorth() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		float ln = Float.parseFloat(PropertiesLoaderImpl.getValor("LAT_NORTH"));
		if (ln > 90 || ln < -90) {
			System.out
					.println("Arquivo simShip.properties com erro: 90 < LAT_NORTH ou -90 > LAT_NORTH)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		if (ln <= Float.parseFloat(PropertiesLoaderImpl.getValor("LAT_SOUTH"))) {
			System.out
					.println("Arquivo simShip.properties com erro: LAT_NORTH < LAT_SOUTH)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		return ln;
	}

	/**
	 * @return Limite sul da area de simulacao em graus (numero negativo)
	 */
	public static float findLatSouth() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		float ls = Float.parseFloat(PropertiesLoaderImpl.getValor("LAT_SOUTH"));
		if (ls > 90 || ls < -90) {
			System.out
					.println("Arquivo simShip.properties com erro: 90 < LAT_SOUTH ou -90 > LAT_SOUTH)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		if (ls >= Float.parseFloat(PropertiesLoaderImpl.getValor("LAT_NORTH"))) {
			System.out
					.println("Arquivo simShip.properties com erro: LAT_NORTH < LAT_SOUTH)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		return ls;
	}

	/**
	 * @return Limite leste da area de simulacao em graus
	 */
	public static float findLongEast() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		float le = Float.parseFloat(PropertiesLoaderImpl.getValor("LONG_EAST"));
		if (le > 180 || le < -180) {
			System.out
					.println("Arquivo simShip.properties com erro: 180 < LONG_EAST ou -180 > LONG_EAST)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		if (le <= Float.parseFloat(PropertiesLoaderImpl.getValor("LONG_WEST"))) {
			System.out
					.println("Arquivo simShip.properties com erro: LONG_EAST < LONG_WEST)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		return le;

	}

	/**
	 * @return Limite oeste da area de simulacao em graus (numero negativo)
	 */
	public static float findLongWest() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		float lw = Float.parseFloat(PropertiesLoaderImpl.getValor("LONG_WEST"));
		if (lw > 180 || lw < -180) {
			System.out
					.println("Arquivo simShip.properties com erro: 180 < LONG_WEST ou -180 > LONG_WEST)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		if (lw >= Float.parseFloat(PropertiesLoaderImpl.getValor("LONG_EAST"))) {
			System.out
					.println("Arquivo simShip.properties com erro: LONG_EAST < LONG_WEST)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		return lw;

	}

	/**
	 * @return limite maximo de velocidade dos navios simulados em nos (milhas
	 *         maritmas por hora)
	 */
	public static int findMaxVeloc() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		int maxV = Integer.parseInt(PropertiesLoaderImpl.getValor("MAX_VELOC"));

		if (maxV <= Integer
				.parseInt(PropertiesLoaderImpl.getValor("MIN_VELOC"))) {
			System.out
					.println("Arquivo simShip.properties com erro: MAX_VELOC < MIN_VELOC)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		return maxV;

	}

	/**
	 * @return limite minimo de velocidade dos navios simulados em nos (milhas
	 *         maritmas por hora)
	 */
	public static int findMinVeloc() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		int minV = Integer.parseInt(PropertiesLoaderImpl.getValor("MIN_VELOC"));

		if (minV >= Integer
				.parseInt(PropertiesLoaderImpl.getValor("MAX_VELOC"))) {
			System.out
					.println("Arquivo simShip.properties com erro: MAX_VELOC < MIN_VELOC)");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		return minV;

	}

	/**
	 * @return intervalo de tempo que será utilizado para verificar a
	 *         necessidade do envio de mensagens, em minutos.
	 *         <p>
	 *         valor default para aderir ao protocolo de comunicacoes do LRIT eh
	 *         15 minutos
	 */
	public static int findDefaultTimeMSG1minutes() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		int dt = Integer.parseInt(PropertiesLoaderImpl
				.getValor("DEFAULT_TIME_MSG1_MINUTES"));

	
		return dt;
	}

	/**
	 * @return true se o sistema deve enviar uma mensagem a cada intervalo de
	 *         verificacao de envio de mensagens
	 */
	public static boolean findSendEachStepType() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		boolean ses = Boolean.parseBoolean(PropertiesLoaderImpl
				.getValor("SEND_EACH_STEP_TYPE"));

		return ses;

	}

	/**
	 * @return periodo para atualizacao de posicoes geograficas em minutos
	 *         <p>
	 *         valor default 1 minuto
	 */
	public static int findDefaultTimePositionSet() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		return Integer.parseInt(PropertiesLoaderImpl
				.getValor("DEFAULT_TIME_POSITION_SET_MINUTES"));
	}

	/**
	 * @return intervalo de tempo em milisegundos entre a instanciacao de dois
	 *         agentes.
	 *         <p>
	 *         este intervalo eh usado para espalhar a amostra de teste
	 */
	public static int findTimeInterval() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		return Integer.parseInt(PropertiesLoaderImpl.getValor("TIME_INTERVAL"));
	}

	/**
	 * @return coordenada de posicionamento inicial do navio a ser usado no
	 *         teste de Port
	 */
	public static Coordinate findPortTestStartCoordinate() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		float x = Float.parseFloat(PropertiesLoaderImpl
				.getValor("COORD_INICIAL_X"));
		if (x > 180 || x < -180) {
			System.out.println("\nCOORD_INICIAL_X fora dos limites");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}

		float y = Float.parseFloat(PropertiesLoaderImpl
				.getValor("COORD_INICIAL_Y"));
		if (x > 90 || x < -90) {
			System.out.println("\nCOORD_INICIAL_Y fora dos limites");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		return new Coordinate(x, y);
	}

	/**
	 * @return coordenada de posicionamento final do navio a ser usado no teste
	 *         de Port
	 */
	public static Coordinate findPortTestFinalCoordinate() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		float x = Float.parseFloat(PropertiesLoaderImpl
				.getValor("COORD_FINAL_X"));
		if (x > 180 || x < -180) {
			System.out.println("\nCOORD_FINAL_X fora dos limites");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		float y = Float.parseFloat(PropertiesLoaderImpl
				.getValor("COORD_FINAL_Y"));
		if (x > 90 || x < -90) {
			System.out.println("\nCOORD_FINAL_Y fora dos limites");
			System.out
					.println("Simulacao encerrada - corrija arquivo simShip.properties");
			System.exit(1);
		}
		
		return new Coordinate(x, y);
	}

	/**
	 * @return velocidade do navio a ser usado no teste de port
	 */
	public static float findPortTestShipSpeed() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		return Float.parseFloat(PropertiesLoaderImpl
				.getValor("VELOC_TESTE_DERROTA_FIXA"));
	}

	/**
	 * @return endereco do webservice do DC (texto com url completa)
	 */
	public static String findDCPortAdrressWS() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		return PropertiesLoaderImpl.getValor("DC_PORT_ADRRESS_WS");
	}

	/**
	 * @return endereco do banco de dados usado como blackboard (texto com
	 *         endereco IP)
	 */
	public static String findDataBaseIPAdrress() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		String ip =PropertiesLoaderImpl.getValor("IP_BANCO");
	 				
	return ip;
	}

	/**
	 * @return true caso o sistema deva realizar comunicações com criptografia
	 */
	public static boolean findUseSSL() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		return Boolean.parseBoolean(PropertiesLoaderImpl.getValor("USE_SSL"));
	}

	/**
	 * @return absoluto do keystore
	 */
	public static String findKeyStoreLocation() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		return PropertiesLoaderImpl.getValor("KEYSTORE_LOCATION");
	}

	/**
	 * @return nome do keystore
	 */
	public static String findKeyStoreName() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		return PropertiesLoaderImpl.getValor("KEYSTORE_NAME");
	}

	/**
	 * @return senha do keystore
	 */
	public static String findKeyStorePassword() {

		if (overallClassTest)
			overallClassTest(); // teste completo de todas propriedades caso nao
		// tenha sido executado

		return PropertiesLoaderImpl.getValor("KEYSTORE_PASSWORD");
	}

	private static String getValor(String chave) {
		return (String) loader.getValor(chave);
	}

	/**
	 * @return true caso o sistema deva ser inicializado em modo verboso para
	 *         debug
	 *         <p>
	 *         Caso utilizado o agente RMA do jade será carregado para
	 *         monitoração dos agentes
	 */
	public static boolean findDebug() {
		return Boolean.parseBoolean(PropertiesLoaderImpl.getValor("DEBUG"));
	}

	private static void overallClassTest() {

		overallClassTest = false;

		findNrSimShip();
		findNrDelayedSimShip();
		findPortTest();
		findLDU();
		findASP();
		findJadePort();
		findCgCode();
		findLatNorth();
		findLatSouth();
		findLongEast();
		findLongWest();
		findMaxVeloc();
		findMinVeloc();
		findDefaultTimeMSG1minutes();
		findSendEachStepType();
		findDefaultTimePositionSet();
		findTimeInterval();
		findPortTestStartCoordinate();
		findPortTestFinalCoordinate();
		findPortTestShipSpeed();
		findDCPortAdrressWS();
		findDataBaseIPAdrress();
		findUseSSL();
		findKeyStoreLocation();
		findKeyStoreName();
		findKeyStorePassword();
		findDebug();
	}

	public static void main(String[] args) {
		overallClassTest();
	}
}

// / para usar:
// PropertiesLoaderImpl.getValor("SUA VARIAVEL")
// find....