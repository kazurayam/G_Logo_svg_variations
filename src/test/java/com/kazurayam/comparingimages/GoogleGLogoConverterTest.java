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
        instance.setXsltDir(projectDir.resolve("src/test/xslt"));
        instance.setSourceSvg(projectDir.resolve("src/test/fixtures/Google__G__Logo.svg"));
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
}
