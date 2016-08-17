package com.liukai.pattern.structural.adapter;

import org.xml.sax.*;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MySAXApp {

    public static void main(String[] args) {

    }

}

/**
 * 此类是一个Properties数据和XML数据之间的转换器
 */
class XMLProperties extends Properties {

    XMLParser xmlParser;

    /**
     * 从一个输入流读入xml文件
     *
     * @param inStream
     * @throws IOException
     */
    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        try {
            xmlParser = new XMLParser(inStream, this);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将xml文件读入
     *
     * @param file
     * @throws IOException
     */
    public synchronized void load(File file) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        try {
            xmlParser = new XMLParser(bis, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(OutputStream out, String comments) {
        try {
            store(out, comments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(OutputStream out, String comments) throws IOException {
        PrintWriter writer = new PrintWriter(out);
        writer.println("<?xml version='1.0'?>");
        if (comments != null) {
            writer.println("<!--" + comments + "-->");
        }
        writer.print("<properties>");
        for (Enumeration<Object> e = keys(); e.hasMoreElements(); ) {
            String key = (String) e.nextElement();
            String value = (String) get(key);
            writer.print("\n <key name=\"" + key + "\">");
            writer.print(encode(value));
            writer.print("</key>");
        }
        writer.print("</properties>");
        writer.flush();
    }

    protected StringBuffer encode(String value) {
        int len = value.length();
        StringBuffer buffer = new StringBuffer(len);
        char c;
        for (int i = 0; i < len; i++) {
            switch (c = value.charAt(i)) {
                case '&':
                    buffer.append("&amp;");
                    break;
                case '<':
                    buffer.append("<");
                    break;
                case '>':
                    buffer.append(">");
                    break;
                default:
                    buffer.append(c);
            }
        }
        return buffer;
    }

}

/**
 * 源角色
 */
class XMLParser implements DocumentHandler {

    private static final int IN_NOTHING = 0;
    private static final int IN_DOCUMENT = 1;
    private static final int IN_KEY = 2;
    private int state = IN_NOTHING;
    private String key;
    private StringBuffer value;
    private Parser parser;
    private Class parser_class;
    private Properties xmlprop;
    public static final String PARSER_P = "org.apache.xerces.parsers.SAXParser";


    XMLParser(InputStream in, XMLProperties xmlprop) throws SAXException {
        this.xmlprop = xmlprop;
        state = IN_NOTHING;
        value = new StringBuffer();

        try {
            parser = getParser();
            parser.setDocumentHandler(this);
            parser.parse(new InputSource(in));
        } catch (Exception e) {
            throw new SAXException(e);
        }

    }


    public Class getParserClass() throws ClassNotFoundException {
        if (parser_class == null) {
            parser_class = Class.forName(PARSER_P);
        }
        return parser_class;
    }

    private Parser getParser() {
        try {
            return (Parser) getParserClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String name, AttributeList atts) throws SAXException {
        if (state == IN_NOTHING) {
            if ("properties".equals(name)) {
                state = IN_DOCUMENT;
            } else {
                throw new SAXException("attempt to find root properties");
            }
        } else if (state == IN_DOCUMENT) {
            if ("key".equals(name)) {
                key = atts.getValue(name);
                if (key == null) {
                    throw new SAXException("no name for key " + atts);
                }
            } else {
                throw new SAXException("attempt to find keys");
            }
        } else {
            throw new SAXException("invalid element " + name);
        }
    }

    @Override
    public void endElement(String name) throws SAXException {
        if (state == IN_KEY) {
            xmlprop.setProperty(key, value.toString());
            System.out.print("<key name=\"" + key + "\">");
            System.out.println(value.toString() + "</key>\n");
            state = IN_DOCUMENT;
            // name = null;
            value = new StringBuffer();
        } else if (state == IN_DOCUMENT) {
            state = IN_NOTHING;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (state == IN_KEY) {
            compute(ch, start, length);
        }
    }

    private void compute(char[] ch, int start, int length) {
        int st = start;
        int len = length - 1;
        while (st < length && ((ch[st] == '\n') || (ch[st] == '\t') || (ch[st] == '\r') || (ch[st] == ' '))) {
            st++;
        }
        while (len > 0 && ((ch[len] == '\n') || (ch[len] == '\t') || (ch[len] == '\r') || (ch[len] == ' '))) {
            len--;
        }
        while (st <= len) {
            value.append(ch[st]);
            st++;
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }
}
