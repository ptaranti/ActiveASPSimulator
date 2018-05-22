package simulador.suport;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import jade.util.leap.Serializable;

public class ShipPositionRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer messageType;
	private String messageId;
	private String IMONum;

	private String dataUserProvider;
	private Integer accessType;
	private String distance;
	private Integer requesttype;
	private Calendar startTime;
	private Calendar stopTime;
	private String dataUserRequestor;

	private Calendar timeStamp;

	public ShipPositionRequest() {
		super();

	}

	/**
	 * @return msgType tipo de mensagem (indica se eh requisicao de informacao,
	 *         requisicao sar, cancelamento ou suspensão de ordem)
	 */
	public int getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType
	 *            tipo de mensagem (indica se eh requisicao de informacao,
	 *            requisicao sar, cancelamento ou suspensão de ordem)
	 */
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return numero de identificacao da requisicao no sistema LRIT
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId
	 *            numero de identificacao da requisicao no sistema LRIT
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return numero IMO do navio consultado
	 */
	public String getIMONum() {
		return IMONum;
	}

	/**
	 * @param num
	 *            numero IMO do navio consultado
	 */
	public void setIMONum(String num) {
		IMONum = num;
	}

	/**
	 * @return LRIT ID do LDU consultado
	 */
	public String getDataUserProvider() {
		return dataUserProvider;
	}

	/**
	 * @param dataUserProvider
	 *            LRIT ID do LDU consultado
	 */
	public void setDataUserProvider(String dataUserProvider) {
		this.dataUserProvider = dataUserProvider;
	}

	/**
	 * @return tipo de acesso indica se a consulta é realizada como port, sar,
	 *         coastal state ou flag state.
	 */
	public Integer getAccessType() {
		return accessType;
	}

	/**
	 * @param accessType
	 *            tipo de acesso indica se a consulta é realizada como port,
	 *            sar, coastal state ou flag state.
	 */
	public void setAccessType(Integer accessType) {
		this.accessType = accessType;
	}

	/**
	 * @return distancia para consulta em milhas nauticas
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            distancia para consulta em milhas nauticas
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}

	/**
	 * @return tipo de requisicao, pode indicar frequencia, cancelamento ou
	 *         sustencao de uma ordem
	 */
	public Integer getRequesttype() {
		return requesttype;
	}

	/**
	 * @param requesttype
	 *            tipo de requisicao, pode indicar frequencia, cancelamento ou
	 *            sustencao de uma ordem
	 */
	public void setRequesttype(Integer requesttype) {
		this.requesttype = requesttype;
	}

	/**
	 * @return instante para inicio de transmissao em UTC
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            instante para inicio de transmissao em UTC
	 */
	public void setStartTime(Calendar startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return instante para termino de transmissao em UTC
	 */
	public Calendar getStopTime() {
		return stopTime;
	}

	public void setStopTime(Calendar stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * @return LRIT ID do requisitante
	 */
	public String getDataUserRequestor() {
		return dataUserRequestor;
	}

	/**
	 * @param dataUserRequestor
	 *            LRIT ID do requisitante
	 */
	public void setDataUserRequestor(String dataUserRequestor) {
		this.dataUserRequestor = dataUserRequestor;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		SimpleDateFormat formatter = new SimpleDateFormat(
				"dd/MM/yyyy HH:mm:ss.SSS");

		String a;

		a = messageType.toString() + " \n";
		a = a + messageId + " \n";
		a = a + IMONum + " \n";
		a = a + dataUserProvider + " \n";
		a = a + accessType.toString() + " \n";
		a = a + distance + " \n";
		a = a + requesttype.toString() + " \n";
		if (startTime != null)
			a = a + formatter.format(startTime.getTime()) + " \n";
		if (stopTime != null)
			a = a + formatter.format(stopTime.getTime()) + " \n";
		a = a + dataUserRequestor + " \n";

		return a;

	}

}
