package org.game.messaging;

import org.game.Request;
import org.game.gui.panels.Message;

public interface Server {
    void onRequest(Client client, Request request);
}
