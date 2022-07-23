<?xml version="1.0" encoding="utf-8" ?>
<!-- Google__G__LogoTransform-4.xsl
   1. resize the Google__G_Logo SVG to be 120x120 pixel
   2. narrow vertical gap
   3. convert SVG to PNG
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:svg="http://www.w3.org/2000/svg">
  <xsl:import href="identity-transform.xsl"/>
  <xsl:import href="resize_G_Logo.xsl"/>

  <xsl:output indent="yes"/>
  <xsl:template match="/">
    <xsl:apply-templates select="*"/>
  </xsl:template>

  <xsl:template match="svg:g">
    <xsl:copy>
      <xsl:apply-templates select="@*"/>
      <xsl:apply-templates select="node()"/>
      <xsl:element name="svg:path">
        <xsl:attribute name="fill">#ffffff</xsl:attribute>
        <xsl:attribute name="d">M -15.554 39.100 L -15.554 63.239 L -16.000 63.239 L -16.000 39.100 Z</xsl:attribute>
      </xsl:element>
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>
