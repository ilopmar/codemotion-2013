package es.codemotion.photo

import org.im4java.core.ConvertCmd
import org.im4java.core.IMOperation

class ImageConverter {

    public FileToConvert applyEffect(FileToConvert fileToConvert) {
        println "Applying effect - start: ${fileToConvert.input}..."

        def inputFile = fileToConvert.input
        def outputFile = fileToConvert.output

        def rnd = new Random()

        def op = new IMOperation()
        op.addImage(inputFile)
        op.thumbnail(260, 260)
        op.set("caption", "Codemotion 2013")
        op.gravity("center")
        op.pointsize(22)
        op.background("black")
        int polaroidRotation = rnd.nextInt(6)
        rnd.nextBoolean() ? op.polaroid(polaroidRotation) : op.polaroid(-polaroidRotation)
        op.addImage(outputFile)

        def command = new ConvertCmd()
        command.run(op)

        println "Applying effect - end: ${fileToConvert.input}"

        return fileToConvert
    }
}