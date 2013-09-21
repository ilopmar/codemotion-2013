package es.codemotion.xmpp

class XmppService {
    static transactional = false

    public void chatMessageReceived(String msg) {
        println "<< Received: ${msg}"
    }
}