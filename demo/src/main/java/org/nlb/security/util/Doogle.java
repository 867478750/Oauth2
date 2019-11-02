package org.nlb.security.util;

public class Doogle {
    public boolean dd(){

        byte [] dongleInfo = new byte [1024];
        int [] count = new int[1];
        int [] handle = new int [1];
        int nRet = 0;
        Dongle dongle = new Dongle();

        int index = 0;//找到的第1把锁
        short []ver = new short [1];
        short []type = new short [1];
        byte []birthday = new byte [8];
        int []agent = new int[1];
        int []pid = new int[1];
        int []uid = new int[1];
        byte []hid = new byte[8];
        int []isMother = new int[1];
        int []devType = new int[1];

        //打开第一把锁
        nRet = dongle.Dongle_Open(handle, 0);
        if(nRet != Dongle.DONGLE_SUCCESS)
        {
            System.out.printf("Dongle_Open error. error code: 0x%08X. \n ", nRet);
            return false;
        }
        System.out.printf("Open Dongle ARM success[handle=0x%08X]. \n",handle[0]);

        //重设COS安全状态
        nRet = dongle.Dongle_ResetState(handle[0]);
        if(nRet != Dongle.DONGLE_SUCCESS)
        {
            System.out.printf("Dongle_ResetState error. error code: 0x%08X. \n ", nRet);
            dongle.Dongle_Close(handle[0]);
            return false;
        }
        System.out.printf("Reset COS State success. \n");

        //获取随机数
        byte []random = new byte[16];
        nRet = dongle.Dongle_GenRandom(handle[0], 16, random);
        if(nRet != Dongle.DONGLE_SUCCESS)
        {
            System.out.printf("Get random error. error code: 0x%08X. \n ", nRet);
            dongle.Dongle_Close(handle[0]);
            return false;
        }
        System.out.printf("The Random data: ");
        for(int i = 0; i < 16; i++)
        {
            System.out.printf("%02X ", random[i]);
        }
        System.out.printf(".\n");

        //关闭加密锁
        nRet = dongle.Dongle_Close(handle[0]);
        if(nRet != Dongle.DONGLE_SUCCESS)
        {
            System.out.printf("Dongle_Close error. error code: 0x%08X \n", nRet);
            return false;
        }
        System.out.printf("Close Dongle ARM success. \n");
        return true;
    }

}
