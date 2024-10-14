package com.voco_task.mapper;


import com.voco_task.dto.request.UserUpdateDto;
import com.voco_task.rabbitmq.model.AuthUserModel;
import com.voco_task.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    User toRegisterUser(AuthUserModel model);
    User toUpdateUser(UserUpdateDto dto);





}