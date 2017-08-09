<?xml version="1.0"?>
<!--
 * Copyright (c) 2016-2017 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://www.w3.org/1999/xhtml" version="1.0">
    <xsl:output method="html" doctype-system="about:legacy-compat"
        encoding="UTF-8" indent="yes" />
    <xsl:strip-space elements="*"/>
    <xsl:include href="/xsl/inner-layout.xsl"/>
    <xsl:template match="page" mode="head">
        <title>
            <xsl:text>board</xsl:text>
        </title>
    </xsl:template>
    <xsl:template match="page" mode="inner">
        <xsl:apply-templates select="projects"/>
    </xsl:template>
    <xsl:template match="projects[not(project)]">
        <p>
            <xsl:text>There are no projects available at the moment.</xsl:text>
        </p>
    </xsl:template>
    <xsl:template match="projects[project]">
        <p>
            <xsl:text>To join any of these projects you have</xsl:text>
            <xsl:text> to apply, but asking @0crat in a chat,</xsl:text>
            <xsl:text> see </xsl:text>
            <a href="http://datum.zerocracy.com/pages/policy.html#1">
                <xsl:text>par.1</xsl:text>
            </a>
            <xsl:text> of our Policy.</xsl:text>
        </p>
        <table>
            <thead>
                <tr>
                    <th>
                        <xsl:text>ID</xsl:text>
                    </th>
                    <th>
                        <xsl:text>Repositories</xsl:text>
                    </th>
                    <th>
                        <xsl:text>Members</xsl:text>
                    </th>
                    <th>
                        <xsl:text>Jobs</xsl:text>
                    </th>
                </tr>
            </thead>
            <tbody>
                <xsl:apply-templates select="project"/>
            </tbody>
        </table>
    </xsl:template>
    <xsl:template match="project">
        <tr>
            <td>
                <code>
                    <xsl:value-of select="id"/>
                </code>
            </td>
            <td>
                <xsl:apply-templates select="repositories"/>
            </td>
            <td>
                <xsl:if test="mine='false'">
                    <xsl:value-of select="members"/>
                </xsl:if>
                <xsl:if test="mine='true'">
                    <xsl:if test="members = 1">
                        <xsl:text>you</xsl:text>
                    </xsl:if>
                    <xsl:if test="members &gt; 1">
                        <xsl:value-of select="members - 1"/>
                        <xsl:text>+you</xsl:text>
                    </xsl:if>
                </xsl:if>
            </td>
            <td>
                <xsl:value-of select="jobs"/>
            </td>
        </tr>
    </xsl:template>
    <xsl:template match="repositories">
        <xsl:for-each select="repository">
            <xsl:if test="position() &gt; 1">
                <xsl:text>, </xsl:text>
            </xsl:if>
            <a href="https://github.com/{.}">
                <xsl:value-of select="."/>
            </a>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>