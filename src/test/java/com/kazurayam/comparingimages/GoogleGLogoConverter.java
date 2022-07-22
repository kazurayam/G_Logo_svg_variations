package com.kazurayam.comparingimages;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.commons.io.FileUtils;

public class GoogleGLogoConverter {

    private Path xsltDir;
    private Path sourceSvg;
    private Path outputDir;

    public static void main(String[] args) throws IOException, TranscoderException, TransformerException {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        GoogleGLogoConverter instance = new GoogleGLogoConverter();
        instance.setXsltDir(projectDir.resolve("src/test/xslt"));
        instance.setSourceSvg(projectDir.resolve("src/test/fixtures/Google__G__Logo.svg"));
        Path outDir = projectDir.resolve("build/tmp/testOutput")
                .resolve(GoogleGLogoConverter.class.getSimpleName());
        if (Files.exists(outDir)) {
            FileUtils.deleteDirectory(outDir.toFile());
        }
        instance.setOutputDir(outDir);
        instance.proc1();
        instance.proc2();
    }
    public GoogleGLogoConverter() {}

    public void setXsltDir(Path xsltDir) {
        this.xsltDir = xsltDir;
    }

    public void setSourceSvg(Path sourceSvg) {
        this.sourceSvg = sourceSvg;
    }

    public void setOutputDir(Path outputDir) throws IOException {
        this.outputDir = outputDir;
        Files.createDirectories(outputDir);
    }

    public void proc1() throws TransformerException, TranscoderException, IOException {
        Path xsltFile = xsltDir.resolve(resolveXsltFilename("1"));
        Path resultSvg = outputDir.resolve(resolveResultSvgFilename("1"));
        ensureParentDir(resultSvg);
        this.transformSVG(sourceSvg, xsltFile, resultSvg);
        Path outputPng = outputDir.resolve(resolvePngFilename("1"));
        ensureParentDir(outputPng);
        this.convertSVGtoPNG(resultSvg, outputPng);
    }

    public void proc2() throws TransformerException, TranscoderException, IOException {
        Path xsltFile = xsltDir.resolve(resolveXsltFilename("2"));
        Path resultSvg = outputDir.resolve(resolveResultSvgFilename("2"));
        ensureParentDir(resultSvg);
        this.transformSVG(sourceSvg, xsltFile, resultSvg);
        Path outputPng = outputDir.resolve(resolvePngFilename("2"));
        ensureParentDir(outputPng);
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
        OutputStream os = Files.newOutputStream(png);
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
        return "svg/Google__G__Logo-" + n + ".svg";
    }

    private String resolvePngFilename(String n) {
        return "png/Google__G__Logo-" + n + ".png";
    }

    private void ensureParentDir(Path file) throws IOException {
        if (! Files.exists(file.getParent())) {
            Files.createDirectories(file.getParent());
        }
    }
}
