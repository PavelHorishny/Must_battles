package org.game.services;

import org.game.CardinalPoint;
import org.game.Weather;
import org.game.Wind;

import java.util.Random;

public class WeatherProcessor implements WeatherService{
    private static final Random random = new Random();
    /**
     * @return 
     */
    @Override
    public Weather getWeather() {
        CardinalPoint cardinalPoint = CardinalPoint.cardinalPoints[random.nextInt(CardinalPoint.cardinalPoints.length)];
        Wind wind = Wind.windForce[random.nextInt(Wind.windForce.length)];
        return new Weather(cardinalPoint,wind);
    }
}
