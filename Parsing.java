package com.company;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Parsing {


    public Document Input()
    {
        try{

            File inputFile = new File("src/CAM53.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.print("Root Element : " +doc.getDocumentElement().getNodeName());
            return doc;

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    public void Ntry()
    {

       NodeList NtryList =  Input().getElementsByTagName("Ntry");

        for (int ntry=0; ntry<NtryList.getLength() ; ntry++) {
            Node NtryNode = NtryList.item(ntry);
            System.out.print("\n Node name  " + NtryNode.getNodeName() + " " + (ntry + 1));
            if (NtryNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) NtryNode;

                String Domn2 = eElement.getElementsByTagName("Domn").item(0).getTextContent().intern();

                boolean c = Domn2.contains("PMNT");
                boolean d = Domn2.contains("RCDT");
                boolean f = Domn2.contains("XBCT");


                if (c && d && f)
                {

                        Output();

                }
                else
                {
                 System.out.println("NOT OKAY");
                }
            }
        }


    }



    public Document Output()
    {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
      Document doc54 = docBuilder.newDocument();

        //////////////////
        NodeList DocumentList =  Input().getElementsByTagName("Document");
        for (int temp = 0; temp < DocumentList.getLength(); temp++) {
            Node DocuemtnNode = DocumentList.item(temp);
            if (DocuemtnNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) DocuemtnNode;

                String Amt53 = eElement.getElementsByTagName("Amt").item(2).getTextContent();
                String Nm53 = eElement.getElementsByTagName("Nm").item(2).getTextContent();
                String CdtDbtInd53 = eElement.getElementsByTagName("CdtDbtInd").item(2).getTextContent();
                String Sts53 = eElement.getElementsByTagName("Sts").item(2).getTextContent();
                String Dt53 = eElement.getElementsByTagName("Dt").item(2).getTextContent();
                String TtlAmt53 = eElement.getElementsByTagName("Amt").item(2).getTextContent();
                String AcctSvcrRef53 = eElement.getElementsByTagName("AddtlTxInf").item(2).getTextContent();
                ///A vérifier
                int a = AcctSvcrRef53.length();
                int b = a - 17;
                String AcctSvcrRef53Mappé = AcctSvcrRef53.substring(b, a - 1);
                String SubFmlyCd53 = eElement.getElementsByTagName("SubFmlyCd").item(0).getTextContent();
                String EndToEndId53 = eElement.getElementsByTagName("EndToEndId").item(0).getTextContent();
                String Ustrd53 = eElement.getElementsByTagName("Ustrd").item(0).getTextContent();
                String DbtrAcct53 = eElement.getElementsByTagName("DbtrAcct").item(0).getTextContent().intern();
               // String CdtrAcct53 = eElement.getElementsByTagName("CdtrAcct").item(0).getTextContent().intern();
                String BookgDt53 = eElement.getElementsByTagName("BookgDt").item(0).getTextContent().intern();
                String ValDt53 = eElement.getElementsByTagName("ValDt").item(0).getTextContent().intern();
                String DbtrAgt53 = eElement.getElementsByTagName("DbtrAgt").item(0).getTextContent().intern();
                String DbtrAgt53Mappé = DbtrAgt53.trim().substring(0, 8);

                Element rootElement = doc54.createElement("Document");
                doc54.appendChild(rootElement);

                Element Ntry = doc54.createElement("Ntry");
                rootElement.appendChild(Ntry);

                Element Amt = doc54.createElement("Amt");
                Amt.appendChild(doc54.createTextNode(Amt53));
                Amt.setAttribute("Ccy", "EUR.");
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
                if (eElement.getChildNodes().toString().contains("InstrId"))
                {
                    String InstrId53 = eElement
                            .getElementsByTagName("InstrId")
                            .item(0)
                            .getTextContent();

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
                IBAN3.appendChild(doc54.createTextNode("à mapper"));
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
                if (eElement.getChildNodes().toString().contains("CdtrAgt"))
                { String CdtrAgt53 = eElement
                        .getElementsByTagName("CdtrAgt")
                        .item(0)
                        .getTextContent().intern();
                    String CdtrAgt53Mappé = CdtrAgt53.trim().substring(0, 8);
                    BIC3.appendChild(doc54.createTextNode(CdtrAgt53Mappé)); }
                else
                {
                    BIC3.appendChild(doc54.createTextNode("NOT PROVIDED"));
                }
                FinInstnId3.appendChild(BIC3);

                Element RmtInf = doc54.createElement("RmtInf");
                TxDtls.appendChild(RmtInf);

                Element Ustrd = doc54.createElement("Ustrd");
                Ustrd.appendChild(doc54.createTextNode(Ustrd53));
                RmtInf.appendChild(Ustrd);


                //////////////////

                try (FileOutputStream output = new FileOutputStream("/Users/e67781/OneDrive - BNP Paribas/Bureau/ComCast/OUTPUT files/EssaiComCast.xml")) {
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
        System.out.println("File saved !");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);


    }

}
