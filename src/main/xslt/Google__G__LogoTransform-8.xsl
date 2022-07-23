<?xml version="1.0" encoding="utf-8" ?>
<!-- Google__G__LogoTransform-8.xsl
   1. resize the Google__G_Logo SVG to be 120x120 pixel
   2. turn the blue, green and yellow to black
   3. convert SVG to PNG
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:svg="http://www.w3.org/2000/svg">
  <xsl:import href="identity-transform.xsl"/>
  <xsl:import href="resize_G_Logo.xsl"/>
  <xsl:import href="replace-string.xsl"/>

  <xsl:output indent="yes"/>
  <xsl:template match="/">
    <xsl:apply-templates select="*"/>
  </xsl:template>

  <xsl:template match="svg:path[@fill='#4285F4' or @fill='#34A853' or @fill='#FBBC05' or @fill='#EA4335']">
    <xsl:copy>
      <xsl:attribute name="fill">#000000</xsl:attribute>
      <xsl:apply-templates select="@d"/>
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>
