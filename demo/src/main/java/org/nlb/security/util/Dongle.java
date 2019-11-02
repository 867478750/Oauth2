package org.nlb.security.util;//package com.FTSafe;
import java.io.*;


public class Dongle
{
	{
		System.loadLibrary("Dongle_java");
	}


	public static final int CONST_PID = 0xECBB8239;   //����ʱĬ�ϵ�PID

    public static final String CONST_USERPIN = "12345678";  //����ʱĬ�ϵ�USERPIN
    public static final String CONST_ADMINPIN = "yctc123456789abc"; //����ʱĬ�ϵ�ADMINPIN

    //������Э������
    public static final int PROTOCOL_HID = 0; //hidЭ��
    public static final int PROTOCOL_CCID = 1; //ccidЭ��

    //�ļ�����
    public static final int FILE_DATA = 1; //��ͨ�����ļ�
    public static final int FILE_PRIKEY_RSA = 2; //RSA˽Կ�ļ�
    public static final int FILE_PRIKEY_ECCSM2 = 3; //ECC����SM2˽Կ�ļ�(SM2˽Կ�ļ���ECC˽Կ�ļ����Ӽ�)
    public static final int FILE_KEY = 4; //SM4��3DES��Կ�ļ�
    public static final int FILE_EXE = 5; //��ִ���ļ�

    //LED��״̬����
    public static final int LED_OFF = 0; //����
    public static final int LED_ON = 1; //����
    public static final int LED_BLINK = 2;//����

    //PIN������
    public static final int FLAG_USERPIN = 0;//�û�PIN
    public static final int FLAG_ADMINPIN = 1;//������PIN

    //�ӽ��ܱ�־
    public static final int FLAG_ENCODE = 0;//����
    public static final int FLAG_DECODE = 1; //����

    //HASH�㷨����
    public static final int FLAG_HASH_MD5 = 0; //MD5     ������16�ֽ�
    public static final int FLAG_HASH_SHA1 = 1;//SHA1    ������20�ֽ�
    public static final int FLAG_HASH_SM3 = 2; //SM3     ������32�ֽ�


    //Զ�������Ĺ��ܺ�
    public static final int UPDATE_FUNC_CreateFile = 1; //�����ļ�
    public static final int UPDATE_FUNC_WriteFile = 2; //д�ļ�
    public static final int UPDATE_FUNC_DeleteFile = 3; //ɾ���ļ�
    public static final int UPDATE_FUNC_FileLic = 4; //�����ļ���Ȩ
    public static final int UPDATE_FUNC_SeedCount = 5; //������������������
    public static final int UPDATE_FUNC_DownloadExe = 6; //������ִ���ļ�
    public static final int UPDATE_FUNC_UnlockUserPin = 7; //�����û�PIN
    public static final int UPDATE_FUNC_Deadline = 8;//ʱ��������ʹ������


    //������
    public static final int DONGLE_SUCCESS = 0x00000000;            // �����ɹ�
    public static final int DONGLE_NOT_FOUND = 0xF0000001;          // δ�ҵ�ָ�����豸
    public static final int DONGLE_INVALID_HANDLE = 0xF0000002;     // ��Ч�ľ��
    public static final int DONGLE_INVALID_PARAMETER = 0xF0000003;  // ��������
    public static final int DONGLE_COMM_ERROR = 0xF0000004;		   // ͨѶ����
    public static final int DONGLE_INSUFFICIENT_BUFFER = 0xF0000005;// �������ռ䲻��
    public static final int DONGLE_NOT_INITIALIZED = 0xF0000006;	   // ��Ʒ��δ��ʼ�� (��û����PID)
    public static final int DONGLE_ALREADY_INITIALIZED = 0xF0000007;// ��Ʒ�Ѿ���ʼ�� (��������PID)
    public static final int DONGLE_ADMINPIN_NOT_CHECK = 0xF0000008; // ����������û����֤
    public static final int DONGLE_USERPIN_NOT_CHECK = 0xF0000009;  // �û�����û����֤
    public static final int DONGLE_INCORRECT_PIN = 0xF000FF00;	   // ���벻��ȷ (��2λָʾʣ�����)
    public static final int DONGLE_PIN_BLOCKED = 0xF000000A;		   // PIN��������
    public static final int DONGLE_ACCESS_DENIED = 0xF000000B;	   // ���ʱ��ܾ� 
    public static final int DONGLE_FILE_EXIST = 0xF000000E;		   // �ļ��Ѵ���
    public static final int DONGLE_FILE_NOT_FOUND = 0xF000000F;	   // δ�ҵ�ָ�����ļ�
    public static final int DONGLE_READ_ERROR = 0xF0000010;         // ��ȡ���ݴ���
    public static final int DONGLE_WRITE_ERROR = 0xF0000011;        // д�����ݴ���
    public static final int DONGLE_FILE_CREATE_ERROR = 0xF0000012;  // �����ļ����ļ��д���
    public static final int DONGLE_FILE_READ_ERROR = 0xF0000013;    // ��ȡ�ļ�����
    public static final int DONGLE_FILE_WRITE_ERROR = 0xF0000014;   // д���ļ�����
    public static final int DONGLE_FILE_DEL_ERROR = 0xF0000015;     // ɾ���ļ����ļ��д���
    public static final int DONGLE_FAILED = 0xF0000016;             // ����ʧ��
    public static final int DONGLE_CLOCK_EXPIRE = 0xF0000017;       // ������ʱ�ӵ���
    public static final int DONGLE_ERROR_UNKNOWN = 0xFFFFFFFF;      // δ֪�Ĵ���
	
