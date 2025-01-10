package org.game.messaging;

import org.game.Request;

public interface Server {
    void onRequest(Client client, Request request);
}
