# Google G Logo SVG variations

## Background

One day I found a post by edward davis:

- https://forum.katalon.com/t/verify-image-present-accuracy/67129

He showed a set of Google G Logo variations:

![G Logo variations](docs/images/3203b879238e8d34698dae233682d017bbbe46df.png)

I was interested in this. The set could be a good fixture to test many software components that compare images with variable accuracy requirements.

## What I have done

I wanted to create a similar set of "Google G Logo" images myself.

I found that a Scalable Vector Graphics of Google G Logo is publicly available at:

- https://commons.wikimedia.org/wiki/File:Google_%22G%22_Logo.svg

I am capable of writing XSLT stylesheets that take the original SVG file as input, outputs transformed SVG files. Yes, I did a lot of XSLT programming 20 years ago. Let me try it again.

Not only SVG to SVG transformation, I would try converting vector images in SVG into raster images in PNG format.

I have developed a Java class, which drives XSLT and Apache Batik library.

- [com.kazurayam.googleglogo.GoogleGLogoConverter](https://github.com/kazurayam/G_Logo_svg_variations/blob/master/src/main/java/com/kazurayam/googleglogo/GoogleGLogoConverter.java)


The `GoogleGLogoConverter` has `public static void main(String[] args)` method. You can execute it by a Gradle task:

```
$ cd <GoogleGLogoVariations project dir>
$ ./gradlew run
```

You can see the demo output at:

- https://kazurayam.github.io/G_Logo_svg_variations/

Also you can download the zip file which contains all SVG and PNG files at:

- https://github.com/kazurayam/G_Logo_svg_variations/releases

