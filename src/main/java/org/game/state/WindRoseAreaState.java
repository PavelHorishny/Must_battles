package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.game.Weather;

@Getter
@Setter
@Builder
@ToString
public class WindRoseAreaState extends GameComponentState {
    private Weather weather;
}
