package simulador.objects;


/**
 * representa uma requisicao  LRTI realizada para um navio
 * 
 * @author taranti
 *
 */
public class Ordem {

	private String origem;
	private String referenceID;
	private long startTime;
	private long stopTime;
	private int freq;
	private String msgType;

	/**
	 * @param s requisitante da informacao (LRIT ID)
	 * @param refId ID da mensagem de requisicao de informacao
	 * @param d1 instante para inicio de transmissao (em milisegundos), se null serah interpretado como "agora"
	 * @param d2 instante para termino de transmissao (em milisegundos), se null serah interpretado como "sem fim"
	 * @param i  frequencia de transmissao (valor inteiro que sera multiplicado pelo periodo de transmissao da msg tipo 1)
	 * @param msgType tipo de mensagem (indica se eh requisicao de informacao, requisicao sar, cancelamento ou suspensão de ordem)
	 */
	public Ordem(String s, String refId, long d1, long d2, int i, String msgType) {
		this.origem = s;
		this.referenceID = refId;
		this.startTime = d1;
		this.stopTime = d2;
		this.freq = i;
		this.msgType = msgType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * compara todos os campos
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Ordem) {
			Ordem outraOrdem = (Ordem) obj;
			if (this.origem == outraOrdem.getOrigem() &&
					this.referenceID == outraOrdem.getReferenceID() && 
					this.startTime == outraOrdem.getStartTime() && 
					this.stopTime == outraOrdem.getStopTime() && 
					this.freq == outraOrdem.getFreq() && 
					this.msgType == outraOrdem.getMsgType()) {
				return true;
			}
		}
		return false;

	}

	/**
	 * @param ordem
	 * @return true caso duas ordens tenha o mesmo requisitante
	 */
	public boolean comparaOrigem(Ordem ordem) {
		if (this.origem.equals(ordem.origem))
			return true;
		else
			return false;
	}

	/**
	 * @return requisitante da informacao (LRIT ID)
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * @param origem requisitante da informacao (LRIT ID)
	 */
	public void setOrigem(String origem) {
		this.origem = origem;
	}

	/**
	 * @return refId ID da mensagem de requisicao de informacao
	 */
	public String getReferenceID() {
		return referenceID;
	}

	/**
	 * @param referenceID refId ID da mensagem de requisicao de informacao
	 */
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}

	/**
	 * @return instante para inicio de transmissao (em milisegundos), se null serah interpretado como "agora"
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime instante para inicio de transmissao (em milisegundos), se null serah interpretado como "agora"
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return instante para termino de transmissao (em milisegundos), se null serah interpretado como "sem fim"
	 */
	public long getStopTime() {
		return stopTime;
	}

	/**
	 * @param stopTime instante para termino de transmissao (em milisegundos), se null serah interpretado como "sem fim"
	 */
	public void setStopTime(long stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * @return frequencia de transmissao (valor inteiro que sera multiplicado pelo periodo de transmissao da msg tipo 1)
	 */
	public int getFreq() {
		return freq;
	}

	/**
	 * @param freq frequencia de transmissao (valor inteiro que sera multiplicado pelo periodo de transmissao da msg tipo 1)
	 */
	public void setFreq(int freq) {
		this.freq = freq;
	}

	/**
	 * @return tipo de mensagem (indica se eh requisicao de informacao, requisicao sar, cancelamento ou suspensão de ordem)
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType tipo de mensagem (indica se eh requisicao de informacao, requisicao sar, cancelamento ou suspensão de ordem)
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}



}