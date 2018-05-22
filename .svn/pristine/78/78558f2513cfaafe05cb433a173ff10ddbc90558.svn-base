package simulador.behaviours;

import jade.core.behaviours.OneShotBehaviour;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import org.imo.gisis.XML.LRIT._2008.DcPortType;
import org.imo.gisis.XML.LRIT._2008.DcServiceLocator;
import org.imo.gisis.XML.LRIT.positionReport._2008.MessageTypeType;
import org.imo.gisis.XML.LRIT.positionReport._2008.ShipPositionReportASPType;

import simulador.agents.ASPShipAgent;
import simulador.suport.PropertiesLoaderImpl;

/**
 * Comportamento que envia uma mensagem ao DC a cada vez que é criado. Logo após
 * o envio o objeto é descartado.
 * <p>
 * Para enviar a mensagem é utilizado um cliente de webservice instanciado com a
 * biblioteca Axis. A transmissao arere ao formato de mensagem do sistema LRIT,
 * utilizando protocolo SOAP 1.2
 * <p>
 * Eh realizado log destes envios para realização de medidas e comparaçoes com o
 * Centro de Dados, sendo este LOG o principal recurso para avaliacao de testes
 * de desempenho.
 * 
 * @author taranti
 * 
 */
public class SendMessage extends OneShotBehaviour {

	private static final long serialVersionUID = 1L;

	private static final SimpleDateFormat logFormatter = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss.SSS");
	private static final SimpleDateFormat logFormatterRegister = new SimpleDateFormat(
	"dd/MM/yyyy'; 'HH:mm:ss.SSS ");
	private static final SimpleDateFormat logFormatterRegisterName = new SimpleDateFormat(
	 "dd_MM_yyyy_HH_mm_ss");

	private ASPShipAgent a;
	private SimpleDateFormat formatadorImo = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private String refId;
	private String msgType;
	private static String timeRegister;
	private static File fileRegister;
	private static FileWriter writerRegister;
	private static PrintWriter outRegister;

	/**
	 * @param a
	 *            referencia ao agente que envia a mensagem
	 * @param refId
	 *            numero de referencia da mensagem que requereu esta transmissao
	 *            (se existir)
	 * @param msgType
	 *            tipo de mensagem sendo transmitida (1 - informacao LRIT, 2-
	 *            mensagem SAR)
	 */
	public SendMessage(ASPShipAgent a, String refId, String msgType) {
		super(a);
		this.a = a;
		if (refId != null)
			this.refId = refId;
		else
			this.refId = "";

		this.msgType = msgType;
		
		if (timeRegister == null) timeRegister = logFormatterRegisterName.format(new GregorianCalendar().getTime()); 
		if (fileRegister == null) fileRegister = new File("register" + timeRegister + ".csv"); 
		if(writerRegister == null )
			try {
				writerRegister = new FileWriter("register" + timeRegister + ".csv", true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("erro na abertura de register" + timeRegister + ".csv");
				e.printStackTrace( );
			}  
			if (outRegister == null) {
				outRegister = new PrintWriter(writerRegister,true); 
				outRegister.println("delivery" + "; " + 
						"dayOfConnection" +  "; " + 
						"timeOfConnection" +  "; " +
						"shipName" + "; " + 
						"imoNumber"  + "; " + 
						"msgID"  + "; " + 
						"MessageType" + "; " + 
						"ReferenceId"
						);
			}
			
			assert (fileRegister.exists() && fileRegister.isFile() && fileRegister.canWrite()): "file register" + timeRegister + ".csv nao existe ou nao possui direito de escrita";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jade.core.behaviours.Behaviour#action() Metodo que cria e envia a
	 * mensagem Executa LOG da transmissao
	 */
	public void action() {

		String msgID = geMsgID(a.getImoNumber());
		assert (msgID.length() == 23): "erro na msgID a ser enviada por " + a.getLocalName();
		
		ShipPositionReportASPType sprASPType;

		assert (a.getTextCoordLat().length() == 10): "erro na getTextCoordLat().length() a ser enviada por " + a.getLocalName();
		assert (a.getTextCoordLong().length() == 11): "erro na getTextCoordLong().length() a ser enviada por " + a.getLocalName();
		assert (PropertiesLoaderImpl.findASP().length() == 4): "erro na ASP LRIT ID a ser enviada por " + a.getLocalName();
	

		sprASPType = new ShipPositionReportASPType(a.getTextCoordLat(), a
				.getTextCoordLong(), new GregorianCalendar(), "SEQU",
				PropertiesLoaderImpl.findASP(), new MessageTypeType(
						new BigInteger(this.msgType)), msgID, this.refId, a
						.getImoNumber(), "123451232", new GregorianCalendar(),
				new GregorianCalendar(), new BigDecimal("1"));

		try {
			DcServiceLocator loc = new DcServiceLocator();
			// DcServiceBindingStub stub;

			DcPortType port = loc.getdcPort();
			// Response response =
			port.shipPositionReport(sprASPType);

			//Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).
			if(PropertiesLoaderImpl.findDebug()) System.out.println(">>> SUCESSO >>> Now ["
					+ logFormatter.format(new GregorianCalendar().getTime())
					+ "] Navio [" + a.getImoNumber() + "] MsgID [" + msgID
					+ "] MsgType [" + sprASPType.getMessageType()
					+ "] ReferenceID [" + sprASPType.getReferenceId() + "]");
			
			outRegister.println("success" + "; " + 
					logFormatterRegister.format(new GregorianCalendar().getTime()) +  "; " + 
					a.getLocalName() + "; " + 
					a.getImoNumber()  + "; " + 
					msgID  + ";" + 
					sprASPType.getMessageType()  + "; " + 
					sprASPType.getReferenceId()
					);

		} catch (Exception e) {
			System.out.println(">>> FALHA >>> Now ["
					+ logFormatter.format(new GregorianCalendar().getTime())
					+ "] Navio [" + a.getImoNumber() + "] MsgID [" + msgID
					+ "] MsgType [" + sprASPType.getMessageType()
					+ "] ReferenceID [" + sprASPType.getReferenceId() + "]");
			
			outRegister.println("fail" + "; " + 
					logFormatterRegister.format(new GregorianCalendar().getTime()) +  "; " + 
					a.getLocalName() + "; " + 
					a.getImoNumber()  + "; " + 
					msgID  + "; " + 
					sprASPType.getMessageType()  + "; " + 
					sprASPType.getReferenceId()
					);
			
			e.printStackTrace();
		}
	}

	private String geMsgID(String imo) {
		String msgID0;

		Double random = Math.random() * 100000;
		msgID0 = random.longValue() + "";
		while (msgID0.length() < 5)
			msgID0 = "0" + msgID0;

		msgID0 = PropertiesLoaderImpl.findLDU()
				+ formatadorImo.format(new GregorianCalendar().getTime())
				+ msgID0;

		return msgID0;
	}

}
