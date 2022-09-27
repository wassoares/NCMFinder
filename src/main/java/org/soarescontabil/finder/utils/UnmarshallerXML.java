package org.soarescontabil.finder.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public final class UnmarshallerXML {

    private UnmarshallerXML() {}

    @SuppressWarnings("unchecked")
    public static <T> T unmarshalFromFile(File file, Class<T> type) throws JAXBException {
        final JAXBContext context = JAXBContext.newInstance(type);
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(file);
    }

}
