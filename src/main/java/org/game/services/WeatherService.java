package org.game.services;

import org.game.CardinalPoint;
import org.game.Weather;

public interface WeatherService {
    Weather getWeather();
    int getPenalty(CardinalPoint windDirection, CardinalPoint routeDirection);
}
