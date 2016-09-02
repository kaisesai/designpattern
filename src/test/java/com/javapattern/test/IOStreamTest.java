package com.javapattern.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Java IO流的测试类
 * 此类演示了InputStream、OutputStream、Reader、Writer等子类的用法
 * 通过使用装饰模式以及适配器模式充分的演示各个子类的实际用法。
 * Created by Administrator on 2016/9/2 0002.
 */
public class IOStreamTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IOStreamTest.class);
    public static final String CHARSET = "UTF-8";
    public static final int SIZE = 1024;
    public static Random random = new Random(47);
    private byte[] car = new byte[SIZE];


    @Test
    public void testDataInputStream() throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream("Hello".getBytes(CHARSET));
        DataInputStream dis = new DataInputStream(bis);
        printlnData(dis, "DataInputStream.read()");
    }

    /**
     * ObjectInputStream对象输入流————InputStream的子类，属于链接流处理器
     * 作用：接收一个InputStream类型的输入流，将ObjectOutputStream输入流串行化的数据并行化
     */
    @Test
    public void testObjectInputStream() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(SIZE);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeInt(1);
        oos.writeByte(2);
        oos.writeBoolean(true);
        oos.writeChar('a');
        oos.writeBytes("hello");
        oos.flush();
        oos.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        printlnData(ois, "ObjectInputStream.read()");
        ois.close();
    }

    /**
     * StringBufferInputStream字符串输入流————InputStream的子类，属于原始流处理器
     * 作用：将字符串作为输入流的流源。已过时
     */
    @Test
    public void testStringBufferInputStream() throws IOException {
        String s = "StringBufferInputStream字符串输入流";
        StringBufferInputStream sbis = new StringBufferInputStream(s);
        printlnData(sbis, "StringBufferInputStream.read()");
    }

    /**
     * PipedInputStream管道输入流————InputStream的子类，属于原始流处理器
     * 作用：配合PipedOutputStream使用，从管道输出流中读取数据
     *
     * @throws IOException
     */
    @Test
    public void testPipedInputStream() throws IOException {
        byte[] bytes = getBytes();
        PipedInputStream pis = new PipedInputStream();
        PipedOutputStream pos = new PipedOutputStream();
        pos.connect(pis);
        pos.write(bytes);
        pos.flush();
        pos.close();
        printlnData(pis, "PipedInputStream.read()");
        pis.close();
    }

    /**
     * FileInputStream文件输入流————InputStream的子类，属于原始流处理器
     * 作用：与文件有关的输入流
     *
     * @throws IOException
     */
    @Test
    public void testFileInputStream() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\Administrator\\Desktop\\新建 Microsoft Word 文档.docx");
        printlnData(fis, "ByteArrayInputStream.read()");
        fis.close();
    }

    /**
     * ByteArrayInputStream字节数组输入流————InputStream的子类，属于原始流处理器
     * 作用：为多线程通信提供缓冲区功能
     */
    @Test
    public void testByteArrayInputStream() {
        byte[] bytes = getBytes();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        int available = bis.available();
        LOGGER.info("流的长度[ByteArrayInputStream.available()]: " + available);
        for (int i = 0; i < available; i++) {
            LOGGER.info("读取数据[ByteArrayInputStream.read()]: " + (byte) bis.read());
        }
    }

    /**
     * 辅助方法：打印数据
     *
     * @param in   输入流
     * @param from 方法字符串
     * @throws IOException
     */
    private void printlnData(InputStream in, String from) throws IOException {
        while (in.read(car) != -1) {
            LOGGER.info("读取数据[" + from + "]: " + Arrays.toString(car));
        }
    }

    /**
     * 辅助方法：生成容量为1024的随机字节数组
     *
     * @return byte[]
     */
    private byte[] getBytes() {
        byte[] bytes = new byte[SIZE];
        int nextInt;
        for (int i = 0; i < bytes.length; i++) {
            nextInt = random.nextInt(256);
            bytes[i] = (byte) nextInt;
        }
        return bytes;
    }
}
