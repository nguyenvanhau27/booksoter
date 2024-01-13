package com.example.bookstore.mapper;

import com.example.bookstore.model.book.Topic;
import com.example.bookstore.payload.TopicResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {

//    Allowance toEntity(AllowanceDTO allowanceDTO);

    TopicResponse toDTO(Topic topic);
}
