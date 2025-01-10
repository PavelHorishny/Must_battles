package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.game.Weather;

@Getter
@Setter
@Builder
public class WindRoseAreaState extends GameComponentState {
    private Weather weather;
}
