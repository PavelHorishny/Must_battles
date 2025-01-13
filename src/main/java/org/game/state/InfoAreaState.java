package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.game.UnitData;

@Getter
@Setter
@Builder
@ToString
public class InfoAreaState extends GameComponentState {
    private String day;
    private boolean selected;
    private UnitData selectedData;
    private boolean target;
    private UnitData targetData;
}
