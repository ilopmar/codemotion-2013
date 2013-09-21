package es.codemotion.xmpp

import groovy.transform.TupleConstructor

@TupleConstructor
class HelpCommand implements XmppCommand {

    public String execute() {
        return """
        Commands available:
          stock QUOTE1 QUOTE2 ... QUOTEN
          weather CITY

        Example:
          stock GOOG AAPL DELL
          weather madrid
        """
    }
}