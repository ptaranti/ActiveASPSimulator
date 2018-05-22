package simulador.behaviours;

import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import simulador.agents.GatewayAgent;
import simulador.objects.ConSingletonPgsql;
import simulador.suport.PropertiesLoaderImpl;
import simulador.suport.ShipPositionRequest;

/**
 * Comportamento de execução periodica que le o blackboard do ASP_sim e traduz
 * as requisicoes para ACL, encaminhando-as aos agentes de destino
 * 
 * @author taranti
 * 
 */
public class InformRequestBehaviour extends TickerBehaviour {

	private static final long serialVersionUID = 1L;
	GatewayAgent a;

	public InformRequestBehaviour(GatewayAgent gatewayAgent, int freqDB) {
		super(gatewayAgent, freqDB);
		this.a = gatewayAgent;

	}

	@Override
	protected void onTick() {

		// int contR = 0;
		List<ShipPositionRequest> requests = getRequests();
		
		

		// System.out.println("Quantidade de request:" + requests.size());

		for (ShipPositionRequest shipPositionRequest : requests) {
			
        assert (shipPositionRequest.getDataUserProvider().equals(PropertiesLoaderImpl.findLDU())):"LDU invalido";
        assert (Integer.parseInt(shipPositionRequest.getIMONum()) >= Integer.parseInt(PropertiesLoaderImpl.findCgCode() + "000000") &&
        		Integer.parseInt(shipPositionRequest.getIMONum()) <= Integer.parseInt(PropertiesLoaderImpl.findCgCode() + "999999")):"Imo number fora da range";

			try {

				String agentName = "Ship" + shipPositionRequest.getIMONum();

				// Prepare the message
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				AID receiver = new AID(agentName, AID.ISLOCALNAME);

				msg.setSender(a.getAID());
				msg.addReceiver(receiver);
				msg.setLanguage(a.getCodec().getName());
				msg.setOntology(a.getOntology().getName());

				// Fill the content

				msg.setContentObject(shipPositionRequest);

				a.send(msg);

				if(PropertiesLoaderImpl.findDebug()) System.out.println("+++++++++++++ ASP enviando requisição para"
						+ new AID(agentName, AID.ISLOCALNAME).getLocalName());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public List<ShipPositionRequest> getRequests() {

		List<ShipPositionRequest> requests = new Vector<ShipPositionRequest>();

		// recebendo conector

		try {
			if(PropertiesLoaderImpl.findDebug()) System.out.println("::::::::::::::::JDBC-CONEXÃO::::::::::::::::::::::::::");
			java.sql.Connection conn = ConSingletonPgsql.getInstance()
					.getConn();

			PreparedStatement ps = conn.prepareStatement("select * "
					+ "from log_asprequest_sim "
					+ "where lritdatauserprovider = ?");
			ps.setString(1, PropertiesLoaderImpl.findLDU());

			ResultSet r = ps.executeQuery();

			if(PropertiesLoaderImpl.findDebug()) System.out.println("::::::::INCIO DA LEITURA NO BANCO:::::::");

			while (r.next()) {

				ShipPositionRequest shipPositionRequest = new ShipPositionRequest();

				shipPositionRequest
						.setMessageType(((BigDecimal) r.getObject(2))
								.intValue());// messageType

				shipPositionRequest.setMessageId((String) r.getObject(3));// messageId
				if(PropertiesLoaderImpl.findDebug()) System.out
						.println("MessageId[" + (String) r.getObject(3) + "]");

				shipPositionRequest.setIMONum((String) r.getObject(4));// IMOnum
				shipPositionRequest
						.setDataUserProvider((String) r.getObject(5));// dataUserProvider

				shipPositionRequest.setAccessType(((BigDecimal) r.getObject(6))
						.intValue());// accessType

				shipPositionRequest.setDistance(((Integer) r.getObject(8))
						.toString());// distance

				shipPositionRequest
						.setRequesttype(((BigDecimal) r.getObject(9))
								.intValue());// requesttype

				GregorianCalendar start = new GregorianCalendar();
				if(PropertiesLoaderImpl.findDebug()) System.out.println("StartTime BD:" + r.getObject(10));

				if (r.getObject(10) != null) {
					start.setTimeInMillis(((Timestamp) r.getObject(10))
							.getTime());
				} else {
					start.setTimeInMillis(System.currentTimeMillis());
					if(PropertiesLoaderImpl.findDebug()) System.out.println("Tempo corrente[StartTime]:"
							+ start.getTimeInMillis());
				}
				shipPositionRequest.setStartTime(start);// startTime

				GregorianCalendar stop = new GregorianCalendar();

				if(PropertiesLoaderImpl.findDebug()) System.out.println("StopTime BD:" + r.getObject(11));
				if (r.getObject(11) != null) {
					stop.setTimeInMillis(((Timestamp) r.getObject(11))
							.getTime());
				} else {
					// long tmp = System.currentTimeMillis() +
					// (1000*60*60*24*30*12);
					// System.out.println( "diferenca em anos ->" + ((tmp -
					// start.getTimeInMillis())/(1000*60*60*24*30*12)));
					// stop.setTimeInMillis(tmp);
					// ajusta o stop para 1 ano após agora
					stop.setTimeInMillis(start.getTimeInMillis());
					stop.add(Calendar.YEAR, 1);
					if(PropertiesLoaderImpl.findDebug()) System.out.println("Tempo corrente[StopTime]:"
							+ stop.getTimeInMillis());
				}
				shipPositionRequest.setStopTime(stop);

				if(PropertiesLoaderImpl.findDebug())System.out.println("Antes GREG Start:"
						+ shipPositionRequest.getStartTime().getTime());
				if(PropertiesLoaderImpl.findDebug())System.out.println("Antes GREG Stop:"
						+ shipPositionRequest.getStopTime().getTime());

				SimpleDateFormat formatter = new SimpleDateFormat(
						"dd/MM/yyyy HH:mm:ss.SSS");
				if(PropertiesLoaderImpl.findDebug())System.out
						.println("*********************** Lendo Duration do banco... ");
				if(PropertiesLoaderImpl.findDebug())System.out.println("****** StartTime: "
						+ formatter.format(shipPositionRequest.getStartTime()
								.getTime()));
				if(PropertiesLoaderImpl.findDebug())System.out.println("****** StopTime: "
						+ formatter.format(shipPositionRequest.getStopTime()
								.getTime()));

				shipPositionRequest.setDataUserRequestor((String) r
						.getObject(12));// dataUserRequestor

				requests.add(shipPositionRequest);

			}

			if(PropertiesLoaderImpl.findDebug()) System.out.println("::::::::FIM DA LEITURA NO BANCO:::::::");

			ps = conn.prepareStatement("delete " + "from log_asprequest_sim "
					+ "where lritdatauserprovider = ?");
			ps.setString(1, PropertiesLoaderImpl.findLDU());

			ps.executeUpdate();

			if(PropertiesLoaderImpl.findDebug()) System.out.println("::::::::LIMPANDO A TABELA [log_asprequest_sim] :::::::");

			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return requests;
	}

}
