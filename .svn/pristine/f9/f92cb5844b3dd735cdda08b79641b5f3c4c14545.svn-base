package simulador.agents;

import java.util.Vector;

import simulador.behaviours.HandleDelayInformBehaviour;

import simulador.behaviours.SeaAgentCinematic;
import simulador.behaviours.VerificaEnvio;
import simulador.objects.Ordem;

import simulador.suport.PropertiesLoaderImpl;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * 
 * Usada para criar agentes que simulam comportamento de navegacao porem nao
 * seguem o protocolo de comunicacoes do sistema LRIT
 * <p>
 * Estes agentes iniciam a transmissão de mensagens com atraso a fim de apoiar
 * casos de teste em condicoes marginais do Centro de Dados
 * 
 * @author taranti
 */
public class ASPShipDelayAgent extends ASPShipAgent {

	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {

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
	
		
		listaOrdens = new Vector<Ordem>();

		System.out.println("\n nasceu " + this.getName());

		// this.a = this;

		contadorMsgTX = 0;

		manager.registerLanguage(codec);
		manager.registerOntology(ontology);

		this.speed = PropertiesLoaderImpl.findMaxVeloc()
				- ((PropertiesLoaderImpl.findMaxVeloc() - PropertiesLoaderImpl
						.findMinVeloc()) * Math.random());

		if(PropertiesLoaderImpl.findDebug()) System.out.println(this.getName()+ " speed " + speed);

		this.curse = 360 * Math.random();
		if(PropertiesLoaderImpl.findDebug()) System.out.println(this.getName() +" curse " + curse);

		double x = PropertiesLoaderImpl.findLongEast()
				- ((PropertiesLoaderImpl.findLongEast() - PropertiesLoaderImpl
						.findLongWest()) * Math.random());
		if(PropertiesLoaderImpl.findDebug()) System.out.println(this.getName()+ " x " + x);

		double y = PropertiesLoaderImpl.findLatNorth()
				- ((PropertiesLoaderImpl.findLatNorth() - PropertiesLoaderImpl
						.findLatSouth()) * Math.random());
		if(PropertiesLoaderImpl.findDebug()) System.out.println(this.getName()+ " y " + y);

		this.coord = new Coordinate(x, y);

		imoNumber = getImoNumber();

		addBehaviour(new SeaAgentCinematic(this, PropertiesLoaderImpl
				.findDefaultTimePositionSet() * 60 * 1000));

		addBehaviour(new HandleDelayInformBehaviour(this));// * 60));

		addBehaviour(new VerificaEnvio(this, PropertiesLoaderImpl
				.findDefaultTimeMSG1minutes() * 60 * 1000)); // 15*60*1000 ->
		// passo padrão
		// 15 min

		// addBehaviour(new SendMessage(this, ""));

	}
}
