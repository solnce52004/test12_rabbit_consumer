package com.example.test12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.IntSequenceGenerator.class,
//        property = "@id",
//        scope = MessageDto.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
//    private Long id;
    private String to;
    private String from;
    private String body;
}
