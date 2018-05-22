package simulador.agents;

import java.util.Vector;

import simulador.behaviours.AgentCinematicPortTest;
import simulador.behaviours.HandleInformBehaviour;
import simulador.behaviours.VerificaEnvio;
import simulador.objects.Ordem;
import simulador.suport.PropertiesLoaderImpl;

/**
 * Usada para criar agente que simula comportamento de navegacao entre dois
 * pontos definidos e seguem o protocolo de comunicacoes do sistema LRIT
 * <p>
 * Os pontos de inicio e fim do percurso, bem como a velocidade sao informados
 * atraves de arquivo de propriedades
 * 
 * @author taranti
 * 
 */
public class ASPShipAgentPortTest extends ASPShipAgent {

	private static final long serialVersionUID = 1L;

	@Override
	protected void setup() {

		listaOrdens = new Vector<Ordem>();

		System.out.println("\n nasceu " + this.getName());
		this.
		// this.a = this;

		contadorMsgTX = 0;

		manager.registerLanguage(codec);
		manager.registerOntology(ontology);

		this.speed = PropertiesLoaderImpl.findPortTestShipSpeed() / 60;

		this.curse = getCurse();

		this.coord = PropertiesLoaderImpl.findPortTestStartCoordinate();

		imoNumber = getImoNumber();

		addBehaviour(new AgentCinematicPortTest(this, PropertiesLoaderImpl
				.findDefaultTimePositionSet() * 60 * 1000));

		addBehaviour(new HandleInformBehaviour(this));// * 60));

		addBehaviour(new VerificaEnvio(this, PropertiesLoaderImpl
				.findDefaultTimeMSG1minutes() * 60 * 1000)); // 15*60*1000 ->
		// passo padrão
		// 15 min

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see simulador.agents.ASPShipAgent#getCurse() Altera o cálculo do rumo
	 * para o rumo entre o ponto inicial da derrota e o final informados em
	 * arquivo de propriedades
	 */
	@Override
	public double getCurse() {
        
		assert (!(PropertiesLoaderImpl.findPortTestFinalCoordinate().equals2D(PropertiesLoaderImpl.findPortTestStartCoordinate()))):"coordenadas inicial e final sao iguais";
		
		double deltaX = PropertiesLoaderImpl.findPortTestFinalCoordinate().x
				- PropertiesLoaderImpl.findPortTestStartCoordinate().x;
		double deltaY = PropertiesLoaderImpl.findPortTestFinalCoordinate().y
				- PropertiesLoaderImpl.findPortTestStartCoordinate().y;

		if (deltaX == 0) {
			if (deltaY >= 0)
				this.curse = 0;
			else
				this.curse = 180;
		}

		assert(radToDegre(Math.PI)== 180): "conversao de radianos em graus com problema -> radToDegre(Math.PI) = " +radToDegre(Math.PI);
		
		if (deltaX > 0) {
			if (deltaY >= 0)
				this.curse = 90 - radToDegre(Math.atan(deltaY / deltaX));
			this.curse = 90 + radToDegre(Math.atan((-1) * deltaY / deltaX));
		}

		if (deltaX < 0) {
			if (deltaY >= 0)
				this.curse = 270 + radToDegre(Math.atan((-1) * deltaY / deltaX));
			this.curse = 270 - radToDegre(Math.atan(deltaY / deltaX));
		}

		return curse;
	}

	private double radToDegre(double rad) {
		double degre;
		degre = (rad * 360) / (2 * Math.PI);
		return degre;
	}

}
