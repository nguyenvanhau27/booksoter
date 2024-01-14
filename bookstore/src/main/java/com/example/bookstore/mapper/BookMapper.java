package com.example.bookstore.mapper;

import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.user.Address;
import com.example.bookstore.payload.address.AddressRequest;
import com.example.bookstore.payload.address.AddressResponse;
import com.example.bookstore.payload.book.BookRequest;
import com.example.bookstore.payload.book.BookResponse;
import com.example.bookstore.repository.BookNatureRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TopicMapper.class, StatusMapper.class})
public interface BookMapper {

    @Mapping(source = "status.name", target = "statusName")
    @Mapping(source = "topic.name", target = "topicName")
    BookResponse toDTO(Book book);

    Book toEntity(BookRequest bookRequest);
}
