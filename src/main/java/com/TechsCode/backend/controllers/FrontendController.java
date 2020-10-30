package com.techscode.backend.controllers;

import com.techscode.backend.configurations.beans.FrontendField;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 *  This controller will match every request that is not an api call with the index.html
 *
 *  Since we run a single page application, the app will deal with its routes.
 *
 */

@Controller
public class FrontendController extends AbstractErrorController {

    private static final String ERROR_PATH=  "/error";

    @Autowired
    private FrontendField frontendField;

    @Autowired
    private FrontendTransformer transformer;

    @Autowired
    public FrontendController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    /**
     *  This method will handle all errors regarding this application.
     *
     *  NOT_FOUND errors (all requests not matching with a backend call or static file request)
     *  will be redirected to the index.html
     *
     *  The rest of the errors will receive regular treatment
     */
    @RequestMapping(ERROR_PATH)
    public ResponseEntity<?> handleErrors(HttpServletRequest request) {
        HttpStatus status = getStatus(request);

        if (status.equals(HttpStatus.NOT_FOUND)){
            File index = new File(frontendField.getPath(), "index.html");

            try {
                String html = FileUtils.readFileToString(index, Charset.defaultCharset());

                if(transformer != null){
                    html = transformer.transform(request, html);
                }

                return ResponseEntity.status(HttpStatus.OK).body(html);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.status(status).body(getErrorAttributes(request, false));
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }


    public interface FrontendTransformer {

        String transform(HttpServletRequest request, String html);

    }

    @Bean
    public FrontendTransformer getEmptyTransformer(){
        return (request, html) -> html;
    }

}
