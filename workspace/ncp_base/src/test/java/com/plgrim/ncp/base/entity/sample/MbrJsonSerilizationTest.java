package com.plgrim.ncp.base.entity.sample;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.framework.commons.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class MbrJsonSerilizationTest {

    private Mbr entity;

    @Before
    public void setup() {
        entity = new Mbr();
        entity.setMbrNo("MB2018");
        entity.setMbrNm("Test");
        entity.setMbrEmail("a@a.com");
    }

    @Test
    public void serilization() throws Exception {
        String serialized = entity.toJSON();
        Mbr deserializedEntity = AbstractEntity.fromJSON(serialized, Mbr.class);

        log.info("serialized:{}", serialized);
        log.info("deserializedEntity:{}", deserializedEntity);

        assertEquals(deserializedEntity, entity);
    }

    @Test
    public void serilization2() throws Exception {
        String serialized = "{\"mbrNo\":\"MB0018050311761\",\"mbrNm\":\"Test\",\"mbrEmail\":\"a@a.com\"}";
        Mbr deserializedEntity = AbstractEntity.fromJSON(serialized, Mbr.class);

        log.info("serialized:{}", serialized);
        log.info("deserializedEntity:{}", deserializedEntity);

        assertEquals(deserializedEntity, JsonService.unmarshalling(serialized, Mbr.class));
    }

}
