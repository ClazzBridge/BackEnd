package com.example.academy.mapper;

import org.mapstruct.factory.Mappers;

public interface MessageMapper {
  MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);
}
