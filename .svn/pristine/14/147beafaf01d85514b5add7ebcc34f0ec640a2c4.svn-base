package simulador.suport;

import jade.content.onto.*;
import jade.content.schema.*;

/**
 * Ontologia que reune os conceitos necessarios a composicao das mensagens em
 * ACL usadas no SimShip
 * 
 * @author taranti
 * 
 */
public class ASPOntology extends Ontology {

	private static final long serialVersionUID = 1L;

	// The name identifying this ontology
	public static final String ONTOLOGY_NAME = "ASPOntology";
	// VOCABULARY

	// general concepts :
	public static final String SHIP_POSITION_REQUEST = "ShipPositionRequest";
	public static final String MESSAGE_TYPE = "messageType";
	public static final String MESSAGE_ID = "messageId";
	public static final String IMO_NUM = "IMONum";
	public static final String DATA_USER_PROVIDER = "dataUserProvider";
	public static final String ACCESS_TIPE = "accessType";
	public static final String DISTANCE = "distance";
	public static final String REQUEST_TYPE = "requesttype";
	public static final String REQUEST_DURATION_START_TIME = "startTime";
	public static final String REQUEST_DURATION_STOP_TIME = "stopTime";
	public static final String DATA_USER_REQUESTOR = "dataUserRequestor";
	public static final String TIME_STAMP = "timeStamp";
	public static final String DDP_VERSION = "DDPVersionNum";

	// The singleton instance of this ontology
	private static Ontology theInstance = new ASPOntology();



	/**
	 * @return referencia para a ontologia a ser usada nas comunicacoes em ACL
	 */
	public static Ontology getInstance() {
		return theInstance;
	}

	// Private constructor
	private ASPOntology() {
		// The Book-trading ontology extends the basic ontology
		super(ONTOLOGY_NAME, BasicOntology.getInstance());

		try {
			add(new ConceptSchema(SHIP_POSITION_REQUEST),
					ShipPositionRequest.class);

			ConceptSchema cs = (ConceptSchema) getSchema(SHIP_POSITION_REQUEST);
			cs.add(MESSAGE_TYPE,
					(PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			cs.add(MESSAGE_ID,
					(PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(IMO_NUM, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(DATA_USER_PROVIDER,
					(PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(ACCESS_TIPE,
					(PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			cs.add(DISTANCE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(REQUEST_TYPE,
					(PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			cs.add(REQUEST_DURATION_START_TIME,
					(PrimitiveSchema) getSchema(BasicOntology.DATE));
			cs.add(REQUEST_DURATION_STOP_TIME,
					(PrimitiveSchema) getSchema(BasicOntology.DATE));
			cs.add(DATA_USER_REQUESTOR,
					(PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs.add(TIME_STAMP,
					(PrimitiveSchema) getSchema(BasicOntology.STRING));
		} catch (OntologyException oe) {
			oe.printStackTrace();
		}
	}

}
