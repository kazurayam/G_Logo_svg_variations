<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:svg="http://www.w3.org/2000/svg">
  <xsl:import href="identity-transform.xsl"/>
  <xsl:output indent="yes"/>
  <xsl:template match="/">
    <xsl:apply-templates select="*"/>
  </xsl:template>

  <xsl:template match="svg:svg">
    <xsl:copy>
      <xsl:apply-templates select="@viewBox"/>

      <!-- change width="24" to width="240", height="24" to height="240" -->
      <xsl:attribute name="width">120</xsl:attribute>
      <xsl:attribute name="height">120</xsl:attribute>

      <xsl:apply-templates />
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>
