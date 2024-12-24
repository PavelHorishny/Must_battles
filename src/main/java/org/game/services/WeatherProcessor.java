package org.game.services;

import org.game.CardinalPoint;
import org.game.CardinalPointsNet;
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

    /**
     * @param windDirection 
     * @param routeDirection
     * @return
     */
    @Override
    public int getPenalty(CardinalPoint windDirection, CardinalPoint routeDirection) {
        int result = 0;
        CardinalPoint left = CardinalPointsNet.pointers.get(windDirection).getLeft();
        CardinalPoint right = CardinalPointsNet.pointers.get(windDirection).getRight();
        if(windDirection==routeDirection){
            return result;
        }
        for (int i = 1; i < CardinalPointsNet.pointers.size(); i++) {
            if (left == routeDirection || right == routeDirection) {
                result=i;
                break;
            }
            left = CardinalPointsNet.pointers.get(left).getLeft();
            right = CardinalPointsNet.pointers.get(right).getRight();
        }
        return result;
    }
}
