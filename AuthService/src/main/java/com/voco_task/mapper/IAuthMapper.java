package com.voco_task.mapper;


import com.voco_task.dto.request.RegisterRequestRestaurantDto;
import com.voco_task.dto.request.RegisterRequestUserDto;
import com.voco_task.rabbitmq.model.AuthUserModel;
import com.voco_task.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth toRegisterUser(RegisterRequestUserDto dto);
    Auth toRegisterRestaurant(RegisterRequestRestaurantDto dto);

    AuthUserModel toAuthUserModel(RegisterRequestUserDto dto);




}