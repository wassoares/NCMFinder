package org.soarescontabil.finder.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.transform.stream.StreamResult;

public class MarshallerXML {
    
    public String marshalToFile(Object object, String fileName) {
        final StringWriter out = new StringWriter();
        JAXBContext context = null;
        Marshaller marshaller = null;
        try {
            context = JAXBContext.newInstance(object.getClass());
            marshaller = context.createMarshaller();
            marshaller.setProperty(
                    javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE
            );
            marshaller.marshal(object, new StreamResult(out));
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Writer writer = null;
        try {
            writer = new FileWriter(fileName);
            marshaller.marshal(object, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return out.toString();
    }
}

// private static void loadMarshall() {
//     Processing processing = new Processing();
//     Invoice invoice = new Invoice();
//     Information information = new Information();
//     Identification identification = new Identification();
//     identification.setNumber("12345");
//     identification.setEmission("2022-07-27T16:41:21-03:00");
//     information.setIdentification(identification);
//     invoice.setInformation(information);
//     processing.setInvoice(invoice);
//     Marshalling marshalling = new Marshalling();
//     marshalling.marshalToFile(processing, "dir/sample/file.xml");
// }
