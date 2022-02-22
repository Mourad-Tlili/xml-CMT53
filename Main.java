package com.system;
import org.w3c.dom.*;

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


public class Main {
  public static void main(String[] args) {
	// write your code here
        String crunchifyFile = "src/CAM53.xml";

        try {
            //////////////Parsing des données XML JVA////////////

            File inputFile = new File("src/CAM53.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("Document");
            NodeList NtryList = doc.getElementsByTagName("Ntry");
            NodeList DomnList = doc.getElementsByTagName("Domn");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    /*System.out.println("Student roll no : "
                            + eElement.getAttribute("rollno")); */
                    String Domn2 = eElement.getElementsByTagName("Domn")
                            .item(0)
                            .getTextContent().intern();
                  /*String  essai = Domn2.replaceAll("\\s+","");
                  System.out.println(essay);*/

                    for (int ntry = 0; ntry < NtryList.getLength(); ntry++) {
                    String NtryEssai = eElement.getElementsByTagName("Ntry")
                            .item(1)
                            .getTextContent().intern();

                    boolean c = NtryEssai.contains("PMNT");
                    boolean d = NtryEssai.contains("RCDT");
                    boolean f = NtryEssai.contains("XBCT");
                    System.out.println(c);
                    System.out.println(d);
                    System.out.println(f);



                        System.out.println("ID : "
                                + eElement
                                .getElementsByTagName("Id")
                                .item(0)
                                .getTextContent());
                        System.out.println("Date : "
                                + eElement
                                .getElementsByTagName("CreDtTm")
                                .item(0)
                                .getTextContent());
                        String DateCreDtTm = eElement
                                .getElementsByTagName("CreDtTm")
                                .item(0)
                                .getTextContent();
                        String Ccy53 = eElement
                                .getElementsByTagName("Ccy")
                                .item(0)
                                .getTextContent();
                        String BIC53 = eElement
                                .getElementsByTagName("BIC")
                                .item(0)
                                .getTextContent();
                        String MBIC53Mappé = BIC53.substring(0, 8);

                        NodeList nodes = doc.getElementsByTagName("CdtDbtInd");
                        int NbOfNtries53 = nodes.getLength();

                        String IBAN53 = eElement
                                .getElementsByTagName("IBAN")
                                .item(0)
                                .getTextContent();


                   /* double value= 0.0;

                    String price = eElement.getElementsByTagName("Sum").item(0).getTextContent();
                    value = value + Double.parseDouble(price);
                    System.out.println(value);
                    double sum = 0.0;
                    System.out.println("NbOfNtries: " +  eElement.getElementsByTagName("NbOfNtries").item(0).getTextContent());
                    sum += Double.parseDouble(eElement.getElementsByTagName("NbOfNtries").item(0).getTextContent());
                    System.out.println(sum); */

                        //////////NTRY//////////

                        String Amt53 = eElement
                                .getElementsByTagName("Amt")
                                .item(ntry)
                                .getTextContent();

                        String Nm53 = eElement
                                .getElementsByTagName("Nm")
                                .item(0)
                                .getTextContent();

                        String CdtDbtInd53 = eElement
                                .getElementsByTagName("CdtDbtInd")
                                .item(ntry)
                                .getTextContent();
                        String Sts53 = eElement
                                .getElementsByTagName("Sts")
                                .item(0)
                                .getTextContent();
                        String Dt53 = eElement
                                .getElementsByTagName("Dt")
                                .item(ntry)
                                .getTextContent();

                        String TtlAmt53 = eElement
                                .getElementsByTagName("Amt")
                                .item(0)
                                .getTextContent();

                        String AcctSvcrRef53 = eElement
                                .getElementsByTagName("AddtlTxInf")
                                .item(0)
                                .getTextContent();

                        ///A vérifier
                        int a = AcctSvcrRef53.length();
                        int b = a - 17;
                   /*System.out.println(a);
                   System.out.println(b);*/

                        String AcctSvcrRef53Mappé = AcctSvcrRef53.substring(b, a - 1);
                        System.out.println(AcctSvcrRef53Mappé);

                        //////Vérification Règle PMNT+RCDT+XBCT/////
                   /* String Domn2 =  eElement.getElementsByTagName("Domn")
                            .item(0)
                            .getTextContent().intern();

                    if (Domn2!="PMNT RCDT XBCT")
                    {
                        System.out.println("NOT THE GOOD ONE");
                    }
                    else {
                        System.out.println(Domn2);
                    }
                  */


                        String SubFmlyCd53 = eElement
                                .getElementsByTagName("SubFmlyCd")
                                .item(0)
                                .getTextContent();


                        String InstrId53 = eElement
                                .getElementsByTagName("InstrId")
                                .item(0)
                                .getTextContent();

                        String EndToEndId53 = eElement
                                .getElementsByTagName("EndToEndId")
                                .item(0)
                                .getTextContent();

                        String Ustrd53 = eElement
                                .getElementsByTagName("Ustrd")
                                .item(0)
                                .getTextContent();

                        String DbtrAcct53 = eElement
                                .getElementsByTagName("DbtrAcct")
                                .item(0)
                                .getTextContent().intern();
                        System.out.println(DbtrAcct53);

                        String CdtrAcct53 = eElement
                                .getElementsByTagName("CdtrAcct")
                                .item(0)
                                .getTextContent().intern();
                        System.out.println(CdtrAcct53);

                        String BookgDt53 = eElement
                                .getElementsByTagName("BookgDt")
                                .item(0)
                                .getTextContent().intern();
                        System.out.println(BookgDt53);

                        String ValDt53 = eElement
                                .getElementsByTagName("ValDt")
                                .item(0)
                                .getTextContent().intern();
                        System.out.println(ValDt53);

                        String DbtrAgt53 = eElement
                                .getElementsByTagName("DbtrAgt")
                                .item(0)
                                .getTextContent().intern();
                        String DbtrAgt53Mappé = DbtrAgt53.trim().substring(0, 8);
                        System.out.println(DbtrAgt53Mappé);

                        String CdtrAgt53 = eElement
                                .getElementsByTagName("CdtrAgt")
                                .item(0)
                                .getTextContent().intern();
                        String CdtrAgt53Mappé = CdtrAgt53.trim().substring(0, 8);
                        System.out.println(CdtrAgt53Mappé);


                        //////////////Création du fichier OUT//////////////
                        // Reads text from a character-input stream
                        BufferedReader crunchifyReader = new BufferedReader(new InputStreamReader(System.in));

                        // Defines a factory API that enables applications to obtain a parser that produces DOM object trees from
                        // XML documents.
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        //XML54
                        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

                        // The Document interface represents the entire HTML or XML document. Conceptually, it is the root of the
                        // document tree, and provides the primary access to the document's data.
                        // Document doc = factory.newDocumentBuilder().parse(crunchifyFile);

                        //CREATE XML DOCUMENT 54
                        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                        Document doc54 = docBuilder.newDocument(); //doc54 est virtuel juste pour qu'il puisse comprendre et utiliser les fonctions createElement, appendChild et setAttribute


                        //Root element 54
                        //Création de la balise Document=rootElement


                        Element rootElement = doc54.createElement("Document");
                        doc54.appendChild(rootElement);

                        //Création de la balise <BkToCstmrDbtCdtNtfctn>
                        Element BkToCstmrDbtCdtNtfctn = doc54.createElement("BkToCstmrDbtCdtNtfctn");
                        rootElement.appendChild(BkToCstmrDbtCdtNtfctn);

                        //Création de la balise <GrpHdr>
                        Element GrpHdr = doc54.createElement("GrpHdr");
                        BkToCstmrDbtCdtNtfctn.appendChild(GrpHdr);

                        DateFormat format = new SimpleDateFormat("'TTM'yyyyMMddHHmmssS");
                        Date date = new Date();
                        String dateMsgId = format.format(date);


                        Element MsgId = doc54.createElement("MsgId");
                        MsgId.appendChild(doc54.createTextNode(dateMsgId));
                        GrpHdr.appendChild(MsgId);

                        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SSS");
                        Date date2 = new Date();
                        String dateTraitement = format2.format(date2);


                        Element CreDtTm = doc54.createElement("CreDtTm");
                        CreDtTm.appendChild(doc54.createTextNode(dateTraitement)); // la date du traitement
                        GrpHdr.appendChild(CreDtTm);


                        Element Ntfctn = doc54.createElement("Ntfctn");
                        BkToCstmrDbtCdtNtfctn.appendChild(Ntfctn);

                        Element Id = doc54.createElement("Id");
                        Id.appendChild(doc54.createTextNode(dateMsgId));
                        Ntfctn.appendChild(Id);

                        Element CreDtTm2 = doc54.createElement("CreDtTm");
                        CreDtTm2.appendChild(doc54.createTextNode(DateCreDtTm)); // à changer avec la date du traitement du CAM53
                        Ntfctn.appendChild(CreDtTm2);

                        Element Acct = doc54.createElement("Acct");
                        Ntfctn.appendChild(Acct);

                        Element Id2 = doc54.createElement("Id");
                        Acct.appendChild(Id2);

                        Element IBAN = doc54.createElement("IBAN");
                        IBAN.appendChild(doc54.createTextNode(IBAN53));
                        Id2.appendChild(IBAN);

                        Element Ccy = doc54.createElement("Ccy");
                        Ccy.appendChild(doc54.createTextNode(Ccy53));
                        Acct.appendChild(Ccy);

                        Element Svcr = doc54.createElement("Svcr");
                        Acct.appendChild(Svcr);

                        Element FinInstnId = doc54.createElement("FinInstnId");
                        Svcr.appendChild(FinInstnId);

                        Element BIC = doc54.createElement("BIC");
                        BIC.appendChild(doc54.createTextNode(MBIC53Mappé));
                        FinInstnId.appendChild(BIC);

                        Element TxsSummry = doc54.createElement("TxsSummry");
                        Ntfctn.appendChild(TxsSummry);

                        Element TtlCdtNtries = doc54.createElement("TtlCdtNtries");
                        TxsSummry.appendChild(TtlCdtNtries);

                        Element NbOfNtries = doc54.createElement("NbOfNtries");
                        NbOfNtries.appendChild(doc54.createTextNode(String.valueOf(NbOfNtries53)));
                        TtlCdtNtries.appendChild(NbOfNtries);

                        Element Sum = doc54.createElement("Sum");
                        Sum.appendChild(doc54.createTextNode("Sum à calculer"));
                        TtlCdtNtries.appendChild(Sum);

                        Element TtlDbtNtriesDébit = doc54.createElement("TtlDbtNtries");
                        TxsSummry.appendChild(TtlDbtNtriesDébit);

                        Element NbOfNtriesDébit = doc54.createElement("NbOfNtries");
                        NbOfNtriesDébit.appendChild(doc54.createTextNode("0"));
                        TtlDbtNtriesDébit.appendChild(NbOfNtriesDébit);

                        Element SumDébit = doc54.createElement("Sum");
                        SumDébit.appendChild(doc54.createTextNode("0"));
                        TtlDbtNtriesDébit.appendChild(SumDébit);


                        ///////////////////////NTRY////////////////////

                        Element Ntry = doc54.createElement("Ntry");
                        Ntfctn.appendChild(Ntry);

                        Element Amt = doc54.createElement("Amt");
                        Amt.appendChild(doc54.createTextNode(Amt53));
                        Amt.setAttribute("Ccy", "EUR");
                        Ntry.appendChild(Amt);

                        Element CdtDbtInd = doc54.createElement("CdtDbtInd");
                        CdtDbtInd.appendChild(doc54.createTextNode(CdtDbtInd53));
                        Ntry.appendChild(CdtDbtInd);

                        Element Sts = doc54.createElement("Sts");
                        Sts.appendChild(doc54.createTextNode(Sts53));
                        Ntry.appendChild(Sts);

                        Element BookgDt = doc54.createElement("BookgDt");
                        Ntry.appendChild(BookgDt);

                        Element Dt = doc54.createElement("Dt");
                        Dt.appendChild(doc54.createTextNode(BookgDt53));
                        BookgDt.appendChild(Dt);

                        Element ValDt = doc54.createElement("ValDt");
                        Ntry.appendChild(ValDt);

                        Element Dt2 = doc54.createElement("Dt");
                        Dt2.appendChild(doc54.createTextNode(ValDt53));
                        ValDt.appendChild(Dt2);

                        Element AcctSvcrRef = doc54.createElement("AcctSvcrRef");
                        if (AcctSvcrRef53Mappé != "") {
                            AcctSvcrRef.appendChild(doc54.createTextNode(AcctSvcrRef53Mappé));
                        } else {
                            AcctSvcrRef.appendChild(doc54.createTextNode("NOT PROVIDED"));
                        }
                        Ntry.appendChild(AcctSvcrRef);

                        Element BkTxCd = doc54.createElement("BkTxCd");
                        Ntry.appendChild(BkTxCd);

                        Element Domn = doc54.createElement("Domn");
                        BkTxCd.appendChild(Domn);

                        Element Cd = doc54.createElement("Cd");
                        Cd.appendChild(doc54.createTextNode("PMNT"));
                        Domn.appendChild(Cd);

                        Element Fmly = doc54.createElement("Fmly");
                        Domn.appendChild(Fmly);

                        Element Cd2 = doc54.createElement("Cd");
                        Cd2.appendChild(doc54.createTextNode("RCDT"));
                        Fmly.appendChild(Cd2);


                        Element SubFmlyCd = doc54.createElement("SubFmlyCd");
                        SubFmlyCd.appendChild(doc54.createTextNode(SubFmlyCd53));
                        Fmly.appendChild(SubFmlyCd);

                        Element NtryDtls = doc54.createElement("NtryDtls");
                        Ntry.appendChild(NtryDtls);

                        Element Btch = doc54.createElement("Btch");
                        NtryDtls.appendChild(Btch);

                        Element NbOfTxs = doc54.createElement("NbOfTxs");
                        NbOfTxs.appendChild(doc54.createTextNode("1"));
                        Btch.appendChild(NbOfTxs);

                        Element TtlAmt = doc54.createElement("TtlAmt");
                        TtlAmt.appendChild(doc54.createTextNode(TtlAmt53));
                        Btch.appendChild(TtlAmt);
                        TtlAmt.setAttribute("Ccy", "EUR");


                        Element CdtDbtInd2 = doc54.createElement("CdtDbtInd");
                        CdtDbtInd2.appendChild(doc54.createTextNode(CdtDbtInd53));
                        Btch.appendChild(CdtDbtInd2);

                        Element TxDtls = doc54.createElement("TxDtls");
                        NtryDtls.appendChild(TxDtls);

                        Element Refs = doc54.createElement("Refs");
                        TxDtls.appendChild(Refs);


                        Element AcctSvcrRef2 = doc54.createElement("AcctSvcrRef");
                        if (AcctSvcrRef53Mappé != "") {
                            AcctSvcrRef2.appendChild(doc54.createTextNode(AcctSvcrRef53Mappé));
                        } else {
                            AcctSvcrRef2.appendChild(doc54.createTextNode("NOT PROVIDED"));
                        }
                        Refs.appendChild(AcctSvcrRef2);

                        Element InstrId = doc54.createElement("InstrId");
                        if (InstrId53 != "") {
                            InstrId.appendChild(doc54.createTextNode(InstrId53));
                        } else {
                            InstrId.appendChild(doc54.createTextNode("NOT PROVIDED"));
                        }
                        Refs.appendChild(InstrId);

                        Element EndToEndId = doc54.createElement("EndToEndId");
                        if (EndToEndId53 != "") {
                            EndToEndId.appendChild(doc54.createTextNode(EndToEndId53));
                        } else {
                            EndToEndId.appendChild(doc54.createTextNode("NOT PROVIDED"));
                        }
                        Refs.appendChild(EndToEndId);

                        Element AmtDtls = doc54.createElement("AmtDtls");
                        TxDtls.appendChild(AmtDtls);

                        Element InstdAmt = doc54.createElement("InstdAmt");
                        AmtDtls.appendChild(InstdAmt);

                        Element Amt2 = doc54.createElement("Amt");
                        Amt2.appendChild(doc54.createTextNode(Amt53));
                        InstdAmt.appendChild(Amt2);
                        Amt2.setAttribute("Ccy", "EUR");

                        Element TxAmt = doc54.createElement("TxAmt");
                        AmtDtls.appendChild(TxAmt);

                        Element Amt3 = doc54.createElement("Amt");
                        Amt3.appendChild(doc54.createTextNode(Amt53));
                        TxAmt.appendChild(Amt3);
                        Amt3.setAttribute("Ccy", "EUR");

                        Element RltdPties = doc54.createElement("RltdPties");
                        TxDtls.appendChild(RltdPties);

                        Element Dbtr = doc54.createElement("Dbtr");
                        RltdPties.appendChild(Dbtr);

                        Element Nm = doc54.createElement("Nm");
                        Nm.appendChild(doc54.createTextNode(Nm53));
                        Dbtr.appendChild(Nm);

                        Element DbtrAcct = doc54.createElement("DbtrAcct");
                        RltdPties.appendChild(DbtrAcct);

                        Element Id3 = doc54.createElement("Id");
                        DbtrAcct.appendChild(Id3);

                        Element IBAN2 = doc54.createElement("IBAN");
                        IBAN2.appendChild(doc54.createTextNode(DbtrAcct53));
                        Id3.appendChild(IBAN2);

                        Element CdtrAcct = doc54.createElement("CdtrAcct");
                        RltdPties.appendChild(CdtrAcct);

                        Element Id4 = doc54.createElement("Id");
                        CdtrAcct.appendChild(Id4);

                        Element IBAN3 = doc54.createElement("IBAN");
                        IBAN3.appendChild(doc54.createTextNode(CdtrAcct53));
                        Id4.appendChild(IBAN3);


                        Element RltdAgts = doc54.createElement("RltdAgts");
                        TxDtls.appendChild(RltdAgts);

                        Element DbtrAgt = doc54.createElement("DbtrAgt");
                        RltdAgts.appendChild(DbtrAgt);

                        Element FinInstnId2 = doc54.createElement("FinInstnId");
                        DbtrAgt.appendChild(FinInstnId2);

                        Element BIC2 = doc54.createElement("BIC");
                        BIC2.appendChild(doc54.createTextNode(DbtrAgt53Mappé));
                        FinInstnId2.appendChild(BIC2);

                        Element CdtrAgt = doc54.createElement("CdtrAgt");
                        RltdAgts.appendChild(CdtrAgt);

                        Element FinInstnId3 = doc54.createElement("FinInstnId");
                        CdtrAgt.appendChild(FinInstnId3);

                        Element BIC3 = doc54.createElement("BIC");
                        BIC3.appendChild(doc54.createTextNode(CdtrAgt53Mappé));
                        FinInstnId3.appendChild(BIC3);

                        Element RmtInf = doc54.createElement("RmtInf");
                        TxDtls.appendChild(RmtInf);

                        Element Ustrd = doc54.createElement("Ustrd");
                        Ustrd.appendChild(doc54.createTextNode(Ustrd53));
                        RmtInf.appendChild(Ustrd);


                        for (int document = 0; document < nList.getLength(); document++) {


                            // write dom document to a file
                            try (FileOutputStream output =
                                         new FileOutputStream("/Users/e67781/OneDrive - BNP Paribas/Bureau/ComCast/OUTPUT files/EssaiComCast" + temp + ".xml")) {
                                writeXml(doc54, output); // l'outil virtuel que j'ai crée je vais le mettre dans un vrai fichier xml " le livrable " le Réél
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // write doc to output stream
    private static void writeXml(Document doc,OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
        System.out.println("File saved !");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);


    }

}
