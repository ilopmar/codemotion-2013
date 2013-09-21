package es.codemotion.xmpp

import groovy.transform.TupleConstructor

@TupleConstructor
class StockCommand implements XmppCommand {
    List<String> quotes

    public String execute() {
        String formatedQuotes = this.quotes.join("+")

        String url = "http://finance.yahoo.com/d/quotes.csv?s=${formatedQuotes}&f=l1s"
        def content = new URL(url).getText("UTF-8")

        def stockPrices = []
        content.eachLine { line ->
            def rawData = line.split(",")

            String price = rawData[0]
            String quote = rawData[1]

            stockPrices << "\nPrice for ${quote} is ${price}"
        }

        return stockPrices.join("")
    }
}