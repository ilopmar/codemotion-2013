package es.codemotion.xmpp

import groovy.json.JsonSlurper
import groovy.transform.TupleConstructor

@TupleConstructor
class WeatherCommand implements XmppCommand {
    String city

    public String execute() {
        String url = "http://api.openweathermap.org/data/2.5/weather?units=metric&q=${this.city}"
        def content = new URL(url).getText("UTF-8")
        def response = new JsonSlurper().parseText(content)

        def weather = []
        response.weather.each { w ->
            weather << w.description
        }
        String country = response.sys.country
        String temp = response.main.temp

        return "The weather for ${city} (${country}) is: \n Weather: ${weather.join(", ")} \n Temp: ${temp} ºC"
    }
}