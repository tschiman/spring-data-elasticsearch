package org.springframework.data.elasticsearch.repositories.bike;

import org.springframework.data.elasticsearch.entities.Bike;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BikeRepository extends ElasticsearchRepository<Bike, Long> {
}
