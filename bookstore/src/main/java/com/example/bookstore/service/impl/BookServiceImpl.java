package com.example.bookstore.service.impl;

import com.example.bookstore.constant.ApiURL;
import com.example.bookstore.constant.Constant;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.model.book.Book;
import com.example.bookstore.model.book.StatusBook;
import com.example.bookstore.model.book.Topic;
import com.example.bookstore.model.enumm.StatusEnum;
import com.example.bookstore.payload.*;
import com.example.bookstore.payload.book.BookItemResponse;
import com.example.bookstore.payload.book.BookRequest;
import com.example.bookstore.payload.book.BookResponse;
import com.example.bookstore.payload.book.FilterSearchBookRequest;
import com.example.bookstore.repository.AlbumRepository;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.StatusRepository;
import com.example.bookstore.repository.TopicRepository;
import com.example.bookstore.service.BookService;
import com.example.bookstore.service.impl.specification.BookSpecifications;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private BookMapper bookMapper;

    private final String topicUpdating = "Updating";
    private final String statusDelete = "Delete";

    @Override
    public MessageResponse create(BookRequest bookRequest) {

        Book book = new Book();

        book.setName(bookRequest.getName());
        book.setAuthor(bookRequest.getAuthor());
        book.setInformation(bookRequest.getInformation());
        book.setDescriptions(bookRequest.getDescriptions());
        book.setCreateDate(new Date());

        book.setSupplier(bookRequest.getSupplier());
        book.setPublishingCompany(bookRequest.getPublishingCompany());
        book.setPublishingYear(bookRequest.getPublishingYear());
        book.setForm(bookRequest.getForm());
        book.setLanguage(bookRequest.getLanguage());
        book.setWeight(bookRequest.getWeight());
        book.setPackagingSize(bookRequest.getPackagingSize());
        book.setNumberOfPage(bookRequest.getNumberOfPage());




        if(!bookRequest.getTopicName().isEmpty()){
            bookRequest.setTopicName(topicUpdating);
        }
        Topic topic = topicRepository.findByName(bookRequest.getTopicName()).orElseThrow(() ->
                new IllegalArgumentException("not found topic"));

        book.setTopic(topic);


//        if(!bookRequest.getStatusName().isEmpty()){
//            bookRequest.setStatusName(StatusEnum.Create.toString());
//        }
        StatusBook statusBook = statusRepository.findByName(StatusEnum.Create.toString()).orElseThrow();
        book.setStatus(statusBook);


        bookRepository.save(book);


        //book_tag


        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.BOOK);

    }

    @Override
    public Book findByBook(UUID id) {

        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public MessageResponse update(UUID id, BookRequest bookRequest) {

        Book bookOld = bookRepository.findById(id).orElseThrow();

        bookOld.setName(bookRequest.getName());
        bookOld.setAuthor(bookRequest.getAuthor());
        bookOld.setInformation(bookRequest.getInformation());
        bookOld.setDescriptions(bookRequest.getDescriptions());
        bookOld.setCreateDate(new Date());

        bookOld.setSupplier(bookRequest.getSupplier());
        bookOld.setPublishingCompany(bookRequest.getPublishingCompany());
        bookOld.setPublishingYear(bookRequest.getPublishingYear());
        bookOld.setForm(bookRequest.getForm());
        bookOld.setLanguage(bookRequest.getLanguage());
        bookOld.setWeight(bookRequest.getWeight());
        bookOld.setPackagingSize(bookRequest.getPackagingSize());
        bookOld.setNumberOfPage(bookRequest.getNumberOfPage());

        if(!bookRequest.getTopicName().isEmpty()){
            Topic topic = topicRepository.findByName(bookRequest.getTopicName()).orElseThrow(() ->
                    new IllegalArgumentException("not found topic"));

            bookOld.setTopic(topic);
        }

        if(!bookRequest.getStatusName().isEmpty()){
            StatusBook statusBook = statusRepository.findByName(bookRequest.getStatusName()).orElseThrow(() ->
                    new IllegalArgumentException("not found status"));

            bookOld.setStatus(statusBook);
        }

        if(bookRequest.getCountPayed() != null){
            bookOld.setCountPaied(bookOld.getCountPaied());
        }
        if(bookRequest.getRating() != null){
            bookOld.setRating(bookRequest.getRating());
        }

        bookRepository.save(bookOld);



        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.BOOK);

    }

    @Override
    public MessageResponse delete(UUID id) {

        Book bookDeleteByStatus = bookRepository.findById(id).orElseThrow();


        StatusBook statusBook = statusRepository.findByName(statusDelete).orElseThrow(() ->
                new IllegalArgumentException("not found status"));

        bookDeleteByStatus.setStatus(statusBook);

        bookRepository.save(bookDeleteByStatus);

        return new MessageResponse(HttpServletResponse.SC_OK, Constant.SUCCESS, ApiURL.BOOK);

    }

    @Override
    public PaginationResponse filter(FilterSearchBookRequest filterSearchBookRequest) {

        Specification<Book> spec = BookSpecifications.filterAndSortByCriteria(filterSearchBookRequest.getName(),
                filterSearchBookRequest.getTopicName(),
                filterSearchBookRequest.getRating(),
                filterSearchBookRequest.getPriceRange(),
                filterSearchBookRequest.getSortOption(),
                filterSearchBookRequest.getAuthor(),
                filterSearchBookRequest.getStatus());

        List<Book> list = bookRepository.findAll(spec);

        List<BookItemResponse> bookItemResponseList = new ArrayList<>();
        String connect = " - ";
        for (Book setList : list){
            BookItemResponse item = new BookItemResponse();

            BookResponse bookResponse = this.bookMapper.toDTO(setList);
            item.setBookResponse(bookResponse);

            List<String> images = new ArrayList<>();
            images = albumRepository.findAllByBook_Id(setList.getId());
            item.setImageName(images);

            bookItemResponseList.add(item);

        }

        Page<BookItemResponse> page = convertListToPage(bookItemResponseList,
                filterSearchBookRequest.getNo(),
                filterSearchBookRequest.getLimit());

        return new PaginationResponse(page.getContent(), page.isFirst(), page.isLast(),
                page.getTotalPages(),
                page.getTotalElements(), page.getSize(),
                page.getNumber());
    }

    public static <T> Page<T> convertListToPage(List<T> list, int pageNo, int pageSize) {
        int start = pageNo * pageSize;
        int end = Math.min((start + pageSize), list.size());

        return new PageImpl<>(list.subList(start, end), PageRequest.of(pageNo, pageSize), list.size());
    }


