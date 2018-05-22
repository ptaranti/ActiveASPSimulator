package simulador.agents;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;

import jade.domain.DFService;
import jade.domain.FIPAException;

import java.util.List;
import java.util.Vector;

import simulador.SimuladorStarterASP;
import simulador.behaviours.HandleInformBehaviour;
import simulador.behaviours.SeaAgentCinematic;
import simulador.behaviours.VerificaEnvio;
import simulador.objects.Ordem;
import simulador.suport.ASPOntology;
import simulador.suport.PropertiesLoaderImpl;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * Usada para criar agentes que simulam comportamento de navegacao e seguem o
 * protocolo de comunicacoes do sistema LRIT
 * 
 * @author taranti
 * 
 */
public class ASPShipAgent extends Agent {

	protected static final long serialVersionUID = 1L;
	protected ContentManager manager = (ContentManager) getContentManager();
	// This agent "speaks" the SL language
	protected Codec codec = new SLCodec();
	// This agent "knows" the PlatDominiumOntology()
	protected Ontology ontology = ASPOntology.getInstance();
	protected List<Ordem> listaOrdens;
	protected int contadorMsgTX = 0;
	protected double speed;
	protected double curse;
	protected Coordinate coord;
	protected String textCoordLat;
	protected String textCoordLong;
	protected String imoNumber;

	protected void setup() {

		listaOrdens = new Vector<Ordem>();

		System.out.println("\n nasceu " + this.getName());

		// this.a = this;

		contadorMsgTX = 0;

		manager.registerLanguage(codec);
		manager.registerOntology(ontology);

		assert(PropertiesLoaderImpl.findMaxVeloc()-PropertiesLoaderImpl.findMinVeloc() > 0): " Max velocidade menor que Minima velocidade";
		assert(PropertiesLoaderImpl.findLatNorth()-PropertiesLoaderImpl.findLatSouth() > 0): " Lat Norte da área de criacao abaixo da latitude sul informada";
		assert(PropertiesLoaderImpl.findLongEast()-PropertiesLoaderImpl.findLongWest() > 0): " Longitude Leste informada a oeste do limite oeste da área";
		assert(PropertiesLoaderImpl.findLatNorth() >= -90): " PropertiesLoaderImpl.findLatNorth() >= -90 :" + PropertiesLoaderImpl.findLatNorth();
		assert(PropertiesLoaderImpl.findLatSouth() >= -90 ): " PropertiesLoaderImpl.findLatSouth() >= -90 :" + PropertiesLoaderImpl.findLatSouth();
		assert(PropertiesLoaderImpl.findLatNorth() <= 90):  " PropertiesLoaderImpl.findLatNorth() <= 90 :" + PropertiesLoaderImpl.findLatNorth();
		assert(PropertiesLoaderImpl.findLatSouth() <= 90 ): " PropertiesLoaderImpl.findLatSouth() <= 90 :" + PropertiesLoaderImpl.findLatSouth();
		assert(PropertiesLoaderImpl.findLongEast() >= -180): " PropertiesLoaderImpl.findLongEast() >= -180: " + PropertiesLoaderImpl.findLongEast();
		assert(PropertiesLoaderImpl.findLongWest() >= -180 ): " PropertiesLoaderImpl.findLongWest() >= -180: " + PropertiesLoaderImpl.findLongWest();
		assert(PropertiesLoaderImpl.findLongEast() <= 180): " PropertiesLoaderImpl.findLongEast() <= 180: " + PropertiesLoaderImpl.findLongEast();
		assert(PropertiesLoaderImpl.findLongWest() <= 180 ): " PropertiesLoaderImpl.findLongWest() <= 180: " + PropertiesLoaderImpl.findLongWest() ;
		
		this.speed = (PropertiesLoaderImpl.findMaxVeloc() - ((PropertiesLoaderImpl
				.findMaxVeloc() - PropertiesLoaderImpl.findMinVeloc()) * Math
				.random())) / 60;

		this.curse = 360 * Math.random();

		double x = PropertiesLoaderImpl.findLongEast()
				- ((PropertiesLoaderImpl.findLongEast() - PropertiesLoaderImpl
						.findLongWest()) * Math.random());

		double y = PropertiesLoaderImpl.findLatNorth()
				- ((PropertiesLoaderImpl.findLatNorth() - PropertiesLoaderImpl
						.findLatSouth()) * Math.random());

		this.coord = new Coordinate(x, y);

		imoNumber = getImoNumber();

		addBehaviour(new SeaAgentCinematic(this, PropertiesLoaderImpl
				.findDefaultTimePositionSet() * 60 * 1000));

		addBehaviour(new HandleInformBehaviour(this));

		addBehaviour(new VerificaEnvio(this, PropertiesLoaderImpl
				.findDefaultTimeMSG1minutes() * 1000 * 60));

	}

