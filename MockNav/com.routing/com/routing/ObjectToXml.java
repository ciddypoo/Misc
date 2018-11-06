package com.routing;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;  

  
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;  

public class ObjectToXml {
	public void serialize(DataExtractor dx) throws JAXBException, FileNotFoundException {
		JAXBContext contextObj = JAXBContext.newInstance(DataExtractor.class);  
		  
	    Marshaller marshallerObj = contextObj.createMarshaller();  
	    marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	      
	     
	    marshallerObj.marshal(dx, new FileOutputStream("question.xml"));  
	}
}
