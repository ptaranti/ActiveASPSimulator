package simulador.behaviours;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.io.WKTWriter;

import simulador.agents.ASPShipAgent;
import simulador.suport.PropertiesLoaderImpl;
import jade.core.behaviours.TickerBehaviour;

/**
 * Comportamento que realiza a atualização da posição de um agente no espaço
 * virtual, considerando a ultima posicao e o rumo previsto.
 * <p>
 * Este comportamento tambem garante a permanencia dos agentes na área
 * geografica de teste, reposicionando-os no limite oposto cada vez que a borda
 * eh atingida
 * 
 * @author taranti
 * 
 */
public class SeaAgentCinematic extends TickerBehaviour {

	private static final long serialVersionUID = 1L;

	ASPShipAgent a;
	double lastTime;

	/**
	 * @param a
	 *            referencia ao ASPShipAgent que cria o comportamento
	 * @param period
	 *            periodo de atualizacao em milisegundos
	 */
	public SeaAgentCinematic(ASPShipAgent a, long period) {
		super(a, period);
		this.a = a;
		lastTime = System.currentTimeMillis();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jade.core.behaviours.TickerBehaviour#onTick() Metodo que gerencia o
	 * posicionamento do navio a cada periodo
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

		if(PropertiesLoaderImpl.findDebug()) System.out.println("agente "+ a.getLocalName()+ " no rumo" + a.getCurse()  + " velocidade: "
		 + a.getSpeed());

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

		// longitude - considera leste e oeste maximos para posicionamento
		double tempX = a.getCoord().x + transCoord.x;

		if (tempX >= PropertiesLoaderImpl.findLongEast())
			a.getCoord().x = PropertiesLoaderImpl.findLongWest();
		else if (tempX < PropertiesLoaderImpl.findLongWest())
			a.getCoord().x = PropertiesLoaderImpl.findLongEast();
		else
			a.getCoord().x = tempX;

		// latitude - se o navio chegar ao polo norte é "transportado" para
		// o polo sul
		// longitude - considera linha 12 horas
		double tempY = a.getCoord().y + transCoord.y;
		if (tempY >= PropertiesLoaderImpl.findLatNorth())
			a.getCoord().y = PropertiesLoaderImpl.findLatSouth();
		else if (tempY < PropertiesLoaderImpl.findLatSouth())
			a.getCoord().y = PropertiesLoaderImpl.findLatNorth();
		else
			a.getCoord().y = tempY;

		String pos = (WKTWriter.toPoint(a.getCoord()));

		if(PropertiesLoaderImpl.findDebug()) System.out.println(a.getLocalName() + "-> nova posição: " +pos);

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

		// System.out.println(textCoordLong);

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

		

		if(PropertiesLoaderImpl.findDebug()) System.out .println(a.getLocalName() + ", imoNumber " +
		 a.getImoNumber() + "-> nova posição:  " + a.getTextCoordLat() + " "  + a.getTextCoordLong());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jade.core.behaviours.TickerBehaviour#onStart() garante o
	 * posicionamento do navio na inicializacao
	 */
	@Override
	public void onStart() {
		onTick();
	}

}
