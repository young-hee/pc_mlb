/* Copyright PT. Indo Lotte Makmur, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential copying of this file, via any medium is strictly prohibited Proprietary and confidential
 *
 * Written by ECM project team
 */
package com.plgrim.ncp.framework.interceptor;

import com.plgrim.ncp.base.mybatis.MybatisInterceptor;
import com.plgrim.ncp.framework.interceptor.data.Holdee;
import com.plgrim.ncp.framework.interceptor.data.Holder;
import org.apache.ibatis.plugin.Invocation;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

/**
 * test case for MybatisMaskingInterceptor
 *
 * @created at 2016-12-30 by Chulhui Park <solsin@sys4u.co.kr>
 * @header: $Header: https://svn.ilotte.com/repos/ECM/trunk/ecm-common/src/test/java/com/indolotte/base/mybatis/MybatisMaskingInterceptorTest.java 22749 2017-05-01 06:36:27Z patracyu $
 * @since 1.0.0
 */
public class MybatisMaskingInterceptorTest {
    @Test
    public void testInterceptNull() throws Throwable {
        Invocation iv = Mockito.mock(Invocation.class);
        Mockito.when(iv.proceed()).thenReturn(null);

        MybatisInterceptor interceptor = new MybatisInterceptor();
        Object result = interceptor.intercept(iv);

        Assert.assertNull(result);
    }

    @Test
    public void testInterceptSimple() throws Throwable {
        final Holder holder = new Holder();
        holder.setHolderName("가나다라마바사");

        Invocation iv = Mockito.mock(Invocation.class);
        Mockito.when(iv.proceed()).thenReturn(holder);

        MybatisInterceptor interceptor = new MybatisInterceptor();
        interceptor.intercept(iv);

        Assert.assertEquals("가나******", holder.getHolderName());
    }

    @Test
    public void testInterceptNested() throws Throwable {
        Holder holder = new Holder();
        holder.setHolderName("가나다라마바사");

        Holdee holdee = new Holdee();
        holdee.setHoldeeName("아자차카타파");

        holder.setHoldee(holdee);

        Invocation iv = Mockito.mock(Invocation.class);
        Mockito.when(iv.proceed()).thenReturn(holder);

        MybatisInterceptor interceptor = new MybatisInterceptor();
        interceptor.intercept(iv);

        Assert.assertEquals("가나******", holder.getHolderName());
        Assert.assertEquals("아자******", holdee.getHoldeeName());

    }

    @Test
    public void testInterceptNestedList() throws Throwable {
        Holder holder = new Holder();
        holder.setHolderName("가나다라마바사");

        Holdee holdee1 = new Holdee();
        holder.setHolderName("아자차카타파하");

        Holdee holdee2 = new Holdee();
        holder.setHolderName("고a노도로모보");

        Holdee holdee3 = new Holdee();
        holdee3.setHoldeeName("a");

        holder.setHoldees(Arrays.asList(holdee1, holdee2));
        holder.setHoldee(holdee3);

        Invocation iv = Mockito.mock(Invocation.class);
        Mockito.when(iv.proceed()).thenReturn(holder);

        MybatisInterceptor interceptor = new MybatisInterceptor();
        interceptor.intercept(iv);

        Assert.assertEquals("가나******", holder.getHolderName());
        Assert.assertEquals("아자******", holdee1.getHoldeeName());
        Assert.assertEquals("고a******", holdee2.getHoldeeName());
        Assert.assertEquals("a", holdee3.getHoldeeName());
    }

}
