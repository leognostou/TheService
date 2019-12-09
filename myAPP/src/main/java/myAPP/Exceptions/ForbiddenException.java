package myAPP.Exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason="Not your average error message!")
public class ForbiddenException extends RuntimeException {}