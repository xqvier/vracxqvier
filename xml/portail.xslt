<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<xsl:param name="datedujour" select="'2011-01-03'"/>
	<xsl:output name="portail" method="html" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>
	<xsl:template match="text()"/>
	<xsl:template match="/">
		<xsl:result-document href="index.html">
			<html>
				<head>
					<link rel="stylesheet" media="screen" type="text/css" title="Default" href="portail.css"/>
					<title>Portail de news</title>
				</head>
				<body>
					<xsl:apply-templates/>
				</body>
			</html>
		</xsl:result-document>
	</xsl:template>
	<xsl:template match="bdd">
		<div id="header">Portail de l'utilisateur</div>
		<xsl:call-template name="auteur"/>
		<xsl:call-template name="keyword"/>
		<xsl:call-template name="article"/>
	</xsl:template>
	<xsl:template name="article">
		<!-- Affichage dans l'index -->
		<div id="main">
			<h1>Articles du jour</h1>
			<xsl:for-each select="article">
				<xsl:if test="xs:string(date)=$datedujour">
					<a href="articles/{date}-{titre}.html">
						<xsl:value-of select="titre"/>
					</a>
					<br/>
				</xsl:if>
			</xsl:for-each>
			<br/>
			<hr/>
			<h1>Articles précédents...</h1>
			<xsl:for-each select="article">
				<xsl:if test="xs:string(date)!=$datedujour">
					<a href="articles/{date}-{titre}.html">
						<xsl:value-of select="titre"/>
					</a>
				</xsl:if>
			</xsl:for-each>
		</div>
		<!-- Creation des pages hypertexte liees -->
		<xsl:for-each select="article">
			<xsl:result-document href="articles/{date}-{titre}.html">
				<html>
					<head>
						<link rel="stylesheet" media="screen" type="text/css" title="Default" href="../portail.css"/>
						<title>
							<xsl:value-of select="titre"/>
						</title>
					</head>
					<body>
						<div id="header">
							<xsl:value-of select="titre"/>
						</div>
						<div id="main">
						Source : <a href="{site}">
								<xsl:value-of select="site"/>
							</a>
							<br/>
						Auteurs : <ul>
								<xsl:for-each select="auteur">
									<li>
										<a href="../auteurs/{text()}.html">
											<xsl:value-of select="text()"/>
										</a>
									</li>
								</xsl:for-each>
							</ul>
						Date : <xsl:value-of select="date"/>
							<br/>
							<p>Résumé : <xsl:value-of select="resume"/>
							</p>
						Mots clés : <ul>
								<xsl:for-each select="keyword">
									<li>
										<a href="../keywords/{text()}.html">
											<xsl:value-of select="text()"/>
										</a>
									</li>
								</xsl:for-each>
							</ul>
							<a href="{src}">Source</a>
						</div>
					</body>
				</html>
			</xsl:result-document>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="auteur">
		<!-- Affichage dans l'index -->
		<div id="auteurs">
			<h1>Auteurs</h1>
			<xsl:for-each-group select="article" group-by="auteur">
				<a href="auteurs/{current-grouping-key()}.html">
					<xsl:value-of select="current-grouping-key()"/>
				</a>
				<br/>
			</xsl:for-each-group>
		</div>
		<!-- Creation de la page hypertexte lie -->
		<xsl:for-each-group select="article" group-by="auteur">
			<xsl:result-document href="auteurs/{current-grouping-key()}.html">
				<html>
					<head>
						<link rel="stylesheet" media="screen" type="text/css" title="Default" href="../portail.css"/>
						<title>
							<xsl:value-of select="current-grouping-key()"/>
						</title>
					</head>
					<body>
						<div id="header">
							<xsl:value-of select="current-grouping-key()"/>
						</div>
						<div id="main">
							<ul>
								<xsl:for-each select="//article">
									<xsl:if test="auteur=current-grouping-key()">
										<li>
											<a href="../articles/{date}-{titre}.html">
												<xsl:value-of select="titre"/>
											</a>
										</li>
									</xsl:if>
								</xsl:for-each>
							</ul>
						</div>
					</body>
				</html>
			</xsl:result-document>
		</xsl:for-each-group>
	</xsl:template>
	<xsl:template name="keyword">
		<!-- Affichage dans l'index -->
		<div id="keywords">
			<h1>Mots clés</h1>
			<xsl:for-each-group select="article" group-by="keyword">
				<a href="keywords/{current-grouping-key()}.html">
					<xsl:value-of select="current-grouping-key()"/>
				</a>
				<br/>
			</xsl:for-each-group>
		</div>
		<!-- Creation de la page hypertexte lie -->
		<xsl:for-each-group select="article" group-by="keyword">
			<xsl:result-document href="keywords/{current-grouping-key()}.html">
				<html>
					<head>
						<link rel="stylesheet" media="screen" type="text/css" title="Default" href="../portail.css"/>
						<title>
							<xsl:value-of select="current-grouping-key()"/>
						</title>
					</head>
					<body>
						<div id="header">
							<xsl:value-of select="current-grouping-key()"/>
						</div>
						<div id="main">
							<ul>
								<xsl:for-each select="//article">
									<xsl:if test="keyword=current-grouping-key()">
										<li>
											<a href="../articles/{date}-{titre}.html">
												<xsl:value-of select="titre"/>
											</a>
										</li>
									</xsl:if>
								</xsl:for-each>
							</ul>
						</div>
					</body>
				</html>
			</xsl:result-document>
		</xsl:for-each-group>
	</xsl:template>
</xsl:stylesheet>
