package com.personal.ibonny.readingxmlproject;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Component
public class StartupScript implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        URL url = new URL("https://jsonplaceholder.typicode.com");

        URLConnection conn = url.openConnection();

        InputStream in = conn.getInputStream();

        String encoding = conn.getContentEncoding();

        encoding = encoding == null ? "UTF-8" : encoding;

        String body = IOUtils.toString(in, encoding);

        System.out.println("Result is:");

        body = "<?xml version=\"1.0\"?>\n" +
               "<Tutorials>\n" +
               "    <Tutorial tutId=\"01\" type=\"java\">\n" +
               "        <title>Guava</title>\n" +
               "  <description>Introduction to Guava</description>\n" +
               "  <date>04/04/2016</date>\n" +
               "  <author>GuavaAuthor</author>\n" +
               "    </Tutorial>\n" +
               "    <Tutorial tutId=\"02\" type=\"java\">\n" +
               "        <title>XML</title>\n" +
               "  <description>Introduction to XPath</description>\n" +
               "  <date>04/05/2016</date>\n" +
               "  <author>XMLAuthor</author>\n" +
               "    </Tutorial>\n" +
               "</Tutorials>\n";

//        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
//
//        DocumentBuilder builder = factory.newDocumentBuilder();
//
//        Document doc = builder.parse(new InputSource(new StringReader(body)));
//
//        XPath xPath = XPathFactory.newInstance().newXPath();
//
//        String expression = "/Tutorials/Tutorial";
//
//        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
//
//        for (int i=0; i<nodeList.getLength(); i++) {
//            Node n = nodeList.item(i);
//
//            System.out.println(n.getChildNodes());
//        }

        Document doc = Jsoup.parse(body);

        Elements entries = doc.select("tutorials tutorial");

        for (Element entry: entries) {
            System.out.println(entry.select("title").text());
            System.out.println(entry.select("description").text());

            System.out.println("=============");
        }

        System.exit(1);
    }
}
