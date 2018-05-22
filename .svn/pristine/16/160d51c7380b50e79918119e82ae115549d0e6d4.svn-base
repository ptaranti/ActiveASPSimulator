package simulador.agents;

import simulador.behaviours.InformRequestBehaviour;
import simulador.suport.ASPOntology;
import simulador.suport.PropertiesLoaderImpl;

import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;

/**
 * 
 * Usada para criar o agente que monitora o blacboard do ASP_sim, realizando a
 * leitura do banco, capturando as mensagens e as transmitindo aos agentes de
 * destino em Agent Communication Language (ACL). Somente uma instancia deste
 * agente é utilizada.
 * 
 * @author taranti
 * 
 */

public class GatewayAgent extends Agent {

	private static final long serialVersionUID = 1L;
	private ContentManager manager = (ContentManager) getContentManager();
	// This agent "speaks" the SL language
	private Codec codec = new SLCodec();
	// This agent "knows" the PlatDominiumOntology()
	
	
	private Ontology ontology = ASPOntology.getInstance();

	@Override
	protected void setup() {
		manager.registerLanguage(codec);
		manager.registerOntology(ontology);
		
		assert(ontology.getConceptNames().get(0) != null): "ontologia sem conceitos";

		addBehaviour(new InformRequestBehaviour(this, 10 * 1000));
		if(PropertiesLoaderImpl.findDebug()) System.out.println("------------------ Behaviour adicionado - InformRequest");
	}

	/**
	 * @return o Codec utilizado pelo agente que eh por construcao uma instancia
	 *         da classe SLCodec provida pela plataforma JADE
	 */
	public Codec getCodec() {
		return codec;
	}

	/**
	 * @return uma instancia da classe ASPOntology
	 */
	public Ontology getOntology() {
		return ontology;
	}

}
