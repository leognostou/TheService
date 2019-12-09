package myAPP.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Requested resourse NOT found!")
public class NotFoundException extends RuntimeException {}