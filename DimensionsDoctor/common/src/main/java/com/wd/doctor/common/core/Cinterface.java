package com.wd.doctor.common.core;


import com.wd.doctor.common.core.exception.ApiException;

/**
 * description ：
 * project name：DimensionMedical
 * author : 李旭斌
 * creation date: 2019/7/10 16:23
 *
 * @version 1.0
 */
public interface Cinterface<T> {
    void success(T data, Object... args);
    void fail(ApiException data, Object... args);
}
