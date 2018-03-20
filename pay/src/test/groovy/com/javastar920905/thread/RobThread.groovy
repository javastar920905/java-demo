package com.javastar920905.thread

import com.javastar920905.service.pay.IRedPacketService

import java.util.concurrent.CountDownLatch

/**
 * @author ouzhx on 2018/3/20.
 * 抢红包的线程用户
 */
class RobThread extends Thread {
    private String userId
    private String redPacketId
    private CountDownLatch countDownLatch
    private IRedPacketService redPacketService

    RobThread(String userId, String redPacketId, CountDownLatch countDownLatch, IRedPacketService redPacketService) {
        this.userId = userId
        this.redPacketId = redPacketId
        this.countDownLatch = countDownLatch
        this.redPacketService = redPacketService
    }

    @Override
    void run() {
        redPacketService.bookingRedPacket2WithDoubleQuque(userId, "欧besos" + userId, redPacketId)
        countDownLatch.countDown()
    }
}
