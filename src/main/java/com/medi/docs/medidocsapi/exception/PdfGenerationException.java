package com.medi.docs.medidocsapi.exception;

/**
 * Exception thrown when a PDF generation process fails.
 */
public class PdfGenerationException extends RuntimeException {

    public PdfGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
