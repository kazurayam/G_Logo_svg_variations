package com.kazurayam.comparingimages;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleGLogoConverterTest {

    private static Path projectDir;
    private static Path outDir;
    private Path xsltDir;
    private Path sourceSvg;
    private GoogleGLogoConverter instance;

    @BeforeAll
    public static void beforeAll() throws IOException {
        projectDir = Paths.get(System.getProperty("user.dir"));
        outDir = projectDir.resolve("build/tmp/testOutput")
                .resolve(GoogleGLogoConverterTest.class.getSimpleName());
        if (Files.exists(outDir)) {
            FileUtils.deleteDirectory(outDir.toFile());
        }
    }

    @BeforeEach
    public void beforeEach() throws IOException {
        instance = new GoogleGLogoConverter();
        instance.setXsltDir(projectDir.resolve("src/main/xslt"));
        instance.setSourceSvg(projectDir.resolve("src/main/data/Google__G__Logo.svg"));
        instance.setOutputDir(outDir);
    }

    @Test
    public void test_proc1() throws TranscoderException, IOException, TransformerException {
        GoogleGLogoConverterResult result = instance.proc1();
        assertTrue(Files.exists(result.getSvg()));
        assertTrue(Files.exists(result.getPng()));
    }

    @Test
    public void test_proc2() throws TranscoderException, IOException, TransformerException {
        GoogleGLogoConverterResult result = instance.proc2();
        assertTrue(Files.exists(result.getSvg()));
        assertTrue(Files.exists(result.getPng()));
    }

    @Test
    public void test_proc3() throws TranscoderException, IOException, TransformerException {
        GoogleGLogoConverterResult result = instance.proc3();
        assertTrue(Files.exists(result.getSvg()));
        assertTrue(Files.exists(result.getPng()));
    }
    @Test
    public void test_proc5() throws TranscoderException, IOException, TransformerException {
        GoogleGLogoConverterResult result = instance.proc5();
        assertTrue(Files.exists(result.getSvg()));
        assertTrue(Files.exists(result.getPng()));
    }
    @Test
    public void test_proc6() throws TranscoderException, IOException, TransformerException {
        GoogleGLogoConverterResult result = instance.proc6();
        assertTrue(Files.exists(result.getSvg()));
        assertTrue(Files.exists(result.getPng()));
    }

    @Test
    public void test_proc7() throws TranscoderException, IOException, TransformerException {
        GoogleGLogoConverterResult result = instance.proc7();
        assertTrue(Files.exists(result.getSvg()));
        assertTrue(Files.exists(result.getPng()));
    }

    @Test
    public void test_proc8() throws TranscoderException, IOException, TransformerException {
        GoogleGLogoConverterResult result = instance.proc8();
        assertTrue(Files.exists(result.getSvg()));
        assertTrue(Files.exists(result.getPng()));
    }
}
