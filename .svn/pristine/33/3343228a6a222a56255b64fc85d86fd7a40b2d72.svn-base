package simulador.behaviours;

import jade.core.behaviours.TickerBehaviour;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import simulador.agents.ASPShipAgent;
import simulador.objects.Ordem;
import simulador.suport.PropertiesLoaderImpl;

/**
 * Comportamento de execucao periodica que verifica a necessidade de envio de
 * mensagens em cumprimento a requisicoes recebidas
 * <p>
 * A cada periodo tambem sao verificadas as ordens que jah perderam efeito e as
 * mesmas sao retiradas do agendamento.
 * 
 * @author taranti
 */
public class VerificaEnvio extends TickerBehaviour {

	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat formatter = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ss.SSS");
	private ASPShipAgent a;
	private int cont = 0;
	private long lastTime;
	private long nowTime = System.currentTimeMillis();
	private long period;

	/**
	 * @param a
	 *            referencia ao agente que cria o comportamento
	 * @param period
	 *            periodo de execucao em milisegundos. O valor default eh 15
	 *            minutos para aderir ao protocolo de comunicacoes do LRIT,
	 *            contudo o valor pode ser configurado parea forcar carga sobre
	 *            o Centro de Dados, a fim de executar testes de desempenho.
	 */
	public VerificaEnvio(ASPShipAgent a, long period) {
		super(a, period);
		this.a = a;
		this.period = period;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jade.core.behaviours.TickerBehaviour#onTick()
	 */
	@Override
	public void onTick() {

	
		lastTime = nowTime;
		nowTime	= System.currentTimeMillis();
				atualizaLista();
		cont = cont + 1;	
	}

	@Override
	public void onStart() {
		super.onStart();
		// this.cont = 0;
		onTick();
	}

	private void atualizaLista() {

		if(nowTime-lastTime > period && nowTime-lastTime < period*1.05){
			System.out.println("agente "+ a.getLocalName()+ " esta executando avanco" +
					" da simulacao com atraso superior a 5% ou o avanco esta adiantado ");
			System.out.println("Simulação sendo encerrada - reconfigure para reduzir " +
					"consumo de recursos ou troque o host");
			System.exit(1);			
		}
		
		assert(nowTime-lastTime > period && nowTime-lastTime < period*1.05):"agente "+ a.getLocalName()+ " esta executando avanco da simulacao com atraso superior a 5% ou o avanco esta adiantado ";
		
		// limpa antigas
	
		// int freqNova = (6 * 60); // maior freq possivel

		for (Ordem ordem : a.getListaOrdens()) {
			if(PropertiesLoaderImpl.findDebug()) System.out.println(">>> ANTES Freq [" + ordem.getFreq()
					+ "] MsgType [" + ordem.getMsgType() + "] Origem ["
					+ ordem.getOrigem() + "] ReferenceId ["
					+ ordem.getReferenceID() + "] StopTime ["
					+ formatter.format(new Date(ordem.getStopTime())) + "]");
		}

		List<Ordem> listOrdemTemp = new Vector<Ordem>();

		for (Ordem ordem : a.getListaOrdens()) {

			if (ordem.getStopTime() < nowTime)
				listOrdemTemp.add(ordem);

		}

		for (Ordem ordem : listOrdemTemp) {
			if(PropertiesLoaderImpl.findDebug()) System.out.println(">>> REMOVENDO ORDEM Freq [" + ordem.getFreq()
					+ "] MsgType [" + ordem.getMsgType() + "] Origem ["
					+ ordem.getOrigem() + "] ReferenceId ["
					+ ordem.getReferenceID() + "] StopTime ["
					+ formatter.format(new Date(ordem.getStopTime())) + "]");
			a.getListaOrdens().remove(ordem);
		}

		for (Ordem ordem : a.getListaOrdens()) {
			if(PropertiesLoaderImpl.findDebug())System.out.println(">>> DEPOIS Freq [" + ordem.getFreq()
					+ "] MsgType [" + ordem.getMsgType() + "] Origem ["
					+ ordem.getOrigem() + "] ReferenceId ["
					+ ordem.getReferenceID() + "] StopTime ["
					+ formatter.format(new Date(ordem.getStopTime())) + "]");
		}

		// ajusta frequencia para menor solicitada

		for (Ordem ordem : a.getListaOrdens()) {
			if (ordem.getStartTime() < nowTime) {
				if (((cont * 15) % (ordem.getFreq())) == 0) {

					try {
						a.addBehaviour(new SendMessage(a, ordem
								.getReferenceID(), ordem.getMsgType()));

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			} else {
				if(PropertiesLoaderImpl.findDebug()) System.out.println(">>> Ordem ainda não iniciada StartTime ["
						+ formatter.format(new Date(ordem.getStartTime()))
						+ "]");
			}
		}

		if(PropertiesLoaderImpl.findDebug()) System.out.println("atualizando lista de ordens para " +
		a.getLocalName()+"\ncontadoe de verificações = " +cont);

		// envio a cada seis horas
		if (((cont * 15) % 360) == 0) {

			try {
				a.addBehaviour(new SendMessage(a, null, "1"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Codigo para enviar a cada intervalo
		if (PropertiesLoaderImpl.findSendEachStepType() == true) {
			try {
				a.addBehaviour(new SendMessage(a, null, "1"));

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}