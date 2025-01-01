package org.game.services;

import org.game.CardinalPoint;
import org.game.Weather;
import org.game.unit.GameUnit;

public interface WeatherService {
    Weather getWeather();
    int getPenalty(CardinalPoint windDirection, CardinalPoint routeDirection);
    boolean isStorm(GameUnit unit);
}
