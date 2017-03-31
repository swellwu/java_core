package com.io.nio;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/31.
 */
public class NIOBaseTest {

    @Rule
    public final SystemOutRule log = new SystemOutRule();
    private NIOBase nioBase = new NIOBase();

    @Before
    public void before(){
        log.enableLog();
    }

    @After
    public void after(){
        log.clearLog();
    }

    @Test
    public void fileChannelTest() throws IOException {
        log.enableLog();
        nioBase.fileChannel();
        assertEquals("11111111111111\n" +
                "2222222222222\n" +
                "eeeeewdwfw", log.getLogWithNormalizedLineSeparator());
    }

    @Test
    public void channelTransfer() throws Exception {
        nioBase.emptyDestFile();
        nioBase.printFile(nioBase.getSrcName());
        String srcString = log.getLogWithNormalizedLineSeparator();
        log.clearLog();
        nioBase.channelTransfer();
        nioBase.printFile(nioBase.getDestName());
        assertEquals(log.getLogWithNormalizedLineSeparator(),srcString);
    }

}