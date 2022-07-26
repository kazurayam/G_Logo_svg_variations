<?xml version="1.0" encoding="utf-8" ?>
<!-- Google__G__LogoTransform-2.xsl
   1. resize the Google__G_Logo SVG to be 120x120 pixel
   2. slightly chopped the blue bit
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

  <xsl:template match="svg:path[@fill='#4285F4']">
    <xsl:copy>
      <xsl:apply-templates select="@fill"/>
      <xsl:attribute name="d">
        <xsl:call-template name="replace-string">
          <xsl:with-param name="text" select="@d"/>
          <xsl:with-param name="replace" select="'L -14.754'"/>
          <xsl:with-param name="with" select="'L -10.000'"/>
        </xsl:call-template>
      </xsl:attribute>
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>
