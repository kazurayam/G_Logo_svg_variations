<?xml version="1.0" encoding="utf-8" ?>
<!-- Google__G__LogoTransform-1.xsl
   1. resize the Google__G_Logo SVG to be 120x120 pixel
   2. no modification of shape and colors
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

</xsl:stylesheet>
