package com.voco_task.mapper;


import com.voco_task.rabbitmq.model.AuthRestaurantModel;
import com.voco_task.repository.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRestaurantMapper {

    IRestaurantMapper INSTANCE = Mappers.getMapper(IRestaurantMapper.class);
    Restaurant toRegisterRestaurant(AuthRestaurantModel model);




}