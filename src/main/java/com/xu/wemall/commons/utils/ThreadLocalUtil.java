package com.xu.wemall.commons.utils;


import com.xu.wemall.entry.Lover;

/**
 * 1、登陆后，将用户登录信息绑定到工具类中
 * 2、该工具类根据线程来判定是否是同一个用户，注意思路
 */
public class ThreadLocalUtil {

    private ThreadLocal<Lover> userInfoThreadLocal = new ThreadLocal<>();

    //new一个实例
    private static final ThreadLocalUtil instance = new ThreadLocalUtil();

    //私有化构造
    private ThreadLocalUtil() {
    }

    //获取单例
    public static ThreadLocalUtil getInstance() {
        return instance;
    }

    /**
     * 将用户对象绑定到当前线程中，键为userInfoThreadLocal对象，值为userInfo对象
     * @param loverInfo
     * 21
     */
    public void bind(Lover loverInfo) {
        userInfoThreadLocal.set(loverInfo);
    }

    /**
     * 将用户数据绑定到当前线程中，键为userInfoThreadLocal对象，值为userInfo对象
     * @param userId
     */
    public void bind(Integer userId) {
        Lover loverInfo = new Lover();
        loverInfo.setId(userId);
        bind(loverInfo);
    }

    /**
     * 得到绑定的用户对象
     * @return
     */
    public Lover getUserInfo() {
        Lover loverInfo = userInfoThreadLocal.get();
        remove();
        return loverInfo;
    }

    /**
     * 移除绑定的用户对象
     */
    public void remove() {
        userInfoThreadLocal.remove();
    }

}
