package com.kazurayam.comparingimages;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class GoogleGLogoConverter {

    private Path xsltDir;
    private Path sourceSvg;

    private Path testOutputDir;

    public static void main(String[] args) throws IOException, TranscoderException, TransformerException {
        GoogleGLogoConverter instance = new GoogleGLogoConverter();
        instance.proc1();
    }
    public GoogleGLogoConverter() throws IOException {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        xsltDir = projectDir.resolve("src/test/xslt");
        sourceSvg = projectDir.resolve("src/test/fixtures/Google__G__Logo.svg");
        testOutputDir = projectDir.resolve("build/tmp/testOutput")
                .resolve(this.getClass().getSimpleName());
        Files.createDirectories(testOutputDir);
    }

    public void proc1() throws TransformerException, TranscoderException, IOException {
        Path xsltFile = xsltDir.resolve(resolveXsltFilename("1"));
        Path resultSvg = testOutputDir.resolve(resolveResultSvgFilename("1"));
        this.transformSVG(sourceSvg, xsltFile, resultSvg);
        Path outputPng = testOutputDir.resolve(resolvePngFilename("1"));
        this.convertSVGtoPNG(resultSvg, outputPng);
    }

    /**
     * transform a source SVG into a result SVG
     */
    private void transformSVG(Path source, Path xslt, Path result)
            throws TransformerException {
        StreamSource styleSource = new StreamSource(xslt.toFile());
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer(styleSource);
        StreamSource ss = new StreamSource(source.toFile());
        StreamResult sr = new StreamResult(result.toFile());
        transformer.transform(ss, sr);
    }

    /**
     * convert the result SVG into a PNG image
     * @param svg INPUT, Scalable Vector Graphics
     * @param png OUTPUT, Portable Network Graphics
     */
    private void convertSVGtoPNG(Path svg, Path png)
            throws IOException, TranscoderException {
        // SVG as Input
        Reader svgReader = new FileReader(svg.toFile());
        TranscoderInput transcoderInput = new TranscoderInput(svgReader);
        // Output
        OutputStream os = new FileOutputStream(png.toFile());
        TranscoderOutput transcoderOutput = new TranscoderOutput(os);
        // Convert SVG to PNG and Save into file
        PNGTranscoder transcoder = new PNGTranscoder();
        transcoder.transcode(transcoderInput, transcoderOutput);
        // Clean Up
        os.flush();
        os.close();
    }


    private String resolveXsltFilename(String n) {
        return "Google__G__LogoTransform-" + n + ".xsl";
    }

    private String resolveResultSvgFilename(String n) {
        return "Google__G__Logo-" + n + ".svg";
    }

    private String resolvePngFilename(String n) {
        return "Google__G__Logo-" + n + ".png";
    }
}
