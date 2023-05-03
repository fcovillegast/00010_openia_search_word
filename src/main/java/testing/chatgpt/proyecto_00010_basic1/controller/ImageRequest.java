package testing.chatgpt.proyecto_00010_basic1.controller;


import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.image.Image;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
@RequestMapping(value = "/imageRequest")
public class ImageRequest {


    @Value("${token}")
    private String token;

    ObjectMapper objectMapper;

    @Autowired
    ImageRequest(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/{n}/{size}/{string}", method = GET)
    @ResponseBody
    public ResponseEntity getImage(@PathVariable("n") Integer n,
                                   @PathVariable("size") String size,
                                   @PathVariable("string") String string) {
        OpenAiService service = new OpenAiService(token);
        CreateImageRequest request = CreateImageRequest.builder().n(n).size(size).prompt(string).build();

        return new ResponseEntity( service.createImage(request).getData(), HttpStatus.OK);
    }


}