	public native int Dongle_Enum(byte[] dongleInfo, int [] count);
	public native int Dongle_Open(int [] handle, int index);
	public native int Dongle_ResetState(int handle);
	public native int Dongle_Close(int handle);
	public native int Dongle_GenRandom(int handle, int len, byte [] random);
	public native int Dongle_LEDControl(int handle, int flag);
	public native int Dongle_SwitchProtocol(int [] handle, int flag);
	public native int Dongle_RFS(int [] handle);
	public native int Dongle_CreateFile(int handle, int fileType, int fileID, byte [] fileAttr);
	public native int Dongle_WriteFile(int handle, int fileType, int fileID, int offset, byte [] data, int dataLen);
	public native int Dongle_ReadFile(int handle, int fileID, int offset, byte []outData, int dataLen);
	public native int Dongle_DownloadExeFile(int handle, byte[] exeFileInfo, int count);
	public native int Dongle_RunExeFile(int handle, int fileID, byte [] inOutData, int inOutDataLen, int [] mainRet);
	public native int Dongle_DeleteFile(int handle, int fileType, int fileID);
	public native int Dongle_ListFile(int handle, int fileType, byte []fileList, int [] dataLen);
	public native int Dongle_GenUniqueKey(int handle, int seedLen, byte [] seed, String [] pid, String [] adminPin);
	public native int Dongle_VerifyPIN(int handle, int flag, String pin, int [] remainCount);
	public native int Dongle_ChangePIN(int handle, int flag, String oldPin, String newPin, int tryCount);
	public native int Dongle_ResetUserPIN(int handle, String adminPin);
	public native int Dongle_SetUserID(int handle, int userID);
	public native int Dongle_GetDeadline(int handle, int [] time);
	public native int Dongle_SetDeadline(int handle, int time);
	public native int Dongle_GetUTCTime(int handle, int [] time);
	public native int Dongle_ReadData(int handle, int offset, byte [] data, int dateLen);
	public native int Dongle_WriteData(int handle, int offset, byte [] data, int dataLen);
	public native int Dongle_ReadShareMemory(int handle, byte [] data);
	public native int Dongle_WriteShareMemory(int handle, byte [] data, int datelen);
	public native int Dongle_RsaGenPubPriKey(int handle, int priFileID, byte [] pubBakup, int []pubLen, byte [] priBakup, int []priLen);
	public native int Dongle_RsaPri(int handle, int priFileID, int flag, byte [] inData, int inDataLen, byte [] outData, int [] outDataLen);
	public native int Dongle_RsaPub(int handle, int flag, byte [] pubKey, byte [] inData, int inDataLen, byte [] outData, int [] outDataLen);
	public native int Dongle_EccGenPubPriKey(int handle, int priFileID, byte [] pubBakup, int []pubLen, byte [] priBakup, int []priLen);
	public native int Dongle_EccSign(int handle, int priFileID, byte [] hashData, int hashDataLen, byte [] outData);
	public native int Dongle_EccVerify(int handle, byte [] pubKey, byte [] hashdata, int hashDataLen, byte [] sign);
	public native int Dongle_SM2GenPubPriKey(int handle, int priFileID, byte [] pubBakup, int []pubLen, byte [] priBakup, int []priLen);
	public native int Dongle_SM2Sign(int handle, int priFileID, byte [] hashData, int hashDataLen, byte [] outData);
	public native int Dongle_SM2Verify(int handle, byte [] pubKey, byte [] hashdata, int hashDataLen, byte [] sign);
	public native int Dongle_TDES(int handle, int keyFileID, int flag, byte [] inData, byte [] outData, int dataLen);
	public native int Dongle_SM4(int handle, int keyFileID, int flag, byte [] inData, byte [] outData, int dataLen);
	public native int Dongle_HASH(int handle, int flag, byte [] inData, int inDataLen, byte [] hash);
	public native int Dongle_Seed(int handle, byte [] seed, int seedLen, byte [] outData);
	public native int Dongle_LimitSeedCount(int handle, int count);
	public native int Dongle_GenMotherKey(int handle, byte [] moterData);
	public native int Dongle_RequestInit(int handle, byte [] request);
	public native int Dongle_GetInitDataFromMother(int handle, byte [] request, byte [] initData, int []dataLen);
	public native int Dongle_InitSon(int handle, byte [] initData, int dataLen);
	public native int Dongle_SetUpdatePriKey(int handle, byte [] priKey);
	public native int Dongle_MakeUpdatePacket(int handle, String shid, int func, int fileType, int fileID, int offset, byte []buffer, int bufferLen, byte [] uPubKey, byte [] outData, int [] outDataLen);
	public native int Dongle_MakeUpdatePacketFromMother(int handle, String shid, int func, int fileType, int fileID, int offset, byte []buffer, int bufferLen, byte [] outData, int [] outDataLen);
	public native int Dongle_Update(int handle, byte [] updateData, int dataLen);
	
