package org.andarworld.educationservice.config.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.andarworld.educationservice.api.exceptions.BadRequestException;
import org.andarworld.educationservice.api.exceptions.GeneralException;
import org.andarworld.educationservice.api.exceptions.ResourceNotFoundException;

public class GlobalFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 400: return new BadRequestException("Bad request for "+s);
            case 404: return new ResourceNotFoundException("Resource not found!");
            default: return new GeneralException
                    ("Unexpected response status code, reason: "+response.reason(), response.status());
        }
    }
}
