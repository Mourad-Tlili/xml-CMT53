package com.company;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Parsing {


    public Document Input() {
        try {

            File inputFile = new File("C:/COMCAST/CAM53.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

          //  System.out.print("Root Element : " + doc.getDocumentElement().getNodeName());
            return doc;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }


    public double CalculSum() {
        List<Double> list = new ArrayList<Double>();
        double Sum = 0;

        NodeList DocumentList = Input().getElementsByTagName("Document");
        for (int temp = 0; temp < DocumentList.getLength(); temp++) {
            Node DocuemtnNode = DocumentList.item(temp);
            if (DocuemtnNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) DocuemtnNode;
                NodeList stmt = eElement.getElementsByTagName("Stmt");
                for (int s = 0; s < stmt.getLength(); s++) {
                    Element stmtElement = (Element) stmt.item(s);

                    NodeList ntries = stmtElement.getElementsByTagName("Ntry");
                    for (int i = 0; i < ntries.getLength(); i++) {
                        Element ntryElement = (Element) ntries.item(i);

                        String Amt53 = ntryElement.getElementsByTagName("Amt").item(0).getTextContent();
                        String Domn2 = ntryElement.getElementsByTagName("Domn").item(0).getTextContent().intern();
                        boolean c = Domn2.contains("PMNT");
                        boolean d = Domn2.contains("RCDT");
                        boolean f = Domn2.contains("XBCT");

                        if (c && d && f) {
                            Sum = Sum + Double.parseDouble(Amt53);
                            list.add(Sum);
                          //  System.out.print(" somme = " + Sum);
                         //   System.out.print(" somme = " + list);


                        }
                    }
                }
            }
        }

        return Sum;
    }

    public void FonctionVerif() {
        NodeList DocumentList = Input().getElementsByTagName("Document");
        for (int temp = 0; temp < DocumentList.getLength(); temp++) {
            Node DocuemtnNode = DocumentList.item(temp);
            if (DocuemtnNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) DocuemtnNode;
                NodeList stmt = eElement.getElementsByTagName("Stmt");
                for (int s = 0; s < stmt.getLength(); s++) {
                    Element stmtElement = (Element) stmt.item(s);
                    NodeList ntries = stmtElement.getElementsByTagName("Ntry");
                    for (int i = 0; i < ntries.getLength(); i++) {

                        String Domn2 = eElement.getElementsByTagName("Domn").item(i).getTextContent().intern();
                        boolean c = Domn2.contains("PMNT");
                        boolean d = Domn2.contains("RCDT");
                        boolean f = Domn2.contains("XBCT");
                        if (c && d && f ) {
                            Output();
                        }
                        }
                    }
                }
            }
        }


    public Document Output()
    {
       double SumMappe=CalculSum();
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc54 = docBuilder.newDocument();
        Element rootElement = doc54.createElement("Document");
        doc54.appendChild(rootElement);
        //////////////////

        NodeList DocumentList =  Input().getElementsByTagName("Document");

        /////GROUPE HEADER/////
        Element BkToCstmrDbtCdtNtfctn = doc54.createElement("BkToCstmrDbtCdtNtfctn");
        rootElement.appendChild(BkToCstmrDbtCdtNtfctn);
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
        ///////////////////////

        for (int temp = 0; temp < DocumentList.getLength(); temp++) {
            Node DocuemtnNode = DocumentList.item(temp);
            if (DocuemtnNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) DocuemtnNode;


                ////////////HEADER////////////


                String DateCreDtTm = eElement.getElementsByTagName("CreDtTm").item(0).getTextContent();
                String Ccy53 = eElement.getElementsByTagName("Ccy").item(0).getTextContent();
                String BIC53 = eElement.getElementsByTagName("BIC").item(0).getTextContent();

                String MBIC53Mappé = BIC53.substring(0, 8);

                ////////////PARSING IBAN////////////
                String IBANPARSE = "";
                String IBAN53 = eElement.getElementsByTagName("Id").item(1).getTextContent().trim();


                if (IBAN53.equals("AT301810010136790300")) {
                    IBANPARSE = "AT301810010136790300";
                } else {
                    if (IBAN53.equals("AT461810010136790400")) {
                        IBANPARSE = "AT461810010136790400";
                    } else {
                        if (IBAN53.equals("AT621810010136790500")) {
                            IBANPARSE = "AT621810010136790500";
                        } else {
                            if (IBAN53.equals("4223658024EUR")) {
                                IBANPARSE = "DE77512106004223658024";
                            } else {
                                if (IBAN53.equals("4223658032EUR")) {
                                    IBANPARSE = "DE55512106004223658032";
                                } else {
                                    if (IBAN53.equals("4223658040EUR")) {
                                        IBANPARSE = "DE33512106004223658040";
                                    } else {
                                        if (IBAN53.equals("4223658065EUR")) {
                                            IBANPARSE = "DE37512106004223658065";
                                        } else {
                                            if (IBAN53.equals("4223658057EUR")) {
                                                IBANPARSE = "DE59512106004223658057";
                                            } else {
                                                if (IBAN53.equals("4223663016EUR")) {
                                                    IBANPARSE = "DE26512106004223663016";
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                NodeList stmt = eElement.getElementsByTagName("Stmt");
                for (int s = 0; s < stmt.getLength(); s++) {
                    Element stmtElement = (Element) stmt.item(s);

                    Element Ntfctn = doc54.createElement("Ntfctn");
                    BkToCstmrDbtCdtNtfctn.appendChild(Ntfctn);

                    Element Id = doc54.createElement("Id");
                    Id.appendChild(doc54.createTextNode(dateMsgId));
                    Ntfctn.appendChild(Id);

                    Element CreDtTm2 = doc54.createElement("CreDtTm");
                    CreDtTm2.appendChild(doc54.createTextNode(DateCreDtTm));
                    Ntfctn.appendChild(CreDtTm2);

                    Element Acct = doc54.createElement("Acct");
                    Ntfctn.appendChild(Acct);

                    Element Id2 = doc54.createElement("Id");
                    Acct.appendChild(Id2);

                    Element IBAN = doc54.createElement("IBAN");
                    IBAN.appendChild(doc54.createTextNode(IBANPARSE));
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

                    Element TtlDbtNtriesDébit = doc54.createElement("TtlDbtNtries");
                    TxsSummry.appendChild(TtlDbtNtriesDébit);

                    Element NbOfNtriesDébit = doc54.createElement("NbOfNtries");
                    NbOfNtriesDébit.appendChild(doc54.createTextNode("0"));
                    TtlDbtNtriesDébit.appendChild(NbOfNtriesDébit);

                    Element SumDébit = doc54.createElement("Sum");
                    SumDébit.appendChild(doc54.createTextNode("0"));
                    TtlDbtNtriesDébit.appendChild(SumDébit);


                    NodeList ntries = stmtElement.getElementsByTagName("Ntry");

                        for (int i = 0; i < ntries.getLength(); i++) {



                        String Domn2 = stmtElement.getElementsByTagName("Domn").item(i).getTextContent().intern();
                        boolean c = Domn2.contains("PMNT");
                        boolean d = Domn2.contains("RCDT");
                        boolean f = Domn2.contains("XBCT");


                        if (c && d && f ) {


                            ////////////NTRY////////////

                            Element ntryElement = (Element) ntries.item(i);

                            String Amt53 = ntryElement.getElementsByTagName("Amt").item(0).getTextContent();
                            String Nm53 = ntryElement.getElementsByTagName("Nm").getLength() != 0 ? ntryElement.getElementsByTagName("Nm").item(0).getTextContent() : "NOT PROVIDED";
                            String CdtDbtInd53 = ntryElement.getElementsByTagName("CdtDbtInd").item(0).getTextContent();
                            String Sts53 = ntryElement.getElementsByTagName("Sts").item(0).getTextContent();
                            String Dt53 = ntryElement.getElementsByTagName("Dt").item(0).getTextContent();
                            String TtlAmt53 = ntryElement.getElementsByTagName("Amt").item(0).getTextContent();
                            String AcctSvcrRef53 = ntryElement.getElementsByTagName("AddtlTxInf").getLength() != 0 ? ntryElement.getElementsByTagName("AddtlTxInf").item(0).getTextContent() : "NOT PROVIDED";
                            int a = AcctSvcrRef53.length();
                            int b = a - 17;
                            String AcctSvcrRef53Mappé = AcctSvcrRef53.substring(b, a - 1);
                            String SubFmlyCd53 = ntryElement.getElementsByTagName("SubFmlyCd").item(0).getTextContent();
                            String InstrId53 = ntryElement.getElementsByTagName("InstrId").getLength() != 0 ? ntryElement.getElementsByTagName("InstrId").item(0).getTextContent() : " NOT PROVIDED ";
                            String EndToEndId53 = ntryElement.getElementsByTagName("EndToEndId").getLength() != 0 ? ntryElement.getElementsByTagName("EndToEndId").item(0).getTextContent() : " NOT PROVIDED ";
                            String Ustrd53 = ntryElement.getElementsByTagName("Ustrd").item(0).getTextContent();
                            String DbtrAcct53 = ntryElement.getElementsByTagName("DbtrAcct").getLength() != 0 ? ntryElement.getElementsByTagName("DbtrAcct").item(0).getTextContent().intern().trim() : "";
                            // String CdtrAcct53 = eElement.getElementsByTagName("CdtrAcct").getLength() != 0 ? ntryElement.getElementsByTagName("CdtrAcct").item(0).getTextContent().intern(): " NOT PROVIDED ";
                            String BookgDt53 = ntryElement.getElementsByTagName("BookgDt").item(0).getTextContent().intern().trim();
                            String ValDt53 = ntryElement.getElementsByTagName("ValDt").item(0).getTextContent().intern().trim();
                            String DbtrAgt53 = ntryElement.getElementsByTagName("DbtrAgt").getLength() != 0 ? ntryElement.getElementsByTagName("DbtrAgt").item(0).getTextContent().intern().trim() : " NOT PROVIDED ";
                            String CdtrAgt53 = ntryElement.getElementsByTagName("CdtrAgt").getLength() != 0 ? ntryElement.getElementsByTagName("CdtrAgt").item(0).getTextContent().intern() : " NOT PROVIDED                ";
                           //String CdtrAgt53Mappé = CdtrAgt53.trim().substring(0, 8);
                           // String DbtrAgt53Mappé = DbtrAgt53.trim().substring(0, 8);


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
                            if (AcctSvcrRef53.contains("/TR/")) {
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
                            SubFmlyCd.appendChild(doc54.createTextNode("XBCT"));
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
                            if (AcctSvcrRef53.contains("/TR/")) {
                                AcctSvcrRef2.appendChild(doc54.createTextNode(AcctSvcrRef53Mappé));
                            } else {
                                AcctSvcrRef2.appendChild(doc54.createTextNode("NOT PROVIDED"));
                            }
                            Refs.appendChild(AcctSvcrRef2);

                            Element InstrId = doc54.createElement("InstrId");
                            InstrId.appendChild(doc54.createTextNode(InstrId53));
                            Refs.appendChild(InstrId);

                            Element EndToEndId = doc54.createElement("EndToEndId");
                            EndToEndId.appendChild(doc54.createTextNode(EndToEndId53));
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
                            IBAN3.appendChild(doc54.createTextNode(IBANPARSE));
                            Id4.appendChild(IBAN3);

                            Element RltdAgts = doc54.createElement("RltdAgts");
                            TxDtls.appendChild(RltdAgts);

                            Element DbtrAgt = doc54.createElement("DbtrAgt");
                            RltdAgts.appendChild(DbtrAgt);

                            Element FinInstnId2 = doc54.createElement("FinInstnId");
                            DbtrAgt.appendChild(FinInstnId2);

                            Element BIC2 = doc54.createElement("BIC");
                            BIC2.appendChild(doc54.createTextNode(DbtrAgt53));
                            FinInstnId2.appendChild(BIC2);

                            Element CdtrAgt = doc54.createElement("CdtrAgt");
                            RltdAgts.appendChild(CdtrAgt);

                            Element FinInstnId3 = doc54.createElement("FinInstnId");
                            CdtrAgt.appendChild(FinInstnId3);

                            Element BIC3 = doc54.createElement("BIC");
                            BIC3.appendChild(doc54.createTextNode(MBIC53Mappé));
                            FinInstnId3.appendChild(BIC3);

                            Element RmtInf = doc54.createElement("RmtInf");
                            TxDtls.appendChild(RmtInf);

                            Element Ustrd = doc54.createElement("Ustrd");
                            Ustrd.appendChild(doc54.createTextNode(Ustrd53));
                            RmtInf.appendChild(Ustrd);

                        }

                        }

                    int a= Ntfctn.getElementsByTagName("Ntry").getLength();
                    Element NbOfNtries = doc54.createElement("NbOfNtries");
                    NbOfNtries.appendChild(doc54.createTextNode(String.valueOf(a)));
                    TtlCdtNtries.appendChild(NbOfNtries);

                    Element Sum = doc54.createElement("Sum");
                    Sum.appendChild(doc54.createTextNode(String.valueOf(SumMappe)));
                    TtlCdtNtries.appendChild(Sum);

                    }

                //////////////////

                try (FileOutputStream output = new FileOutputStream("C:/COMCAST/EssaiComCast.xml")) {
                    writeXml(doc54, output);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                }
            }
        }
        return doc54;
    }


    private static void writeXml(Document doc,OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);
     //   System.out.println("File saved !");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);


    }

}
