package org.springframework.data.elasticsearch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.entities.Bike;
import org.springframework.data.elasticsearch.repositories.bike.BikeRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.apache.commons.lang.RandomStringUtils.randomNumeric;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Tim Schimandle
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/alias-test.xml")
public class AliasTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void before() {
        elasticsearchTemplate.deleteIndex(Bike.class);
        elasticsearchTemplate.createIndex(Bike.class);
        elasticsearchTemplate.putMapping(Bike.class);
        elasticsearchTemplate.refresh(Bike.class, true);
    }

    @Test
    public void aliasSearch() {
        // given
        String id = randomNumeric(5);
        Bike bike = new Bike();
        bike.setId(Long.parseLong(id));
        bike.setManufacturer("xyz");
        bike.setGearCount(3);
        // when
        bikeRepository.save(bike);
        // then
        assertThat(bikeRepository.findOne(Long.parseLong(id)), is(notNullValue()));
    }
}
