package com.company;

import org.w3c.dom.*;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.io.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.List;


public class Main {

    public static void main(String[] args) {



       Parsing ParsingObject = new Parsing();
      // ParsingObject.Input();
      // ParsingObject.Output();
        ParsingObject.FonctionVerif();
      // ParsingObject.CalculSum();
        System.out.print(" jar is working ");




  }

}
//// INPUT file, Output File, Config Fle