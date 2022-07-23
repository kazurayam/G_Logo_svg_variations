package com.kazurayam.googleglogo;

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

/**
 * INPUT: a *.svg file of Google "G" Logo
 * OUTPUT:
 *   - a *.svg file of Google "G" Logo slightly transformed
 *   - a *.png file converted from the transformed SVG
 *
 * This class implements a set of "procN" methods where N=1,2,3,...
 *
 * N=1: identical to the source SVG but resized to width=120, height=120 pixcels
 * N=2: slightly chopped the blue bit
 * N=3: shopped the blue bit off
 * N=4: small gap
 * N=5: turn the blue bit to black
 * N=6: more black, turn the green bit to black
 * N=7: more black, turn the yellow bit to black
 * N=8: more black, turn the red bit to black
 * N=9: N/A
 * N=a: N/A
 * N=b: switch the color; red and blue
 * N=c: N/A
 * N=d: N/A
 * N=e: flipped vertically
 * N=f: rotated
 * N=g: flipped horizontally
 *
 *
 * Inspired by a post https://forum.katalon.com/t/verify-image-present-accuracy/67129
 */
public class GoogleGLogoConverter {
    private Path xsltDir;
    private Path sourceSvg;
    private Path outputDir;

    public static void main(String[] args) throws IOException, TranscoderException, TransformerException {
        Path projectDir = Paths.get(System.getProperty("user.dir"));
        GoogleGLogoConverter instance = new GoogleGLogoConverter();
        instance.setXsltDir(projectDir.resolve("src/main/xslt"));
        instance.setSourceSvg(projectDir.resolve("src/main/data/Google__G__Logo.svg"));
        Path outDir = projectDir.resolve("docs/output");
        if (Files.exists(outDir)) {
            FileUtils.deleteDirectory(outDir.toFile());
        }
        instance.setOutputDir(outDir);
        //
        instance.proc1();
        instance.proc2();
        instance.proc3();
        instance.proc4();
        instance.proc5();
        instance.proc6();
        instance.proc7();
        instance.proc8();
        instance.proc9();
        instance.procA();
        instance.procB();
        instance.procC();
        instance.procD();
        instance.procE();
        instance.procF();
        instance.procG();

        System.out.println("Done. Find the output at " + outDir.toString());
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

    /**
     * Just resized, not modification of image
     */
    public GoogleGLogoConverterResult proc1() throws TransformerException, TranscoderException, IOException {
        return this.process("1");
    }

    /**
     * The blue portion is slightly chopped
     */
    public GoogleGLogoConverterResult proc2() throws TransformerException, TranscoderException, IOException {
        return this.process("2");
    }

    /**
     * The blue portion is completely erased
     */
    public GoogleGLogoConverterResult proc3() throws TransformerException, TranscoderException, IOException {
        return this.process("3");
    }

    /**
     * Narrow gap
     */
    public GoogleGLogoConverterResult proc4() throws TransformerException, TranscoderException, IOException {
        return this.process("4");
    }

    /**
     * Turn blue to black
     */
    public GoogleGLogoConverterResult proc5() throws TransformerException, TranscoderException, IOException {
        return this.process("5");
    }

    /**
     * Turn blue + green to black
     */
    public GoogleGLogoConverterResult proc6() throws TransformerException, TranscoderException, IOException {
        return this.process("6");
    }

    /**
     * Turn blue + green + yellow to black
     */
    public GoogleGLogoConverterResult proc7() throws TransformerException, TranscoderException, IOException {
        return this.process("7");
    }

    /**
     * All portions in black
     */
    public GoogleGLogoConverterResult proc8() throws TransformerException, TranscoderException, IOException {
        return this.process("8");
    }

    /**
     * Black background
     */
    public GoogleGLogoConverterResult proc9() throws TransformerException, TranscoderException, IOException {
        return this.process("9");
    }

    /**
     * Dirt points
     */
    public GoogleGLogoConverterResult procA() throws TransformerException, TranscoderException, IOException {
        return this.process("A");
    }

    /**
     * Switch colors
     */
    public GoogleGLogoConverterResult procB() throws TransformerException, TranscoderException, IOException {
        return this.process("B");
    }

    /**
     * SkewX
     */
    public GoogleGLogoConverterResult procC() throws TransformerException, TranscoderException, IOException {
        return this.process("C");
    }

    /**
     * Resize smaller
     */
    public GoogleGLogoConverterResult procD() throws TransformerException, TranscoderException, IOException {
        return this.process("D");
    }

    /**
     * Flip vertical
     */
    public GoogleGLogoConverterResult procE() throws TransformerException, TranscoderException, IOException {
        return this.process("E");
    }

    /**
     * Rotate
     */
    public GoogleGLogoConverterResult procF() throws TransformerException, TranscoderException, IOException {
        return this.process("F");
    }

    /**
     * Flip horizontally
     */
    public GoogleGLogoConverterResult procG() throws TransformerException, TranscoderException, IOException {
        return this.process("G");
    }

    private GoogleGLogoConverterResult process(String n) throws TransformerException, TranscoderException, IOException {
        Path xsltFile = xsltDir.resolve(resolveXsltFilename(n));
        Path resultSvg = outputDir.resolve(resolveResultSvgFilename(n));
        ensureParentDir(resultSvg);
        this.transformSVG(sourceSvg, xsltFile, resultSvg);
        Path outputPng = outputDir.resolve(resolvePngFilename(n));
        ensureParentDir(outputPng);
        this.convertSVGtoPNG(resultSvg, outputPng);
        return new GoogleGLogoConverterResult(resultSvg, outputPng);
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
        return "svg/GoogleGLogo-" + n + ".svg";
    }

    private String resolvePngFilename(String n) {
        return "png/GoogleGLogo-" + n + ".png";
    }

    private void ensureParentDir(Path file) throws IOException {
        if (! Files.exists(file.getParent())) {
            Files.createDirectories(file.getParent());
        }
    }
}
