package com.medi.docs.medidocsapi.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.medi.docs.medidocsapi.exception.PdfGenerationException;
import com.medi.docs.medidocsapi.model.RecipeResponse;

import java.io.ByteArrayOutputStream;

/**
 * Utility class to generate PDF documents for medical recipes.
 */
public final class PdfGenerator {

    private PdfGenerator() {
        // Utility class - no instances allowed
    }

    /**
     * Generates a PDF document for the given recipe.
     *
     * @param recipe the recipe data
     * @return the PDF content as a byte array
     * @throws PdfGenerationException if an error occurs during PDF generation
     */
    public static byte[] generateRecipePdf(RecipeResponse recipe) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, baos);
            document.open();

            addHeader(document);
            addPatientInfo(document, recipe);
            addPrescription(document, recipe);
            addFooter(document, recipe);

            document.close();
            return baos.toByteArray();
        } catch (Exception ex) {
            throw new PdfGenerationException("Failed to generate PDF document", ex);
        }
    }

    private static void addHeader(Document document) throws DocumentException {
        Paragraph clinicName = new Paragraph("CENTRO DE SALUD FRANCISCO", new Font(Font.HELVETICA, 18, Font.BOLD));
        clinicName.setAlignment(Element.ALIGN_CENTER);
        document.add(clinicName);

        Paragraph clinicAddress = new Paragraph("Barrio La Bodega - Av. Fray Mamerto Esquiú", new Font(Font.HELVETICA, 12));
        clinicAddress.setAlignment(Element.ALIGN_CENTER);
        document.add(clinicAddress);

        Paragraph phone = new Paragraph("Tel: 3837-691723", new Font(Font.HELVETICA, 12));
        phone.setAlignment(Element.ALIGN_CENTER);
        document.add(phone);

        document.add(Chunk.NEWLINE);
    }

    private static void addPatientInfo(Document document, RecipeResponse recipe) throws DocumentException {
        document.add(new Paragraph("Patient: " + recipe.fullName()));
        document.add(new Paragraph("DNI: " + recipe.dni() + "   Age: " + (recipe.age() != null ? recipe.age() + " years" : "-")));
        document.add(new Paragraph("Health Insurance: " + recipe.healthInsurance()));
        document.add(new Paragraph("Affiliate Number: " + recipe.affiliateNumber()));
        if (recipe.plan() != null) {
            document.add(new Paragraph("Plan: " + recipe.plan()));
        }
        document.add(new Paragraph("City: " + recipe.city()));
        document.add(new Paragraph("Date of Emission: " + recipe.date()));
        document.add(Chunk.NEWLINE);
    }

    private static void addPrescription(Document document, RecipeResponse recipe) throws DocumentException {
        document.add(new Paragraph("Prescription 1: " + recipe.rp1Medication()));
        document.add(new Paragraph("Diagnosis 1: " + recipe.rp1Diagnosis()));
        if (recipe.rp2Medication() != null) {
            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Prescription 2: " + recipe.rp2Medication()));
            document.add(new Paragraph("Diagnosis 2: " + recipe.rp2Diagnosis()));
        }
        document.add(Chunk.NEWLINE);
    }

    private static void addFooter(Document document, RecipeResponse recipe) throws DocumentException {
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Doctor: " + recipe.doctorName() + " - License No: " + recipe.doctorLicenseNumber()));
        document.add(Chunk.NEWLINE);
        document.add(new Paragraph("Signature: ____________________________________"));
    }
}
