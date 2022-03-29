package com.me.travel_backend.config.exception;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

// lỗi lien quan đến link

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice  // khai báo exception global
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	// default exception
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception exception) {
		ApiErrorResponse error = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error! Contact admin at lechinh737@gmail.com", exception.getMessage());
		return new ResponseEntity<>(error, error.getStatus());
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<Object> handleEntityNotFound(EmptyResultDataAccessException exception) {
		ApiErrorResponse error = new ApiErrorResponse(HttpStatus.NOT_FOUND, "This record has been deleted!", exception.getMessage());
		return new ResponseEntity<>(error, error.getStatus());
	}

	// not found entity (getByID not found)
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception) {
		ApiErrorResponse error = new ApiErrorResponse(HttpStatus.NOT_FOUND, "No record found! Please check your request!", exception.getMessage());
		return new ResponseEntity<>(error, error.getStatus());
	}
//	// not found url handler
//	@Override
//	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		return buildResponseEntity(new ApiErrorResponse(HttpStatus.NOT_FOUND, exception));
//	}
//
//	// not support HTTP Method
//	@Override
//	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
//			HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatus status,
//			WebRequest request) {
//		return buildResponseEntity(new ApiErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, exception));
//	}
//
//	// not support media type
//	@Override
//	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		return buildResponseEntity(new ApiErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, exception));
//	}
//
//	// BindException: This exception is thrown when fatal binding errors occur.
//	// MethodArgumentNotValidException: This exception is thrown when argument
//	// annotated with @Valid failed validation:
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, exception));
//	}
//
//	// MissingServletRequestPartException: This exception is thrown when when the
//	// part of a multipart request not found
//	// MissingServletRequestParameterException: This exception is thrown when
//	// request missing parameter:
//	@Override
//	protected ResponseEntity<Object> handleMissingServletRequestParameter(
//			MissingServletRequestParameterException exception, HttpHeaders headers, HttpStatus status,
//			WebRequest request) {
//		return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, exception));
//	}
//
//	// TypeMismatchException: This exception is thrown when try to set bean property
//	// with wrong type.
//	// MethodArgumentTypeMismatchException: This exception is thrown when method
//	// argument is not the expected type:
//	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
//	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
//		return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, exception));
//	}
//
//	// bean validation error
//	@ExceptionHandler(ConstraintViolationException.class)
//	ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
//		return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, exception));
//	}
//
//	// Spring Security
//	// Access Denied
//	@ExceptionHandler(AccessDeniedException.class)
//	public ResponseEntity<Object> AccessDeniedException(AccessDeniedException exception) {
//		return buildResponseEntity(new ApiErrorResponse(HttpStatus.FORBIDDEN, exception));
//	}
//
//	// sending email
//	@ExceptionHandler(MailException.class)
//	public ResponseEntity<Object> MailException(MailException exception) {
//		return buildResponseEntity(new ApiErrorResponse(HttpStatus.BAD_REQUEST, exception));
//	}
//
//	private ResponseEntity<Object> buildResponseEntity(ApiErrorResponse error) {
//		return new ResponseEntity<>(error, error.getStatus());
//	}
}