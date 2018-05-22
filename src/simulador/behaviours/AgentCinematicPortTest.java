package simulador.behaviours;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.io.WKTWriter;

import simulador.agents.ASPShipAgent;
import jade.core.behaviours.TickerBehaviour;

/**
 * Comportamento que realiza a atualização da posição de um agente no espaço
 * virtual, considerando a derrota entre dois pontos
 * 
 * @author taranti
 * 
 */
public class AgentCinematicPortTest extends TickerBehaviour {

	private static final long serialVersionUID = 1L;

	private ASPShipAgent a;
	private double lastTime;

	/**
	 * @param a
	 *            referencia do agente ASPShipAgent que cria o método
	 * @param period
	 *            periodo de atualização em milisegundos
	 */
	public AgentCinematicPortTest(ASPShipAgent a, long period) {
		super(a, period);
		this.a = a;
		lastTime = System.currentTimeMillis();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jade.core.behaviours.TickerBehaviour#onTick() Realiza a atualização
	 * periodica da posição
	 */
	@Override
	public void onTick() {

		double currentTime = System.currentTimeMillis();
		double deltaTime = currentTime - lastTime;

		// calculo de deslocamento
		// tempo em horas

		
		double deltaTimeHour;
		deltaTimeHour = deltaTime / (1000 * 60 * 60); // tempo em horas
		double curseRad = (a.getCurse() * (2 * Math.PI)) / 360;
		double deltaSpace = a.getSpeed() * deltaTimeHour;
		Coordinate transCoord = new Coordinate();

		if (curseRad <= (Math.PI / 2)) {
			transCoord.y = deltaSpace * Math.cos(curseRad);
			transCoord.x = deltaSpace * Math.sin(curseRad);
		} else if (curseRad <= (Math.PI)) {
			curseRad = Math.PI - curseRad;
			transCoord.y = (-1) * deltaSpace * Math.cos(curseRad);
			transCoord.x = deltaSpace * Math.sin(curseRad);
		} else if (curseRad <= (Math.PI * 3 / 2)) {
			curseRad = (Math.PI * 3 / 2) - curseRad;
			transCoord.y = (-1) * deltaSpace * Math.sin(curseRad);
			transCoord.x = (-1) * deltaSpace * Math.cos(curseRad);
		} else if (curseRad <= (Math.PI * 2)) {
			curseRad = (Math.PI * 2) - curseRad;
			transCoord.y = deltaSpace * Math.cos(curseRad);
			transCoord.x = (-1) * deltaSpace * Math.sin(curseRad);
		}

		// longitude - considera linha 12 horas - se ultrapassar maxino
		// reposiciona
		double tempX = a.getCoord().x + transCoord.x;
		if (tempX >= 180)
			a.getCoord().x = -180;
		else if (tempX < -180)
			a.getCoord().x = 180;
		else
			a.getCoord().x = tempX;

		// latitude - se o navio chegar ao polo norte é "transportado" para
		// o polo sul
		// longitude - considera linha 12 horas
		double tempY = a.getCoord().y + transCoord.y;
		if (tempY >= 90)
			a.getCoord().y = -90;
		else if (tempY < -90)
			a.getCoord().y = 90;
		else
			a.getCoord().y = tempY;

		// String pos = (WKTWriter.toPoint(a.getCoord()));

		Integer tempInt;
		double tempDouble;
		String tempString;

		// longitude
		tempDouble = a.getCoord().x;
		if (tempDouble > 0)
			tempString = "E";
		else {
			tempString = "W";
			tempDouble = (-1) * tempDouble;
		}

		// graus
		tempInt = (int) tempDouble;
		if (tempInt > 99)
			a.setTextCoordLong(tempInt.toString());
		else if (tempInt > 9)
			a.setTextCoordLong("0" + tempInt.toString());
		else
			a.setTextCoordLong("00" + tempInt.toString());

		// minutos
		tempDouble = (tempDouble - tempInt) * 60;
		tempInt = (int) tempDouble;

		if (tempInt > 9)
			a.setTextCoordLong(a.getTextCoordLong() + "." + tempInt.toString());
		else
			a
					.setTextCoordLong(a.getTextCoordLong() + ".0"
							+ tempInt.toString());

		// segundos e E/W

		tempDouble = (tempDouble - tempInt) * 60;
		tempInt = (int) tempDouble;

		if (tempInt > 9)
			a.setTextCoordLong(a.getTextCoordLong() + "." + tempInt.toString()
					+ "." + tempString);
		else
			a.setTextCoordLong(a.getTextCoordLong() + ".0" + tempInt.toString()
					+ "." + tempString);

		// latitude
		tempDouble = a.getCoord().y;

		if (tempDouble > 0)
			tempString = "N";
		else {
			tempString = "S";
			tempDouble = (-1) * tempDouble;
		}

		// graus
		tempInt = (int) tempDouble;
		if (tempInt > 9)
			a.setTextCoordLat(tempInt.toString());
		else
			a.setTextCoordLat("0" + tempInt.toString());

		// minutos
		tempDouble = (tempDouble - tempInt) * 60;
		tempInt = (int) tempDouble;

		if (tempInt > 9)
			a.setTextCoordLat(a.getTextCoordLat() + "." + tempInt.toString());
		else
			a.setTextCoordLat(a.getTextCoordLat() + ".0" + tempInt.toString());

		// segundos e N/S

		tempDouble = (tempDouble - tempInt) * 60;
		tempInt = (int) tempDouble;

		if (tempInt > 9)
			a.setTextCoordLat(a.getTextCoordLat() + "." + tempInt.toString()
					+ "." + tempString);
		else
			a.setTextCoordLat(a.getTextCoordLat() + ".0" + tempInt.toString()
					+ "." + tempString);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jade.core.behaviours.TickerBehaviour#onStart() forca a execucao da
	 * atualizacao de posicao na instanciacao
	 */
	@Override
	public void onStart() {
		onTick();
	}

}
