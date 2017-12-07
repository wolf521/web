package main.java.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2017/11/17.
 *
 * 主键生成器(twitter雪花算法)
 */
public class IDGenerator {
    /**
     * 开始时间截 (2015-01-01)
     */
    private static final long START_TIMESTAMP = 1483200000000L;

    /**
     * 机器id所占的位数
     */
    private static final int WORKER_ID_LENGTH = 5;

    /**
     * 数据标识id所占的位数
     */
    private static final long DATA_CENTER_ID_LENGTH = 5;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_LENGTH);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private static final long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_LENGTH);

    /**
     * 序列在id中占的位数
     */
    private static final long SEQUENCE_LENGTH = 12L;

    /**
     * 机器ID向左移12位
     */
    private static final long WORKER_ID_SHIFT = SEQUENCE_LENGTH;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private static final long DATA_CENTER_ID_SHIFT = SEQUENCE_LENGTH + WORKER_ID_LENGTH;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_LENGTH + WORKER_ID_LENGTH + DATA_CENTER_ID_LENGTH;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_LENGTH);

    /**
     * 工作机器ID(0~31)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    private static IDGenerator generator = null;
    //==============================Constructors=====================================

    /**
     * 构造函数
     *
     * @param dataCenterId 数据中心ID (0~31)
     * @param workerId     工作ID (0~31)
     */
    public IDGenerator(long dataCenterId, long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("data center Id can't be greater than %d or less than " +
                    "0", MAX_DATA_CENTER_ID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    // ==============================Methods==========================================

    public synchronized static long getId() {
        if (generator == null) {
            generator = new IDGenerator(1, 1);
        }
        return generator.nextId();
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized long nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_LEFT_SHIFT) //
                | (dataCenterId << DATA_CENTER_ID_SHIFT) //
                | (workerId << WORKER_ID_SHIFT) //
                | sequence;
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * @Description: 生成规则ID
     */
   /* public static String getId(){
        String t  = sdf.format(new Date());
        String uuid = UUID.randomUUID().toString();
        String[] uuids = uuid.split("-");
        return t+uuids[3].substring(1)+uuids[4];
    }*/

    /**
     * @Description: 投资编号、产品编号、申请编号
     */
    public static String getNo(){
        String t  = new SimpleDateFormat(DateUtils.PATTERN_YYYYMMDDHHMMSSSSS).format(new Date());
        Random random = new Random();
        int r1= random.nextInt(9);
        int r2= random.nextInt(9);
        int r3= random.nextInt(9);
        return t+r1+r2+r3;
    }

    /**
     * @Description: 订单号、任务号生成规则
     */
    public static String getOrderNo(){
        String t  = new SimpleDateFormat(DateUtils.PATTERN_YYYYMMDDHHMMSSSSS).format(new Date());
        Random random = new Random();
        int r1= random.nextInt(9);
        int r2= random.nextInt(9);
        int r3= random.nextInt(9);
        int r4= random.nextInt(9);
        return t+r1+r2+r3+r4;
    }



    /**
     * 电子合同模板Id <=32位
     * @return
     */
    public static String genTemplateId(){
        String t  = new SimpleDateFormat(DateUtils.PATTERN_YYYYMMDDHHMMSSSSS).format(new Date());
        Random random = new Random();
        int r1= random.nextInt(9);

        String uuid = UUID.randomUUID().toString();

        String prefix = "mb";
        return prefix + t + r1 + uuid.substring(0,12);
    }

    /**
     * 系统模板Id <=32位
     * @return
     */
    public static String genSysTemplateId(){
        String t  = new SimpleDateFormat(DateUtils.PATTERN_YYYYMMDDHHMMSSSSS).format(new Date());
        Random random = new Random();
        int r1= random.nextInt(9);

        String uuid = UUID.randomUUID().toString();

        String prefix = "xb";
        return prefix + t + r1 + uuid.substring(0,12);
    }


    /**
     * 生成交易号
     * @return
     */
    public static String genTransactionId(){
        String t  = new SimpleDateFormat(DateUtils.PATTERN_YYYYMMDDHHMMSSSSS).format(new Date());
        Random random = new Random();
        int r1= random.nextInt(9);
        String uuid = UUID.randomUUID().toString();
        String prefix = "jy";
        return prefix + t + r1 + uuid.substring(0,12);
    }

    /**
     * 生成合同
     * @return
     */
    public static String genContractId() {
        String t  = new SimpleDateFormat(DateUtils.PATTERN_YYYYMMDDHHMMSSSSS).format(new Date());
        Random random = new Random();
        int r1= random.nextInt(9);
        String uuid = UUID.randomUUID().toString();
        String prefix = "ht";
        return prefix + t + r1 + uuid.substring(0,12);
    }
}
