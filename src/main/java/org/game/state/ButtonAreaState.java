package org.game.state;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class ButtonAreaState extends GameComponentState{
    private boolean onRepairButton;
    private boolean helpButton;
    private boolean selectedReadyForRepair;
    private boolean selectedReadyToHelp;
    private boolean selectedIsHelping;
    private boolean selectedOnRepair;
}