	protected void takeDown() {

		// Deregister NormativeAgent from the yellow pages
		try {
			DFService.deregister(this);
		} catch (FIPAException fe) {
		}

	}

	/**
	 * @return palavra contendo o numero de identificacao do agente (IMO NR) na
	 *         simulacao
	 */
	public String getImoNumber() {

		String tmp = this.getLocalName();
		return tmp.substring(4);

	}

	private String getContMsgTX() {

		this.contadorMsgTX = this.contadorMsgTX + 1;
		if (PropertiesLoaderImpl.findDebug()) {
			System.out.println("Msg a ser tx por " + this.getLocalName()
					+ " nr: " + contadorMsgTX + "\n no rumo" + curse
					+ " e velocidade " + speed);
		}
		String tmp = (new Integer(contadorMsgTX)).toString();
		if (PropertiesLoaderImpl.findDebug())
			System.out.println(tmp);
		return tmp;
	}

	/**
	 * @return Lista de Ordens ativas que estao em execucao pelo agente
	 */
	public List<Ordem> getListaOrdens() {
		return listaOrdens;
	}

	/**
	 * @return Velocidade do agente no espaco virtual em milhas por hora
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * @return Rumo do agente no espaco virtual em graus em relacao ao norte
	 *         geografico
	 *         <p>
	 *         limites de 000 a 360 graus
	 */
	public double getCurse() {
		return curse;
	}

	/**
	 * @return coordenadas geograficas no espaco virtual, informadas em graus na
	 *         projecao de mercartor
	 *         <p>
	 *         formato (LONGITUDE, LATITUDE) - atencao, pois eh o inverso do
	 *         utilizado normalmente na pratica da navegacao area e maritima
	 *         <p>
	 *         Limites para Longitude: -180 a 180
	 *         <p>
	 *         Limites para Latitude: -90 a 90
	 */
	public Coordinate getCoord() {
		return coord;
	}

	/**
	 * @param coord
	 *            nova coordenada geografica do agente no espaco virtual, em
	 *            graus na projecao de mercartor
	 *            <p>
	 *            formato (LONGITUDE, LATITUDE) - atencao, pois eh o inverso do
	 *            utilizado normalmente na pratica da navegacao area e maritima
	 *            <p>
	 *            Limites para Longitude: -180 a 180
	 *            <p>
	 *            Limites para Latitude: -90 a 90
	 */
	public void setCoord(Coordinate coord) {
		this.coord = coord;
	}

	/**
	 * @return palavra contendo latitude formata como GG.MM.SS (graus, minutos e
	 *         segundos) e letra indicando norte (N) ou sul (S)
	 */
	public String getTextCoordLat() {
		return textCoordLat;
	}

	/**
	 * @param textCoordLat
	 *            palavra contendo latitude formata como GG.MM.SS (graus,
	 *            minutos e segundos) e letra indicando norte (N) ou sul (S)
	 */
	public void setTextCoordLat(String textCoordLat) {
		this.textCoordLat = textCoordLat;
	}

	/**
	 * @return palavra contendo longitude formata como GGG.MM.SS (graus, minutos
	 *         e segundos) e letra indicando leste (E) ou oeste (W)
	 */
	public String getTextCoordLong() {
		return textCoordLong;
	}

	/**
	 * @param textCoordLong
	 *            palavra contendo longitude formata como GGG.MM.SS (graus,
	 *            minutos e segundos) e letra indicando leste (E) ou oeste (W)
	 */
	public void setTextCoordLong(String textCoordLong) {
		this.textCoordLong = textCoordLong;
	}

}
