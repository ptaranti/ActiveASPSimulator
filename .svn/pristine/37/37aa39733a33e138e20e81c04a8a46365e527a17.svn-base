package simulador.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import simulador.agents.ASPShipAgent;
import simulador.objects.Ordem;
import simulador.suport.PropertiesLoaderImpl;
import simulador.suport.ShipPositionRequest;

/**
 *Comportamento que permite aos ASPShipAgent receber requisiçoes em ACL e
 * agendar o cumprimento das mesmas de acordo com o protocolo de comunicacoes do
 * sistema LRIT
 * 
 * @author taranti
 * 
 */
public class HandleDelayInformBehaviour extends CyclicBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ASPShipAgent a;

	/**
	 * @param a
	 *            referencia para o agente ASPShipAgentque instancia o objeto
	 */
	public HandleDelayInformBehaviour(ASPShipAgent a) {
		super(a);
		this.a = a;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jade.core.behaviours.Behaviour#action() verifica periodicamente as
	 * mensagens recebidas e agenda seu cumprimento
	 */
	public void action() {
		ACLMessage msg = a.receive(MessageTemplate
				.MatchPerformative(ACLMessage.INFORM));
		if (msg != null) {

			try {
				ShipPositionRequest shipPositionRequest = (ShipPositionRequest) msg
						.getContentObject();

				if(PropertiesLoaderImpl.findDebug()) System.out.println("--------------------------------------");
				if(PropertiesLoaderImpl.findDebug())System.out.print(a.getLocalName() + "   recebeu:\n"
						+ shipPositionRequest.toString());

				int requestTypeINT = (int) shipPositionRequest.getRequesttype();

				assert((requestTypeINT >= 0) && (requestTypeINT <= 11)) : "request Type nao previsto no protocolo de comunicacoes";
				
				if(PropertiesLoaderImpl.findDebug()) System.out.println("requestType Recebido : " + requestTypeINT);
				if(PropertiesLoaderImpl.findDebug()) System.out.println("-------------------------------------");

				if (requestTypeINT == 1) {
					if (shipPositionRequest.getMessageType() == 5) {

						a.addBehaviour(new SendMessage(a, shipPositionRequest
								.getMessageId(), "3"));
						if(PropertiesLoaderImpl.findDebug()) System.out.println("chamada msg one poll - sar request");
					}
					if (shipPositionRequest.getMessageType() == 4) {

						a.addBehaviour(new SendMessage(a, shipPositionRequest
								.getMessageId(), "2"));
						if(PropertiesLoaderImpl.findDebug()) System.out.println("chamada msg one poll");
					}

				}

				if (requestTypeINT == 0 || requestTypeINT == 8) {

					Ordem ordemTmp = null;

					for (Ordem ordem : a.getListaOrdens()) {

						if (ordem.getOrigem().equals(
								shipPositionRequest.getDataUserRequestor()))
							ordemTmp = ordem;

					}
					a.getListaOrdens().remove(ordemTmp);

				}

				if(PropertiesLoaderImpl.findDebug()) System.out.println("requestTypeINT : " + requestTypeINT);

				if (((requestTypeINT > 1) && (requestTypeINT < 7))
						|| (requestTypeINT == 10) || (requestTypeINT == 11)) {

					String requestor = shipPositionRequest
							.getDataUserRequestor();
					long startTime = shipPositionRequest.getStartTime()
							.getTimeInMillis();
					long stopTime = shipPositionRequest.getStopTime()
							.getTimeInMillis();

					int freq;

					if (requestTypeINT == 2)
						freq = 15;
					else if (requestTypeINT == 3)
						freq = 30;
					else if (requestTypeINT == 4)
						freq = 60; // 1 h
					else if (requestTypeINT == 5)
						freq = 180; // 3h
					else if (requestTypeINT == 6)
						freq = 360; // 6h
					else if (requestTypeINT == 10)
						freq = 720; // 12 h
					else
						freq = 1440; // 24h accessType=11

					String ordemMsgType;
					if (shipPositionRequest.getMessageType() == 5)
						ordemMsgType = "3";
					else
						ordemMsgType = "2";

					Ordem novaOrdem = new Ordem(requestor, shipPositionRequest
							.getMessageId(), (stopTime + 1000 * 60),
							(2 * stopTime + 1000 * 60 - startTime), freq,
							ordemMsgType);

					Ordem ordemTmp = null;
					for (Ordem ordem : a.getListaOrdens()) {

						if (ordem.comparaOrigem(novaOrdem))
							ordemTmp = ordem;
					}
					
					int contOrdem = a.getListaOrdens().size(); //preparacao para teste abaixo
					
					a.getListaOrdens().remove(ordemTmp);
					
					assert (ordemTmp != null && a.getListaOrdens().size() == contOrdem-1): "ordem nao removida";
					
					
					contOrdem = a.getListaOrdens().size(); //preparacao para teste abaixo
									
					a.getListaOrdens().add(novaOrdem);
					
					assert (novaOrdem != null && a.getListaOrdens().size() == contOrdem+1): "ordem nao inserida";
						

					if(PropertiesLoaderImpl.findDebug()) System.out.println("inserindo nova ordem para " + requestor
							+ " " + shipPositionRequest.getMessageId() + " "
							+ (stopTime + 1000 * 60) + " "
							+ (2 * stopTime + 1000 * 60 - startTime) + "  "
							+ freq + freq + "resposta tipo -> " + ordemMsgType);

				}

				else {
					if(PropertiesLoaderImpl.findDebug()) System.out.println("Não foi sol de msg frequente");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			block();
		}
	}

}
