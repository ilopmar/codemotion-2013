package es.codemotion.photo

import org.apache.commons.lang.RandomStringUtils

class FileService {

    def grailsApplication

    public FileToConvert processFiles(File file) {
        println "Generating file name - start"

        String outputPath = grailsApplication.config.codemotion.photos.webpath
        String filename = RandomStringUtils.randomAlphanumeric(10)
        def outputFile = "${outputPath}/${new Date().time}_${filename}.png"

        println "Generating file name - end"
        return new FileToConvert(input: file.absolutePath, output: outputFile)
    }

    public void deleteFile(FileToConvert fileToConvert) {
        println "Deleting - start ${fileToConvert.input}"
        new File(fileToConvert.input).delete()
        println "Deleting - end ${fileToConvert.input}"
    }
}