package simulador.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.text.SimpleDateFormat;

import simulador.agents.ASPShipAgent;
import simulador.objects.Ordem;
import simulador.suport.PropertiesLoaderImpl;
import simulador.suport.ShipPositionRequest;

/**
 * Comportamento de execução periodica que le o blackboard do ASP_sim e traduz
 * as requisicoes para ACL, encaminhando-as aos agentes de destino
 * 
 * @author taranti
 * 
 */
public class HandleInformBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = 1L;
	ASPShipAgent a;

	/**
	 * @param a
	 */
	public HandleInformBehaviour(ASPShipAgent a) {
		super(a);
		this.a = a;
	}

	public void action() {
		ACLMessage msg = a.receive(MessageTemplate
				.MatchPerformative(ACLMessage.INFORM));
		if (msg != null) {

			try {
				ShipPositionRequest shipPositionRequest = (ShipPositionRequest) msg
						.getContentObject();
				if(PropertiesLoaderImpl.findDebug()) System.out
						.println("\n\n --------------------------------------");
				if(PropertiesLoaderImpl.findDebug()) System.out.print(a.getLocalName() + "   recebeu:\n"
						+ shipPositionRequest.toString());
				if(PropertiesLoaderImpl.findDebug()) System.out.println("----------------------------\n");
				int requestTypeINT = (int) shipPositionRequest.getRequesttype();

				if(PropertiesLoaderImpl.findDebug()) System.out.println("requestType Recebido : " + requestTypeINT);

				if (requestTypeINT == 1) {
					if (shipPositionRequest.getMessageType() == 5) {

						a.addBehaviour(new SendMessage(a, shipPositionRequest
								.getMessageId(), "3"));
						if(PropertiesLoaderImpl.findDebug()) System.out.println("chamada msg one poll");
					}
					if (shipPositionRequest.getMessageType() == 4) {

						a.addBehaviour(new SendMessage(a, shipPositionRequest
								.getMessageId(), "2"));
						if(PropertiesLoaderImpl.findDebug()) System.out.println("chamada msg one poll");
					}

				}

				if(PropertiesLoaderImpl.findDebug()) System.out.println("------------------------------\n\n");

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

					SimpleDateFormat formatter = new SimpleDateFormat(
							"dd/MM/yyyy HH:mm:ss.SSS");
					if(PropertiesLoaderImpl.findDebug()) System.out
							.println("******************* Inserindo nova ordem:");
					if(PropertiesLoaderImpl.findDebug()) System.out.println("****** Requestor: " + requestor);
					if(PropertiesLoaderImpl.findDebug()) System.out.println("****** MessageID: "
							+ shipPositionRequest.getMessageId());
					if(PropertiesLoaderImpl.findDebug()) System.out.println("****** StartTime: "
							+ formatter.format(shipPositionRequest
									.getStartTime().getTime()));
					if(PropertiesLoaderImpl.findDebug()) System.out.println("****** StopTime: "
							+ formatter.format(shipPositionRequest
									.getStopTime().getTime()));
					if(PropertiesLoaderImpl.findDebug()) System.out.println("****** Freq: " + freq);
					if(PropertiesLoaderImpl.findDebug()) System.out.println("****** MessageType: " + ordemMsgType);

					Ordem novaOrdem = new Ordem(requestor, shipPositionRequest
							.getMessageId(), shipPositionRequest.getStartTime()
							.getTimeInMillis(), shipPositionRequest
							.getStopTime().getTimeInMillis(), freq,
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
