<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
   <head>
      <link href="mycss.css" rel="stylesheet" type="text/css"/>
   </head>
  <body>
    <h1>CD collectie</h1>
    <table class="center">
        <tr class="trcolor1">
		    <td><b>artiest</b></td>
		    <td><b>titel</b></td>
		    <td><b>jaar</b></td>
            <td><b>lener</b></td>
        </tr>
        <xsl:for-each select="collectie/cd">
            <tr class="trcolor2">
		        <td><xsl:value-of select="artiest" /></td>
		        <td><xsl:value-of select="titel" /></td>
		        <td><xsl:value-of select="jaar" /></td>
                <td><xsl:value-of select="lener" /></td>
            </tr>
        </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>