package org.game.services;

import org.game.map.Surface;

import java.util.List;

public interface FiringService {
    void setUnderAttack(List<Surface> fireZone/*, ArrayList<Surface> activeCells*/, boolean isFirstPlayer);
}
