#!/usr/bin/python

##############
# File: xmlRemake.py
# Name: XML Remaker
# Creator: D. Tyler Long
# Date: 01 Dec 2012
# Dependents: xml.etree, xml.dom
# About: This script takes the XML data parser found in the Python standard library
# and converts and re-organizes the data to a more user friendly readable format.
##############

from xml.etree import ElementTree
from xml.dom import minidom

def organize(elem):
    """Return a pretty-printed XML string for the Element.
    """
    rough_string = ElementTree.tostring(elem, 'utf-8')
    reparsed = minidom.parseString(rough_string)
    return reparsed.toprettyxml(indent="  ")