	public native int GetDongleInfo(byte []dongleInfo, int index, short []ver, short []type, byte []birthday, int []agent, int []pid, int []uid, byte[]hid, int []isMother, int []devType);
	public native int Convert_DATA_LIC_To_Buffer(short readPriv, short writePriv, byte [] buffer, int [] bufferLen);
	public native int Convert_PRIKEY_LIC_To_Buffer(int count, byte callPriv, byte isDecOnRAM, byte isReset, byte []buffer, int[]bufferLen);
	public native int Convert_KEY_LIC_To_Buffer(int privEnc, byte []buffer, int []bufferLen);
	public native int Convert_EXE_LIC_To_Buffer(short privExe, byte []buffer, int []bufferLen);
	public native int Convert_DATA_FILE_ATTR_To_Buffer(int size, byte []dataLic, int dataLicLen, byte []buffer, int []bufferLen);
	public native int Convert_PRIKEY_FILE_ATTR_To_Buffer(short type, short size, byte []prikeyLic, int prikeyLicLen, byte []buffer, int []bufferLen);
	public native int Convert_KEY_FILE_ATTR_To_Buffer(int size, byte []keyLic, int keyLicLen, byte []buffer, int []bufferLen);
	public native int Convert_EXE_FILE_ATTR_To_Buffer(short size, byte []exeLic, int exeLicLen, byte []buffer, int []bufferLen);
	public native int Get_DATA_FILE_LIST_Info(byte[]fileList, int fileListLen,int index, short []fileID, int []fileSize, short []readPriv, short []writePriv);
	public native int Get_EXE_FILE_LIST_Info(byte[]fileList, int fileListLen, int index, short []fileID, short []fileSize, short []callPriv);
	public native int Get_KEY_FILE_LIST_Info(byte[]fileList, int fileListLen, int index, short []fileID, int []fileSize, int []encPriv);
	public native int Get_PRIKEY_FILE_LIST_Info(byte[]fileList, int fileListLen, int index, short []fileID, short []type, short []fileSize, int []count, byte []priv, byte []isDecOnRAM, byte []isReset);
	public native int Add_EXE_FILE_INFO_To_Buffer(byte []fileInfoBuffer, int[]fileInfoBufferLen, int index, short fileID, short fileSize, byte filePriv, byte [] exeBuffer);
	public native int Clear_EXE_FILE_INFO_Buffer(byte []fileInfoBuffer, int count);
	//Convert Struct to Buffer
	//Convert Buffer to Struct	
	//static

}
