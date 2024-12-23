package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.game.UnitData;

@Getter
@Setter
@Builder
public class InfoAreaState extends GameComponentState {
    private String day;
    private boolean selected;
    private UnitData selectedData;
    private boolean target;
    private UnitData targetData;
}