//    public PaginationResponse filter(FilterSearchBookRequest filterSearchBookRequest) {
//
//        Specification<Book> spec = BookSpecifications.filterAndSortByCriteria(filterSearchBookRequest.getName(),
//                filterSearchBookRequest.getTopicName(),
//                filterSearchBookRequest.getRating(),
//                filterSearchBookRequest.getPriceRange(),
//                filterSearchBookRequest.getSortOption(),
//                filterSearchBookRequest.getAuthor(),
//                filterSearchBookRequest.getStatus());
//
//        List<Book> list = bookRepository.findAll(spec);
//
//        List<BookItemResponse> bookItemResponseList = new ArrayList<>();
//        String connect = " - ";
//        for (Book setList : list){
//            BookItemResponse item = new BookItemResponse();
//            item.setName(setList.getName());
//            item.setRating(setList.getRating());
//
//            if(Objects.equals(setList.getPriceMin(), setList.getPriceMax())){
//                item.setPrice(setList.getPriceMax().toString());
//            }else {
//                String price = setList.getPriceMin().toString() + connect + setList.getPriceMax().toString();
//                item.setPrice(price);
//            }
//
//            item.setDiscount(setList.getDiscountBook());
//            item.setStatus(setList.getStatus().getName());
//
//            Optional<Album> albumOptional = albumRepository.findByNameLikeOrFallback(setList.getId());
//
//            String imageName = albumOptional.map(Album::getName).orElse(null);
//            item.setImageName(imageName);
//
//            bookItemResponseList.add(item);
//        }
//
//        Page<BookItemResponse> page = convertListToPage(bookItemResponseList,
//                filterSearchBookRequest.getNo(),
//                filterSearchBookRequest.getLimit());
//
//        return new PaginationResponse(page.getContent(), page.isFirst(), page.isLast(),
//                page.getTotalPages(),
//                page.getTotalElements(), page.getSize(),
//                page.getNumber());
//    }
//
//    public static <T> Page<T> convertListToPage(List<T> list, int pageNo, int pageSize) {
//        int start = pageNo * pageSize;
//        int end = Math.min((start + pageSize), list.size());
//
//        return new PageImpl<>(list.subList(start, end), PageRequest.of(pageNo, pageSize), list.size());
//    }

    @Override
    public List<String> findAuthor() {
        return bookRepository.findDistinctAuthors();
    }

}
