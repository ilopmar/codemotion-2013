package es.codemotion.photo

import groovy.json.JsonBuilder

class BrowserPushService {
    static transactional = false

    public void pushToBrowser(FileToConvert fileToConvert) {
        println "Pushing to browser - start ${fileToConvert.output}"
        def fullPathName = fileToConvert.output
        def fileName = fullPathName.substring(fullPathName.lastIndexOf("/") + 1, fullPathName.length())

        def builder = new JsonBuilder()
        builder {
            url(fileName)
        }

        event topic:'photo', data:builder.toString()
        println "Pushing to browser - end ${fileToConvert.output}"
    }
}