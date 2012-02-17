package com.md_5.minetunnel;

public enum SessionState {

    /**
     * In the exchange handshake state, the server is waiting for the client to
     * send its initial handshake packet.
     */
    EXCHANGE_HANDSHAKE,
    /**
     * In the exchange identification state, the server is waiting for the
     * client to send its identification packet.
     */
    EXCHANGE_IDENTIFICATION,
    /**
     * In the game state the session has an associated player.
     */
    GAME
}
