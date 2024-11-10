package org.game.messaging;

import org.game.gui.panels.Message;

public interface Server {
    void onRequest(Client client, Message message);
}
